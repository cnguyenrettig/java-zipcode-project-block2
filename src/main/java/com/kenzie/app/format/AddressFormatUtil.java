package com.kenzie.app.format;


import java.util.ArrayList;
import java.util.HashMap;

public class AddressFormatUtil {
    //declare properties
    private static HashMap<String,String> codeTable = new HashMap<>();
    private static HashMap<String,String> cityCodeTable = new HashMap<>();

    private static ArrayList<ArrayList<String>> listItems;

    //static initializer - runs automatically when class loaded
    static {
        codeTable.put("ST", "STREET");
        codeTable.put("St.", "STREET");
        codeTable.put("St", "STREET");
        codeTable.put("ST.", "STREET");
        codeTable.put("Rd.", "ROAD");
        codeTable.put("RD", "ROAD");
        codeTable.put("Rd", "ROAD");
        codeTable.put("RD.", "ROAD");
        codeTable.put("AVE", "AVENUE");
        codeTable.put("AVE.", "AVENUE");
        codeTable.put("Ave", "AVENUE");
        codeTable.put("Ave.", "AVENUE");

        //city code table
        cityCodeTable.put("ST", "SAINT");
        cityCodeTable.put("St.", "SAINT");
        cityCodeTable.put("St", "SAINT");
        cityCodeTable.put("ST.", "SAINT");

    }

    public static void initCodeTable(){
        //enter values into HashMap
        codeTable.put("ST", "STREET");
        codeTable.put("St.", "STREET");
        codeTable.put("St", "STREET");
        codeTable.put("ST.", "STREET");
        codeTable.put("Rd.", "ROAD");
        codeTable.put("RD", "ROAD");
        codeTable.put("Rd", "ROAD");
        codeTable.put("RD.", "ROAD");
        codeTable.put("AVE", "AVENUE");
        codeTable.put("AVE.", "AVENUE");
        codeTable.put("Ave", "AVENUE");
        codeTable.put("Ave.", "AVENUE");
    }

    //123 Main St. - input
    //123 Main STREET - output
    public static String replaceAbbreviation(String input){
        String result=input;

        for ( String currentKey  : codeTable.keySet()   ) {
            //search and replace
            if(input.contains(currentKey)) {
                String currentValue = codeTable.get(currentKey);
                result = input.replaceAll(currentKey, currentValue);
            }
        }
        return result;
    }

    public static String replaceCityAbbreviation(String input){
        String result=input;

        for ( String currentKey  : cityCodeTable.keySet()   ) {
            //search and replace
            if(input.contains(currentKey)) {
                String currentValue = cityCodeTable.get(currentKey);
                result = input.replaceAll(currentKey, currentValue);
            }
        }
        return result;
    }


    public static String formatAddress(String input){
        return input.toUpperCase();
    }

    public static String formatStreetAddress(String input){
        return formatAddress(replaceAbbreviation(input));
    }

    public static String formatCity(String input){
        return formatAddress(replaceCityAbbreviation(input));
    }

    public static void main(String[] args) {
        String testAddr = "123 Main St. Apt 2";
        String testAddr2 = "123 Main RD";
        String testAddr3 = "123 Main Ave";

        initCodeTable();
        System.out.println(replaceAbbreviation(testAddr));

        //Test Rd
        System.out.println(replaceAbbreviation(testAddr2));

        //Test Avenue
        System.out.println(replaceAbbreviation(testAddr3));







    }
}
