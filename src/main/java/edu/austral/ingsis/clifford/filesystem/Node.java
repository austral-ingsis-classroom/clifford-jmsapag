package edu.austral.ingsis.clifford.filesystem;

public interface Node {
    String getName();
    Node getParent();
    boolean isComposite();
}
