package com.github.situx.adventofcode2017.day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Solution to Advent Of Code 2017 Day 5.
 * @author situx
 */
public class Task5 {

	/**
	 * Takes an Integer array and walks over it changing the current position according to the array value at the current position.
	 * The current position value in the array is subsequently either incremented (!evenStanger) or
	 * (evenStranger) incremented when the array value <3 and decremented when the array value >3
	 * @param args the Integer array to process
	 * @param evenStranger indicates a mode change
	 * @return the amount of steps needed to escape the array
	 */
	public Integer jumpOut(Integer[] args,Boolean evenStranger){
		Integer steps=0,position=0;
		while(position<args.length && position>-1){
			if(evenStranger){
				position+=(args[position]>2?args[position]--:args[position]++);
			}else{
				position+=args[position]++;
			}
			steps++;
		}
		return steps;
	}
	
	/**
	 * The main method of this project
	 * @param args 
	 * @throws IOException on file reading error
	 */
	public static void main(String[] args) throws IOException{
		Task5 task5=new Task5();
		Integer[] intArray=Arrays.stream(new String(Files.readAllBytes(Paths.get("aoc2017files/task5_2017.txt"))).split(System.lineSeparator()))
				.map(word -> Integer.valueOf(word.trim())).toArray(Integer[]::new);
		System.out.println("Part1: The processor needs "+task5.jumpOut(intArray,false)+"steps to reach the exit");
		System.out.println("Part2: The processor needs "+task5.jumpOut(intArray,true)+"steps to reach the exit");
	}
	
}
