import java.util.Scanner;

public class InputHandler {
    private static  Scanner bemenet = new Scanner(System.in); // Közös példány

    public InputHandler(Scanner s) 
    {
        bemenet = new Scanner(System.in);
    }

    public static void setScanner(Scanner s)
    {
        bemenet.close();
        bemenet = s;
    }

    public static Scanner getScanner() {
        return bemenet;
    }
}