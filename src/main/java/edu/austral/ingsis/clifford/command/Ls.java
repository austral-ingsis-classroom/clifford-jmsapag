package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.FileSystem;
import edu.austral.ingsis.clifford.filesystem.Directory;
import edu.austral.ingsis.clifford.filesystem.Node;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Ls implements Command {

    private final Optional<String> flags;

    public Ls() {
        this.flags = Optional.empty();
    }

    public Ls(String flags) {
        this.flags = Optional.of(flags);
    }

    @Override
    public String execute(FileSystem fileSystem) {

        Directory currentDirectory = fileSystem.currentDirectory();
        List<Node> children = currentDirectory.getChildren();

        if (flags.isEmpty()) {
            return children.stream().map(Node::getName).collect(Collectors.joining(" "));
        } else {

            List<String> names = children.stream().map(Node::getName).collect(Collectors.toList());

            if (flags.get().equals("asc")) {
                names.sort(String::compareTo);
            }
            else if (flags.get().equals("desc")) {
                names.sort(Comparator.reverseOrder());
            }
            return String.join(" ", names);
        }
    }
}
