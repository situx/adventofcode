package com.github.situx.aoc2017.day12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map;
import java.util.Set;

import com.github.situx.aoc2016.day4.Task4;

/**
 * Solution to Advent Of Code 2017 Day 12.
 * @author situx
 */
public class Task12 {

	/**The biggest node in the graph.*/
	Integer maxNode=0;
	
	/**
	 * Creates a graph out of the given input.
	 * @param lines the lines of the input
	 * @return the graph
	 */
	public Map<Integer,Set<Integer>> getMappings(String[] lines){
		Map<Integer,Set<Integer>> linkset=new TreeMap<>();
		for(String line:lines){
			Integer from=Integer.valueOf(line.split("<->")[0].trim());
			if(from>maxNode){
				maxNode=from;
			}
			for(String to:line.split("<->")[1].split(",")){
				if(!linkset.containsKey(from)){
					linkset.put(from, new TreeSet<>());
				}
				linkset.get(from).add(Integer.valueOf(to.trim()));
			}
		}
		System.out.println(linkset);
		return linkset;
	}
	
	/**
	 * Recursive function to get all members of a clique in a graph starting at a given point.
	 * @param start the starting point
	 * @param linkset the graph
	 * @param resultset the member set
	 * @return the member set
	 */
	public Set<Integer> walkOverMappings(Integer start,Map<Integer,Set<Integer>> linkset,Set<Integer> resultset){
		for(Integer go:linkset.get(start)){
			if(!resultset.contains(go)){
				resultset.add(go);
				resultset.addAll(walkOverMappings(go, linkset,resultset));
			}

		}
		return resultset;
	}
	
	/**
	 * Checks how many groups are in the current graph.
	 * @param linkset the graph
	 * @return the number of groups
	 */
	public Integer findNumberOfGroups(Map<Integer,Set<Integer>> linkset){
		Set<Integer> results=new TreeSet<Integer>();
		Integer groups=0;
		for(int i=0;i<maxNode;i++){
			if(!results.contains(i)){
				results.addAll(this.walkOverMappings(i, linkset, new TreeSet<Integer>()));
				groups++;
			}
		}
		return groups;
	}
	
    /**
     * The main method of this project.
     * @param args
     * @throws IOException on file reading errors
     */
    public static void main(String[] args) throws IOException {
        Task12 task12=new Task12();
        String[] intArray= new String(Files.readAllBytes(Paths.get("aoc2017files/task12_2017.txt"))).split(System.lineSeparator());
        Map<Integer, Set<Integer>> linkset=task12.getMappings(intArray);
        System.out.println("Amount of nodes reachable from 0: "+task12.walkOverMappings(0,linkset,new TreeSet<>()).size());
        System.out.println("Amount of cliques in the graph: "+task12.findNumberOfGroups(linkset));

    }
}
