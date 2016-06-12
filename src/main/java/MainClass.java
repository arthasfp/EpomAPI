import org.apache.commons.io.IOUtils;

import javax.net.ssl.*;
import java.io.InputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;


public class MainClass {
    private User user;


    public MainClass(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    private static String getMD5(String data) throws NoSuchAlgorithmException {
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

        long timestamp = new Date().getTime();
        String hash = getMD5(getMD5(new User("apimaster","apimaster").getPassword()) + String.valueOf(timestamp));
        int [] publishingCategories = null;
        URL url = null;
        if(publishingCategories == null){
         url = new URL("https://n29.epom.com/rest-api/sites.do?hash=" + hash + "&timestamp=" + timestamp + "&username=apimaster");
        }else {
        url = new URL("https://n29.epom.com/rest-api/sites.do?hash=" + hash + "&timestamp=" + timestamp + "&username=apimaster&publishingCategories=" + publishingCategories);
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

        URL url2 = new URL("https://n29.epom.com/rest-api/zones/update.do?hash=" + hash + "&timestamp=" + timestamp + "&username=apimaster&name=fffff&description=jkdhjghn&siteId=2078");
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
