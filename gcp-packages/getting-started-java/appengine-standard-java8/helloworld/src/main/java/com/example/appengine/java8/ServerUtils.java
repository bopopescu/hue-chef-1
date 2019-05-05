package com.example.appengine.java8;

import javafx.util.Pair; 
import java.util.*;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

public class ServerUtils {

  static int DELTA = 40; // RGB +/- relaxation factor.

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


  static boolean inRange(int val, int L, int R) {
    if(val>=L && val<=R) {
      return true;
    }
    return false;
  }

  static boolean isPaletteMatch(int R, int G, int B, ArrayList<Integer> palette) {

    if(inRange(R, palette.get(0) - DELTA, palette.get(0) + DELTA) &&
       inRange(G, palette.get(1) - DELTA, palette.get(1) + DELTA) &&
       inRange(B, palette.get(2) - DELTA, palette.get(2) + DELTA)) {
         
      return true;
    }       
    return false;
  }


  public static String GetReco(String pixel) {
    
    ArrayList<Integer> ansRGB  = null;
    int ans_max_count = 0;

    try {
    
    String[] rgb = pixel.split("\\|");
    int R = Integer.parseInt(rgb[0]);
    int G = Integer.parseInt(rgb[1]);
    int B = Integer.parseInt(rgb[2]);
    
    // do job
    
    
    for(Map.Entry mapElement : edgeMap.entrySet()) { 
      Pair pair = (Pair)mapElement.getKey();
      ArrayList<Integer> X = (ArrayList<Integer>)pair.getKey();
      ArrayList<Integer> Y = (ArrayList<Integer>)pair.getValue(); 
      Integer value = (Integer)mapElement.getValue();

      if(isPaletteMatch(R,G,B,X) && value>=ans_max_count) {
        ans_max_count = value;
        ansRGB = X;
      }
    }

    } catch(Exception e) {

    }

    // stubbing for now.
    String color1 = "255|128|20";
    String color2 = "255|128|20";
    String color3 = "255|128|20";

    
    if(ansRGB!=null) {
      color1 = "";
      for(int i=0;i<3;i++) {
        color1+=Integer.toString(ansRGB.get(i));
        if(i!=2) color1+="|";
      }
    }
    
    
    String ans = color1 + "#" + color2 + "#" + color3;
    System.out.println(ans);
    return ans;
  }

}

