package summerexperience.patrykferenc.githubapi.stats;

import java.util.Map;

public class Repo {

    private final String name;
    private Map<String, Long> languageStats;

    public Repo(String name, Map<String, Long> languageStats) {
        this.name = name;
        this.languageStats = languageStats;
    }

    public String getName() {
        return name;
    }

    public Map<String, Long> getLanguageStats() {
        return languageStats;
    }

    public void setLanguageStats(Map<String, Long> languageStats) {
        this.languageStats = languageStats;
    }
}

