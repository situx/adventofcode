package com.github.situx.aoc2015.day11;

 /** Solution to Advent Of Code 2015 Day11.
  * * @author situx
  * */
public class Task11 {

     /**
      * Increments a String as if it was a number beginning from the right.
      * @param toincrement the String as char[] to increment
      * @return the incremented String
      */
    public String incrementString(char[] toincrement){
        Boolean incfinish=false;
        int i=toincrement.length-1;
        while(!incfinish){
            if(toincrement[i]=='z'){
                toincrement[i]='a';
                i--;
            }else{
                toincrement[i]++;
                incfinish=true;
            }
        }
        return new String(toincrement);
    }

     /**
      * Checks the password constraints that at least two repeating equal character groups,
      * no characters i,o and l
      * and on increasing strait of characters are present in the password.
      * @param input the input
      * @return true if the password is valid, false if it is not
      */
    public Boolean checkConstraints(String input){
        char[] characters=input.toCharArray();
        Boolean hasIncreasingStraight=false;
        Boolean containsiol=false;
        Integer pairs=0;
        Boolean lastWasPair=false;
        for(int i=0;i<characters.length;i++){
            if(i+2<characters.length && characters[i]+1==characters[i+1] && characters[i+1]+1==characters[i+2]){
                hasIncreasingStraight=true;
            }
            if(characters[i]=='i' || characters[i]=='o' || characters[i]=='l'){
                containsiol=true;
            }
            if(i+1<characters.length && !lastWasPair && characters[i]==characters[i+1]){
                pairs++;
                lastWasPair=true;
            }else if(lastWasPair){
                lastWasPair=false;
            }
        }
        return !containsiol && hasIncreasingStraight && pairs>=2;
    }

     /**
      * Executes the new password generation process consisting of interation and constraint check.
      * @param input the input String
      * @param times the number of times a new password should be generated
      * @return the new password as String
      */
    public String execute(String input,Integer times){
        for(int i=0;i<times;i++) {
            while (!checkConstraints(input)) {
                input = incrementString(input.toCharArray());
            }
            if(i+1<times)
                input=incrementString(input.toCharArray());
        }
        return input;

    }

     /**
      * The main method of this project.
      * @param args
      */
    public static void main(String[] args){
        Task11 task11=new Task11();
        String input="hxbxwxba";
        System.out.println("Santas next password is: "+task11.execute(input,1));
        System.out.println("Santas second next password is: "+task11.execute(input,2));
    }
}
