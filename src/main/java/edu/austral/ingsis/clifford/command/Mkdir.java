package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.FileSystem;
import edu.austral.ingsis.clifford.filesystem.Directory;
import edu.austral.ingsis.clifford.filesystem.Node;

import java.util.ArrayList;
import java.util.List;

public class Mkdir implements Command {

    private final String directoryName;

    public Mkdir(String directoryName) {
        this.directoryName = directoryName;
    }

    @Override
    public String execute(FileSystem fileSystem) {

        if (directoryName.contains("/") || directoryName.contains(" ")){
            return "Cannot contain / or space";
        }

        Directory currentDirectory = fileSystem.currentDirectory();
        List<Node> children = currentDirectory.getChildren();

        for (Node child : children) {
            if (child.getName().equals(directoryName) && child.isComposite()) {
                return "Existing directory with name " + child.getName();
            }
        }

        Node directory = new Directory(directoryName, new ArrayList<>(), currentDirectory);
        currentDirectory.addChild(directory);
        return "'" + directoryName + "' directory created";

    }
}
