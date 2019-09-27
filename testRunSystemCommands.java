import java.util.Properties;
import java.io.FileNotFoundException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RunSystemCommands{
  public printOutput getStreamWrapper(InputStream is, String type){
    return new printOutput(is, type);
  }

  public static String run(String command){
    Runtime rt = Runtime.getRuntime();
    RunSystemCommands rte = new RunSystemCommands();
    printOutput errorReported, outputMessage;

    try{
      Process proc1 = rt.exec(command);
      errorReported = rte.getStreamWrapper(proc1.getErrorStream(), "ERROR");
      outputMessage = rte.getStreamWrapper(proc1.getInputStream(), "OUTPUT");
      errorReported.start();
      outputMessage.start();
    }
    catch (IOException e){
      e.printStackTrace();
    }
  }


private class printOutput extends Thread {
  InputStream is = null;

  printOutput(InputStream is, String type){
    this.is = is;
  }
  public void run(){
    String s = null;
    try{
      BufferedReader br = new BufferedReader(new InputStreamReader(is));
      while ((s = br.readLine()) != null){
        System.out.println(s);
        }
      }
      catch (IOException ioe){
        ioe.printStackTrace();
      }
    }
  }
}
