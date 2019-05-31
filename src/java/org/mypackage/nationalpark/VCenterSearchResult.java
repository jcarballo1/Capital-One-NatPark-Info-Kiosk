/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mypackage.nationalpark;

import java.util.ArrayList;

/**
 *
 * @author jcarb
 */
public class VCenterSearchResult {
    private String name;
    private String descrip;
    private String direct;
    private String coords;
    private ArrayList<PhoneNumber> numbers;
    private ArrayList<String> emails;
    private ArrayList<Address> addies;
    private ArrayList<Hours> hours;
    private String url;
    
    public VCenterSearchResult(String n, String d, String di, String c, ArrayList<PhoneNumber> num, ArrayList<String> e, ArrayList<Address> a, ArrayList<Hours> h, String u){
        name = n;
        descrip = d;
        direct = di;
        coords = c;
        numbers = num;
        emails = e;
        addies = a;
        hours = h;
        url = u;
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

    /**
     * @return the descrip
     */
    public String getDescrip() {
        return descrip;
    }

    /**
     * @param descrip the descrip to set
     */
    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    /**
     * @return the direct
     */
    public String getDirect() {
        return direct;
    }

    /**
     * @param direct the direct to set
     */
    public void setDirect(String direct) {
        this.direct = direct;
    }

    /**
     * @return the coords
     */
    public String getCoords() {
        return coords;
    }

    /**
     * @param coords the coords to set
     */
    public void setCoords(String coords) {
        this.coords = coords;
    }

    /**
     * @return the addies
     */
    public ArrayList<Address> getAddies() {
        return addies;
    }

    /**
     * @param addies the addies to set
     */
    public void setAddies(ArrayList<Address> addies) {
        this.addies = addies;
    }

    /**
     * @return the hours
     */
    public ArrayList<Hours> getHours() {
        return hours;
    }

    /**
     * @param hours the hours to set
     */
    public void setHours(ArrayList<Hours> hours) {
        this.hours = hours;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the numbers
     */
    public ArrayList<PhoneNumber> getNumbers() {
        return numbers;
    }

    /**
     * @param numbers the numbers to set
     */
    public void setNumbers(ArrayList<PhoneNumber> numbers) {
        this.numbers = numbers;
    }

    /**
     * @return the emails
     */
    public ArrayList<String> getEmails() {
        return emails;
    }

    /**
     * @param emails the emails to set
     */
    public void setEmails(ArrayList<String> emails) {
        this.emails = emails;
    }
}
