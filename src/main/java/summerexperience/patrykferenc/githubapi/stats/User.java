package summerexperience.patrykferenc.githubapi.stats;

class User {

    private final String name;
    private final String bio;
    private final String login;

    public User(String name, String bio, String login) {
        this.name = name;
        this.bio = bio;
        this.login = login;
    }

    public String getBio() {
        return bio;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

}
