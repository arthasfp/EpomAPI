import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javax.net.ssl.*;
import java.io.*;
import java.lang.reflect.Type;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;


public class GetListOfCampaignsFromAdvertiser {



    public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException, IOException {
        SSLContext ctx = SSLContext.getInstance("TLS");
        ctx.init(new KeyManager[0], new TrustManager[]{new DefaultTrustManager()}, new SecureRandom());
        SSLContext.setDefault(ctx);

        new GetListOfCampaignsFromAdvertiser().getList(133);
    }

    private void getList(int advertiserId) throws NoSuchAlgorithmException, IOException {
        String username = "testS";
        String password = "9403e0b37889fbbac10892cb25d557cf";
        String timestamp = String.valueOf(System.currentTimeMillis());
        String hash = getMD5(password + timestamp);
        String offerurl = "https://epomaffiliate.com/rest-api/advertiser/" + advertiserId + "/campaigns.do?username=" + username + "&timestamp=" + timestamp + "&hash=" + hash;
        System.out.println("offerurl   " + offerurl);

        URL url = new URL(offerurl);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        getVerifierForConnection(conn);
//        System.out.println(conn.getResponseCode());
        InputStream inputStream = conn.getInputStream();
        String myString = IOUtils.toString(inputStream, "UTF-8");
//        System.out.println(myString);

//        myString = myString.substring(1, myString.length() - 1);
        System.out.println(myString);


        JsonElement json = new JsonParser().parse(myString);
        JsonArray array= json.getAsJsonArray();
        Iterator iterator = array.iterator();
        while(iterator.hasNext()) {

            JsonElement json2 = (JsonElement) iterator.next();

            Gson gson = new Gson();
            GetResult gresult = gson.fromJson(json2, GetResult.class);
            System.out.println(gresult.getId()+",");
        }


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
