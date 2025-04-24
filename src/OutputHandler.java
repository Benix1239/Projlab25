
import java.io.PrintStream;

public class OutputHandler 
{
    private  static PrintStream kimenet = new PrintStream(System.out);

    public OutputHandler() 
    {
        kimenet = new PrintStream(System.out);
    }

    public static void setKimenet(PrintStream ps)
    {
        kimenet.close();
        kimenet = ps;
    }
    
    public static PrintStream getKimenet()
    {
        return kimenet;
    }

    public static void Close()
    {
        kimenet.close();
    }

}
