package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.FileSystem;
import edu.austral.ingsis.clifford.filesystem.Directory;

public class Pwd implements Command {

  @Override
  public String execute(FileSystem fileSystem) {

    // get the current directory from the file system
    Directory currentDirectory = fileSystem.currentDirectory();

    // get the full path of the current directory
    String fullPath = buildPath(currentDirectory);

    // return the full path, ensure it starts with /
    return fullPath.isEmpty() ? "/" : fullPath;
  }

  // recursive method to build the full path from the current directory
  private String buildPath(Directory currentDirectory) {

    // base case: if the current directory has no parent, return its name or root
    if (currentDirectory.getParent() == null) {
      return "";
    }

    // recursive call: get the parent's path, then append the current directory's name
    return buildPath(currentDirectory.getParent()) + "/" + currentDirectory.getName();
  }
}
