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
import java.util.Arrays;
import java.util.Date;


public class EpomService {
    private User user;

    public EpomService(User user) {
        this.user = user;
    }

    public static void main(String[] args) throws Exception {
        SSLContext ctx = SSLContext.getInstance("TLS");
        ctx.init(new KeyManager[0], new TrustManager[]{new DefaultTrustManager()}, new SecureRandom());
        SSLContext.setDefault(ctx);

        EpomService epomService = new EpomService(new User("apimaster", "apimaster"));
        epomService.getSitesData(null);
        System.out.println();
        epomService.createZone("someName", "SomeShortDescription", 2078);
    }

    private void getSitesData(int[] publishingCategories) throws NoSuchAlgorithmException, IOException {
        String stringFromPublCategories = Arrays.toString(publishingCategories);
        stringFromPublCategories = stringFromPublCategories.replace("[", "");
        stringFromPublCategories = stringFromPublCategories.replace("]", "");
        stringFromPublCategories = stringFromPublCategories.replace(" ", "");
        URL url = null;
        if (publishingCategories == null) {
            url = new URL("https://n29.epom.com/rest-api/sites.do?hash=" + getHash() + "&timestamp=" + getTimestamp() + "&username=" + user.getUsername());
        } else {
            url = new URL("https://n29.epom.com/rest-api/sites.do?hash=" + getHash() + "&timestamp=" + getTimestamp() + "&username=" + user.getUsername() + "&publishingCategories=" + stringFromPublCategories);
        }
        HttpsURLConnection conn = getURLConnection(url);
        System.out.println(conn.getResponseCode());
        InputStream inputStream = conn.getInputStream();
        printWebSites(inputStream);
        conn.disconnect();
        inputStream.close();
    }


    private void createZone(String name, String description, int siteId) throws NoSuchAlgorithmException, IOException {
        URL url = new URL("https://n29.epom.com/rest-api/zones/update.do?hash=" + getHash() + "&timestamp=" + getTimestamp() + "&username=" + user.getUsername() + "&name=" + name + "&description=" + description + "&siteId=" + siteId);
        HttpsURLConnection conn = getURLConnection(url);
        conn.setRequestMethod("POST");

        System.out.println(conn.getResponseCode());
        InputStream inputStream = conn.getInputStream();
        String myString = IOUtils.toString(inputStream, "UTF-8");
        myString = myString.replace("{", "");
        myString = myString.replace("}", "");
        System.out.println(myString);
        conn.disconnect();
        inputStream.close();
    }

    private void printWebSites(InputStream inputStream) throws IOException {
        String myString = IOUtils.toString(inputStream, "UTF-8");
        String temp = myString.replace("[", "");
        temp = temp.replace("]", "");
        String[] array = temp.split("},");
        for (String sepString : array) {
            sepString = sepString.replace("{", "");
            sepString = sepString.replace("}", "");
            System.out.println(sepString);
        }
    }

    private HttpsURLConnection getURLConnection(URL url) throws IOException {
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String arg0, SSLSession arg1) {
                return true;
            }
        });
        return conn;
    }

    public long getTimestamp() {
        return new Date().getTime();
    }

    public String getHash() throws NoSuchAlgorithmException {
        String hash = getMD5(getMD5(user.getPassword()) + String.valueOf(getTimestamp()));
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
