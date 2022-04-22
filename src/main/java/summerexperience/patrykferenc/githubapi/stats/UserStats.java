package summerexperience.patrykferenc.githubapi.stats;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserStats {

    @JsonProperty("user")
    private final User user;
    @JsonProperty("repositories")
    private final List<Repo> userRepos;
    @JsonProperty("language_statistics")
    private Map<String, Long> languageStats;

    public UserStats(User myUser, List<Repo> userRepos) {
        this.userRepos = userRepos;
        this.user = myUser;
        this.languageStats = generateLanguageStatsFromRepos();
    }

    public User getUser() {
        return user;
    }

    public List<Repo> getUserRepos() {
        return userRepos;
    }

    public Map<String, Long> getLanguageStats() {
        return languageStats;
    }

    private Map<String, Long> generateLanguageStatsFromRepos() {
        var stats = new HashMap<String, Long>();
        for (Repo r : userRepos) {
            for (String key : r.getLanguageStats().keySet())
                stats.put(key, stats.getOrDefault(key, 0L) + r.getLanguageStats().get(key));
        }
        return stats;
    }

}