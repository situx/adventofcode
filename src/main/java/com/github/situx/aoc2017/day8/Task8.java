package com.github.situx.aoc2017.day8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

/**
 * Solution to Advent Of Code 2017 Day 8.
 * @author situx
 */
public class Task8 {

    /**
     * Processes assembler code and implements the commands for inc and dec with conditions.
     * @param lines the assembly code lines to process
     * @return the largest value of any register at the end, the value of the largest register in process
     */
    public Integer[] processAssembly(String[] lines){
        Map<String,Integer> nameToRegister=new TreeMap<String,Integer>();
        Integer maxValue=0;
        for (String line : lines) {
            Integer afterif = line.indexOf("if ") + 3;
            Integer operand = line.indexOf(" ", afterif);
            Integer commandend = line.indexOf(" ", line.indexOf(" ") + 1);
            Integer newvalue = 0;
            String from = line.substring(0, line.indexOf(" ")).trim();
            String command = line.substring(line.indexOf(" ") + 1, commandend).trim();
            Integer value = Integer.valueOf(line.substring(commandend, line.indexOf(" ", commandend + 1)).trim());
            String iffirstoperand = line.substring(afterif, line.indexOf(" ", afterif)).trim();
            String ifoperand = line.substring(operand, line.indexOf(" ", operand + 1)).trim();
            Integer ifsecondoperand = Integer.valueOf(line.substring(line.indexOf(" ", operand + 1) + 1, line.length()).trim());
            if (!nameToRegister.containsKey(iffirstoperand)) {
                nameToRegister.put(iffirstoperand, 0);
            }
            if (!nameToRegister.containsKey(from)) {
                nameToRegister.put(from, 0);
            }
            switch (ifoperand) {
                case ">":
                    if (nameToRegister.get(iffirstoperand) > ifsecondoperand) {
                        newvalue = this.incordec(command.equals("inc"), from, nameToRegister, value);
                    }
                    break;
                case "<":
                    if (nameToRegister.get(iffirstoperand) < ifsecondoperand) {
                        newvalue = this.incordec(command.equals("inc"), from, nameToRegister, value);
                    }
                    break;
                case "<=":
                    if (nameToRegister.get(iffirstoperand) <= ifsecondoperand) {
                        newvalue = this.incordec(command.equals("inc"), from, nameToRegister, value);
                    }
                    break;
                case ">=":
                    if (nameToRegister.get(iffirstoperand) >= ifsecondoperand) {
                        newvalue = this.incordec(command.equals("inc"), from, nameToRegister, value);
                    }
                    break;
                case "!=":
                    if (!nameToRegister.get(iffirstoperand).equals(ifsecondoperand)) {
                        newvalue = this.incordec(command.equals("inc"), from, nameToRegister, value);
                    }
                    break;
                case "==":
                    if (nameToRegister.get(iffirstoperand).equals(ifsecondoperand)) {
                        newvalue = this.incordec(command.equals("inc"), from, nameToRegister, value);
                    }
                    break;
                default:
            }
            if (newvalue > maxValue) {
                maxValue = newvalue;
            }
        }
        Integer maxVal=0;
        for(Integer val:nameToRegister.values()){
            if(maxVal<val){
                maxVal=val;
            }
        }
        return new Integer[]{maxVal,maxValue};
    }

    /**
     * Increases or decreases a register according to the value given, returns the new value.
     * @param incOrDec indicates if increasing or decreasing should happen
     * @param from the register to increase/decrease
     * @param nameToRegister the register map
     * @param value the value to increase/decrease
     * @return the register value
     */
    private Integer incordec(Boolean incOrDec, String from, Map<String, Integer> nameToRegister, Integer value){
        Integer newvalue;
        if(incOrDec) {
            newvalue=(nameToRegister.get(from)+value);
            nameToRegister.put(from, newvalue);
        }else {
            newvalue=(nameToRegister.get(from)-value);
            nameToRegister.put(from, newvalue);
        }
        return newvalue;
    }

    /**
     * The main method of this project.
     * @param args
     * @throws IOException on file reading errors
     */
    public static void main(String[] args) throws IOException {
        Task8 task8=new Task8();
        String[] intArray= new String(Files.readAllBytes(Paths.get("aoc2017files/task8_2017.txt")))
                .split(System.lineSeparator());
        Integer[] res=task8.processAssembly(intArray);
        System.out.println("Largest value in any register after processing: "+res[0]);
        System.out.println("Largest value in any register in process: "+res[1]);
    }
}

