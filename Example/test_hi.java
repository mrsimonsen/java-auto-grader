import org.junit.*;
import static org.junit.Assert.*;
import java.io.*;

public class test_hi {
  hi student = new hi();
  private ByteArrayOutputStream TOut;
  private ByteArrayInputStream TIn;
  private final PrintStream SOut = System.out;
  private final InputStream SIn = System.in;
  String[] args = {};

  @Test
  public void test1(){
    student.main(args);
    String correct = "hi\n";
    String result = getOutput().substring(0,correct.length());
    assertEquals(correct, result);
  }

  //Set up methods
  @Before
  public void setOutput(){
    TOut = new ByteArrayOutputStream();
    System.setOut(new PrintStream(TOut));
  }
  private void setInput(String data){
    TIn = new ByteArrayInputStream(data.getBytes());
    System.setIn(TIn);
  }
  private String getOutput(){
    return TOut.toString();
  }
  @After
  public void restoreSystem(){
    System.setOut(SOut);
    System.setIn(SIn);
  }
}
