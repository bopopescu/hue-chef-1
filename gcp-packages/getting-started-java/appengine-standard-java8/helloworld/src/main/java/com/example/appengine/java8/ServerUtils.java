package com.example.appengine.java8;

public class ServerUtils {

  static {
    // static block to load map 
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