package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.FileSystem;
import edu.austral.ingsis.clifford.filesystem.Directory;
import edu.austral.ingsis.clifford.filesystem.Node;

public class Cd implements Command {

    private final Directory directory;

    public Cd(Directory directory) {
        this.directory = directory;
    }

    @Override
    public String execute(FileSystem fileSystem) {
        fileSystem.setCurrentDirectory(directory);
        return "Moved to directory: '" + directory.getName() + "'";
    }
}
