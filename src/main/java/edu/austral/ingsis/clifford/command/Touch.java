package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.FileSystem;
import edu.austral.ingsis.clifford.filesystem.Directory;
import edu.austral.ingsis.clifford.filesystem.File;
import edu.austral.ingsis.clifford.filesystem.Node;
import java.util.List;

public class Touch implements Command {

  private final String fileName;

  public Touch(String fileName) {
    this.fileName = fileName;
  }

  @Override
  public String execute(FileSystem fileSystem) {

    // validate file name
    if (fileName.contains("/") || fileName.contains(" ")) {
      return "File name cannot contain '/' or space";
    }

    // get the current directory and its children
    Directory currentDirectory = fileSystem.currentDirectory();
    List<Node> children = currentDirectory.getChildren();

    // check if a file with the same name already exists, if so, remove it
    for (Node child : children) {
      if (child.getName().equals(fileName) && !child.isComposite()) {
        currentDirectory.removeChild(fileName);
        break;
      }
    }

    // create a new file and add it to the current directory
    Node file = new File(fileName, currentDirectory);
    currentDirectory.addChild(file);

    // return a success message
    return "'" + fileName + "' file created";
  }
}
