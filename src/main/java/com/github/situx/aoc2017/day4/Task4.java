package com.github.situx.aoc2017.day4;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 * Solution to Task 4 of Advent Of Code 2017.
 * @author situx
 *
 */
public class Task4 {
	
	/**
	 * Scans a String for character matches using a predefined offset.
	 * @param numString the String to examine
	 * @return the sum
	 */
	public Integer scanString(String numString,Boolean anagram){
		Integer sum=0;
		String[] lines=numString.split(System.lineSeparator());
		for(String line:lines){
			String[] words=line.split("\\s");
			Set<String> wordset=new TreeSet<String>();
			if(anagram){
				for(String word:words){
					char[] chars = word.toCharArray();
					Arrays.sort(chars);
					wordset.add(new String(chars));
				}
			}else{
				wordset.addAll(Arrays.asList(words));
			}
			sum+=wordset.size()==words.length?1:0;
		}
		return sum;
	}
	
	/**
	 * Main method of this program.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException{
		Task4 task4=new Task4();
		String numString=new String(Files.readAllBytes(Paths.get("aoc2017files/task4_2017.txt")));
		System.out.println("Valid Passphrases without duplicate words: "+task4.scanString(numString,false));
		System.out.println("Valid Passphrases without anagrams: "+task4.scanString(numString,true));
	}
	
}
