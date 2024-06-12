package edu.austral.ingsis.clifford.parser;

import edu.austral.ingsis.clifford.FileSystem;
import edu.austral.ingsis.clifford.command.Cd;
import edu.austral.ingsis.clifford.command.Command;
import edu.austral.ingsis.clifford.filesystem.Directory;
import edu.austral.ingsis.clifford.filesystem.File;
import edu.austral.ingsis.clifford.filesystem.Node;

import java.util.Optional;

public class CdParser implements CommandParser{

    private final FileSystem fileSystem;

    public CdParser(FileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }

    @Override
    public Optional<Command> getCommand(String commandLine) {

        String[] elements = commandLine.split(" ");
        if (elements.length != 2) {
            return Optional.empty();
        }

        String path = elements[1];
        Directory current = fileSystem.currentDirectory();
        if (path.charAt(0) == '/') {
            current = fileSystem.getRoot();
        }

        String[] e = path.split("/");
        for (String node : e){
            if (node.equals("..") && current.getParent() != null){
                current = (Directory) current.getParent();
            }
            else{
                boolean exists = false;
                for (Node n : current.getChildren()){
                    if (n.getName().equals(node)){
                        current = (Directory) n.getParent();
                        exists = true;
                    }
                }
                if (!exists){
                    return Optional.empty();
                }

            }
        }
        return Optional.of(new Cd(current));
    }
}
