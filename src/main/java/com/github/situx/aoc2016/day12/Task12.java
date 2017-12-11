package com.github.situx.aoc2016.day12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

/**
 * Solution to Advent Of Code 2016 Day 12.
 * @author situx
 */
public class Task12 {

	/**
	 * Processes assembunny code and implements the commands for copy, jump not zero, inc and dec.
	 * @param lines the assembly code lines to process
	 * @param initializeC indicates whether or not to initialize register c with 1
	 * @return the value of register a after processing
	 */
	public Integer processAssembly(String[] lines,Boolean initializeC){
		Map<String,Integer> nameToRegister=new TreeMap<String,Integer>();
		if(initializeC)
			nameToRegister.put("c", 1);
		for(int i=0;i<lines.length;){
			String line=lines[i];
			Integer secondstart=line.indexOf(" ",line.indexOf(" ")+1);
			String to=null;
			String from=null;
			switch(line.substring(0, line.indexOf(" "))){
			case "cpy":
				from=line.substring(line.indexOf(" ")+1,secondstart).trim();
				to=line.substring(secondstart,line.length()).trim();
				if(from.matches("[0-9]+")){
					nameToRegister.put(to, Integer.valueOf(from));
				}else{
					nameToRegister.put(to, nameToRegister.get(from));
				}
				break;
			case "jnz":
				from=line.substring(line.indexOf(" ")+1,secondstart).trim();
				to=line.substring(secondstart,line.length()).trim();
				if(from.matches("[0-9]+") && Integer.valueOf(from)!=0){
					i+=Integer.valueOf(to);
					continue;
				}
				if(nameToRegister.containsKey(from) && nameToRegister.get(from)!=0){
					i+=Integer.valueOf(to);
					continue;
				}
				break;
			case "inc":
				from=line.substring(line.indexOf(" ")+1,line.length()).trim();
				nameToRegister.put(from, nameToRegister.get(from)+1);
				break;
			case "dec":
				from=line.substring(line.indexOf(" ")+1,line.length()).trim();
				nameToRegister.put(from, nameToRegister.get(from)-1);
				break;
			}
			i++;
		}
		return nameToRegister.get("a");
	}
	
	
    /**
     * The main method of this project.
     * @param args
     * @throws IOException on file reading errors
     */
    public static void main(String[] args) throws IOException {
        Task12 task12=new Task12();
        String[] intArray= new String(Files.readAllBytes(Paths.get("aoc2016files/task12_2016.txt")))
        		.split(System.lineSeparator());
        System.out.println("Value in register a: "+task12.processAssembly(intArray,false));
        System.out.println("Value in register a after initializing c with 1: "+task12.processAssembly(intArray,true));
    }
}
