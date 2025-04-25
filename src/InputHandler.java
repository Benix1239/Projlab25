import java.io.File;
import java.util.Scanner;

public class InputHandler {
    private static  Scanner bemenet = new Scanner(System.in); // Közös példány
    private static File forras = null;

    public InputHandler() 
    {
        bemenet = new Scanner(System.in);
    }

    public static void setScanner(Scanner s)
    {
       
        bemenet = s;
    }

    public static Scanner getScanner() {
        return bemenet;
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
        bemenet.close();
    }
}