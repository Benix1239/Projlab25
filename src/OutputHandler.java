
import java.io.File;
import java.io.PrintStream;

public class OutputHandler 
{
    private  static PrintStream kimenet = new PrintStream(System.out);
    private static File forras = null;

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
    
    public static File getForras()
    {
        return forras;
    }

    public static void setForras(File f)
    {
        forras = f;
    }

    public static void Close()
    {
        kimenet.close();
    }

}
