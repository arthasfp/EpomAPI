import java.util.ArrayList;

public class SiteGetter {

    private User user;
    private ArrayList<Site> listOfSites;

    public SiteGetter(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void getSitesByUserData(String hash, long timestamp, String username, int[] publishingCategories){

        System.out.println("Названия сайта: " + "ID сайта: ");
    }
}
