import enums.Format;
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
 * Created by user8 on 18.10.2016.
 */
public class GeneralAnalyticsReportAPI {

    private User user;

    public GeneralAnalyticsReportAPI(User user) {
        this.user = user;
    }


    /**
     *
     * Get Analytics report
     *
     *   statisticType	Empty, General report is the default value.
     *   range	(optional) Specifies the time period(TimeRange enum): TODAY, YESTERDAY, CURRENT_WEEK, CURRENT_MONTH, LAST_7_DAYS, LAST_30_DAYS, LAST_MONTH, LAST_3_MONTH, LAST_6_MONTH, CURRENT_YEAR, LAST_YEAR, CUSTOM. Default value is TODAY. When CUSTOM range is used, customFrom and customTo parameters should be specified.
     *   customFrom	(optional) Start date for analytics in format yyyy-MM-dd; range parameter should be set to CUSTOM.
     *   customTo	(optional) End date for analytics in format yyyy-MM-dd; range parameter should be set to CUSTOM.
     *   hourFrom	(optional) Start hour for analytics in format HH; can be useful only if groupRange is set to HOUR.
     *   hourTo	(optional) End hour for analytics in format HH; can be useful only if groupRange is set to HOUR.
     *   groupRange	(optional) Time frame for grouping analytical data(GroupRange enum). Available values: NONE, HOUR, DAY, MONTH, YEAR. Default value: NONE.
     *   groupBy	(optional) Parameters for grouping analytics data(Entity enum). Available values: PLACEMENT, CHANNEL, ZONE, SITE, BANNER, CAMPAIGN, ACTION, ADVERTISER, COUNTRY, PLACEMENT_SIZE, PLACEMENT_TYPE, OPERATING_SYSTEM, BROWSER, MOBILE_NETWORK, PLATFORM, REFERER.
     *   displayIds	(optional) Used for showing IDs along with the names of the grouped entities: true/false.
     *   eqStr	(optional) Parameters for filtering analytics data. Consists of pairs of field names and lists of string values. Field name can be one of the following: CHANNEL, COUNTRY, PLACEMENT_TYPE, BANNER_LABEL, PLACEMENT_LABEL, ACTION.
     *   eqLong	(optional) Parameters for filtering analytics data. Consists of pairs of field names and lists of string values. Field name can be one of the following: BANNER, CAMPAIGN, ADVERTISER, PLACEMENT, ZONE, SITE.
     *   breakdownByCustomParameter	(optional) Breakdown by Custom Parameter: true/false.
     *   limit	(optional) Specifies the numbers of entries to get in the array.
     *   offset	(optional) First entry of the array.
     *
     *
     */

    public void getAnalyticsReport(Format format) throws NoSuchAlgorithmException, IOException {
        URL url = new URL(user.getNetwork() + "/rest-api/analytics/" + format + "/"+ user.getUsername()+"/" + getHash() + "/" + getTimestamp() + ".do");
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
