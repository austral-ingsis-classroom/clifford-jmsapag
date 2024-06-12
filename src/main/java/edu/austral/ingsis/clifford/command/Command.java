package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.FileSystem;

public interface Command {
  public String execute(FileSystem fileSystem);
}
