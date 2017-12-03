package com.github.situx.aoc2015.day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import java.util.TreeMap;

/**
 * Solution to Advent Of Code 2015 Day 3.
 */
public class Task3 {

    /**
     * Counts the number of positions n to which n actors move on a position grid with defined moves up/down/left/right (^v<>).
     * @param moveString the String containing the moves to be done
     * @param helpers the amount of actors to move on the grid
     * @return the amount of places visited as Integer
     */
    public Integer countMoves(String moveString,Integer helpers){
        int[] updown=new int[helpers];
        int[] leftright=new int[helpers];
        java.util.Map<String,Integer> wasthere=new TreeMap<String,Integer>();
        wasthere.put("0_0",0);
        for(int i=0;i<moveString.length();i++){
            Character curchar=moveString.charAt(i);
            switch (curchar){
                case '>': leftright[i%helpers]+=1;
                    break;
                case '<': leftright[i%helpers]-=1;
                    break;
                case 'v':updown[i%helpers]-=1;
                    break;
                case '^':updown[i%helpers]+=1;
                    break;
                default:
            }
            String curpos=updown[i%helpers]+"_"+leftright[i%helpers];
            if(!wasthere.containsKey(curpos)){
                wasthere.put(curpos,0);
            }
            wasthere.put(curpos,wasthere.get(curpos)+1);
        }
        return wasthere.size();
    }

    /**
     * The main method of this project.
     * @param args
     * @throws IOException when the file fails to read
     */
    public static void main(String[] args) throws IOException {
        Task3 task3=new Task3();
        System.out.println("Santa Claus visits "+task3.countMoves(new String(Files.readAllBytes(Paths.get("aoc2015files/task3_2015.txt"))),1)+" houses at least once.");
        System.out.println("Santa Claus and his robot visit "+task3.countMoves(new String(Files.readAllBytes(Paths.get("aoc2015files/task3_2015.txt"))),2)+" houses at least once.");
    }

}
