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
	 * Scans a String for reoccuring characters and if two characters match up adds the Integer value of the first one to a sum.
	 * @param numString the String to examine
	 * @return the sum
	 */
	public Integer scanString(String numString){
		Character curchar,nextchar;
		Integer sum=0;
			for(int i=0;i<numString.length()-1;i++){
				curchar=numString.charAt(i);
				nextchar=numString.charAt(i+1);
				if(curchar==nextchar){
					System.out.println("Match "+curchar+" - "+nextchar+" CurrentSum: "+sum);
					sum+=Integer.valueOf(curchar.toString());
				}
		}
		if(numString.charAt(0)==numString.charAt(numString.length()-1)){
			sum+=Integer.valueOf(numString.charAt(0)+"");
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
		System.out.println("Result: "+task1.scanString(numString));
	}
	
}
