import java.util.*;
import java.io.*;
import java.time.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import RunSystemCommands;

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
    String source = root + "/" + folder +"/" + file;
    //can't chagne the destination file name -needs to be the same as the class name
    String dest = root + "/testing/" + folder +"/" + file;
    for (int i=0;i<subdirs.length; i++){
      //TODO
      copyFile()
    }
  }//end gather

//https://www.journaldev.com/861/java-copy-file
public static void copyFile(File source, File dest){
  FileChannel sourseChannel = null;
  FileChannel destChannel = null;
  try{
    sourceChannel = new FileInputStream(souce).getChannel();
    destChannel = new FileOutputSteam(dest).getChannel();
    destChannel.transferFrom(sourceChannel,0,sourceChannel.size());
  }finally{
    sourceChannel.close();
    destChannel.close();
  }
}

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
    a.put("00","HelloWorld.java");
    a.put("01","BasicInput.java");
    a.put("02","PaintEstimator.java");
    a.put("03","TextMsgAbbreviation.java");
    a.put("04","TextMsgDecoder.java");
    a.put("05","TextMsgExpander.java");
    a.put("06","DrawRightTriangle.java");
    a.put("07","DrawHalfArrow.java");
    a.put("08","PeopleWeights.java");
    a.put("09","PlayerRoster.java");
    a.put("10","CoinFlipper.java");
    a.put("11","ReverseMessage.java");
    a.put("12","DiceStats.java");
    a.put("13","TextAnalyzer.java");
    a.put("14","AuthoringAssistant.java");
    a.put("15","ShoppingCartPrinter.java");
    a.put("16","ShoppingCartManager.java");
    a.put("17","BinaryConverter.java");
    a.put("18","ParseStrings.java");
    a.put("19","DataVisualizer.java");
    a.put("20","CaesarCipher.java");
    a.put("21","Yahtzee.java");

    return a;
  }
  public static Dictionary set_assignment_folders(){
    Dictionary f = new Hashtable();
    f.put("1","test1");
    f.put("00","00HelloWorld.java");
    f.put("01","01BasicInput.java");
    f.put("02","02PaintEstimator.java");
    f.put("03","03TextMsgAbbreviation.java");
    f.put("04","04TextMsgDecoder.java");
    f.put("05","05TextMsgExpander.java");
    f.put("06","06DrawRightTriangle.java");
    f.put("07","07DrawHalfArrow.java");
    f.put("08","08PeopleWeights.java");
    f.put("09","09PlayerRoster.java");
    f.put("10","10CoinFlipper.java");
    f.put("11","11ReverseMessage.java");
    f.put("12","12DiceStats.java");
    f.put("13","13TextAnalyzer.java");
    f.put("14","14AuthoringAssistant.java");
    f.put("15","15ShoppingCartPrinter.java");
    f.put("16","16ShoppingCartManager.java");
    f.put("17","17BinaryConverter.java");
    f.put("18","18ParseStrings.java");
    f.put("19","19DataVisualizer.java");
    f.put("20","20CaesarCipher.java");
    f.put("21","21Yahtzee.java");

    return f;
  }
  public static Dictionary set_due_dates(){
    Dictionary d = new Hashtable();
    d.put("1",LocalDateTime.of(2019,8,30,7,27,30));
    //d.put("00",LocalDateTime.of(2020,month,day,11,59,59))

    return d;
  }
}
