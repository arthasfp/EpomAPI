
public class Site {

    private String name;
    private String url;
    private int ID;

    public Site(String name, String url, int ID) {
        this.name = name;
        this.url = url;
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Site site = (Site) o;

        if (ID != site.ID) return false;
        if (name != null ? !name.equals(site.name) : site.name != null) return false;
        if (url != null ? !url.equals(site.url) : site.url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + ID;
        return result;
    }

    @Override
    public String toString() {
        return "Site{" +
                "name='" + name + '\'' +
                ", ID=" + ID +
                '}';
    }
}
