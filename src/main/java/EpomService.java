import org.apache.commons.io.IOUtils;

import javax.net.ssl.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;


public class EpomService {
    private User user;
    long timestamp = new Date().getTime();




    public EpomService(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private void getSitesData() throws NoSuchAlgorithmException, IOException {

        int [] publishingCategories = null;
        URL url = null;
        if(publishingCategories == null){
            url = new URL("https://n29.epom.com/rest-api/sites.do?hash=" + getHash() + "&timestamp=" + timestamp + "&username=apimaster");
        }else {
            url = new URL("https://n29.epom.com/rest-api/sites.do?hash=" + getHash() + "&timestamp=" + timestamp + "&username=apimaster&publishingCategories=" + publishingCategories);
        }
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String arg0, SSLSession arg1) {
                return true;
            }
        });
        System.out.println(conn.getResponseCode());
        InputStream inputStream = conn.getInputStream();
        String myString = IOUtils.toString(inputStream, "UTF-8");
        String temp = myString.replace("[", "");
        temp = temp.replace("]", "");
        String[] a = temp.split("},");
        for (String b: a){
            b = b.replace("{","");
            b = b.replace("}","");
            System.out.println(b);
        }
        conn.disconnect();
        inputStream.close();
    }

    private void createZone(String hash, long timestamp, String username, String name, String description, int id) throws NoSuchAlgorithmException, IOException {
        URL url2 = new URL("https://n29.epom.com/rest-api/zones/update.do?hash=" + hash + "&timestamp=" + timestamp + "&username=" + username + "name=" + name + "&description=" + description + "&siteId=" + id);
        HttpsURLConnection conn2 = (HttpsURLConnection) url2.openConnection();
        conn2.setRequestMethod("POST");
        conn2.setHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String arg0, SSLSession arg1) {
                return true;
            }
        });
        System.out.println(conn2.getResponseCode());
        InputStream inputStream2 = conn2.getInputStream();
        String myString2 = IOUtils.toString(inputStream2, "UTF-8");
        myString2 = myString2.replace("{", "");
        myString2 = myString2.replace("}", "");
        System.out.println(myString2);
        conn2.disconnect();
        inputStream2.close();

    }

    public String getHash() throws NoSuchAlgorithmException {
        String hash = getMD5(getMD5(user.getPassword()) + String.valueOf(timestamp));
        return hash;
    }

    private String getMD5(String data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(data.getBytes());
        byte byteData[] = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte aByteData : byteData) {
            sb.append(Integer.toString((aByteData & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }


    public static void main(String[] args) throws Exception {
        SSLContext ctx = SSLContext.getInstance("TLS");
        ctx.init(new KeyManager[0], new TrustManager[]{new DefaultTrustManager()}, new SecureRandom());
        SSLContext.setDefault(ctx);

        EpomService epomService = new EpomService(new User("apimaster", "apimaster"));
        epomService.getSitesData();
        epomService.createZone(epomService.getHash(), epomService.timestamp, epomService.getUser().getUsername(), "someName", "Some short description", 2078);


    }


    private static class DefaultTrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }
}
