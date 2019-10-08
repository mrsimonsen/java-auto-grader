import java.util.*;
import java.io.*;
import java.time.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.stream.Stream;
import java.lang.Math;

public class Grader{
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    System.out.println("Java Grader");
    System.out.println("This program nees to be ran from the parent directory of the collection of student repos");
    Dictionary files = set_assignment_names();
    Dictionary folders = set_assignment_folders();
    Map dates = set_due_dates();
    Dictionary tests = set_test_names();
    String assign = intro(s, files);
    System.out.println("--Gathering Files--");
    Map days = gather(folders.get(assign).toString(), files.get(assign).toString(), tests.get(assign).toString());
    System.out.println("--Grading Files--");
    grade(assign, tests, days, dates);
    System.out.println("--Testing Complete--");
  }//end main

  public static void grade(String assign, Dictionary tests, Map days, Map dates){
    String root = System.getProperty("user.dir");
    Dictionary username = format_usernames();
    Command c = new Command();
    c.run("cd testing");
    FileWriter write = new FileWriter("report.csv");
    String[] header = {"GitHub_File","Weber name", "points earned", "is late?"};
    csvWriter(header, write);
    String name = tests.get(assign).toString();
    ArrayList<Path> testPaths = getFiles(name);
    String folder;
    String student;
    String late = "TRUE";
    double points;
    String[] row = new String[4];
    String file;
    for (int i=0; i< testPaths.size(); i++){
      //get into a folder
      file = testPaths.get(i).toString();
      folder = file.substring(0,file.length()-name.length());
      System.out.println("Grading "+folder);
      c.run("cd "+folder);
      //Compile test and dependicies
      c.run("javac "+file);
      points = string_to_math(c.run("java "+name.substring(0, name.length()-4)));
      //go back to testing folder
      c.run("cd ..");
      //get data for csv
      student = username.get(folder).toString();
      if (days.get(folder).isBefore(dates.get(assign))){
        late = "FALSE";
      }
      csvWriter(row, write);
    }
    write.close();
  }//end grade method

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

  public static ArrayList<Path> getFiles(String name){
    ArrayList<Path> filePaths = new ArrayList<Path>();
    try{
      Files.walk(Paths.get(System.getProperty("user.dir")+"/testing")).filter(Files::isRegularFile).filter(s->s.endsWith(name)).forEach(y->filePaths.add(y));
    }catch (Exception e){
      e.printStackTrace();
    }
    return filePaths;
  }

  public static void csvWriter(String[] data, FileWriter writer){
    for (int i = 0; i < data.length; i++){
      writer.append(data[i]);
    }
    writer.append("\n");//don't forget that newline
    writer.flush();//sync changes
  }

//https://stackabuse.com/reading-and-writing-csvs-in-java/
  public static Dictionary format_usernames(){
    Dictionary username = new Hashtable();
    BufferedReader reader = new BufferedReader(new FileReader("1400 usernames - Form Responsees 1.csv"));
    String first;
    String last;
    String github;
    String student;
    String row;
    //skipping first line - header
    String[] data = reader.readLine().split(",");
    while ((row = reader.readLine()) != null){
      //don't need weber state names, just match github with name
      data = row.split(",");
      first = data[1];
      last = data[2];
      github = data[3];
      student = String.join(",",last,first);
      username.put(github,student);
    }
    reader.close();
    return username;
  }

  public static Map gather(String folder, String sfile, String testName){
    String root = System.getProperty("user.dir");
    deleteFolder("testing");
    //https://stackoverflow.com/questions/2581158/java-how-to-get-all-subdirs-recursively
    File file = new File(root);
    File[] subdirs = file.listFiles(new FileFilter(){
      public boolean accept(File f){
        return f.isDirectory();
      }
    });
    new File("testing").mkdirs();
    Command c = new Command();
    LocalDateTime time;
    String gout;
    String source;
    String dest;
    String testSource;
    String testDest;
    Map<String,LocalDateTime> days = new HashMap<String,LocalDateTime>();
    for (int i=0;i<subdirs.length; i++){
      source = root + "/" + folder +"/" + sfile;
      //can't chagne the destination file name -needs to be the same as the class name
      dest = root + "/testing/" + folder +"/" + sfile;
      testSource = root + "/" + folder +"/" + testName;
      testDest = root + "/testing/" + folder +"/" + testName;
      copyFile(new File(source), new File(dest));
      copyFile(new File(testSource), new File(testDest));
      c.run("cd "+folder);
      gout = c.run("git log -1 --format=%ci");
      c.run("cd ..");
      time = makeTime(gout);
      days.put(folder,time);
    }
    return days;
  }//end gather

public static LocalDateTime makeTime(String gout){
  int year = Integer.parseInt(gout.substring(0,4));
  int month = Integer.parseInt(gout.substring(5,7));
  int day = Integer.parseInt(gout.substring(8,10));
  int hour = Integer.parseInt(gout.substring(11,13));
  int min = Integer.parseInt(gout.substring(14,16));
  int sec = Integer.parseInt(gout.substring(17,19));
  LocalDateTime time = LocalDateTime.of(year, month, day, hour, min, sec);
  return time;
}

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
      String check = files.get(assign).toString();
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
    f.put("00","00HelloWorld");
    f.put("01","01BasicInput");
    f.put("02","02PaintEstimator");
    f.put("03","03TextMsgAbbreviation");
    f.put("04","04TextMsgDecoder");
    f.put("05","05TextMsgExpander");
    f.put("06","06DrawRightTriangle");
    f.put("07","07DrawHalfArrow");
    f.put("08","08PeopleWeights");
    f.put("09","09PlayerRoster");
    f.put("10","10CoinFlipper");
    f.put("11","11ReverseMessage");
    f.put("12","12DiceStats");
    f.put("13","13TextAnalyzer");
    f.put("14","14AuthoringAssistant");
    f.put("15","15ShoppingCartPrinter");
    f.put("16","16ShoppingCartManager");
    f.put("17","17BinaryConverter");
    f.put("18","18ParseStrings");
    f.put("19","19DataVisualizer");
    f.put("20","20CaesarCipher");
    f.put("21","21Yahtzee");

    return f;
  }
  public static Map set_due_dates(){
    Map<String, LocalDateTime> d = new HashMap<String,LocalDateTime>();
    d.put("1",LocalDateTime.of(2019,8,30,7,27,30));
    //d.put("00",LocalDateTime.of(2020,month,day,11,59,59))

    return d;
  }
  public static Dictionary set_test_names(){
    Dictionary t = new Hashtable();
    t.put("1", "test_hi.java");

    return t;
  }
}
