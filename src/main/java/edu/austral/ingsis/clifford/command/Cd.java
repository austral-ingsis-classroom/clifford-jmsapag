package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.FileSystem;
import edu.austral.ingsis.clifford.filesystem.Directory;
import edu.austral.ingsis.clifford.filesystem.Node;

public class Cd implements Command {

    private final Node node;

    public Cd(Node node) {
        this.node = node;
    }

    @Override
    public String execute(FileSystem fileSystem) {
        if (node.isComposite()){
            fileSystem.setCurrentDirectory((Directory) node);
            return "Moved to directory: '" + node.getName() + "'";
        }
        return "Failed to move";
    }
}
