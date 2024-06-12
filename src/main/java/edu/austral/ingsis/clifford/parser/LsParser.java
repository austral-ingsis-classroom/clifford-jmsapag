package edu.austral.ingsis.clifford.parser;

import edu.austral.ingsis.clifford.command.Command;
import edu.austral.ingsis.clifford.command.Ls;

import java.util.Optional;

public class LsParser implements CommandParser {

    @Override
    public Optional<Command> getCommand(String commandLine) {
        String[] elements = commandLine.split(" ");
        if (elements.length == 1) {
            return Optional.of(new Ls());
        }
        else if (elements.length == 2) {
            return Optional.of(new Ls(elements[1].split("=")[1]));
        }
        return Optional.empty();
    }
}
