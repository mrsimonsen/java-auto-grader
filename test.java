import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.*;

public class test{
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

  public static void main(String[] args){
    LocalDateTime x1 = LocalDateTime.of(2019,8,30,17,29,30);
    LocalDateTime x2 = LocalDateTime.of(2019,8,29,17,29,30);
    DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    System.out.println(x1.isBefore(x2));

  }
}
