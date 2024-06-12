package edu.austral.ingsis.clifford.parser;

import edu.austral.ingsis.clifford.command.Command;
import java.util.Optional;

public interface CommandParser {
  Optional<Command> getCommand(String commandLine);
}
