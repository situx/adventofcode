package com.github.situx.adventofcode2017.day4;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 * Solution to Task 1 of advent calendar.
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
			Set<String> wordset=new TreeSet<String>(Arrays.asList(words));
			if(anagram){
				//System.out.println(wordset);
				Set<String> anagramset=new TreeSet<String>();
				for(String word:words){
					Set<String> perms=getStringPermutation(word);
					perms.remove(word);
					anagramset.addAll(perms);
				}
				wordset.retainAll(anagramset);
				if(wordset.size()>0){
					System.out.println(wordset);
				}
				sum+=wordset.size()>0?0:1;
			}else{
				sum+=wordset.size()==words.length?1:0;
			}
		}
		return sum;
	}
	
	public Set<String> getStringPermutation(String input) {
		Set<String> result = new TreeSet<String>();
		if (input == null) {
			return null;
		} else if (input.isEmpty()) {
			result.add("");
			return result;
		}
 
		char firstChar = input.charAt(0);
		String remainders = input.substring(1);
		Set<String> words = getStringPermutation(remainders);
		for (String newString : words) {
			for (int i = 0; i <= newString.length(); i++) {
				result.add(addChar(newString, firstChar, i));
			}
		}
		return result;
	}
 
	private String addChar(String str, char c, int j) {
		String first = str.substring(0, j);
		String last = str.substring(j);
		return first + c + last;
	}
	
	/**
	 * Main method of this program.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException{
		Task4 task4=new Task4();
		String numString=new String(Files.readAllBytes(Paths.get("aoc2017files/task4_2017.txt")));
		System.out.println("Result: "+task4.scanString(numString,false));
		System.out.println("Result: "+task4.scanString(numString,true));
	}
	
}
