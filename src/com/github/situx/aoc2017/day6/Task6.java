package com.github.situx.aoc2017.day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Solution to Advent Of Code 2017 Day 6.
 * @author situx
 */
public class Task6 {

    Set<String> states=new HashSet<String>();

    public Integer distribute(Integer[] array,Integer cycle){
        System.out.println(Arrays.toString(array));
        Integer maxPosition=this.getMaxPosition(array);
        Integer remainder=array[maxPosition]%array.length;
        Integer dist=array[maxPosition]/array.length;
        System.out.println(maxPosition+" - "+dist+" - "+remainder);
        for(int i=0;i<array.length;i++){
            if(i!=maxPosition){
                array[i]+=dist;
            }else{
                array[i]=remainder;
            }
        }
        String state=Arrays.toString(array);
        System.out.println(state);
        if(states.contains(state)){
            return cycle;
        }
        states.add(Arrays.toString(array));
        return distribute(array,++cycle);

    }

    public Integer getMaxPosition(Integer[] array){
        Integer max=0;
        for(int i=0;i<array.length;i++){
            max = array[i] > array[max] ? i : max;
        }
        return max;
    }

    public static void main(String[] args) throws IOException {
        Task6 task6=new Task6();
        Integer[] intArray= Arrays.stream(new String(Files.readAllBytes(Paths.get("aoc2017files/task6_2017.txt"))).split("\t")).map(word -> Integer.valueOf(word.trim())).toArray(Integer[]::new);
        System.out.println(task6.distribute(intArray,0));
    }
}
