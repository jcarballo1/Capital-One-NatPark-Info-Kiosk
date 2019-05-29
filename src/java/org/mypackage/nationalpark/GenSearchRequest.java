package org.mypackage.nationalpark;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
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
public class GenSearchRequest {

    private String name;
    
    public static class MyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            // verification of hostname is switched off
            return true;
        }
    }

//    public void sendGet(String[] desigs, String[] states) throws Exception {
//        String baseURL = "https://developer.nps.gov/api/v1/parks?parkCode=";
//        for (int i = 0; i < desigs.length; i++) {
//            baseURL += desigs[i];
//            if (i < desigs.length - 1) {
//                baseURL += "%20"; //for comma
//            }
//        }
//        baseURL += "&stateCode=";
//        for (int i = 0; i < states.length; i++) {
//            baseURL += states[i];
//            if (i < states.length - 1) {
//                baseURL += "%20"; //for comma
//            }
//        }
//        URL url = new URL(baseURL);
//
//        HttpURLConnection connect = (HttpURLConnection) url.openConnection();
//        connect.setRequestProperty("Authorization", "pbqqBh28as0JfseBPFfzOmrewfemNpAQmflDXLNk");
//
//        int responseCode = connect.getResponseCode();
//
//        BufferedWriter inFile = new BufferedWriter(new FileWriter("output"));;
//        inFile.write("Sending 'GET' request to URL : " + baseURL);
//        inFile.write("\nResponse Code : " + responseCode);
//
//        inFile.close();
//    }

    public void sendGetSingle(String desigs, String states) throws Exception {
        String baseURL = "https://developer.nps.gov/api/v1/parks?parkCode=";
        baseURL += desigs;
        baseURL += "&stateCode=" + states;
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

        BufferedWriter inFile = new BufferedWriter(new FileWriter("C:\\Users\\jcarb\\Documents\\NetBeansProjects\\NationalParkServiceKiosk\\output.txt"));;
        inFile.write("Sending 'GET' request to URL : " + url.toString());
        inFile.write("\n\nResponse Code : " + responseCode);

        inFile.close();
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
