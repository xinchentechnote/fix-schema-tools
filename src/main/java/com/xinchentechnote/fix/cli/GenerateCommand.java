package com.xinchentechnote.fix.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "generate", description = "Generate code from FIX schema")
public class GenerateCommand implements Runnable {

  @Option(names = "--schema", required = true, description = "Schema source (quickfixj|xml)")
  String schema;

  @Option(names = "--lang", required = true, description = "Target language (java|rust)")
  String lang;

  @Option(names = "--target", required = true, description = "Target output (codec|validator)")
  String target;

  @Option(names = "--out", required = true, description = "Output directory")
  String outDir;

  @Override
  public void run() {
    System.out.println("Generating " + target + " for " + lang);
  }
}
