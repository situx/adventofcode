package com.github.situx.aoc2017.day9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Solution to Advent Of Code 2017 Day 9.
 * @author situx
 */
public class Task9 {

    /**
     * Processes the input of groups and garbage data, marked by {} and <> respectively.
     * @param input the input as String
     * @return the total score for all group nestings, the total amount of characters in garbage
     */
    public int[] processInput(String input){
        Integer openbrace=0;
        int[] result=new int[]{0,0};
        Boolean inex=false,ingarbage=false;
        for(int i=0;i<input.length();i++){
            if(inex){
                inex=false;
                continue;
            }
            if(ingarbage && input.charAt(i)!='>' && input.charAt(i)!='!'){
                result[1]++;
            }
            switch (input.charAt(i)){
                case '{':
                    if(!ingarbage) {
                        openbrace++;
                        result[0]+=openbrace;
                    }
                    break;
                case '}':
                    if(!ingarbage && openbrace>0) {
                        openbrace--;
                    }
                    break;
                case '<':
                    if(!ingarbage) {
                        ingarbage = true;
                    }
                    break;
                case '>':
                    if(ingarbage) {
                        ingarbage = false;
                    }
                    break;
                case '!':
                        inex=true;
                    break;
                default:
            }
        }
        return result;
    }


    /**
     * The main method of this project.
     * @param args
     * @throws IOException on file reading errors
     */
    public static void main(String[] args) throws IOException {
        Task9 task9=new Task9();
        String intArray= new String(Files.readAllBytes(Paths.get("aoc2017files/task9_2017.txt")));
        int[] result=task9.processInput(intArray);
        System.out.println("Total Score for all groups: "+result[0]);
        System.out.println("Garbage Characters: "+result[1]);
    }

}
