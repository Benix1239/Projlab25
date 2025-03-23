import java.util.Scanner;

public class InputHandler {
    private static final Scanner bemenet = new Scanner(System.in); // Közös példány

    public static Scanner getScanner() {
        return bemenet;
    }
}