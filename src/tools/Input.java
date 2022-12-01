package tools;

import java.util.Scanner;

public class Input {
    private final static Scanner sc = new Scanner(System.in);
    public static String string(String message) {
        System.out.print(message);
        return sc.nextLine();
    }
}
