package com.github.situx.aoc2015.day16;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Solution to Advent Of Code 2016 Day 16.
 * @author situx
 */
public class Task16 {
	
	/**
	 * Finds the aunt sue which is closest to the specifications on the gift wrap.
	 * @param sueToFind the aunt sue to find
	 * @param sues all available aunt sues
	 * @return the number of the correct aunt sue
	 */
	public Integer findSue(Map<String,Integer> sueToFind,List<Map<String,Integer>> sues){
		Map<Integer,Double> similarities=new TreeMap<Integer,Double>();
		int i=0;
		for(Map<String,Integer> cursue:sues){
			similarities.put(i, 0.);
			for(String key:cursue.keySet()){
				if(sueToFind.containsKey(key) && sueToFind.get(key).equals(cursue.get(key))){
					similarities.put(i, similarities.get(i)+1);
				}
			}
			similarities.put(i, similarities.get(i)/sueToFind.size());
			i++;
		}
		Integer maxKey=-1;
		Double maxVal=0.;
		for(Integer key:similarities.keySet()){
			if(maxVal<similarities.get(key)){
				maxVal=similarities.get(key);
				maxKey=key;
			}
		}
		return maxKey+1;
	}

	
    /**
     * The main method of this project.
     * @param args
     * @throws IOException on file reading errors
     */
    public static void main(String[] args) throws IOException {
        Task16 task16=new Task16();
        String[] intArray= Arrays.stream(new String(Files.readAllBytes(Paths.get("aoc2015files/task16_2015.txt"))).split(System.lineSeparator())).toArray(String[]::new);
        List<Map<String,Integer>> sues=new LinkedList<>();
        for(String line:intArray){
        	line=line.substring(line.indexOf(':')+1);
        	sues.add(new TreeMap<String,Integer>());
        	for(String key:line.split(",")){
        		sues.get(sues.size()-1).put(key.substring(0, key.lastIndexOf(':')).trim(), Integer.valueOf(key.substring(key.lastIndexOf(':')+1).trim()));	
        	}
        }
        Map<String,Integer> sueToFind=new TreeMap<String,Integer>();
        sueToFind.put("children", 3);
        sueToFind.put("cats", 7);
        sueToFind.put("samoyeds", 2);
        sueToFind.put("pomeranians", 2);
        sueToFind.put("akitas", 0);
        sueToFind.put("vizslas", 0);
        sueToFind.put("goldfish", 5);
        sueToFind.put("trees", 3);
        sueToFind.put("cars", 2);
        sueToFind.put("perfumes", 1);
        System.out.println(task16.findSue(sueToFind, sues));
    }
	
}
