package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.FileSystem;
import edu.austral.ingsis.clifford.filesystem.Directory;
import edu.austral.ingsis.clifford.filesystem.Node;

public class Cd implements Command {

  private final String path;

  public Cd(String path) {
    this.path = path;
  }

  @Override
  public String execute(FileSystem fileSystem) {

    // determine the starting directory based on whether the path is absolute or relative
    Directory current = path.startsWith("/") ? fileSystem.getRoot() : fileSystem.currentDirectory();

    // split the path into individual directory components
    String[] directories = path.split("/");

    // navigate through each directory in the path
    for (String dir : directories) {
      current = navigateToDirectory(current, dir);
      // if the directory does not exist, return an error message
      if (current == null) {
        return "'" + dir + "' directory does not exist";
      }
    }

    // set the current directory in the file system to the target directory
    fileSystem.setCurrentDirectory(current);
    return "moved to directory '" + current.getName() + "'";
  }

  private Directory navigateToDirectory(Directory current, String dir) {

    // ignore empty and current directory components (".")
    if (dir.isEmpty() || dir.equals(".")) {
      return current;
    }

    // handle parent directory navigation
    if (dir.equals("..")) {
      return current.getParent() != null ? current.getParent() : current;
    }

    // search for the directory among the current directory's children
    for (Node child : current.getChildren()) {
      if (child.getName().equals(dir) && child instanceof Directory) {
        return (Directory) child;
      }
    }

    // return null if the directory does not exist
    return null;
  }
}
