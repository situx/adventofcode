package com.github.situx.aoc2017.day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Solution to Advent Of Code 2017 Day 6.
 * @author situx
 */
public class Task6 {
	/**
	 * A map of array state as String to cycle occurance as Integer.
	 */
    Map<String,Integer> states=new HashMap<String,Integer>();

    /**
     * Distributes the highest value in an int array evenly among the remaining values 
     * until a reoccuring state is observed.
     * @param array the array to investigate
     * @return an Integer array of the amount of cycles until the first reoccuring state
     * and the amount of cycles needed from this state until its reoccurance
     */
    public Integer[] distribute(Integer[] array){
        Integer cycle=0;
        states.clear();
        String state=Arrays.toString(array);
        while(!states.keySet().contains(state)){
            states.put(Arrays.toString(array),cycle);
            Integer maxPosition=this.getMaxPosition(array);
            Integer maxAmount=array[maxPosition];
            array[maxPosition]=0;
            Integer curpos=(maxPosition+1)%array.length;
            while(maxAmount>0){
            	array[curpos]++;
            	maxAmount--;
                curpos=(curpos+1)%array.length;
            }
        	state=Arrays.toString(array);
        	cycle++;
        }
        return new Integer[]{cycle,(cycle-states.get(state))};
    }

    /**
     * Gets the position of the maximum value in an Integer array.
     * @param array the array to investigate
     * @return the position of the maximum value as Integer
     */
    public Integer getMaxPosition(Integer[] array){
        Integer max=0;
        for(int i=0;i<array.length;i++){
            max = array[i] > array[max] ? i : max;
        }
        return max;
    }

    /**
     * The main method of this project.
     * @param args
     * @throws IOException on file reading errors
     */
    public static void main(String[] args) throws IOException {
        Task6 task6=new Task6();
        Integer[] intArray= Arrays.stream(new String(Files.readAllBytes(Paths.get("aoc2017files/task6_2017.txt"))).split("\t")).map(word -> Integer.valueOf(word.trim())).toArray(Integer[]::new);
        System.out.println("Cycles until reoccurance of the same state: "+task6.distribute(intArray)[0]);
        System.out.println("Infinite Loop Cycle: "+task6.distribute(intArray)[1]);
    }
}
