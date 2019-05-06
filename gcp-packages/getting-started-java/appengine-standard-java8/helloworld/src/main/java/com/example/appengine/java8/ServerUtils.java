package com.example.appengine.java8;

import java.lang.Math; 
import java.util.*;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

import java.net.*;
import java.io.*;


public class ServerUtils {

  static int DELTA = 30; // RGB +/- relaxation factor.

  static HashMap<ArrayList<Integer>, Integer> edgeMap = new HashMap<>();

  static {

    // Block to load map
    try {


      URL url = new URL("https://raw.githubusercontent.com/anicksaha/color-recommender/master/code/mapper-job/Edge_Map.txt");
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
      String line;

      //FileReader fileReader = new FileReader("Edge_Map.txt");

      //BufferedReader bufferedReader = new BufferedReader(fileReader);

      while((line = bufferedReader.readLine()) != null) {
          
        //System.out.println(line);

        String[] strArray = line.split("\\|");

        int[] intArray = new int[strArray.length];
        
        for(int i = 0; i < strArray.length; i++) {
          intArray[i] = Integer.parseInt(strArray[i]);
        }

        ArrayList<Integer> key = new ArrayList<>( Arrays.asList(intArray[0], intArray[1], intArray[2], intArray[3], intArray[4], intArray[5]));
        int count = intArray[6];

        if(edgeMap.containsKey(key)) {
          int oldCount = edgeMap.get(key);
          count+=oldCount;
          edgeMap.put(key, count);
        } else {
          edgeMap.put(key, count);
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

  static Integer getPaletteMatchScore(int R, int G, int B, ArrayList<Integer> palette) {

    Integer score = -1;

    if(inRange(R, palette.get(0) - DELTA, palette.get(0) + DELTA) &&
       inRange(G, palette.get(1) - DELTA, palette.get(1) + DELTA) &&
       inRange(B, palette.get(2) - DELTA, palette.get(2) + DELTA)) {

       score = Math.abs(R-palette.get(0)) +
               Math.abs(G-palette.get(1)) +
               Math.abs(B-palette.get(2));
    }       
    return score;
  }


  public static String GetReco(String pixel) {
    
    ArrayList<Integer> ansRGB_1  = null;
    int ans_max_count_1 = 0;

    ArrayList<Integer> ansRGB_2  = null;
    int ans_max_count_2 = 0;

    ArrayList<Integer> ansRGB_3  = null;
    int ans_max_count_3 = 0;


    try {
    
    String[] rgb = pixel.split("\\|");
    int R = Integer.parseInt(rgb[0]);
    int G = Integer.parseInt(rgb[1]);
    int B = Integer.parseInt(rgb[2]);
    
    // do job
    
    for(Map.Entry mapElement : edgeMap.entrySet()) { 

      ArrayList<Integer> key = (ArrayList)mapElement.getKey();
      
      Integer value = (Integer)mapElement.getValue();

      ArrayList<Integer> X = new ArrayList<>( Arrays.asList(key.get(0), key.get(1), key.get(2)));
      ArrayList<Integer> Y = new ArrayList<>( Arrays.asList(key.get(3), key.get(4), key.get(5)));
      
      Integer score_x = getPaletteMatchScore(R,G,B,X);
      Integer score_y = getPaletteMatchScore(R,G,B,Y);

      
      // when both edge nodes fits in the query 
      if(score_x>=0 && score_y>=0) {
        if(value>=ans_max_count_1) {

          ans_max_count_3 = ans_max_count_2;
          ansRGB_3 = (ArrayList) ansRGB_2.clone();  
          
          ans_max_count_2 = ans_max_count_1;
          ansRGB_2 = (ArrayList) ansRGB_1.clone();    
          
          ans_max_count_1 = value;
        
          if(score_x > score_y) {
            ansRGB_1 = (ArrayList) X.clone();
          } else {
            ansRGB_1 = (ArrayList) Y.clone();          
          }  
        } else if(value>=ans_max_count_2) {
          ans_max_count_3 = ans_max_count_2;
          ansRGB_3 = (ArrayList) ansRGB_2.clone();  

          ans_max_count_2 = value;
        
          if(score_x > score_y) {
            ansRGB_2 = (ArrayList) X.clone();
          } else {
            ansRGB_2 = (ArrayList) Y.clone();          
          }  
        } else if(value>=ans_max_count_3) {
          ans_max_count_3 = value;
        
          if(score_x > score_y) {
            ansRGB_3 = (ArrayList) X.clone();
          } else {
            ansRGB_3 = (ArrayList) Y.clone();          
          }
        }
        

      } else if(score_x>=0) {

        if(value>=ans_max_count_1) {
          ans_max_count_3 = ans_max_count_2;
          ansRGB_3 = (ArrayList) ansRGB_2.clone();  
          
          ans_max_count_2 = ans_max_count_1;
          ansRGB_2 = (ArrayList) ansRGB_1.clone();    
          
          ans_max_count_1 = value;
          ansRGB_1 = (ArrayList) Y.clone(); 

        } else if(value>=ans_max_count_2) {
          ans_max_count_3 = ans_max_count_2;
          ansRGB_3 = (ArrayList) ansRGB_2.clone();  

          ans_max_count_2 = value;
          ansRGB_2 = (ArrayList) Y.clone(); 

        } else if(value>=ans_max_count_3) {
          ans_max_count_3 = value;
          ansRGB_3 = (ArrayList) Y.clone(); 
        }

      } else if(score_y>=0) {
        
        if(value>=ans_max_count_1) {
          ans_max_count_3 = ans_max_count_2;
          ansRGB_3 = (ArrayList) ansRGB_2.clone();  
          
          ans_max_count_2 = ans_max_count_1;
          ansRGB_2 = (ArrayList) ansRGB_1.clone();    
          
          ans_max_count_1 = value;
          ansRGB_1 = (ArrayList) X.clone(); 

        } else if(value>=ans_max_count_2) {
          ans_max_count_3 = ans_max_count_2;
          ansRGB_3 = (ArrayList) ansRGB_2.clone();  

          ans_max_count_2 = value;
          ansRGB_2 = (ArrayList) X.clone(); 

        } else if(value>=ans_max_count_3) {
          ans_max_count_3 = value;
          ansRGB_3 = (ArrayList) X.clone(); 
        }

      } else {
        // default case
      }
      


    }

    } catch(Exception e) {

    }

    // stub | default values.
    String color1 = "173|255|47";
    String color2 = "173|255|47";
    String color3 = "173|255|47";

    
    if(ansRGB_1!=null) {
      color1 = "";
      for(int i=0;i<3;i++) {
        color1+=Integer.toString(ansRGB_1.get(i));
        if(i!=2) color1+="|";
      }
    }
    
    if(ansRGB_2!=null) {
      color2 = "";
      for(int i=0;i<3;i++) {
        color2+=Integer.toString(ansRGB_2.get(i));
        if(i!=2) color2+="|";
      }
    }

    if(ansRGB_3!=null) {
      color3 = "";
      for(int i=0;i<3;i++) {
        color3+=Integer.toString(ansRGB_3.get(i));
        if(i!=2) color3+="|";
      }
    }
    
    String ans = color1 + "#" + color2 + "#" + color3;
    System.out.println(ans);
    return ans;
  }

}

