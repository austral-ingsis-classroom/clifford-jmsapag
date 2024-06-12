package edu.austral.ingsis;

import edu.austral.ingsis.clifford.FileSystem;
import edu.austral.ingsis.clifford.FileSystemImpl;
import java.util.ArrayList;
import java.util.List;

public class FileSystemRunnerImpl implements FileSystemRunner {

  @Override
  public List<String> executeCommands(List<String> commands) {
    FileSystem fs = new FileSystemImpl();
    List<String> results = new ArrayList<>();
    for (String command : commands) {
      String result = fs.run(command);
      results.add(result);
    }
    return results;
  }
}
