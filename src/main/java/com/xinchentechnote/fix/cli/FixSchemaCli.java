package com.xinchentechnote.fix.cli;

import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(
    name = "fix-schema",
    mixinStandardHelpOptions = true,
    version = "fix-schema 0.1",
    description = "FIX schema tools",
    subcommands = {GenerateCommand.class})
public class FixSchemaCli implements Runnable {

  @Override
  public void run() {
    CommandLine.usage(this, System.out);
  }

  public static void main(String[] args) {
    int exitCode = new CommandLine(new FixSchemaCli()).execute(args);
    System.exit(exitCode);
  }
}
