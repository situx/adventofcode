package com.github.situx.aoc2015.day13;

import com.google.common.collect.Collections2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Solution to Advent Of Code 2015 Day 13.
 * @author situx
 */
public class Task13 {
    /**
     * Reads the String representation of the graph into a map structure.
     * @param input the String representation
     * @return the map structure
     */
    public Map<String,Map<String,Integer>> readStringToMap(String[] input){
        Map<String,Map<String,Integer>> result=new TreeMap<>();
        for(String in:input){
            String from=in.substring(0,in.indexOf(' ')).trim();
            String to=in.substring(in.lastIndexOf(' ')).replace(".","").trim();
            String numberString=in.substring(in.indexOf(' ',in.indexOf(' ')+1),in.indexOf("happiness")).trim();
            Integer number=0;
            if(numberString.contains("gain")){
                number=Integer.valueOf(numberString.replace("gain","").trim());
            }else{
                number=Integer.valueOf(numberString.replace("lose","").trim())*(-1);
            }
            if(!result.containsKey(from)){
                result.put(from,new TreeMap<>());
            }
            result.get(from).put(to,number);
        }
        return result;
    }

    /**
     * Adds myself to the round table with connections of value 0 to all other participants.
     * @param map the map to modify
     * @return the modified map
     */
    public Map<String,Map<String,Integer>> addMySelf(Map<String,Map<String,Integer>> map){
        map.put("me",new TreeMap<>());
        for(String key:map.keySet()){
            map.get("me").put(key,0);
            map.get(key).put("me",0);
        }
        return map;
    }

    /**
     * Generates permutations of the keyset of the given map and calculates the best order to sit.
     * @param map the map containing the relationships
     * @param addMySelf indicates if myself should be added to the map
     * @return the total change in happyness of all the participants
     */
    public Integer generatePermutations(Map<String,Map<String,Integer>> map,Boolean addMySelf){
        if(addMySelf)
            map=this.addMySelf(map);
        Collection<List<String>> perms=Collections2.orderedPermutations(map.keySet());
        Integer maxPermScore=0;
        for(List<String> perm:perms){
            Integer permscore=0;
            permscore+=map.get(perm.get(0)).get(perm.get(1));
            permscore+=map.get(perm.get(0)).get(perm.get(perm.size()-1));
            for(int i=1;i<perm.size();i++){
                permscore+=map.get(perm.get(i%perm.size())).get(perm.get((i+1)%perm.size()));
                permscore+=map.get(perm.get(i%perm.size())).get(perm.get((i-1)%perm.size()));
            }
            if(permscore>maxPermScore){
                maxPermScore=permscore;
            }
        }
        return maxPermScore;
    }


    /**
     * The main method of this project
     * @param args
     * @throws IOException on file reading errors.
     */
    public static void main(String[] args) throws IOException {
        Task13 task13 =new Task13();
        Map<String,Map<String,Integer>> intArray = task13.readStringToMap(new String(Files.readAllBytes(Paths.get("aoc2015files/task13_2015.txt"))).split(System.lineSeparator()));
        System.out.println(task13.generatePermutations(intArray,false)+" total change in happiness");
        System.out.println(task13.generatePermutations(intArray,true)+" total change in happiness including myself");
    }
}
