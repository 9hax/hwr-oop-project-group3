package hwr.oop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConsoleUI implements UserInterface {

    private final ConsoleIOHandler ioHandler;
    public ConsoleUI(ConsoleIOHandler io) {
        this.ioHandler = io;
    }
    /**
     * Puts an output to Stdout.
     * @param outputValue String to be sent to stdout.
     */
    public void sendOutput(String outputValue) {
        ioHandler.StringOut(outputValue);
    }

    /**
     * Prompts the user with a customizable prompt and returns the input.
     * @param prompt The custom prompt that is at the start of the input line.
     * @return the String that was input up to the first lineSeparator.
     */
    public String promptUser(String prompt) {
        ioHandler.StringOut(prompt);
        return ioHandler.StringIn();
    }

    /**
     * Prompts the user with a prompt of "> ".
     * @return the String that was input up to the fist lineSeparator.
     */
    public String promptUser() {
        return promptUser("> ");
    }

    /**
     * Parse a list-like string containing numbers separated with commata with basic error correction.
     * @param input The String to be parsed.
     * @return an Array of numbers present in the input.
     */
    public int[] parseInputNumberList(String input) {
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

    /**
     * Provide a Multiple Choice-like input for a list of Strings with a prompt.
     * @param prompt Prompt to output before listing available choices.
     * @param choices List of available choices.
     * @param invalidChoice Fallback Value if input is invalid.
     * @return the index of the chosen option in the choices list.
     */
    public int multichoice(String prompt, ArrayList<String> choices, int invalidChoice) {
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

    /**
     * Overloaded function Handler for multichoice but defaults invalidChoice to -1.
     */
    public int multichoice(String prompt, ArrayList<String> choices) {
        return multichoice(prompt, choices, -1);
    }

    // TODO: This function needs a test, since using a test that simply sends multiple lines throws an error,
    //  which has nothing to do with this code, but Scanner. ¯\_(ツ)_/¯
    /**
     * Retries a multichoice until the input is valid. For more info check out {@link UserInterface#multichoice(String, ArrayList, int)}
     * @see UserInterface#multichoice(String, ArrayList, int)
     * @param prompt Prompt for the multichoice.
     * @param choices Choices for the multichoice.
     * @return The first valid input.
     */
    public  int retryMultichoice(String prompt, ArrayList<String> choices) {
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
