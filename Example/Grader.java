import java.util.*;
import java.time.*;

public class Grader{
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    System.out.println("Java Grader");
    System.out.println("This program nees to be ran from the parent directory of the collection of student repos");
    Dictionary files = set_assignment_names();
    Dictionary folders = set_assignment_folders();
    Dictionary dates = set_due_dates();
    String assign = intro(s, files);
  }//end main

  public static String intro(Scanner s, Dictionary files){
    String assign;
    boolean n = true;
    while (n){
      System.out.println("What is the number of the assignment folder?");
      assign = s.nextLine();
      System.out.print
      String check = files.get(assign);
      if (check != null){    
        n = false;
      }
      else{
        System.out.println("That wasn't a valid assignment number!");
      }
    }//end while
    return assign;
  }//end intro

  public static Dictionary set_assignment_names(){
    Dictionary a = new Hashtable();
    a.put("1","hi.java");

    return a;
  }
  public static Dictionary set_assignment_folders(){
    Dictionary f = new Hashtable();
    f.put("1","test1");

    return f;
  }
  public static Dictionary set_due_dates(){
    Dictionary d = new Hashtable();
    d.put("1",LocalDateTime.of(2019,8,30,7,27,30));

    return d;
  }
}
