package edu.austral.ingsis.clifford;

import edu.austral.ingsis.clifford.filesystem.Directory;

public interface FileSystem {
  public String run(String commandLine);

  public Directory getRoot();

  public Directory currentDirectory();

  public void setCurrentDirectory(Directory directory);
}
