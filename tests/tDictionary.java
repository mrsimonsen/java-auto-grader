import java.util.*;

public class tDictionary{
  public static void main(String[] args){
    Dictionary<String,String> a = new Hashtable<String,String>();
    a.put("1","hi.java");
    String name = "1";
    String check = a.get(name);
    if (check != null){
      System.out.print(check.toString());
    }
    else{
      System.out.print("no");
    }
  }
}
