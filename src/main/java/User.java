public class User {

    private String username;
    private String password;
    private String network;

    public User(String username, String password, String network) {
        this.username = username;
        this.password = password;
        this.network = network;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNetwork() {
        return network;
    }
}
