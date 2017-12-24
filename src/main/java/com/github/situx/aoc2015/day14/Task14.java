package com.github.situx.aoc2015.day14;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Solution to Advent Of Code 2015 Day14.
 * @author situx
 */
public class Task14 {

    /**
     * Gets the distance of the winning reindeer at a given time
     * @param deers the reindeers to watch
     * @param time the time to consider
     * @return the distance as first element of an Integer array, the reindeer number as second element
     */
    public int[] distanceOfWinningReindeer(List<Reindeer> deers,Integer time){
        int[] maxDistance=new int[2];
        int j=0;
        for(Reindeer deer:deers){
            Integer distance=calculateDistance(deer,time);
            if(distance>maxDistance[0]){
                maxDistance[0]=distance;
                maxDistance[1]=j;
            }
            j++;
        }
        return maxDistance;
    }

    /**
     * Gets the reindeer status according to Santas second scoring system.
     * @param time the time to consider
     * @param deers the reindeers
     * @return the maximum points according to the new grading system
     */
    public Integer reindeerStatus(List<Reindeer> deers,Integer time){
        int[] distances=new int[deers.size()];
        for(int i=0;i<time;i++){
            int[] res=this.distanceOfWinningReindeer(deers,i);
            System.out.println(Arrays.toString(res));
            distances[res[1]]++;
        }
        Arrays.sort(distances);
        System.out.println(Arrays.toString(distances)+" - "+time);
        return distances[distances.length-1];
    }

    /**
     * Parses the reindeer specifications given as String to a class Reindeer.
     * @param reindeers the reindeer information as String
     * @return a list of Reindeers
     */
    public List<Reindeer> parseReindeers(String[] reindeers){
        List<Reindeer> deers=new LinkedList<Reindeer>();
        for(String deer:reindeers){
            Integer speed=Integer.valueOf(deer.substring(deer.indexOf("fly")+4,deer.indexOf("km/s")).trim());
            Integer speedduration=Integer.valueOf(deer.substring(deer.indexOf("for")+4,deer.indexOf("seconds,")).trim());
            Integer restduration=Integer.valueOf(deer.substring(deer.lastIndexOf("for")+4,deer.lastIndexOf("seconds")).trim());
            deers.add(new Reindeer(restduration,speedduration,speed));
        }
        return deers;
    }

    /**
     * Calculates the distance travelled by a reindeer considering its speed and resttimes.
     * @param deer the reindeer to consider
     * @param seconds the amount of seconds
     * @return the distance as Integer
     */
    public Integer calculateDistance(Reindeer deer,Integer seconds){
        Integer distance=0;
        Boolean speedOrRest=true;
        while(seconds>0){
            if(speedOrRest) {
                if(seconds<deer.speedtime){
                    distance+=deer.speed*seconds;
                }else{
                    distance += deer.speed * deer.speedtime;
                }
                seconds-=deer.speedtime;
            }else{
                seconds-=deer.resttime;
            }
            speedOrRest=!speedOrRest;
        }
        return distance;
    }

    /**
     * The main method of this project.
     * @param args
     * @throws IOException on file reading errors
     */
    public static void main(String[] args) throws IOException {
        Task14 task14=new Task14();
        List<Reindeer> intArray= task14.parseReindeers(new String(Files.readAllBytes(Paths.get("aoc2015files/task14_2015.txt"))).split(System.lineSeparator()));
        System.out.println("Distance of winning reindeer: "+task14.distanceOfWinningReindeer(intArray,2503)[0]);
        System.out.println("Distance of winning reindeer: "+task14.reindeerStatus(intArray,2503));
    }
}
