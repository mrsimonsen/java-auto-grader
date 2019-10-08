import java.nio.file.*;
import java.nio.file.attribute.*;
import java.io.*;
import java.util.*;


public class This{
  public static void main(String[] args){
    File file = new File("M:\\GitHub\\java-auto-grader\\Example");
    int len = "M:\\GitHub\\java-auto-grader\\Example".length();
      File[] sub = file.listFiles(new FileFilter(){
        public boolean accept(File f){
          return f.isDirectory();
        }
      });
      System.out.println(len);
      for (int i=0;i<sub.length;i++){
        System.out.println(sub[i]);
    }
    ArrayList <String> names = new ArrayList<String>();
    for (int i=0;i<sub.length;i++){
      names.add(sub[i].toString().substring(len+1));
  }
  for (int i=0;i<names.size();i++){
    System.out.println(names.get(i));
}
  }
}
