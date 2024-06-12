package edu.austral.ingsis.clifford.filesystem;

import java.util.List;
import java.util.Optional;

public class Directory implements Node {

  private final List<Node> children;
  private final String name;
  private final Optional<Directory> parent;

  public Directory(String name, List<Node> children, Directory parent) {
    this.name = name;
    this.children = children;
    this.parent = Optional.of(parent);
  }

  public Directory(String name, List<Node> children) {
    this.name = name;
    this.children = children;
    this.parent = Optional.empty();
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Directory getParent() {
    return parent.orElse(null);
  }

  @Override
  public boolean isComposite() {
    return true;
  }

  public List<Node> getChildren() {
    return children;
  }

  public void addChild(Node child) {
    children.add(child);
  }

  public void removeChild(String childName) {
    children.removeIf(child -> child.getName().equals(childName));
  }
}
