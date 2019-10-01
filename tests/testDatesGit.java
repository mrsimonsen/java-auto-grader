import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.*;

public class testDatesGit{
  private ByteArrayOutputStream TOut;
  private ByteArrayInputStream TIn;
  private final PrintStream SOut = System.out;
  private final InputStream SIn = System.in;

  public void setOutput(){
    TOut = new ByteArrayOutputStream();
    System.setOut(new PrintStream(TOut));
  }
  public void setInput(String data){
    TIn = new ByteArrayInputStream(data.getBytes());
    System.setIn(TIn);
  }
  public String getOutput(){
    return TOut.toString();
  }
  public void restoreSystem(){
    System.setOut(SOut);
    System.setIn(SIn);
  }

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
  public static void main(String[] args){
    LocalDateTime x1 = LocalDateTime.of(2019,8,30,17,29,30);
    LocalDateTime x2 = LocalDateTime.of(2019,8,29,17,29,30);
    DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy MM dd HH mm ss");
    System.out.println(x1.format(f).substring(0,4));
    System.out.println(x1.isBefore(x2));
    System.out.println(makeTime("2019-09-30 09:30:38"));

  }
}
