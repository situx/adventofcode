package com.github.situx.aoc2015.day5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;

/**
 * Solution to Advent Of Code 2015 Day 3.
 * @author situx
 */
public class Task5 {

    private static final Pattern DUPLICATES_WO_OVERLAP = Pattern.compile("(\\w)(\\w)(\\w*)\\1\\2+");
    private static final Pattern REPEATSTR_W_GAP = Pattern.compile("(\\w)(\\w)\\1+");

    /**
     * Checks Niceness of a string which is defined as at least 3 vowels, no "ab","cd","pq" and "xy and
     * at least one duplicated character set.
     * @param tocheck the String to check for Niceness
     * @return true if the String is nice, false otherwise
     */
    public Boolean checkNiceNess(String tocheck){
        Set<String> vowelset=new TreeSet<>(Arrays.asList("a","e","i","o","u"));
        if(tocheck.contains("ab") || tocheck.contains("cd") || tocheck.contains("pq")
        || tocheck.contains("xy")){
            return false;
        }
        Integer vowels=0;
        Boolean doublechar=false;
        for(int i=0;i<tocheck.length()-1;i++){
            Character curchar=tocheck.charAt(i);
            Character nextchar=tocheck.charAt(i+1);
            if(vowelset.contains(curchar.toString())){
                vowels++;
            }
            if(curchar.equals(nextchar)){
                doublechar=true;
            }
        }
        if(vowelset.contains(tocheck.charAt(tocheck.length()-1)+"")){
            vowels++;
        }
        if(vowels<3)
            return false;
        return doublechar;
    }

    /**
     * Checks for the new definition of Niceness using Regular expressions.
     * Checks for duplicates of characters without overlap and repeated Strings with Gap.
     * @param tocheck the String to check
     * @return true if the String is nice, false otherwise
     */
    public Boolean checkNewNiceNess(String tocheck){
        if(DUPLICATES_WO_OVERLAP.matcher(tocheck).find() && REPEATSTR_W_GAP.matcher(tocheck).find()){
            return true;
        }
        return false;
    }

    /**
     * Processes a String filepath and evaluates Niceness on its lines.
     * @param filepath the filepath to the file
     * @param OldOrNew configures if the old or new definition of Niceness should be used
     * @return the amount of nice Strings in the file
     * @throws IOException on file reading errors
     */
    public Integer processString(String filepath,Boolean OldOrNew) throws IOException {
        BufferedReader reader=new BufferedReader(new FileReader(new File(filepath)));
        String line;
        Integer result=0;
        while((line=reader.readLine())!=null){
            if(OldOrNew){
                if(this.checkNiceNess(line.trim())){
                    result++;
                }
            }else {
                if(this.checkNewNiceNess(line.trim())){
                    result++;
                }
            }

        }
        return result;
    }

    /**
     * The main method of this project
     * @param args
     * @throws IOException on file reading errors.
     */
    public static void main(String[] args) throws IOException {
        Task5 task5=new Task5();
        System.out.println(task5.processString("aoc2015files/task5_2015.txt",true)+" Strings are nice using the first definition of Niceness.");
        System.out.println(task5.processString("aoc2015files/task5_2015.txt",false)+" Strings are nice using the second definition of Niceness.");
    }
}
