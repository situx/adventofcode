package com.github.situx.aoc2015.day8;

import com.github.situx.aoc2015.day5.Task5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by timo on 03.12.17 .
 */
public class Task8 {

    public Integer getCodeMemoryDiff(String sequence){
        Integer minuschars=2;
        for(int i=0;i<sequence.length();i++){
            if(sequence.charAt(i)=='\\' && sequence.charAt(i+1)=='\\'){
                minuschars++;
                i+=2;
            }
            if(sequence.charAt(i)=='\\' && sequence.charAt(i+1)=='"'){
                minuschars++;
            }
            if(sequence.charAt(i)=='\\' && sequence.charAt(i+1)=='x'){
                minuschars+=3;
                i+=3;
            }
        }
        System.out.println(sequence+" "+sequence.length()+" - "+minuschars+" - "+(sequence.length()-minuschars));
        return minuschars;
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
                result+=this.getCodeMemoryDiff(line.trim());
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
    }

}
