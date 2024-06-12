package edu.austral.ingsis.clifford;

import edu.austral.ingsis.clifford.command.Command;
import edu.austral.ingsis.clifford.filesystem.Directory;
import edu.austral.ingsis.clifford.parser.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FileSystemImpl implements FileSystem {

  private Directory currentDirectory;
  private Directory rootDirectory;
  private Map<String, CommandParser> parsers;

  public FileSystemImpl() {
    this.rootDirectory = new Directory("/", new ArrayList<>());
    this.currentDirectory = rootDirectory;
    this.parsers = createParsersMap();
  }

  private Map<String, CommandParser> createParsersMap() {
    Map<String, CommandParser> parsers = new HashMap<>();
    parsers.put("ls", new LsParser());
    parsers.put("cd", new CdParser());
    parsers.put("touch", new TouchParser());
    parsers.put("mkdir", new MkdirParser());
    parsers.put("rm", new RmParser());
    parsers.put("pwd", new PwdParser());
    return parsers;
  }

  @Override
  public String run(String commandLine) {
    Optional<Command> optionalCommand =
        parsers.get(commandLine.split(" ")[0]).getCommand(commandLine);
    if (optionalCommand.isEmpty()) {
      return "Failed";
    }
    Command command = optionalCommand.get();
    return command.execute(this);
  }

  @Override
  public Directory getRoot() {
    return rootDirectory;
  }

  @Override
  public Directory currentDirectory() {
    return currentDirectory;
  }

  @Override
  public void setCurrentDirectory(Directory directory) {
    currentDirectory = directory;
  }
}
