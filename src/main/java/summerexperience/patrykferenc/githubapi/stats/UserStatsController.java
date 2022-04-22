package summerexperience.patrykferenc.githubapi.stats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public UserStats userStats(@RequestParam(value = "name", defaultValue = DEFAULT_USER) String name) {
        var userClient = context.getBean(GithubAPIClient.class);
        var user = userClient
                .getUser(name)
                .share()
                .block();
        var repos = userClient
                .getRepos(name)
                .collect(Collectors.toList())
                .share()
                .block();
        for (Repo r : repos) {
            var repoStats = userClient.getLanguages(name, r.getName()).share().blockLast();
            r.setLanguageStats(repoStats);
        }
        return new UserStats(user, repos);
    }

}
