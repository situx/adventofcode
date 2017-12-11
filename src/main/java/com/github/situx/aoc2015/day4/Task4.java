package com.github.situx.aoc2015.day4;

import com.github.situx.aoc2017.day8.Task8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Solution to Task4 of Advent Of Code 2015.
 * @author situx
 *
 */
public class Task4 {

    MessageDigest digest=MessageDigest.getInstance("MD5");

    public Task4() throws NoSuchAlgorithmException {

    }

    /**
     * Generates an MD5 Hash of a String and converts it to a hex String.
     * @param hashString the String to convert
     * @return the hex string of the md5 result
     */
    public String generateMD5(String hashString){
        return javax.xml.bind.DatatypeConverter.printHexBinary(this.digest.digest(hashString.getBytes()));
    }

    public Integer checkForMatchingMD5Sum(String input,Integer numberOfZeros){
        Boolean foundHash=false;
        Integer i=0;
        StringBuilder zeroString= new StringBuilder();
        for(int j=0;j<numberOfZeros;j++){
            zeroString.append("0");
        }
        while(!foundHash){
            String md5hash=generateMD5(input+i+"");
            if(md5hash.startsWith(zeroString.toString())){
                foundHash=true;
            }
            i++;
        }
        return i-1;
    }

    /**
     * The main method of this project.
     * @param args
     * @throws IOException on file reading errors
     */
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        Task4 task4=new Task4();
        String input="yzbqklnj";
        System.out.println("First AdventCoin Mining Number (5 Zeros): "+task4.checkForMatchingMD5Sum(input,5));
        System.out.println("First AdventCoin Mining Number (6 Zeros): "+task4.checkForMatchingMD5Sum(input,6));
    }

}
