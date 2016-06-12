import java.util.Date;

public class ParsingDemo {
    public static void main(String [] args) throws Exception {
        // configure the SSLContext with a TrustManager


//        URL url = new URL("https://mms.nw.ru");
//        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
//        con.setHostnameVerifier(new HostnameVerifier() {
//            @Override
//            public boolean verify(String arg0, SSLSession arg1) {
//                return true;
//            }
//        });
//        System.out.println(conn.getResponseCode());
//        conn.disconnect();
        System.out.println(new Date().getTime());
    }



//    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, URISyntaxException {
//    SiteGetter siteGetter = new SiteGetter(new User("apimaster","apimaster"));
//
//
//        siteGetter.getSitesByUserData();
//
//    }

}
