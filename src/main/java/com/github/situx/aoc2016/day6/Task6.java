package com.github.situx.aoc2016.day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Solution to Advent Of Code 2016 Day6.
 * @author situx
 */
public class Task6 {

    /**
     * Analyzes the input String for the least or most common characters in each String column
     * and returns the respective word comprised of these characters.
     * @param investigate the String to analyze
     * @param mostOrLeastCommonChar indicates if the most or least common character should be used
     * @return the String comprised of the respective characters
     */
    public String analyzeString(String investigate,Boolean mostOrLeastCommonChar){
        StringBuilder result= new StringBuilder();
        Map<Integer,Map<String,Integer>> counted=new TreeMap<>();
        for(String line:investigate.split(System.lineSeparator())){
            for(int i=0;i<line.length();i++){
                if(!counted.containsKey(i)){
                    counted.put(i,new TreeMap<>());
                }
                if(!counted.get(i).containsKey(line.charAt(i)+"")){
                    counted.get(i).put(line.charAt(i)+"",0);
                }
                counted.get(i).put(line.charAt(i)+"",counted.get(i).get(line.charAt(i)+"")+1);
            }
        }
        for(Integer key:counted.keySet()){
            int maxVal=0;
            if(!mostOrLeastCommonChar){
                maxVal=Integer.MAX_VALUE;
            }
            String maxKey="";
            for (Map.Entry<String, Integer> entry : counted.get(key).entrySet()) {
                if (mostOrLeastCommonChar && maxVal < entry.getValue()) {
                    maxVal = entry.getValue();
                    maxKey = entry.getKey();
                }else if(!mostOrLeastCommonChar && maxVal > entry.getValue()){
                    maxVal = entry.getValue();
                    maxKey = entry.getKey();
                }
            }
            result.append(maxKey);
        }
        return result.toString();
    }

    /**
     * The main method for this project
     * @param args
     * @throws IOException on file reading errors
     */
    public static void main(String[] args) throws IOException {
        Task6 task6=new Task6();
        System.out.println("The encrypted message is: "+task6.analyzeString(new String(Files.readAllBytes(Paths.get("aoc2016files/task6_2016.txt"))),true));
        System.out.println("The encrypted message is: "+task6.analyzeString(new String(Files.readAllBytes(Paths.get("aoc2016files/task6_2016.txt"))),false));
    }
}
