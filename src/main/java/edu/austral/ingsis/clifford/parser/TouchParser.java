package edu.austral.ingsis.clifford.parser;

import edu.austral.ingsis.clifford.command.Command;
import edu.austral.ingsis.clifford.command.Touch;
import java.util.Optional;

public class TouchParser implements CommandParser {

  @Override
  public Optional<Command> getCommand(String commandLine) {
    String[] elements = commandLine.split(" ");
    if (elements.length != 2) {
      return Optional.empty();
    }
    return Optional.of(new Touch(elements[1]));
  }
}
