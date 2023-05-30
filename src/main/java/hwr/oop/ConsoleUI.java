package hwr.oop;

import java.util.List;

public class ConsoleUI implements UI {
    final IOAdapter ioAdapter;

    public ConsoleUI(IOAdapter ioAdapter) {
        this.ioAdapter = ioAdapter;
    }

    @Override
    public Game createGame() {
        List <String> playerNames = List.of(ioAdapter.getString());

        return new Game(playerNames);
    }
}