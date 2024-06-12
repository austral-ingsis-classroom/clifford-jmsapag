package edu.austral.ingsis.clifford.filesystem;

public class File implements Node {

  private final String name;
  private final Directory parent;

  public File(String name, Directory directory) {
    this.name = name;
    this.parent = directory;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Node getParent() {
    return parent;
  }

  @Override
  public boolean isComposite() {
    return false;
  }
}
