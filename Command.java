//https://stackoverflow.com/questions/3403226/how-to-run-linux-commands-in-java

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Command{
  public static String run(String command){
    String s;
    Process p;
    String output = "";
    try{
      p = Runtime.getRuntime().exec(command);
      BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
      while ((s = br.readLine()) != null){
        output += s+"\n";
      }
      p.waitFor();
      p.destroy();
    } catch (Exception e){
      output += "ERROR:\n"+e.getMessage();
    }
    return output;
  }
}
