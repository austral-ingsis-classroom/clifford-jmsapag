package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.FileSystem;
import edu.austral.ingsis.clifford.filesystem.Directory;

import java.util.ArrayList;
import java.util.List;

public class Pwd implements Command {

    @Override
    public String execute(FileSystem fileSystem) {
        Directory currentDirectory = fileSystem.currentDirectory();
        List<String> folders = new ArrayList<>();
        while (currentDirectory.getParent() != null) {
            folders.add(currentDirectory.getName());
        }
        StringBuilder path = new StringBuilder();
        for (int i = folders.size() - 1; i >= 0; i--) {
            path.append("/").append(folders.get(i));
        }
        return path.toString();
    }
}
