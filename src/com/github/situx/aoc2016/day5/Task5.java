package com.github.situx.aoc2016.day5;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

/**
 * Solution to Advent Of Code 2016 Day5.
 * @author situx
 */
public class Task5 {

    private MessageDigest digest;

    /**
     * Constructor for this class initializing the MessageDigest.
     * @throws NoSuchAlgorithmException on error
     */
    public Task5() throws NoSuchAlgorithmException {
        digest=MessageDigest.getInstance("MD5");
    }

    /**
     * Generates an MD5 Hash of a String and converts it to a hex String.
     * @param hashString the String to convert
     * @return the hex string of the md5 result
     */
    public String generateMD5(String hashString){
        return javax.xml.bind.DatatypeConverter.printHexBinary(this.digest.digest(hashString.getBytes()));
    }

    /**
     * Checks for hex Strings starting with 00000 and either adds the following number
     * to a result String or takes the following number as the position to add the next
     * following number in.
     * @param input the input String
     * @param moreSophisticated indicates whether to use the first or second method of insertion
     * @return the result String
     */
    public String checkForHashWith000(String input,Boolean moreSophisticated){
        Integer counter=0;
        String hash;
        char[] args=new char[8];
        for(int i=0;i<8;i++) {
            hash="";
            while (!hash.startsWith("00000")) {
                hash = generateMD5(input + counter.toString());
                counter++;
            }
            if(moreSophisticated && Integer.valueOf(hash.substring(5,6),16)<8 && args[Integer.valueOf(hash.substring(5,6),16)]==0){
                args[Integer.valueOf(hash.substring(5,6),16)]=hash.charAt(6);
            }else if(moreSophisticated && (Integer.valueOf(hash.substring(5,6),16)>8 || args[Integer.valueOf(hash.substring(5,6),16)]!=0)){
                i--;
            } else{
                args[i]=hash.charAt(5);
            }
        }
        return new String(args);
    }


    /**
     * Main method of this project
     * @param args
     * @throws NoSuchAlgorithmException on MessageDigest error
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String input="cxdnnyjw";
        Task5 task5=new Task5();
        System.out.println(task5.checkForHashWith000(input,false));
        System.out.println(task5.checkForHashWith000(input,true));
    }

}
