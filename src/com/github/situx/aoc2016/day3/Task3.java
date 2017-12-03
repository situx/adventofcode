package com.github.situx.aoc2016.day3;

import java.io.*;
import java.util.Arrays;

/**
 * Solution to Advent Of Code 2016 Day3.
 * @author situx
 */
public class Task3 {

    /**
     * Checks the amount of possible triangles by taking into account columns of n points and
     * @param filePath the path to the file containing triangles definitions.
     * @return the amount of possible triangles as Integer
     * @throws IOException on file reading error
     */
    public Integer checkAmountOfPossibleTrianglesInCols(String filePath) throws IOException {
        BufferedReader reader=new BufferedReader(new FileReader(new File(filePath)));
        String line;
        Integer sum=0,rowcounter=0,amountOfRows=3;
        int[][] colarray=new int[3][3];
        while((line=reader.readLine())!=null){
            line=line.trim();
            int[] sides = Arrays.stream(line.split("[\\s]+")).mapToInt(Integer::parseInt).toArray();
            colarray[0][rowcounter]=sides[0];
            colarray[1][rowcounter]=sides[1];
            colarray[2][rowcounter]=sides[2];
            rowcounter++;
            if(rowcounter.equals(amountOfRows)){
                for(int i=0;i<amountOfRows;i++){
                    if(isPossibleTriangle(colarray[i][0],colarray[i][1],colarray[i][2])){
                        sum++;
                    }
                }
                rowcounter=0;
            }
        }
        return sum;
    }

    /**
     * Checks the amount of possible triangles from a file defining the edge length of triangles in three tuples.
     * @param filePath the path to the file containing triangles definitions.
     * @return the sum of valid triangles
     * @throws IOException on file reading errors
     */
    public Integer checkAmountOfPossibleTrianglesInRows(String filePath) throws IOException {
        BufferedReader reader=new BufferedReader(new FileReader(new File(filePath)));
        String line;
        Integer sum=0;
        while((line=reader.readLine())!=null){
            line=line.trim();
            int[] sides = Arrays.stream(line.split("[\\s]+")).mapToInt(Integer::parseInt).toArray();
            if(isPossibleTriangle(sides[0],sides[1],sides[2])){
                sum++;
            }
        }
        return sum;
    }

    /**
     * Checks if a triangle defined by a tuple of its edge length is valid.
     * @param side1 the first edge
     * @param side2 the second edge
     * @param side3 the third edge
     * @return true if valid, false if invalid
     */
    public Boolean isPossibleTriangle(Integer side1,Integer side2,Integer side3){
        Integer sum12=side1+side2;
        Integer sum13=side1+side3;
        Integer sum23=side2+side3;
        if(sum12>side3 && sum13>side2 && sum23>side1)
            return true;
        return false;
    }

    /**
     * The main method of this project.
     * @param args
     * @throws IOException on file reading errors
     */
    public static void main(String[] args) throws IOException {
        Task3 task3=new Task3();
        System.out.println("There are "+task3.checkAmountOfPossibleTrianglesInRows("aoc2016files/task3_2016.txt")+" possible triangles in row notation");
        System.out.println("There are "+task3.checkAmountOfPossibleTrianglesInCols("aoc2016files/task3_2016.txt")+" possible triangles in col notation");
    }
}

