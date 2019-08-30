import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class test{
  public static void main(String[] args){
    LocalDateTime x = LocalDateTime.of(2019,8,30,17,29,30);
    DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    System.out.println(x);
    System.out.println(f.format(x));
  }
}
