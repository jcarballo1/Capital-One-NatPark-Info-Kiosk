package org.mypackage.nationalpark;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import sun.misc.BASE64Encoder;
import org.json.*;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jcarb
 */
public class VCenterSearchRequest {

    private String jsonString;
    BufferedWriter inFile;

    public static class MyHostnameVerifier implements HostnameVerifier {

        public boolean verify(String hostname, SSLSession session) {
            // verification of hostname is switched off
            return true;
        }
    }

    public ArrayList<VCenterSearchResult> sendGetWithKeyword(String[] keywords, String desigs, String states) throws Exception {
        String baseURL = "https://developer.nps.gov/api/v1/parks?parkCode=";
        baseURL += desigs;
        baseURL += "&stateCode=" + states + "&q=";
        for (int i = 0; i < keywords.length; i++) {
            if (i == keywords.length-1) {
                baseURL += keywords[i];
            } else {
                baseURL += keywords[i] + "%20";
            }
        }
        baseURL += "&fields=addresses%2Ccontacts%2Coperatinghours&";
        baseURL += "&api_key=CAYHsEFFEaczB1PMOxrLh5GQjtumjbZpdRsZE8Xm";
        URL url = new URL(baseURL);
        String userName = "admin";
        String password = "admin";
        String authentication = "CAYHsEFFEaczB1PMOxrLh5GQjtumjbZpdRsZE8Xm";

        HttpURLConnection connection = null;
        connection = (HttpsURLConnection) url.openConnection();
        ((HttpsURLConnection) connection).setHostnameVerifier(new MyHostnameVerifier());
        connection.setRequestProperty("Content-Type", "text/plain; charset=\"utf8\"");
        connection.setRequestMethod("GET");
        BASE64Encoder encoder = new BASE64Encoder();
        String encoded = encoder.encode((authentication).getBytes("UTF-8"));
        connection.setRequestProperty("Authorization", encoded);
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        connection.setDoOutput(true);
        connection.connect();

        int responseCode = connection.getResponseCode();

        inFile = new BufferedWriter(new FileWriter("C:\\Users\\jcarb\\Documents\\NetBeansProjects\\NationalParkServiceKiosk\\output.txt"));;
        inFile.write("Sending 'GET' request to URL : " + url.toString());
        inFile.write("\nResponse Code : " + responseCode);
        inFile.write("\n");
        inFile.write(connection.getContentType());

        InputStream input = (InputStream) connection.getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, "iso-8859-1"), 8);
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        jsonString = sb.toString();
        ArrayList<VCenterSearchResult> res = parseJSON();
        inFile.close();

        return res;
    }

    public ArrayList<VCenterSearchResult> sendGetSingle(String desigs, String states) throws Exception {
        String baseURL = "https://developer.nps.gov/api/v1/visitorcenters?parkCode=";
        baseURL += desigs;
        baseURL += "&stateCode=" + states;
        baseURL += "&fields=addresses%2Ccontacts%2Coperatinghours&api_key=CAYHsEFFEaczB1PMOxrLh5GQjtumjbZpdRsZE8Xm";
        URL url = new URL(baseURL);
        String userName = "admin";
        String password = "admin";
        String authentication = "CAYHsEFFEaczB1PMOxrLh5GQjtumjbZpdRsZE8Xm";

        HttpURLConnection connection = null;
        connection = (HttpsURLConnection) url.openConnection();
        ((HttpsURLConnection) connection).setHostnameVerifier(new MyHostnameVerifier());
        connection.setRequestProperty("Content-Type", "text/plain; charset=\"utf8\"");
        connection.setRequestMethod("GET");
        BASE64Encoder encoder = new BASE64Encoder();
        String encoded = encoder.encode((authentication).getBytes("UTF-8"));
        connection.setRequestProperty("Authorization", encoded);
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        connection.setDoOutput(true);
        connection.connect();

        int responseCode = connection.getResponseCode();

        inFile = new BufferedWriter(new FileWriter("C:\\Users\\jcarb\\Documents\\NetBeansProjects\\NationalParkServiceKiosk\\output.txt"));;
        inFile.write("Sending 'GET' request to URL : " + url.toString());
        inFile.write("\nResponse Code : " + responseCode);
        inFile.write("\n");
        inFile.write(connection.getContentType());

        InputStream input = (InputStream) connection.getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, "iso-8859-1"), 8);
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        jsonString = sb.toString();
        ArrayList<VCenterSearchResult> res = parseJSON();
        inFile.close();

        return res;
    }

    public ArrayList<VCenterSearchResult> parseJSON() throws Exception {
        JSONObject mainObj = new JSONObject(jsonString);
        JSONArray array = mainObj.getJSONArray("data");
        ArrayList<VCenterSearchResult> results = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            ArrayList<Address> addies = new ArrayList<>();
            ArrayList<PhoneNumber> numbers = new ArrayList<>();
            ArrayList<String> emails = new ArrayList<>();
            ArrayList<Hours> hours = new ArrayList<>();

            JSONObject subObj = array.getJSONObject(i);

            JSONArray curr = subObj.getJSONArray("addresses");
            JSONObject currObj;
            int j;
            for (j = 0; j < curr.length(); j++) {
                currObj = curr.optJSONObject(j);
                addies.add(new Address(currObj.getString("line1"), currObj.getString("line2"), currObj.getString("line3"), currObj.getString("city"),
                        currObj.getString("stateCode"), currObj.getString("postalCode"), currObj.getString("type"))); //PROBLEM HERE
            }

            JSONObject con = subObj.getJSONObject("contacts");
            curr = con.getJSONArray("phoneNumbers");
            for (j = 0; j < curr.length(); j++) {
                currObj = curr.optJSONObject(j);
                numbers.add(new PhoneNumber(currObj.getString("phoneNumber"), currObj.getString("type")));
            }

            curr = con.getJSONArray("emailAddresses");
            for (j = 0; j < curr.length(); j++) {
                currObj = curr.optJSONObject(j);
                emails.add(currObj.getString("emailAddress"));
            }

            curr = subObj.getJSONArray("operatingHours");
            if (curr.length() > 0) {
                for (j = 0; j < 1; j++) {
                    currObj = curr.optJSONObject(j);
                    JSONObject hoursArray = currObj.getJSONObject("standardHours");
                    Map<String, String> stan = new HashMap<>();
                    Iterator<String> iter = hoursArray.keys();
                    while (iter.hasNext()) {
                        String key = iter.next();
                        String val = hoursArray.getString(key);
                        stan.put(key, val);
                    }
                    hours.add(new Hours(currObj.getString("name"), currObj.getString("description"), stan));
                }
            }

            results.add(new VCenterSearchResult(subObj.getString("name"), subObj.getString("description"), subObj.getString("directionsInfo"),
                    subObj.getString("latLong"), numbers, emails, addies, hours, subObj.getString("url")));

            inFile.write(results.get(i).getName() + "\n");
        }
        return results;
    }
}
