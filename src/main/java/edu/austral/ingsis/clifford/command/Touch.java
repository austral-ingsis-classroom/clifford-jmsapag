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

        if (fileName.contains("/") || fileName.contains(" ")){
            return "Cannot contain / or space";
        }

        Directory currentDirectory = fileSystem.currentDirectory();
        List<Node> children = currentDirectory.getChildren();

        for (Node child : children) {
            if (child.getName().equals(fileName) && !child.isComposite()) {
                return "Existing file with name " + child.getName();
            }
        }

        Node file = new File(fileName, currentDirectory);
        currentDirectory.addChild(file);
        return "'" + fileName + "' file created";
    }
}
