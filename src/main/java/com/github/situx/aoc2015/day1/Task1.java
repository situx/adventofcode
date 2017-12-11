package com.github.situx.aoc2015.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Solution for task1 of the Advent Of Code Calendar 2015.
 * @author situx
 */
public class Task1 {

    /**
     * Checks for the end position of a String in which an opening bracket
     * determines on position forward and a closing brackets signifies one
     * position backward.
     * @param input an input String of brackets
     * @return the end position as Integer
     */
    public Integer countOpeningAndClosedParanthesis(String input){
        Integer opening=0,closing=0;
        for(int i=0;i<input.length();i++){
            Character curchar=input.charAt(i);
            if(curchar=='('){
                opening++;
            }else if(curchar==')'){
                closing++;
            }
        }
        return opening-closing;
    }

    /**
     * Checks for the first negative position in an input String of brackets.
     * The first negative position is defined as the first position in which
     * the amount of closing brackets exceeds the amount of opening brackets.
     * @param input an input String of brackets
     * @return the first negative position as Integer
     */
    public Integer findFirstNegativePosition(String input){
        Integer opening=0,closing=0;
        for(int i=0;i<input.length();i++){
            Character curchar=input.charAt(i);
            if(curchar=='('){
                opening++;
            }else if(curchar==')'){
                closing++;
            }
            if(closing>opening){
                return i+1;
            }
        }
        return -1;
    }

    /**
     * The main method of this project executing the code.
     * @param args
     * @throws IOException on file reading errors
     */
    public static void main(String[] args) throws IOException {
        Task1 task1=new Task1();
        String fileString=new String(Files.readAllBytes(Paths.get("aoc2015files/task1_2015.txt")));
        System.out.println(task1.countOpeningAndClosedParanthesis(fileString));
        System.out.println(task1.findFirstNegativePosition(fileString));
    }
}
