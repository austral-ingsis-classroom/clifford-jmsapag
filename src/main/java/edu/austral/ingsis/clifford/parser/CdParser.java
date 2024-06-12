package edu.austral.ingsis.clifford.parser;

import edu.austral.ingsis.clifford.command.Cd;
import edu.austral.ingsis.clifford.command.Command;
import java.util.Optional;

public class CdParser implements CommandParser {

  @Override
  public Optional<Command> getCommand(String commandLine) {
    String path = commandLine.split(" ")[1];
    return Optional.of(new Cd(path));
  }
}
