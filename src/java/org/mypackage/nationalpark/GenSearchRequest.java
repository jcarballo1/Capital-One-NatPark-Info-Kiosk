package org.mypackage.nationalpark;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
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
public class GenSearchRequest {

    private String jsonString;
    BufferedWriter inFile;

    public static class MyHostnameVerifier implements HostnameVerifier {

        public boolean verify(String hostname, SSLSession session) {
            // verification of hostname is switched off
            return true;
        }
    }
    
    public String sendGetSingle(String desigs, String states) throws Exception {
        String baseURL = "https://developer.nps.gov/api/v1/parks?parkCode=";
        baseURL += desigs;
        baseURL += "&stateCode=" + states;
        baseURL += "&fields=addresses%2Ccontacts%2Centrancefees%2Centrancepasses%2Cimages%2Coperatinghours&api_key=CAYHsEFFEaczB1PMOxrLh5GQjtumjbZpdRsZE8Xm";
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
        parseJSON();
        inFile.close();
        
        return jsonString;
    }
    
    public void parseJSON() throws Exception{
        JSONObject mainObj = new JSONObject(jsonString);
        JSONArray array = mainObj.getJSONArray("data");
        ArrayList<GeneralSearchResult> results = null;
        
        for(int i = 0; i < array.length(); i++){
            ArrayList<Address> addies = new ArrayList<>();
            ArrayList<Contact> contacts = new ArrayList<>();
            ArrayList<EntranceFee> fees = new ArrayList<>();
            ArrayList<EntrancePass> passes = new ArrayList<>();
            ArrayList<Image> images = new ArrayList<>();
            ArrayList<Hours> hours = new ArrayList<>();
            
            JSONObject subObj = array.getJSONObject(i);
            
            JSONArray curr = subObj.getJSONArray("addresses");
            JSONObject currObj;
            int j;
            for(j = 0; j < curr.length(); j++){
                currObj = curr.optJSONObject(i);
//                addies.add(new Address(curr.getString(0), curr.getString(1), curr.getString(2), curr.getString(3),
//                curr.getString(4), curr.getString(5), curr.getString(6))); PROBLEM HERE
            }
//            
//            curr = subObj.getJSONArray("contacts");
//            for(j = 0; j < curr.length(); j++){
//                currObj = curr.optJSONObject(i);
//                contacts.add(new Contact(curr.getString(0), curr.getString(3)));
//            }
//            
//            curr = subObj.getJSONArray("entranceFees");
//            for(j = 0; j < curr.length(); j++){
//                currObj = curr.optJSONObject(i);
//                fees.add(new EntranceFee(curr.getString(0), curr.getString(1), curr.getString(2)));
//            }
//            
//            curr = subObj.getJSONArray("entrancePasses");
//            for(j = 0; j < curr.length(); j++){
//                currObj = curr.optJSONObject(i);
//                passes.add(new EntrancePass(curr.getString(0), curr.getString(1), curr.getString(2)));
//            }
//            
//            curr = subObj.getJSONArray("images");
//            for(j = 0; j < curr.length(); j++){
//                currObj = curr.optJSONObject(i);
//                images.add(new Image(curr.getString(5), curr.getString(4), curr.getString(0)));
//            }
//            
//            curr = subObj.getJSONArray("operatingHours");
//            for(j = 0; j < curr.length(); j++){
//                currObj = curr.optJSONObject(i);
//                JSONArray hoursArray = currObj.getJSONArray("standardHours");
//                Map<String, String> stan = null;
//                for(int ii = 0; ii < hoursArray.length(); ii++){
//                    JSONObject hoursObj = hoursArray.getJSONObject(ii);
//                    Iterator<String> iter = hoursObj.keys();
//                    while(iter.hasNext()){
//                        String key = iter.next();
//                        stan.put(key, hoursObj.getString(key));
//                    }
//                        
//                }
//                hours.add(new Hours(curr.getString(0), curr.getString(1), stan));
//            }
//            
//            
//            results.add(new GeneralSearchResult(subObj.getString("fullName"), subObj.getString("latLong"), subObj.getString("description"),
//                subObj.getString("weatherInfo"), addies, contacts, fees, passes, images, hours, subObj.getString("url")));
//            
//            inFile.write(results.get(i).getName() + "\n");
        }
    }
}
