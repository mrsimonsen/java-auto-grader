import java.nio.file.*;
import java.nio.file.attribute.*;
import java.io.*;
import java.util.*;


public class This{
  public static void main(String[] args){
    Command c = new Command();
    System.out.println(c.run("javac test_hi.java"));
  }
}
