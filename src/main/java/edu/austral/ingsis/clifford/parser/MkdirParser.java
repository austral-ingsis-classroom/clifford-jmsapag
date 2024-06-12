package edu.austral.ingsis.clifford.parser;

import edu.austral.ingsis.clifford.command.Command;
import edu.austral.ingsis.clifford.command.Mkdir;

import java.util.Optional;

public class MkdirParser implements CommandParser{

    @Override
    public Optional<Command> getCommand(String commandLine) {
        String[] elements = commandLine.split(" ");
        if (elements.length != 2) {
            return Optional.empty();
        }
        return Optional.of(new Mkdir(elements[1]));
    }

}
