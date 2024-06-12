package edu.austral.ingsis.clifford.parser;

import edu.austral.ingsis.clifford.command.Command;
import edu.austral.ingsis.clifford.command.Pwd;

import java.util.Optional;

public class PwdParser implements CommandParser{

    @Override
    public Optional<Command> getCommand(String commandLine) {
        String[] elements = commandLine.split(" ");
        if (elements.length == 1) {
            return Optional.of(new Pwd());
        }
        return Optional.empty();
    }

}
