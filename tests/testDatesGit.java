import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.*;
import java.util.*;

public class testDatesGit{
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
    Map<String, LocalDateTime> times = new HashMap<String, LocalDateTime>();
    LocalDateTime x1 = LocalDateTime.of(2019,8,30,17,29,30);
    LocalDateTime x2 = LocalDateTime.of(2019,8,29,17,29,30);
    times.put("1",x1);
    times.put("2",x2);
    System.out.println(times.get("1").isBefore(times.get("2")));

/*
    DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy MM dd HH mm ss");
    System.out.println(x1.format(f).substring(0,4));
    System.out.println(x1.isBefore(x2));
    System.out.println(makeTime("2019-09-30 09:30:38"));
    */
  }
}
