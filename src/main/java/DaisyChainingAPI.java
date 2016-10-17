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

/**
 * Created by user8 on 17.10.2016.
 */
public class DaisyChainingAPI {
    private User user;

    public DaisyChainingAPI(User user) {
        this.user = user;
    }


    /**
     * Get Daisy Chaining information for the given Placement.
     * The ID of placement must be specified as method's param.
     *
     */

    public void getDaisyChaining(int placementId) throws NoSuchAlgorithmException, IOException {
        URL url = new URL(user.getNetwork() + "/rest-api/placements/" + placementId + "/chaining.do?hash=" + getHash() + "&timestamp=" + getTimestamp() + "&username=" + user.getUsername());
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
     *
     * Set or Change Daisy Chaining priority
     * The ID of placement/banner/campaign and new priority must be specified as method's param.
     *
     */

    public void setDaisyChainingPriority(int id, int bannerId, int campaignId, int priority) throws NoSuchAlgorithmException, IOException {
        URL url = new URL(user.getNetwork() + "/rest-api/placements/chaining/update-priority.do?hash=" + getHash() + "&timestamp=" + getTimestamp() + "&username=" + user.getUsername()
                + "&id=" + id + "&bannerId=" + bannerId + "&campaignId=" + campaignId + "&priority=" + priority);
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
