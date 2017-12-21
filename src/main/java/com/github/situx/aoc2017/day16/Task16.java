package com.github.situx.aoc2017.day16;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Solution to Advent Of Code 2017 Day 16.
 * @author situx
 */
public class Task16 {

	/**
	 * Spins a number of characters of a String to its front.
	 * @param number the number of characters to spin
	 * @param input the input as a StringBuilder
	 * @return the result as StringBuilder
	 */
	public StringBuilder spin(Integer number,StringBuilder input){
		return new StringBuilder(input.substring(input.length()-number)+input.substring(0,input.length()-number));
	}
	
	/**
	 * Exchanges two characters in the current String at position pos1 and pos2
	 * @param pos1 the first position
	 * @param pos2 the second position
	 * @param input the input as a StringBuilder
	 * @return the result as StringBuilder
	 */
	public StringBuilder exchange(Integer pos1,Integer pos2,StringBuilder input){
		Character char1=input.charAt(pos1);
		Character char2=input.charAt(pos2);
		input.setCharAt(pos1, char2);
		input.setCharAt(pos2, char1);
		return input;
	}
	
	
	/**
	 * Exchanges two characters in the current String assuming the String only contains unique characters.
	 * @param char1 the first character
	 * @param char2 the second character
	 * @param input the input as a StringBuilder
	 * @return the result as StringBuilder
	 */
	public StringBuilder exchange(String char1,String char2,StringBuilder input){
		Integer pos1=input.indexOf(char1);
		Integer pos2=input.indexOf(char2);
		input.setCharAt(pos1, char2.toCharArray()[0]);
		input.setCharAt(pos2, char1.toCharArray()[0]);
		return input;
	}
	
	/**
	 * Lets an input string dance according to the input instructions given.
	 * @param input the input instructions as String array
	 * @param workon the String as StringBuilder
	 * @return the result as StringBuilder
	 */
	public StringBuilder dance(String[] input,StringBuilder workon){
		for(String line:input){
			if(!line.contains("/")){
				workon=this.spin(Integer.valueOf(line.substring(1)),workon);
			}else if(line.contains("/") && line.substring(line.length()-1).matches("[0-9]")){
				workon=this.exchange(Integer.valueOf(line.substring(1, line.indexOf('/'))), Integer.valueOf(line.substring(line.indexOf("/")+1)), workon);
			}else{
				workon=this.exchange(line.substring(1, line.indexOf('/')), line.substring(line.indexOf("/")+1), workon);
			}
		}
		return workon;
	}
	
	/**
	 * Lets a String dance a certain amount of times.
	 * If it detects a repeating pattern during this process it
	 * calculates the correct value at "times".
	 * @param input the input instructions as String array
	 * @param workon the String to work on
	 * @param times the amount of times to run the instructions
	 * @return the result as StringBuilder
	 */
	public StringBuilder billionDance(String[] input, StringBuilder workon,Integer times){
		Set<String> hasSeen=new TreeSet<String>();
		Map<Integer,String> hasSeenMap=new TreeMap<Integer,String>();
		for(int i=0;i<times;i++){
			workon=this.dance(input, workon);
			if(hasSeen.contains(workon.toString())){
				return new StringBuilder(hasSeenMap.get((times%i)-1));
			}else{
				hasSeen.add(workon.toString());
				hasSeenMap.put(i, workon.toString());
			}
		}
		return workon;
	}
	
    /**
     * The main method of this project.
     * @param args
     * @throws IOException on file reading errors
     */
    public static void main(String[] args) throws IOException {
        Task16 task16=new Task16();
        String[] intArray= new String(Files.readAllBytes(Paths.get("aoc2017files/task16_2017.txt"))).split(",");
        System.out.println("Danced String: "+task16.dance(intArray,new StringBuilder("abcdefghijklmnop")));
        System.out.println("Billion Danced String: "+task16.billionDance(intArray,new StringBuilder("abcdefghijklmnop"),1000000000));
    }
	
}
