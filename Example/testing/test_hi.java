import java.io.*;

public class test_hi {
  static hi student = new hi();
  private static ByteArrayOutputStream TOut;
  private static ByteArrayInputStream TIn;
  private static final PrintStream SOut = System.out;
  private static final InputStream SIn = System.in;
  static String[] args = {};


  public static String tests(){
    int total = 0;
    int score = 0;
    setOutput();
    //test 1
    total++;
    student.main(args);
    String correct = "hi\n";
    String result = getOutput().substring(0,correct.length());
    if (result.equals(correct)){
      score++;
    }
    
    restoreSystem();
    String rep = ""+ score +"/"+total;
    return rep;
  }

  //Set up methods
  public static void setOutput(){
    TOut = new ByteArrayOutputStream();
    System.setOut(new PrintStream(TOut));
  }
  private static void setInput(String data){
    TIn = new ByteArrayInputStream(data.getBytes());
    System.setIn(TIn);
  }
  private static String getOutput(){
    return TOut.toString();
  }
  public static void restoreSystem(){
    System.setOut(SOut);
    System.setIn(SIn);
  }
}
