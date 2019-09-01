//https://stackoverflow.com/questions/2581158/java-how-to-get-all-subdirs-recursively

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;

public class tListSubdirs{
  public static void main(String[] args){
    File file = new File("Example");
File[] subdirs = file.listFiles(new FileFilter() {
    public boolean accept(File f) {
        return f.isDirectory();
    }
});
for (int i=0; i<subdirs.length; i++){
  System.out.println(subdirs[i]);
}
  }//end main
}//end class
