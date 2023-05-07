package hwr.oop;

// TODO Delete this placeholder SUT.
class BowlingGame {
    public static void main(String[] args) {
        UserInterface ui = new ConsoleUI(new ConsoleIOHandler(false));
        ui.sendOutput("Hello Bowling Alley!");
    }
    boolean get() {
        return true;
    }
}
