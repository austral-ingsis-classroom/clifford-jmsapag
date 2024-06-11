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
        this.flag = Optional.of(flag);
    }

    public Rm(String node) {
        this.node = node;
        this.flag = Optional.empty();
    }

    @Override
    public String execute(FileSystem fileSystem) {

        Directory currentDirectory = fileSystem.currentDirectory();
        List<Node> children = currentDirectory.getChildren();

        for (Node child : children) {
            if (child.getName().equals(node) && child.isComposite() && flag.isPresent()) {
                children.remove(child);
                return "'" + child.getName() + "' removed";
            }
            else if (child.getName().equals(node) && child.isComposite() && flag.isEmpty()) {
                return "Failed to remove directory '" + child.getName() + "'";
            }
            else if (child.getName().equals(node) && !child.isComposite()) {
                children.remove(child);
                return "'" + child.getName() + "' removed";
            }

        }
        return "Failed to remove non existing directory or file";
    }
}
