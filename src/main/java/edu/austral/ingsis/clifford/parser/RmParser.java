package edu.austral.ingsis.clifford.parser;

import edu.austral.ingsis.clifford.command.Command;
import edu.austral.ingsis.clifford.command.Rm;
import java.util.Optional;

public class RmParser implements CommandParser {

  @Override
  public Optional<Command> getCommand(String commandLine) {

    String[] elements = commandLine.split(" ");
    if (elements.length == 3) {
      return Optional.of(new Rm(elements[2], elements[1]));
    } else if (elements.length == 2) {
      return Optional.of(new Rm(elements[1]));
    }

    return Optional.empty();
  }
}
