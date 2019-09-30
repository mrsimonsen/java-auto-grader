import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.stream.Stream;
import java.util.*;
//https://javaconceptoftheday.com/list-all-files-in-directory-in-java/
public class This{
  public static void main(String[] args){
    String name = "hi.java";
    try{
      System.out.println(getFiles(name));
    }catch (Exception e){
      e.printStackTrace();
    }
  }
  public static ArrayList<Path> getFiles(String name) throws IOException{
    ArrayList<Path> fileList = new ArrayList<Path>();
   Files.walk(Paths.get(System.getProperty("user.dir")+"/Example")).filter(Files::isRegularFile).filter(s->s.endsWith(name)).forEach(y->fileList.add(y));
   return fileList;
  }
}
