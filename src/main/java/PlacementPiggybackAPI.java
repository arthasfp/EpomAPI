import enums.PiggybackType;
import org.apache.commons.io.IOUtils;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Date;

public class PlacementPiggybackAPI {
    private User user;

    public PlacementPiggybackAPI(User user) {
        this.user = user;
    }

    /**
     *
     * Enables Piggyback functionality for the given Placement.
     * The ID of placement must be specified as method's param.
     *
     */

    public void enablePlacementPiggyback(int placementId) throws NoSuchAlgorithmException, IOException {
        URL url = new URL(user.getNetwork() + "/rest-api/piggyback/placement/enable.do?hash=" + getHash() + "&timestamp=" + getTimestamp() + "&username=" + user.getUsername() + "&id=" + placementId);
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
     *
     * Disables Piggyback functionality for the given Placement.
     * The ID of placement must be specified as method's param.
     *
     */

    public void disablePlacementPiggyback(int placementId) throws NoSuchAlgorithmException, IOException {
        URL url = new URL(user.getNetwork() + "/rest-api/piggyback/placement/disable.do?hash=" + getHash() + "&timestamp=" + getTimestamp() + "&username=" + user.getUsername() + "&id=" + placementId);
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
     *
     * Saves Piggyback for the given Placement.
     * The ID of placement, type of piggyback and script/url code must be specified as method's param.
     * "type" and "codeUrl" are optional parameters.
     * "type" has default value BEACON.
     *
     */

    public void savePlacementPiggyback(int placementId, PiggybackType type, String codeUrl) throws NoSuchAlgorithmException, IOException {
        URL url = new URL(user.getNetwork() + "/rest-api/piggyback/placement.do?hash=" + getHash() + "&timestamp=" + getTimestamp() + "&username=" + user.getUsername()
                + "&placementId=" + placementId + "&type=" + type + "&codeUrl=" + codeUrl);
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
     * Get Piggyback for the given Placement.
     * The ID of placement must be specified as method's param.
     *
     */

    public void getPlacementPiggyback(int placementId) throws NoSuchAlgorithmException, IOException {
        URL url = new URL(user.getNetwork() + "/rest-api/piggyback/placement/get.do?hash=" + getHash() + "&timestamp=" + getTimestamp() + "&username="
                + user.getUsername() + "&placementId=" + placementId);
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

    private LocalDate getDateFromString(String value){
        String[] newValue = value.split("-");
        for (String a: newValue){
            System.out.println(a);
        }
        if (newValue.length > 3){
            System.out.println("Wrong input!!!");
        }
        return LocalDate.of(Integer.parseInt(newValue[0]), Integer.parseInt(newValue[1]), Integer.parseInt(newValue[2]));
    }

}
