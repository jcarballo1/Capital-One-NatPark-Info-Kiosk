package com.mycompany.nationalparkserviceinfokiosk;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import org.json.JSONArray;
import org.json.JSONObject;
import sun.misc.BASE64Encoder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jcarb
 */
public class CESearchRequest {

    private String jsonString;
    private ArrayList<CESearchResult> results;

    public static class MyHostnameVerifier implements HostnameVerifier {

        public boolean verify(String hostname, SSLSession session) {
            // verification of hostname is switched off
            return true;
        }
    }

    public ArrayList<CESearchResult> process(String[] keywords, String desigs, String states, String key) throws Exception {
        results = new ArrayList<>();
        if (key.equals("")) {
            sendGetAlert(keywords, desigs, states);
            sendGetArticle(keywords, desigs, states);
            sendGetEvent(keywords, desigs, states);
            sendGetNews(keywords, desigs, states);
        } else if (key.equals("alr")) {
            sendGetAlert(keywords, desigs, states);
        } else if (key.equals("art")) {
            sendGetArticle(keywords, desigs, states);
        } else if (key.equals("ev")) {
            sendGetEvent(keywords, desigs, states);
        } else {
            sendGetNews(keywords, desigs, states);
        }

        return results;
    }

    public void sendGetAlert(String[] keywords, String desigs, String states) throws Exception {
        String baseURL = "https://developer.nps.gov/api/v1/alerts?parkCode=";
        baseURL += desigs;
        baseURL += "&stateCode=" + states;

        if (!keywords[0].equals("")) {
            baseURL += "&q=";
            for (int i = 0; i < keywords.length; i++) {
                if (i == keywords.length - 1) {
                    baseURL += keywords[i];
                } else {
                    baseURL += keywords[i] + "%20";
                }
            }
        }

        baseURL += "&api_key=CAYHsEFFEaczB1PMOxrLh5GQjtumjbZpdRsZE8Xm";
        URL url = new URL(baseURL);
        String userName = "admin";
        String password = "admin";
        String authentication = "CAYHsEFFEaczB1PMOxrLh5GQjtumjbZpdRsZE8Xm";

        HttpURLConnection connection = null;
        connection = (HttpsURLConnection) url.openConnection();
        ((HttpsURLConnection) connection).setHostnameVerifier(new VCenterSearchRequest.MyHostnameVerifier());
        connection.setRequestProperty("Content-Type", "text/plain; charset=\"utf8\"");
        connection.setRequestMethod("GET");
        BASE64Encoder encoder = new BASE64Encoder();
        String encoded = encoder.encode((authentication).getBytes("UTF-8"));
        connection.setRequestProperty("Authorization", encoded);
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        connection.setDoOutput(true);
        connection.connect();

        int responseCode = connection.getResponseCode();
        InputStream input = (InputStream) connection.getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, "UTF-8"), 8);
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        jsonString = sb.toString();
        parseAlertJSON();
    }

    public void sendGetArticle(String[] keywords, String desigs, String states) throws Exception {
        String baseURL = "https://developer.nps.gov/api/v1/articles?parkCode=";
        baseURL += desigs;
        baseURL += "&stateCode=" + states;

        if (!keywords[0].equals("")) {
            baseURL += "&q=";
            for (int i = 0; i < keywords.length; i++) {
                if (i == keywords.length - 1) {
                    baseURL += keywords[i];
                } else {
                    baseURL += keywords[i] + "%20";
                }
            }
        }

        baseURL += "&api_key=CAYHsEFFEaczB1PMOxrLh5GQjtumjbZpdRsZE8Xm";
        URL url = new URL(baseURL);
        String userName = "admin";
        String password = "admin";
        String authentication = "CAYHsEFFEaczB1PMOxrLh5GQjtumjbZpdRsZE8Xm";

        HttpURLConnection connection = null;
        connection = (HttpsURLConnection) url.openConnection();
        ((HttpsURLConnection) connection).setHostnameVerifier(new VCenterSearchRequest.MyHostnameVerifier());
        connection.setRequestProperty("Content-Type", "text/plain; charset=\"utf8\"");
        connection.setRequestMethod("GET");
        BASE64Encoder encoder = new BASE64Encoder();
        String encoded = encoder.encode((authentication).getBytes("UTF-8"));
        connection.setRequestProperty("Authorization", encoded);
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        connection.setDoOutput(true);
        connection.connect();

        int responseCode = connection.getResponseCode();
        InputStream input = (InputStream) connection.getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, "UTF-8"), 8);
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        jsonString = sb.toString();
        parseArticleJSON();
    }

    public void sendGetEvent(String[] keywords, String desigs, String states) throws Exception {
        String baseURL = "https://developer.nps.gov/api/v1/events?parkCode=";
        baseURL += desigs;
        baseURL += "&stateCode=" + states;

        if (!keywords[0].equals("")) {
            baseURL += "&q=";
            for (int i = 0; i < keywords.length; i++) {
                if (i == keywords.length - 1) {
                    baseURL += keywords[i];
                } else {
                    baseURL += keywords[i] + "%20";
                }
            }
        }

        baseURL += "&api_key=CAYHsEFFEaczB1PMOxrLh5GQjtumjbZpdRsZE8Xm";
        URL url = new URL(baseURL);
        String userName = "admin";
        String password = "admin";
        String authentication = "CAYHsEFFEaczB1PMOxrLh5GQjtumjbZpdRsZE8Xm";

        HttpURLConnection connection = null;
        connection = (HttpsURLConnection) url.openConnection();
        ((HttpsURLConnection) connection).setHostnameVerifier(new VCenterSearchRequest.MyHostnameVerifier());
        connection.setRequestProperty("Content-Type", "text/plain; charset=\"utf8\"");
        connection.setRequestMethod("GET");
        BASE64Encoder encoder = new BASE64Encoder();
        String encoded = encoder.encode((authentication).getBytes("UTF-8"));
        connection.setRequestProperty("Authorization", encoded);
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        connection.setDoOutput(true);
        connection.connect();

        int responseCode = connection.getResponseCode();
        InputStream input = (InputStream) connection.getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, "UTF-8"), 8);
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        jsonString = sb.toString();
        parseEventJSON();
    }

    public void sendGetNews(String[] keywords, String desigs, String states) throws Exception {
        String baseURL = "https://developer.nps.gov/api/v1/newsreleases?parkCode=";
        baseURL += desigs;
        baseURL += "&stateCode=" + states;

        if (!keywords[0].equals("")) {
            baseURL += "&q=";
            for (int i = 0; i < keywords.length; i++) {
                if (i == keywords.length - 1) {
                    baseURL += keywords[i];
                } else {
                    baseURL += keywords[i] + "%20";
                }
            }
        }

        baseURL += "&api_key=CAYHsEFFEaczB1PMOxrLh5GQjtumjbZpdRsZE8Xm";
        URL url = new URL(baseURL);
        String userName = "admin";
        String password = "admin";
        String authentication = "CAYHsEFFEaczB1PMOxrLh5GQjtumjbZpdRsZE8Xm";

        HttpURLConnection connection = null;
        connection = (HttpsURLConnection) url.openConnection();
        ((HttpsURLConnection) connection).setHostnameVerifier(new VCenterSearchRequest.MyHostnameVerifier());
        connection.setRequestProperty("Content-Type", "text/plain; charset=\"utf8\"");
        connection.setRequestMethod("GET");
        BASE64Encoder encoder = new BASE64Encoder();
        String encoded = encoder.encode((authentication).getBytes("UTF-8"));
        connection.setRequestProperty("Authorization", encoded);
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        connection.setDoOutput(true);
        connection.connect();

        int responseCode = connection.getResponseCode();
        InputStream input = (InputStream) connection.getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, "UTF-8"), 8);
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        jsonString = sb.toString();
        parseNewsJSON();
    }

    public void parseAlertJSON() throws Exception {
        JSONObject mainObj = new JSONObject(jsonString);
        JSONArray array = mainObj.getJSONArray("data");
        boolean good = true;

        for (int i = 0; i < array.length(); i++) {
            String title = "";
            String category = "";
            String descrip = "";
            String url = "";

            int j = 0;
            JSONObject subObj = array.getJSONObject(i);
            JSONArray curr = null;
            JSONObject currObj = null;

            try {
                title = subObj.getString("title");
            } catch (Exception e) {
            }

            try {
                category = subObj.getString("category");
            } catch (Exception e) {
            }

            try {
                descrip = subObj.getString("description");
            } catch (Exception e) {
            }

            try {
                url = subObj.getString("url");
            } catch (Exception e) {
            }

            results.add(new CESearchResult(title, category, descrip, url));
        }
    }

    public void parseArticleJSON() throws Exception {
        JSONObject mainObj = new JSONObject(jsonString);
        JSONArray array = mainObj.getJSONArray("data");

        for (int i = 0; i < array.length(); i++) {
            String title = "";
            String listingDes = "";
            String url = "";
            String imageURL = "";
            String altText = "";
            JSONObject subObj = array.getJSONObject(i);
            JSONArray curr = null;
            JSONObject currObj = null;
            boolean good = true;

            try {
                currObj = subObj.getJSONObject("listingimage");
            } catch (Exception e) {
                good = false;
            }

            if (good) {
                altText = currObj.getString("altText");
                imageURL = currObj.getString("url");
            }

            try {
                title = subObj.getString("title");
            } catch (Exception e) {
            }

            try {
                listingDes = subObj.getString("listingdescription");
            } catch (Exception e) {
            }

            try {
                url = subObj.getString("url");
            } catch (Exception e) {
            }

            results.add(new CESearchResult(altText, imageURL, listingDes, title, url));

        }
    }

    public void parseEventJSON() throws Exception {
        JSONObject mainObj = new JSONObject(jsonString);
        JSONArray array = mainObj.getJSONArray("data");

        for (int i = 0; i < array.length(); i++) {
            String email = "";
            String contactName = "";
            String contactPhone = "";
            ArrayList<String> dates = new ArrayList<>();
            String location = "";
            String timeStart = "";
            String timeEnd = "";
            String descrip = "";
            String title = "";
            String fees = "";
            String url = "";

            JSONObject subObj = array.getJSONObject(i);
            JSONArray curr = null;
            JSONObject currObj = null;
            boolean good = true;

            try {
                email = subObj.getString("contactemailaddress");
            } catch (Exception e) {
            }

            try {
                contactName = subObj.getString("contactName");
            } catch (Exception e) {
            }

            try {
                curr = subObj.getJSONArray("dates");
            } catch (Exception e) {
                good = false;
            }
            if (good) {
                for (int j = 0; j < curr.length(); j++) {
                    dates.add(curr.getString(j));
                }
            }

            try {
                location = subObj.getString("location");
            } catch (Exception e) {
            }

            good = true;
            try {
                curr = subObj.getJSONArray("times");
            } catch (Exception e) {
                good = false;
            }
            if (good) {
                currObj = curr.getJSONObject(0);
                timeStart = currObj.getString("timestart");
                timeEnd = currObj.getString("timeend");
            }

            try {
                descrip = subObj.getString("description");
            } catch (Exception e) {
            }

            try {
                title = subObj.getString("title");
            } catch (Exception e) {
            }

            try {
                title = subObj.getString("title");
            } catch (Exception e) {
            }

            try {
                fees = subObj.getString("feeinfo");
            } catch (Exception e) {
            }

            try {
                url = subObj.getString("infourl");
            } catch (Exception e) {
            }

            results.add(new CESearchResult(email, contactName, contactPhone, dates, location, timeStart,
                    timeEnd, descrip, title, fees, url));
        }
    }

    public void parseNewsJSON() throws Exception {
        JSONObject mainObj = new JSONObject(jsonString);
        JSONArray array = mainObj.getJSONArray("data");

        for (int i = 0; i < array.length(); i++) {
            String abs = "";
            String releaseDate = "";
            String title = "";
            String url = "";
            String imageURL = "";

            JSONObject subObj = array.getJSONObject(i);
            JSONArray curr = null;
            JSONObject currObj = null;
            boolean good = true;
            
            try {
                abs = subObj.getString("abstract");
            } catch (Exception e) {
            }
            
            try {
                releaseDate = subObj.getString("releasedate");
            } catch (Exception e) {
            }
            
            try {
                title = subObj.getString("title");
            } catch (Exception e) {
            }
            
            try {
                url = subObj.getString("url");
            } catch (Exception e) {
            }
            
            try {
                currObj = subObj.getJSONObject("image");
            } catch (Exception e) {
                good = false;
            }
            if(good){
                imageURL = currObj.getString("url");
            }
            
            results.add(new CESearchResult(abs, releaseDate, title, url, imageURL, ""));
        }
    }
}
