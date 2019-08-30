import java.util.Dictionary;
import java.time.*;

public class Grader{
  public static void main(String[] args){

  }
  public static Dictionary set_assignment_names(){
    a = new Hashtable();
    a.put("1","hi.java");

    return a;
  }
  public static Dictionary set_assignment_folders(){
    f = new Hashtable();
    f.put("1","test1");

    return f;
  }
  public static Dictionary set_due_dates(){
    d = new Hashtable();
    d.put("1",LocalDateTime.of(2019,8,30,7,27,30));

    return d;
  }
}
