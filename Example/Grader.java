import java.util.*;
import java.io.*;
import java.time.*;
import java.nio.file.*;
import java.nio.file.attribute.*;

public class Grader{
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    System.out.println("Java Grader");
    System.out.println("This program nees to be ran from the parent directory of the collection of student repos");
    Dictionary files = set_assignment_names();
    Dictionary folders = set_assignment_folders();
    Dictionary dates = set_due_dates();
    String assign = intro(s, files);
    System.out.print("--Gathering files--");
    Dictionary days = gather(folders.get(assign),files.get(files));


  }//end main

  public static String gather(String folder, String file){
    String root = System.getProperty("user.dir");
    deleteFolder("testing");
    //https://stackoverflow.com/questions/2581158/java-how-to-get-all-subdirs-recursively
    File file = new File(root);
    File[] subdirs = file.listFiles(newFileFileter() {
      public boolean accept(File f){
        return f.isDirector();
      }
    })
    new File("testing").mkdirs();
    for (int i=0;i<subdirs.length; i++){
      
    }
  }//end gather

  public static void deleteFolder(String dir){
    //https://stackoverflow.com/questions/779519/delete-directories-recursively-in-java/27917071#27917071
    try{
      Path directory = Paths.get("dir");
      Files.walkFileTree(directory, new SimpleFileVisitor<Path>(){
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException{
          Files.delete(file);
          return FileVisitResult.CONTINUE;
        }
        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException e) throws IOException{
          Files.delete(dir);
          return FileVisitResult.CONTINUE;
        }
      });
    }//end try
      catch (Exception e){
        ;//do nothing
      }//end catch
  }//end deleteFolder

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
