package com.github.situx.aoc2015.day2;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * Solution to Advent Of Code 2015 Day 2.
 * @author situx
 */
public class Task2 {

    enum select{Paper,Ribbon};

    /**
     * Gets the paper needed to wrap gifts for the elves for one package.
     * @param length the length of the box
     * @param width the width of the box
     * @param height the height of the box
     * @return the paper size in feet
     */
    public Integer getGiftSizeForWrappingPaper(Integer length,Integer width,Integer height){
        Integer lw=length*width;
        Integer wh=width*height;
        Integer hl=height*length;
        Integer size1=2*lw+2*wh+2*hl;
        if(lw<=wh && lw<=hl){
            return size1+lw;
        }
        if(wh<=lw && wh<=hl){
            return size1+wh;
        }
        if(hl<=wh && hl<=lw){
            return size1+hl;
        }
        return -1;
    }

    /**
     * Gets the ribbon needed to wrap gifts for the elves for one package.
     * @param length the length of the box
     * @param width the width of the box
     * @param height the height of the box
     * @return the ribbon size in feet
     */
    public Integer getGiftSizeForRibbon(Integer length,Integer width,Integer height){
        Integer bow=length*width*height;
        Integer[] measures=new Integer[]{length,width,height};
        Arrays.sort(measures);
        Integer present=2*measures[0]+2*measures[1];
        return present+bow;
    }

    /**
     * Processes the measurement file for the gift boxes and applies the chosen function.
     * @param filepath the filepath to the input file
     * @param option an enumeration to choose ribbon or paper size
     * @return the respective result as Integer
     * @throws IOException on file reading error
     */
    public Integer processGiftMeasuresFromFile(String filepath,Enum option) throws IOException {
        BufferedReader reader=new BufferedReader(new FileReader(new File(filepath)));
        String line;
        Integer sum=0;
        while((line=reader.readLine())!=null){
            int[] measures= Arrays.stream(line.split("x")).mapToInt(Integer::parseInt).toArray();
            if(option==select.Paper){
                sum+=this.getGiftSizeForWrappingPaper(measures[0],measures[1],measures[2]);
            }else{
                Integer temp=this.getGiftSizeForRibbon(measures[0],measures[1],measures[2]);
                sum+=temp;
            }
        }
        return sum;
    }

    /**
     * The main method of this program.
     * @param args
     * @throws IOException on file reading errors.
     */
    public static void main(String[] args) throws IOException {
        Task2 task2=new Task2();
        System.out.println("The elves require "+task2.processGiftMeasuresFromFile("aoc2015files/task2_2015.txt",select.Paper)+" feet of paper");
        System.out.println("The elves require "+task2.processGiftMeasuresFromFile("aoc2015files/task2_2015.txt",select.Ribbon)+" feet of ribbon");

    }
}
