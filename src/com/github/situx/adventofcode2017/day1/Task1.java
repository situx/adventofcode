package com.github.situx.adventofcode2017.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Solution to Task 1 of advent calendar.
 * @author situx
 *
 */
public class Task1 {
	
	/**
	 * Scans a String for character matches using a predefined offset.
	 * @param numString the String to examine
	 * @return the sum
	 */
	public Integer scanString(String numString,Integer offset){
		Character curchar,nextchar;
		Integer sum=0;
		for(int i=0;i<numString.length();i++){
			curchar=numString.charAt(i);
			nextchar=numString.charAt((i+offset)%numString.length());
			if(curchar==nextchar){
				System.out.println("Match "+curchar+" - "+nextchar+" CurrentSum: "+sum);
				sum+=Integer.valueOf(curchar.toString());
			}
		}
		return sum;
	}
	
	
	/**
	 * Main method of this program.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException{
		Task1 task1=new Task1();
		String numString=new String(Files.readAllBytes(Paths.get("task1.txt")));
		System.out.println("Result: "+task1.scanString(numString,1));
		System.out.println("Result: "+task1.scanString(numString,numString.length()/2));
	}
	
}
