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

    // get the current directory
    Directory current = fileSystem.currentDirectory();

    // if the path starts with / we go and fetch the root
    if (path.charAt(0) == '/') {
      current = fileSystem.getRoot();
    }

    // split into individual directories
    String[] e = path.split("/");

    // iterate every possible directory
    for (String node : e) {

      // if there is a .., we want to go to a parent directory
      if (node.equals("..") && (current.getParent() != null)) {
        current = current.getParent(); // if there is a parent
      } else if (node.equals("..") && (current.getParent() == null)) {
        current = fileSystem.getRoot(); // if there is not it is the root
      } else {
        // look for the directory in the current directory's children
        boolean exists = false;
        for (Node n : current.getChildren()) {
          if (n.getName().equals(node)) {
            current = (Directory) n;
            exists = true;
          }
        }
        if (!exists) {
          return "'" + node + "' directory does not exist";
        }
      }
    }
    fileSystem.setCurrentDirectory(current);
    return "moved to directory '" + current.getName() + "'";
  }
}
