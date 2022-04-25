package summerexperience.patrykferenc.githubapi.stats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import summerexperience.patrykferenc.githubapi.stats.exceptions.RequestLimitExceededException;
import summerexperience.patrykferenc.githubapi.stats.exceptions.UserNotFoundException;

import java.util.stream.Collectors;

@RestController
public class UserStatsController {

    private static final String DEFAULT_USER = "octocat";

    private ApplicationContext context;

    @Autowired
    public void context(ApplicationContext context) {
        this.context = context;
    }

    @GetMapping("/stats")
    public UserStats userStats(@RequestParam(value = "name", defaultValue = DEFAULT_USER) String name) throws UserNotFoundException, RequestLimitExceededException {
        var userClient = context.getBean(GithubAPIClient.class);
        var user = userClient
                .getUser(name).onErrorResume(WebClientResponseException.class,
                        ex -> {
                            if (ex.getRawStatusCode() == 404) {
                                return Mono.error(new UserNotFoundException("User " + name + " could not be found."));
                            } else if (ex.getRawStatusCode() == 403) {
                                return Mono.error(new RequestLimitExceededException("Too many requests from given IP"));
                            } else {
                                return Mono.error(ex);
                            }
                        })
                .share()
                .block();
        var repos = userClient
                .getRepos(name)
                .collect(Collectors.toList())
                .share()
                .block();
        if (repos != null)
            for (Repo r : repos) {
                var repoStats = userClient.getLanguages(name, r.getName()).share().blockLast();
                r.setLanguageStats(repoStats);
            }
        return new UserStats(user, repos);
    }

}
