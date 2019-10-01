import java.io.*;
import java.lang.Math;

public class This{
  public static void main(String[] args){
    String p1 = "3/5";
    String p2 = "10/10";
    String p3 = "9/11";
    System.out.println(string_to_math(p1));
    System.out.println(string_to_math(p2));
    System.out.println(string_to_math(p3));
}
public static double string_to_math(String points){
  double total;
  int score;
  //single digit values
  if(points.length()%3==0){
    score = Integer.parseInt(points.substring(0,1));
    total = Integer.parseInt(points.substring(2,3));
    total = Math.round(score/total*10);
  }
  //double digit values
  else if(points.length()%5==0){
    total = Integer.parseInt(points.substring(3,5));
    score = Integer.parseInt(points.substring(0,2));
    total = Math.round(score/total*100.0)/10.0;
  }
  //single digit score with 2 digit total
  else{
    total = Integer.parseInt(points.substring(2,4));
    score = Integer.parseInt(points.substring(0,1));
    total = Math.round(score/total*100.0)/10.0;
  }
  return total;
}//end string_to_math
}
