package com.github.situx.aoc2016.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 * Solution to Advent Of Code 2016 Day1.
 * @author situx
 */
public class Task1 {

    /**
     * Finds the shortestPath in a twodimensional grid given a moving pattern of left/right movements and returns its manhattanDistance to the point of origin.
     * Using the firstPositionToVisitTwice parameter it will find the first coordinate which is visited twice
     * and returns its manhattansDistance to the point of origin.
     * @param movePattern the moving pattern given as a series of left right movements
     * @param firstPositionToVisitTwice indicates if the first coordinate visited twice should be chosen for calculation
     * @return the manhattanDistance to the point of origin as Integer
     */
    public Integer findShortestPathOnCityGrid(String movePattern,Boolean firstPositionToVisitTwice){
        int[] directionMoves=new int[]{0,0,0,0};
        Integer arraypos=0;
        Integer posx=0,posy=0;
        String[] moves=movePattern.split(",\\s");
        Set<String> positions=new TreeSet<String>();
        for(String move:moves){
            Integer distance=0;
            if(move.contains("L")){
                if(arraypos==0){
                    arraypos=directionMoves.length-1;
                }else{
                    arraypos-=1;
                }
            }else if(move.contains("R")){
                if(arraypos==directionMoves.length-1){
                    arraypos=0;
                }else{
                    arraypos+=1;
                }
            }
            if(firstPositionToVisitTwice) {
                distance = Integer.valueOf(move.substring(1));
                switch (arraypos) {
                    case 0:
                        for (int j = 0; j < distance; j++) {
                            posy++;
                            if (positions.contains(posx + "_" + posy)) {
                                return manhattanDistance(0, posx, 0, posy);
                            }
                            positions.add(posx + "_" + posy);
                        }
                        break;
                    case 1:
                        for (int j = 0; j < distance; j++) {
                            posx--;
                            if (positions.contains(posx + "_" + posy)) {
                                return manhattanDistance(0, posx, 0, posy);
                            }
                            positions.add(posx + "_" + posy);
                        }
                        break;
                    case 2:
                        for (int j = 0; j < distance; j++) {
                            posy--;
                            if (positions.contains(posx + "_" + posy)) {
                                return manhattanDistance(0, posx, 0, posy);
                            }
                            positions.add(posx + "_" + posy);
                        }
                        break;
                    case 3:
                        for (int j = 0; j < distance; j++) {
                            posx++;
                            if (positions.contains(posx + "_" + posy)) {
                                return manhattanDistance(0, posx, 0, posy);
                            }
                            positions.add(posx + "_" + posy);
                        }
                        break;
                    default:
                }
            }
            directionMoves[arraypos]+=Integer.valueOf(move.substring(1));
        }
        System.out.println("| NORTH | EAST | SOUTH | WEST | "+System.lineSeparator()+Arrays.toString(directionMoves));
        return manhattanDistance(0,Math.abs(directionMoves[2]-directionMoves[0]),0,Math.abs(directionMoves[3]-directionMoves[1]));
    }

    /**
     * Calculates the manhattanDistance on four input coordinates.
     * @param x1 the x1 coordinate
     * @param x2 the x2 coordinate
     * @param y1 the y1 coordinate
     * @param y2 the y2 coordinate
     * @return the manhattanDistance as Integer
     */
    public Integer manhattanDistance(Integer x1,Integer x2,Integer y1,Integer y2){
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    /**
     * The main method of this program.
     * @param args
     * @throws IOException on file reading errors
     */
    public static void main(String[] args) throws IOException {
        Task1 task1=new Task1();
        System.out.println("The shortest path length for Santa to find Easter Bunny headquarters is "+task1.findShortestPathOnCityGrid(new String(Files.readAllBytes(Paths.get("aoc2016files/task1_2016.txt"))),false)+" steps.");
        System.out.println("The shortest path length for Santa to find Easter Bunny headquarters is "+task1.findShortestPathOnCityGrid(new String(Files.readAllBytes(Paths.get("aoc2016files/task1_2016.txt"))),true)+" steps.");
    }

}
