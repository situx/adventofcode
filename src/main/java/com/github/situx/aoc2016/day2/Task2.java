package com.github.situx.aoc2016.day2;

import java.io.*;

/**
 * Solution to Advent Of Code 2016 Day2.
 * @author situx
 */
public class Task2 {

    /**
     * Gets the Easter Bunny HQ Bathroom code from a line of Strings provided a last position is given.
     * Character U,D,L,R signify moves on the keypad and will result in a final position indicating one
     * number of the code
     * @param line the line to analyze
     * @param lastPosition the last position on the keypad, intially 5
     * @return the code as Integer
     */
    public Integer getBathRoomCodeFromStringLine(String line,Integer lastPosition){
        Integer newPosition=lastPosition;
        for(int i=0;i<line.length();i++){
            switch (line.charAt(i)){
                case 'D': if(newPosition<7){
                    newPosition+=3;
                }
                break;
                case 'U': if(newPosition>3){
                    newPosition-=3;
                }
                break;
                case 'L': if(newPosition!=1 && newPosition!=7 && newPosition!=4){
                    newPosition--;
                }
                break;
                case 'R': if(newPosition!=3 && newPosition!=6 && newPosition!=9){
                    newPosition++;
                }
                break;
                default:
            }
        }
        return newPosition;
    }

    /**
     * Gets the Easter Bunny HQ Bathroom code from a line of Strings provided a last position is given.
     * Character U,D,L,R signify moves on the keypad and will result in a final position indicating one
     * number of the code
     * This method does not use a rectangular keypad but rather a rhombus shaped keypad
     * @param line the line to analyze
     * @param lastPosition the last position on the keypad, intially 5
     * @return the code as Integer
     */
    public Integer getBathRoomCodeFromStringLine2(String line,Integer lastPosition){
        Integer newPosition=lastPosition;
        for(int i=0;i<line.length();i++){
            switch (line.charAt(i)){
                case 'D': if(newPosition!=5 && newPosition!=9 && newPosition!=10 && newPosition!=12 && newPosition!=13){
                    if(newPosition==1 || newPosition==11){
                        newPosition+=2;
                    }else{
                        newPosition+=4;
                    }
                }
                    break;
                case 'U': if(newPosition>2 && newPosition!=4 && newPosition!=9 && newPosition!=5){
                    if(newPosition==3 || newPosition==13){
                        newPosition-=2;
                    }else{
                        newPosition-=4;
                    }
                }
                    break;
                case 'L': if(newPosition!=1 && newPosition!=2 && newPosition!=5 && newPosition!=10 && newPosition!=13){
                    newPosition--;
                }
                    break;
                case 'R': if(newPosition!=1 && newPosition!=4 && newPosition!=9 && newPosition!=12 && newPosition!=13){
                    newPosition++;
                }
                    break;
                default:
            }
        }
        return newPosition;
    }

    /**
     * Collects the digits of the bathroom code for Easter Bunny HQ line by line.
     * @param filepath the file in which the codes are stored
     * @param keyPad1Or2 indicates if keypad 1 or keypad 2 is used
     * @return the bathroom code as String
     * @throws IOException on file reading error
     */
    public String collectBathRoomCodes(String filepath,Boolean keyPad1Or2) throws IOException {
        BufferedReader reader=new BufferedReader(new FileReader(new File(filepath)));
        String line;StringBuilder result= new StringBuilder("");
        Integer pos=5;
        while((line=reader.readLine())!=null){
            if(keyPad1Or2){
                pos=this.getBathRoomCodeFromStringLine(line,5);
            }else{
                pos=this.getBathRoomCodeFromStringLine2(line,5);
            }
            String hex=Integer.toHexString(pos);
            result.append(hex.substring(hex.length()-1).toUpperCase());
        }
        return result.toString();
    }

    /**
     * Main method of this project.
     * @param args
     * @throws IOException on file reading errors
     */
    public static void main(String[] args) throws IOException {
        Task2 task2=new Task2();
        System.out.println("Easter Bunny HQ Bathroom Code: "+task2.collectBathRoomCodes("aoc2016files/task2_2016.txt",true));
        System.out.println("Easter Bunny HQ Bathroom Code2: "+task2.collectBathRoomCodes("aoc2016files/task2_2016.txt",false));
    }

}
