package hwr.oop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

    public static int[] parseInputNumberList(String input) {
        String[] numbers = input.split(",");
        List<Integer> outputList = new ArrayList<Integer>();
        for (String number:numbers ) {
            try {
                outputList.add(
                        Integer.parseInt(
                                number.replace(" ", "")
                                      .replace(",", "")
                        )
                );
            } catch (NumberFormatException e) {
                System.err.println("Failed to parse all numbers in list, continuing..");
            }
        }
        Collections.sort(outputList);
        int[] numbersArray = new int[outputList.size()];
        for (int i = 0; i < outputList.size(); i++) {
            numbersArray[i] = outputList.get(i);
        }
        return numbersArray;
    }
}
