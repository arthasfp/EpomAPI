

import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.apache.log4j.AsyncAppender;
import org.apache.wicket.ajax.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Logger;

public class ParsingDemo {
    public static void main(String[] args) {
        new ReadPlacesFeedTask().doInBackground("/rest-api/sites.do");

    }
    public static String readJSONFeed(String URL) {
        StringBuilder stringBuilder = new StringBuilder();
        HttpClient httpClient = new CloseableHttpClient() {
            @Override
            public HttpParams getParams() {
                return null;
            }

            @Override
            public ClientConnectionManager getConnectionManager() {
                return null;
            }

            @Override
            public void close() throws IOException {

            }

            @Override
            protected CloseableHttpResponse doExecute(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) throws IOException, ClientProtocolException {
                return null;
            }
        };
        HttpGet httpGet = new HttpGet(URL);

        try {

            HttpResponse response = httpClient.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();

            if (statusCode == 200) {

                HttpEntity entity = response.getEntity();
                InputStream inputStream = entity.getContent();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }

                inputStream.close();

            } else {
                Logger.getLogger("JSON", "Failed to download file");
            }
        } catch (Exception e) {
            Logger.getLogger("readJSONFeed", e.getLocalizedMessage());
        }
        return stringBuilder.toString();
    }
    private static class ReadPlacesFeedTask extends AsyncAppender {
        protected String doInBackground(String... urls) {

            return readJSONFeed(urls[0]);
        }

        protected void onPostExecute(String result) {

            JSONObject json;
            try {
                json = new JSONObject(result);

                ////CREATE A JSON OBJECT////

                JSONObject data = json.getJSONObject("facebook");

                ////GET A STRING////

                String title = data.getString("");

                //Similarly you can get other types of data
                //Replace String to the desired data type like int or boolean etc.

            } catch (JSONException e1) {
                e1.printStackTrace();

            }

            //GETTINGS DATA FROM JSON ARRAY//

            try {

                JSONObject jsonObject = new JSONObject(result);
                JSONArray postalCodesItems = new JSONArray(
                        jsonObject.getString("postalCodes"));

                JSONObject postalCodesItem = postalCodesItems
                        .getJSONObject(1);

            } catch (Exception e) {
                Logger.getLogger("ReadPlacesFeedTask", e.getLocalizedMessage());
            }
        }
    }
}
