package com.xinchentechnote.fix.cli;

import com.xinchentechnote.fix.gen.CodeGenerator;
import com.xinchentechnote.fix.gen.JavaFixJsonCodecGenerator;
import com.xinchentechnote.fix.gen.MsgCodeModel;
import com.xinchentechnote.fix.out.FreemarkerHelper;
import com.xinchentechnote.fix.parser.Fix;
import com.xinchentechnote.fix.parser.FixXmlParser;
import freemarker.template.TemplateException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "generate", description = "Generate code from FIX schema")
public class GenerateCommand implements Runnable {

  @Option(names = "--xml", required = true, description = "Schema source xml file")
  String schema;

  @Option(names = "--lang", required = true, description = "Target language (java)")
  String lang;

  @Option(names = "--package", required = true, description = "Base package name")
  String packageName;

  @Option(names = "--out", required = true, description = "Output directory")
  String outDir;

  @Override
  public void run() {
    File file = new File(schema);
    if (!file.exists()) {
      System.err.println("Schema file does not exist: " + schema);
      return;
    }
    System.out.println("Generating code for " + lang + " to " + outDir + " from schema " + schema);
    try {
      FileInputStream fis = new FileInputStream(file);
      FixXmlParser loader = new FixXmlParser();
      Fix fix = loader.loadFix(fis);
      CodeGenerator generator = new JavaFixJsonCodecGenerator();
      List<MsgCodeModel> msgCodeModels = generator.parseCodeModel(fix, packageName);
      msgCodeModels.stream()
          .filter(model -> "Logon".equals(model.getMessageName()))
          .forEach(
              model -> {
                System.out.println("-------------------------------");
                System.out.println(
                    "Message: " + model.getMessageName() + " (" + model.getMessageType() + ")");
                System.out.println("Encode Code:");
                System.out.println(model.getEncodeCode());
                System.out.println("Decode Code:");
                System.out.println(model.getDecodeCode());
                System.out.println("-------------------------------");
                try {
                  FreemarkerHelper.renderAndWriteToFile(
                      "fixJsonCodec.ftl", model, outDir, packageName);
                } catch (IOException e) {
                  throw new RuntimeException(e);
                } catch (TemplateException e) {
                  throw new RuntimeException(e);
                }
              });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
