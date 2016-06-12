public class SiteGetter {

    private User user;

    public SiteGetter(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    public void getSitesByUserData() throws NoSuchAlgorithmException, URISyntaxException, IOException, KeyManagementException {
//
//        long timestamp = new Date().getTime();
//        String hash = getMD5(getMD5(user.getPassword()) + String.valueOf(timestamp));
////        URIBuilder builder = new URIBuilder("https://n29.epom.com/rest-api/sites.do");
////        builder.setParameter("hash", hash)
////                .setParameter("timestamp", String.valueOf(timestamp))
////                .setParameter("username", user.getUsername());
////
////        HttpGet request = new HttpGet(builder.build());
////        HttpClient client = HttpClientBuilder.create().build();
////        HttpResponse response = client.execute(request);
////        System.out.println("Response Code : "
////                + response.getStatusLine().getStatusCode());
//        SSLContext ctx = SSLContext.getInstance("TLS");
//        ctx.init(new KeyManager[0], new TrustManager[] {new DefaultTrustManager()}, new SecureRandom());
//        SSLContext.setDefault(ctx);
//
//        URL url = new URL("https://n29.epom.com/rest-api/sites.do?hash=a62e079d6b9fe641a71e3b20ee0a8a33&timestamp=1460656831983&username=apimaster");
//        HttpURLConnection con = (HttpURLConnection) url.openConnection();
//
//        // optional default is GET
////        con.setRequestMethod("GET");
//        con.setHostnameVerifier(new HostnameVerifier() {
//            @Override
//            public boolean verify(String arg0, SSLSession arg1) {
//                return true;
//            }
//        });
//        System.out.println(conn.getResponseCode());
//        conn.disconnect();
//
//        int responseCode = con.getResponseCode();
//        System.out.println("\nSending 'GET' request to URL : " + "https://n29.epom.com/rest-api/sites.do");
//        System.out.println("Response Code : " + responseCode);
//
//        BufferedReader in = new BufferedReader(
//                new InputStreamReader(con.getInputStream()));
//        String inputLine;
//        StringBuffer response = new StringBuffer();
//
//        while ((inputLine = in.readLine()) != null) {
//            response.append(inputLine);
//        }
//        in.close();
//
//        //print result
//        System.out.println(response.toString());
//
//
//    }
//
//    private String getMD5(String data) throws NoSuchAlgorithmException {
//        MessageDigest md = MessageDigest.getInstance("MD5");
//        md.update(data.getBytes());
//        byte byteData[] = md.digest();
//        StringBuilder sb = new StringBuilder();
//        for (byte aByteData : byteData) {
//            sb.append(Integer.toString((aByteData & 0xff) + 0x100, 16).substring(1));
//        }
//        System.out.println("Digest(in hex format):: " + sb.toString());
//        return sb.toString();
//    }
//
//    private static class DefaultTrustManager implements X509TrustManager {
//
//        @Override
//        public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}
//
//        @Override
//        public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}
//
//        @Override
//        public X509Certificate[] getAcceptedIssuers() {
//            return null;
//        }
//    }
}
