package hwr.oop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * UserInterface for the base game.
 * Output is standardized to allow for external output parsing.
 */
public interface UserInterface {
    void sendOutput(String outputValue);
    String promptUser(String prompt);
    String promptUser();
    int[] parseInputNumberList(String input);
    int multichoice(String prompt, ArrayList<String> choices, int invalidChoice);
    int multichoice(String prompt, ArrayList<String> choices);
    int retryMultichoice(String prompt, ArrayList<String> choices);

}
