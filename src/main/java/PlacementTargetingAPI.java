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

/**
 * Created by user8 on 31.10.2016.
 */
public class PlacementTargetingAPI {

    private User user;

    public PlacementTargetingAPI(User user) {
        this.user = user;
    }

    /**
     * Enables Targeting for the given Placement.
     * The ID of placement must be specified as method's param.
     */

    public void enablePlacementTargeting(int placementId) throws NoSuchAlgorithmException, IOException {
        URL url = new URL(user.getNetwork() + "/rest-api/placement/" + placementId + "/targeting/enable.do?hash=" + getHash() + "&timestamp=" + getTimestamp() + "&username=" + user.getUsername());
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
     * Disables Targeting for the given Placement.
     * The ID of placement must be specified as method's param.
     */

    public void disablePlacementTargeting(int placementId) throws NoSuchAlgorithmException, IOException {
        URL url = new URL(user.getNetwork() + "/rest-api/placement/" + placementId + "/targeting/disable.do?hash=" + getHash() + "&timestamp=" + getTimestamp() + "&username=" + user.getUsername());
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
     * Removes Targeting by its ID for the given Placement.
     * The ID of placement and targeting must be specified as method's param.
     */

    public void removePlacementTargeting(int placementId, int targetId) throws NoSuchAlgorithmException, IOException {
        URL url = new URL(user.getNetwork() + "/rest-api/placement/" + placementId + "/targeting/" + targetId + "/delete.do?hash=" + getHash() + "&timestamp=" + getTimestamp() + "&username=" + user.getUsername());
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("DELETE");
        getVerifierForConnection(conn);
        System.out.println(conn.getResponseCode());
        InputStream inputStream = conn.getInputStream();
        String myString = IOUtils.toString(inputStream, "UTF-8");
        System.out.println(myString);
        conn.disconnect();
        inputStream.close();
    }


    /**
     * Get the Targeting by its ID for the given Placement.
     * The ID of placement and targeting must be specified as method's param.
     */

    public void getPlacementTargeting(int placementId, int targetId) throws NoSuchAlgorithmException, IOException {
        URL url = new URL(user.getNetwork() + "/rest-api/placement/" + placementId + "/targeting/" + targetId + ".do?hash=" + getHash() + "&timestamp=" + getTimestamp() + "&username=" + user.getUsername());
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
     * Get all Targetings by Placement ID.
     * The ID of placement and targeting must be specified as method's param.
     */

    public void getAllPlacementTargetings(int placementId) throws NoSuchAlgorithmException, IOException {
        URL url = new URL(user.getNetwork() + "/rest-api/placement/" + placementId + "/targetings.do?hash=" + getHash() + "&timestamp=" + getTimestamp() + "&username=" + user.getUsername());
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
     * Get the Targeting types for the given Placement.
     * The ID of placement and targeting must be specified as method's param.
     */

    public void getPlacementTargetingTypes(int placementId) throws NoSuchAlgorithmException, IOException {
        URL url = new URL(user.getNetwork() + "/rest-api/placement/" + placementId + "/targeting/types.do?hash=" + getHash() + "&timestamp=" + getTimestamp() + "&username=" + user.getUsername());
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
     * Get available Browser values for Browser Targeting type.
     * The ID of placement and targeting must be specified as method's param.
     */

    public void getBrowsers(int placementId) throws NoSuchAlgorithmException, IOException {
        URL url = new URL(user.getNetwork() + "/rest-api/placement/" + placementId + "/targeting/browsers/values.do?hash=" + getHash() + "&timestamp=" + getTimestamp() + "&username=" + user.getUsername());
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
     * Get available Mobile Carrier names for Mobile Carrier Targeting type.
     * The ID of placement and targeting must be specified as method's param.
     */

    public void getMobileCarriers(int placementId) throws NoSuchAlgorithmException, IOException {
        URL url = new URL(user.getNetwork() + "/rest-api/placement/" + placementId + "/targeting/mobilecarriers/values.do?hash=" + getHash() + "&timestamp=" + getTimestamp() + "&username=" + user.getUsername());
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
     * Get available Firefox browser version values for Browser Targeting type.
     * The ID of placement and targeting must be specified as method's param.
     */

    public void getFirefoxVersions(int placementId) throws NoSuchAlgorithmException, IOException {
        URL url = new URL(user.getNetwork() + "/rest-api/placement/" + placementId + "/targeting/browsers/firefox/values.do?hash=" + getHash() + "&timestamp=" + getTimestamp() + "&username=" + user.getUsername());
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
     * Get available Chrome browser version values for Browser Targeting type.
     * The ID of placement and targeting must be specified as method's param.
     */

    public void getChromeVersions(int placementId) throws NoSuchAlgorithmException, IOException {
        URL url = new URL(user.getNetwork() + "/rest-api/placement/" + placementId + "/targeting/browsers/chrome/values.do?hash=" + getHash() + "&timestamp=" + getTimestamp() + "&username=" + user.getUsername());
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
     * Get available Safari browser version values for Browser Targeting type.
     * The ID of placement and targeting must be specified as method's param.
     */

    public void getSafariVersions(int placementId) throws NoSuchAlgorithmException, IOException {
        URL url = new URL(user.getNetwork() + "/rest-api/placement/" + placementId + "/targeting/browsers/safari/values.do?hash=" + getHash() + "&timestamp=" + getTimestamp() + "&username=" + user.getUsername());
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
     * Get available Internet Explorer browser version values for Browser Targeting type.
     * The ID of placement and targeting must be specified as method's param.
     */

    public void getIEVersions(int placementId) throws NoSuchAlgorithmException, IOException {
        URL url = new URL(user.getNetwork() + "/rest-api/placement/" + placementId + "/targeting/browsers/ie/values.do?hash=" + getHash() + "&timestamp=" + getTimestamp() + "&username=" + user.getUsername());
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
     * Get available operating systems values for OS Targeting type.
     * The ID of placement and targeting must be specified as method's param.
     */

    public void getOSValues(int placementId) throws NoSuchAlgorithmException, IOException {
        URL url = new URL(user.getNetwork() + "/rest-api/placement/" + placementId + "/targeting/os/values.do?hash=" + getHash() + "&timestamp=" + getTimestamp() + "&username=" + user.getUsername());
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
     * Get available operating systems versions' values for OS Targeting type.
     * Values of OS must be uppercased
     * The ID of placement and targeting must be specified as method's param.
     */

    public void getOSVersions(int placementId, String osName) throws NoSuchAlgorithmException, IOException {
        URL url = new URL(user.getNetwork() + "/rest-api/placement/" + placementId + "/targeting/os/" + osName + "/versions.do?hash=" + getHash() + "&timestamp=" + getTimestamp() + "&username=" + user.getUsername());
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
     * Get available device formats values for Device Format Targeting type.
     * The ID of placement and targeting must be specified as method's param.
     */

    public void getDeviceFormats(int placementId) throws NoSuchAlgorithmException, IOException {
        URL url = new URL(user.getNetwork() + "/rest-api/placement/" + placementId + "/targeting/device/format/values.do?hash=" + getHash() + "&timestamp=" + getTimestamp() + "&username=" + user.getUsername());
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
     * Get available device vendor values for Device Vendor Targeting type.
     * The ID of placement and targeting must be specified as method's param.
     */

    public void getDeviceVendors(int placementId) throws NoSuchAlgorithmException, IOException {
        URL url = new URL(user.getNetwork() + "/rest-api/placement/" + placementId + "/targeting/device/vendor/values.do?hash=" + getHash() + "&timestamp=" + getTimestamp() + "&username=" + user.getUsername());
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
     * Get available screen resolution values for Screen Resolution Targeting type.
     * The ID of placement and targeting must be specified as method's param.
     */

    public void getResolutions(int placementId) throws NoSuchAlgorithmException, IOException {
        URL url = new URL(user.getNetwork() + "/rest-api/placement/" + placementId + "/targeting/resolution/values.do?hash=" + getHash() + "&timestamp=" + getTimestamp() + "&username=" + user.getUsername());
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
     * Get available regions for the given country.
     * The ID of placement and countryCode must be specified as method's param.
     */

    public void getRegions(int placementId, String countryCode) throws NoSuchAlgorithmException, IOException {
        URL url = new URL(user.getNetwork() + "/rest-api/placement/" + placementId + "/targeting/country/region/values.do?hash=" + getHash() + "&timestamp=" + getTimestamp()
                + "&username=" + user.getUsername() + "&countryCode=" + countryCode);
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
     * Get available City names for given Country and Region.
     * The ID of placement, coutryCode, regionName must be specified as method's param.
     */

    public void getCities(int placementId, String countryCode, String regionName) throws NoSuchAlgorithmException, IOException {
        URL url = new URL(user.getNetwork() + "/rest-api/placement/" + placementId + "/targeting/country/region/city/values.do?hash=" + getHash() + "&timestamp=" + getTimestamp()
                + "&username=" + user.getUsername() + "&countryCode=" + countryCode + "&regionName=" + regionName);
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
     * Get available languages.
     * The ID of placement must be specified as method's param.
     */

    public void getLanguages(int placementId) throws NoSuchAlgorithmException, IOException {
        URL url = new URL(user.getNetwork() + "/rest-api/placement/" + placementId + "/targeting/language/values.do?hash=" + getHash() + "&timestamp=" + getTimestamp()
                + "&username=" + user.getUsername());
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
     * Sets Browser Targeting for the Placement (and enable if it was disabled).
     *
     *
     */

    public void createBrowserTargeting(int placementId, String rule, String browser, String relation, double browserVersion) throws NoSuchAlgorithmException, IOException {
        URL url = new URL(user.getNetwork() + " /rest-api/placement/" + placementId + "/targeting/create.do?hash=" + getHash() + "&timestamp=" + getTimestamp() + "&username=" + user.getUsername()
                + "&rule=" + rule + "&browser=" + browser + "&relation=" + relation + "&browserVersion=" + browserVersion);
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

    public String getMD5(String data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(data.getBytes());
        byte byteData[] = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte aByteData : byteData) {
            sb.append(Integer.toString((aByteData & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    private LocalDate getDateFromString(String value) {
        String[] newValue = value.split("-");
        for (String a : newValue) {
            System.out.println(a);
        }
        if (newValue.length > 3) {
            System.out.println("Wrong input!!!");
        }
        return LocalDate.of(Integer.parseInt(newValue[0]), Integer.parseInt(newValue[1]), Integer.parseInt(newValue[2]));
    }
}
