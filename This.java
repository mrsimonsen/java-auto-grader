public class This{
  public static void main(String[] args){
    Command c = new Command();
    System.out.println(c.run("git log --oneline"));
  }
}
