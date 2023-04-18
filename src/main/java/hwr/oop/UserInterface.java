package hwr.oop;

import java.util.Scanner;

public class UserInterface {
    public static void sendOutput(String outputValue) {
        // This is a wrapper function to make custom user interfaces easier in the future.


        // Add any custom output code above this line.
        System.out.println(outputValue);
    }

    public static String promptUser() {
        // This is a wrapper function to make custom user interfaces easier in the future.
        Scanner scanner = new Scanner(System.in);
        System.out.print("> "); // Default prompt
        return scanner.nextLine();
    }

    public static String promptUser(String customPrompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(customPrompt);
        return scanner.nextLine();
    }
}
