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

        EpomService epomService = new EpomService(new User("berezhnyi1234", "211111", "https://n101.epom.com"));
//        epomService.createStandartPlacement("someName", 1282);
//        epomService.deletePlacement(5695);
//        epomService.getPlacementInvocationCode("4f6dab177059c8900bfa5e20b2566b29");
        epomService.getPlacementSummary();
    }

    private void createStandartPlacement(String name, int zoneId) throws NoSuchAlgorithmException, IOException {
        URL url = new URL(user.getNetwork() + "/rest-api/placements/update/standard.do?hash=" + getHash() + "&timestamp=" + getTimestamp() + "&username=" + user.getUsername()
                + "&zoneId=" + zoneId + "&type=STANDARD_SITE_PLACEMENTS" + "&name=" + name + "&size.adUnit=" + Ad_Unit.LEADERBOARD);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        getVerifierForConnection(conn);
        System.out.println(conn.getResponseCode());
        InputStream inputStream = conn.getInputStream();
        String myString = IOUtils.toString(inputStream, "UTF-8");
        myString = myString.replace("{", "");
        myString = myString.replace("}", "");
        System.out.println(myString);
        conn.disconnect();
        inputStream.close();
    }

    /**
     * Deletes placement by ID number.
     * The ID number must be specified as method's param.
     *
     */

    private void deletePlacement(int placementId) throws NoSuchAlgorithmException, IOException {
        URL url = new URL(user.getNetwork() + "/rest-api/placements/" + placementId + "/delete.do?hash=" + getHash() + "&timestamp=" + getTimestamp() + "&username=" + user.getUsername());
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        getVerifierForConnection(conn);
        System.out.println(conn.getResponseCode());
        InputStream inputStream = conn.getInputStream();
        String myString = IOUtils.toString(inputStream, "UTF-8");
        System.out.println(myString);
        conn.disconnect();
        inputStream.close();
    }


     /**
     * Gets invocation code of placement by Placement Key.
     * The Placement Key must be specified as method's param.
     * Placement Key can be replaced with md5 value of site ID, zone ID, placement ID
     *
     */

    private void getPlacementInvocationCode(String placementKey) throws NoSuchAlgorithmException, IOException {
        URL url = new URL(user.getNetwork() + "/rest-api/placement/" + placementKey + "/code.do?hash=" + getHash() + "&timestamp=" + getTimestamp() + "&username="
                + user.getUsername() + "&t=" + InvocationCode.JS_SYNC);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        getVerifierForConnection(conn);
        System.out.println(conn.getResponseCode());
        InputStream inputStream = conn.getInputStream();
        String myString = IOUtils.toString(inputStream, "UTF-8");
        System.out.println(myString);
        conn.disconnect();
        inputStream.close();
    }

    /**
     * Get the Placement ID, Category, Size and Key.
     * Could be added two optional parameters:
     * publishingCategories – IDs of publishing categories to filter the results (optional).
     * placementIds – IDs of placements to filter the results (optional).
     */

    private void getPlacementSummary() throws NoSuchAlgorithmException, IOException {
        URL url = new URL(user.getNetwork() + "/rest-api/placements/summary.do?hash=" + getHash() + "&timestamp=" + getTimestamp() + "&username=" + user.getUsername());
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        getVerifierForConnection(conn);
        System.out.println(conn.getResponseCode());
        InputStream inputStream = conn.getInputStream();
        String myString = IOUtils.toString(inputStream, "UTF-8");
        System.out.println(myString);
        conn.disconnect();
        inputStream.close();
    }



    private void getSitesData(int[] publishingCategories) throws NoSuchAlgorithmException, IOException {
        String stringFromPublCategories = Arrays.toString(publishingCategories);
        stringFromPublCategories = stringFromPublCategories.replace("[", "");
        stringFromPublCategories = stringFromPublCategories.replace("]", "");
        stringFromPublCategories = stringFromPublCategories.replace(" ", "");
        URL url = null;
        if (publishingCategories == null) {
            url = new URL(user.getNetwork() + "/rest-api/sites.do?hash=" + getHash() + "&timestamp=" + getTimestamp() + "&username=" + user.getUsername());
        } else {
            url = new URL(user.getNetwork() + "/rest-api/sites.do?hash=" + getHash() + "&timestamp=" + getTimestamp() + "&username=" + user.getUsername() + "&publishingCategories=" + stringFromPublCategories);
        }
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        getVerifierForConnection(conn);
        System.out.println(conn.getResponseCode());
        InputStream inputStream = conn.getInputStream();
        printWebSites(inputStream);
        conn.disconnect();
        inputStream.close();
    }


    private void createZone(String name, String description, int siteId) throws NoSuchAlgorithmException, IOException {
        URL url = new URL(user.getNetwork() + "/rest-api/zones/update.do?hash=" + getHash() + "&timestamp=" + getTimestamp() + "&username=" + user.getUsername() + "&name=" + name + "&description=" + description + "&siteId=" + siteId);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        getVerifierForConnection(conn);
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

    private void getVerifierForConnection(HttpsURLConnection conn) {
        conn.setHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String arg0, SSLSession arg1) {
                return true;
            }
        });
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
