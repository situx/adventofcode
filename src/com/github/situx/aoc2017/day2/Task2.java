package com.github.situx.day2;

import java.io.*;
import java.util.Arrays;

/**
 * Solves task2 of the adventofcode2017 challenge.
 * @author situx
 */
public class Task2 {

    /**
     * Gets the difference between the maximum value and the minimum value of a tab delimited number file and sums it up to a value checksum.
     * @param filename the filename of the tab delimited numbe file
     * @return the summed up checksum value
     * @throws IOException when the file is not readable
     */
    public Integer getMinMaxDiffCheckSumFromFile(String filename) throws IOException {
        Integer checksum=0,maxval,minval;
        BufferedReader reader=new BufferedReader(new FileReader(new File(filename)));
        String line;
        while((line=reader.readLine())!=null){
            String[] splittedline=line.split("\t");
            maxval=0;
            minval=Integer.MAX_VALUE;
            int[] numbers = Arrays.stream(splittedline).mapToInt(Integer::parseInt).toArray();
            for(int i=0;i<numbers.length;i++){
                Integer curnum=numbers[i];
                if(curnum>maxval){
                    maxval=curnum;
                }
                if(curnum<minval){
                    minval=curnum;
                }
            }
            checksum+=maxval-minval;
        }
        return checksum;
    }

    /**
     * Gets the sum of the divisors of numbers with zero remainder in a tab delimited number file.
     * @param filename the filename of the tab delimited number file
     * @return the summed up checksum value
     * @throws IOException when the file is not readable
     */
    public Integer getDividerCheckSumFromFile(String filename) throws IOException {
        Integer checksum=0;
        BufferedReader reader=new BufferedReader(new FileReader(new File(filename)));
        String line;
        while((line=reader.readLine())!=null){
            String[] splittedline=line.split("\t");
            int[] numbers = Arrays.stream(splittedline).mapToInt(Integer::parseInt).toArray();
            for(int i=0;i<numbers.length;i++){
                Integer curnum=numbers[i];
                if(i<numbers.length){
                    for(int j=0;j<numbers.length;j++){
                        if(i!=j){
                            if(curnum%numbers[j]==0){
                                checksum+=curnum/numbers[j];
                            }
                        }
                    }
                }
            }
        }
        return checksum;
    }

    /**
     * The main method of this program.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Task2 task2=new Task2();
        System.out.println(task2.getMinMaxDiffCheckSumFromFile("task2.txt"));
        System.out.println(task2.getDividerCheckSumFromFile("task2.txt"));
    }

}
