import org.apache.commons.io.IOUtils;

import javax.net.ssl.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Created by user8 on 22.05.2017.
 */
public class UpdateAccounts {
    public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException, IOException {
        SSLContext ctx = SSLContext.getInstance("TLS");
        ctx.init(new KeyManager[0], new TrustManager[]{new DefaultTrustManager()}, new SecureRandom());
        SSLContext.setDefault(ctx);

        String[] list = {"supervisor", "marc", "cristianRoger.Adsclock", "supervisor1", "support_test", "templates"};

        for (int i = 0; i < list.length; i++) {
            new UpdateAccounts().getList(list[i]);
        }


    }

    private void getList(String usernameToUpdate) throws NoSuchAlgorithmException, IOException {
        String username = "s.berezhnyi";
        String password = "iloventv_89";
        String timestamp = String.valueOf(System.currentTimeMillis());
        String hash = getMD5(getMD5(password) + timestamp);
        String url = "https://n127.epom.com/rest-api/user/update.do?username=" + username + "&timestamp=" + timestamp + "&hash=" + hash + "&usernameToUpdate=" + usernameToUpdate +
                "&active=false";




        System.out.println("offerurl   " + url);

        URL urlForConnection = new URL(url);
        HttpsURLConnection conn = (HttpsURLConnection) urlForConnection.openConnection();
        conn.setRequestMethod("POST");
        getVerifierForConnection(conn);
        System.out.println(conn.getResponseCode());
        InputStream inputStream = conn.getInputStream();
        String myString = IOUtils.toString(inputStream, "UTF-8");
        System.out.println(myString);
        conn.disconnect();
        inputStream.close();


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

    private void getVerifierForConnection(HttpsURLConnection conn) {
        conn.setHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String arg0, SSLSession arg1) {
                return true;
            }
        });
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

