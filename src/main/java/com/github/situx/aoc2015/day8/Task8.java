package com.github.situx.aoc2015.day8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


/**
 * Solution to Advent Of Code 2015 Day 8.
 * @author situx
 */
public class Task8 {

    /**
     * Gets the difference of the Stirng memory representaion and its actual content.
     * @param sequence the String sequence to calculate on
     * @return the difference as Integer
     */
    public Integer getCodeMemoryDiff(String sequence){
        Integer minuschars=2;
        for(int i=0;i<sequence.length();i++){
            if(sequence.charAt(i)=='\\' && sequence.charAt(i+1)=='\\'){
                minuschars++;
                i++;
            }
            else if(sequence.charAt(i)=='\\' && sequence.charAt(i+1)=='"'){
                minuschars++;
            }
            else if(sequence.charAt(i)=='\\' && sequence.charAt(i+1)=='x'){
                minuschars+=3;
                i+=3;
            }
        }
        return minuschars;
    }

    /**
     * Gets the difference of the encoded String version to its original version.
     * @param sequence the String sequence to calculate on
     * @return the difference as Integer
     */
    public Integer getEncodeDiff(String sequence){
        Integer addedchars=2;
        for(int i=0;i<sequence.length();i++){
            if(sequence.charAt(i)=='\\' || sequence.charAt(i)=='"'){
                addedchars++;
            }
        }
        return addedchars;
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
                result+=this.getCodeMemoryDiff(line.trim());
            }else {
                result+=this.getEncodeDiff(line.trim());
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
        Task8 task8=new Task8();
        System.out.println(task8.processString("aoc2015files/task8_2015.txt",true)+" Code/Memory Difference");
        System.out.println(task8.processString("aoc2015files/task8_2015.txt",false)+" Encoding Difference");
    }

}
