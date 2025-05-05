package backend;

import java.io.File;
import java.io.PrintStream;

public class OutputHandler 
{
    private static PrintStream konzol = new PrintStream(System.out);
    private  static PrintStream kimenet;
    private static File forras = null;

    public OutputHandler() 
    {
        kimenet = new PrintStream(System.out);
    }

    public static void setKimenet(PrintStream ps)
    {
        if(kimenet != null)
        {
            kimenet.close();
        }
        
        kimenet = ps;
    }
   
    public static PrintStream getKimenet()
    {
        return kimenet;
    }

    public static PrintStream getKonzol()
    {
        return konzol;
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
