package org.mypackage.nationalpark;

import java.io.BufferedWriter;
import java.util.ArrayList;

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

    private void sendGetAlert(String[] keywords, String desigs, String states) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void sendGetArticle(String[] keywords, String desigs, String states) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void sendGetEvent(String[] keywords, String desigs, String states) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void sendGetNews(String[] keywords, String desigs, String states) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
