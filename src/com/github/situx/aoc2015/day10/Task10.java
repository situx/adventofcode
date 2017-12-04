package com.github.situx.aoc2015.day10;

/**
 * Solution to Advent Of Code 2015 Day10.
 * @author situx
 */
public class Task10 {

    /**
     * Morphs the input String into Look-and-Say sequences in a recursive way.
     * A look and say sequence cosists of the number of times a number appears in the previous input String.
     * @param numberString the numberString to analyze
     * @param amount the (remaining) amount of times to run the function
     * @return the morphed String
     */
    public String lookAndSay(String numberString,Integer amount){
        if(amount==0)
            return numberString;
        StringBuilder nextString=new StringBuilder();
        String lastChar=numberString.substring(0,1),curchar=null;
        Integer charcounter=1;
        for(int i=1;i<numberString.length();i++){
            curchar=numberString.substring(i,i+1);
            if(!lastChar.equals(curchar)){
                nextString.append(charcounter.toString()).append(lastChar);
                charcounter=0;
            }
            charcounter++;
            lastChar=curchar;
        }
        nextString.append(charcounter.toString()).append(curchar);
        return lookAndSay(nextString.toString(),amount-1);
    }

    /**
     * The main method of this project.
     * @param args
     */
    public static void main(String[] args){
        Task10 task10=new Task10();
        String inputSequence="1321131112";
        System.out.println("For input sequence: "+inputSequence);
        System.out.println("40x LookAndSay: "+task10.lookAndSay("1321131112",40).length());
        System.out.println("50x LookAndSay: "+task10.lookAndSay("1321131112",50).length());
    }
}
