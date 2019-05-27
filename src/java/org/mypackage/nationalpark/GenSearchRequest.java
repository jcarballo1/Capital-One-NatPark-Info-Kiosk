package org.mypackage.nationalpark;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;

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

    public void sendGet(String[] desigs, String[] states) throws Exception {
        String baseURL = "https://developer.nps.gov/api/v1/parks?parkCode=";
        for (int i = 0; i < desigs.length; i++) {
            baseURL += desigs[i];
            if (i < desigs.length - 1) {
                baseURL += "%20"; //for comma
            }
        }
        baseURL += "&stateCode=";
        for (int i = 0; i < states.length; i++) {
            baseURL += states[i];
            if (i < states.length - 1) {
                baseURL += "%20"; //for comma
            }
        }
        URL url = new URL(baseURL);

        HttpURLConnection connect = (HttpURLConnection) url.openConnection();
        connect.setRequestProperty("Authorization", "pbqqBh28as0JfseBPFfzOmrewfemNpAQmflDXLNk");

        int responseCode = connect.getResponseCode();

        BufferedWriter inFile = new BufferedWriter(new FileWriter("output"));;
        inFile.write("Sending 'GET' request to URL : " + baseURL);
        inFile.write("\nResponse Code : " + responseCode);

        inFile.close();
    }

    public void sendGetSingle(String desigs, String states) throws Exception {
        String baseURL = "https://developer.nps.gov/api/v1/parks?parkCode=";

        baseURL += desigs;
        baseURL += "&stateCode=";
        baseURL += states;
        baseURL += "&api_key=CAYHsEFFEaczB1PMOxrLh5GQjtumjbZpdRsZE8Xm";
        URL url = new URL(baseURL);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("GET");
        connection.setReadTimeout(15 * 1000);
        int responseCode = connection.getResponseCode();

        BufferedWriter inFile = new BufferedWriter(new FileWriter("C:\\Users\\jcarb\\Documents\\NetBeansProjects\\NationalParkServiceKiosk\\output"));;
        inFile.write("Sending 'GET' request to URL : " + baseURL);
        inFile.write("\nResponse Code : " + responseCode);

        inFile.close();

    }

}
