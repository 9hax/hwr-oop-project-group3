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

    public static String promptUser(String prompt) {
        // Prompt the User with a customizable Prompt and return the first line of input.
        // This is a wrapper function to make custom user interfaces easier in the future.
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static String promptUser() {
        return promptUser("> "); // Default prompt
    }

    public static int[] parseInputNumberList(String input) {
        String[] numbers = input.split(",");
        List<Integer> outputList = new ArrayList<>();
        for (String number:numbers ) {
            try {
                // If a single number input fails, skip it, and parse the rest.
                outputList.add(
                        Integer.parseInt(
                                // The following removes any commas before and after the list.
                                number.replace(" ", "")
                                        .replace(",", "")
                        )
                );
            } catch (NumberFormatException e) {
                System.err.println("Failed to parse all numbers in list, continuing..");
            }
        }
        // Sort the list of numbers for easier handling.
        Collections.sort(outputList);
        int[] numbersArray = new int[outputList.size()];
        // Convert the List to an Array to natively handle the list with java-internal tools.
        for (int i = 0; i < outputList.size(); i++) {
            numbersArray[i] = outputList.get(i);
        }
        return numbersArray;
    }

    public static int multichoice(String prompt, List<String> choices, int invalidChoice) {
        // Using a list here is faster from Java 17 on.
        // It also makes removing objects easier.
        System.out.println(prompt);
        System.out.println("Please enter the number corresponding to the action of choice:");
        for (int i = 0; i < choices.size()-1; i++) {
            // Print out all choices and assign a number to them.
            System.out.print((i+1)+": "+ choices.get(i));
        }
        String input = promptUser();
        try {
            int returnValue = Integer.parseInt(input)-1; // First choice is actually listIndex 0, avoid confusion
            if (returnValue >= 0 && returnValue < choices.size()) { // Number between 0 and maximum choice number
                return returnValue;
            }
            return invalidChoice;
        } catch (NumberFormatException e) {
            return invalidChoice;
        }
    }

    public static int multichoice(String prompt, List<String> choices) {
        // Overload function without default
        return multichoice(prompt, choices, -1);
    }

    // TODO: This function needs a test, since using a test that simply sends multiple lines throws an error,
    //  which has nothing to do with this code, but Scanner. ¯\_(ツ)_/¯
    public static int retryMultichoice(String prompt, List<String> choices) {
        // This retries a multichoice until the return is a valid number.
        int returnValue = -1;
        while (returnValue < 0) {
            returnValue = multichoice(prompt, choices, -1);
            if (returnValue < 0) {
                System.out.println("Invalid Input. Please choose a valid integer within the available range.");
            }
        }
        return returnValue;
    }
}
