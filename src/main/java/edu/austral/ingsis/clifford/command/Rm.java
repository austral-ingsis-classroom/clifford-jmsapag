package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.FileSystem;
import edu.austral.ingsis.clifford.filesystem.Directory;
import edu.austral.ingsis.clifford.filesystem.Node;
import java.util.List;
import java.util.Optional;

public class Rm implements Command {

  private final String node;
  private final Optional<String> flag;

  public Rm(String node, String flag) {
    this.node = node;
    this.flag = Optional.ofNullable(flag);
  }

  public Rm(String node) {
    this.node = node;
    this.flag = Optional.empty();
  }

  @Override
  public String execute(FileSystem fileSystem) {

    // get the current directory and its children
    Directory currentDirectory = fileSystem.currentDirectory();
    List<Node> children = currentDirectory.getChildren();

    // iterate over the children to find the node to remove
    for (Node child : children) {
      // if the node is found and it's a directory
      if (child.getName().equals(node) && child.isComposite()) {
        // if the flag is present remove the directory
        if (flag.isPresent()) {
          children.remove(child);
          return "'" + child.getName() + "' removed";
        } else {
          return "Failed to remove directory '" + child.getName() + "'. Use the appropriate flag.";
        }
      }
      // if it is a file we remove it
      else if (child.getName().equals(node) && !child.isComposite()) {
        children.remove(child);
        return "'" + child.getName() + "' removed";
      }
    }
    return "Failed to remove non-existing directory or file";
  }
}
