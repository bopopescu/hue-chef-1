package com.example.appengine.java8;

import javafx.util.Pair; 
import java.util.*;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

public class ServerUtils {

  static HashMap<Pair<ArrayList<Integer>, ArrayList<Integer>>, Integer> edgeMap = new HashMap<>();

  static {
    // static block to load map 
    
    try {
      String line;

      FileReader fileReader = new FileReader("Edge_Map.txt");

      BufferedReader bufferedReader = new BufferedReader(fileReader);

      while((line = bufferedReader.readLine()) != null) {
          
        //System.out.println(line);

        String[] strArray = line.split("\\|");

        int[] intArray = new int[strArray.length];
        
        for(int i = 0; i < strArray.length; i++) {
          intArray[i] = Integer.parseInt(strArray[i]);
        }

        ArrayList<Integer> X = new ArrayList<>( Arrays.asList(intArray[0], intArray[1], intArray[2]));
        ArrayList<Integer> Y = new ArrayList<>( Arrays.asList(intArray[3], intArray[4], intArray[5]));    
        int count = intArray[6];

        Pair P = new Pair(X,Y);

        if(edgeMap.containsKey(P)) {
          int oldCount = edgeMap.get(P);
          count+=oldCount;
          edgeMap.put(P, count);
        } else {
          edgeMap.put(P, count);
        }
   
      }

      bufferedReader.close();         
    
    } catch(Exception e) {
      System.out.println("Exception in reading file."); 
      e.printStackTrace(System.out);     
    }

  }


  public static String GetReco(String pixel) {

    //String[] rgb = pixel.split("|"); 
    //int red = Integer.parseInt(rgb[0]);
    //int green = Integer.parseInt(rgb[1]);
    //int blue = Integer.parseInt(rgb[2]);
    
    // do job


    // stubbing for now.
    
    String color1 = "255|128|20";
    String color2 = "255|128|20";
    String color3 = "255|128|20";

    String ans = color1 + "#" + color2 + "#" + color3;
    System.out.println(ans);
    return ans;
  }
}