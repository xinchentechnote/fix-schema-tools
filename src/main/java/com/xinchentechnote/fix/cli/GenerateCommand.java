package com.xinchentechnote.fix.cli;

import com.xinchentechnote.fix.gen.MsgCodeModel;
import com.xinchentechnote.fix.gen.CodeGenerator;
import com.xinchentechnote.fix.gen.JavaFixJsonCodecGenerator;
import com.xinchentechnote.fix.out.FreemarkerHelper;
import com.xinchentechnote.fix.parser.v2.FixSchema;
import com.xinchentechnote.fix.parser.v2.FixXmlDomParser;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
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

  @Option(
      names = "--messages",
      required = true,
      description = "Message names to generate, comma separated")
  String messageNames;

  @Option(names = "--out", required = true, description = "Output directory")
  String outDir;

  @Override
  public void run() {
    System.out.println("Generating code for " + lang + " to " + outDir + " from schema " + schema);
    try {
      FixXmlDomParser loader = new FixXmlDomParser();
      FixSchema fix = loader.parse(schema);
      CodeGenerator generator = new JavaFixJsonCodecGenerator();
      Set<String> msgNames = new HashSet<>();
      if (!StringUtils.isEmpty(messageNames)) {
        msgNames = Set.of(messageNames.split(","));
      }
      List<MsgCodeModel> msgCodeModels = generator.parseCodeModel(fix, packageName, msgNames);
      Set<String> finalMsgNames = msgNames;
      msgCodeModels.stream()
          .filter(
              model -> finalMsgNames.isEmpty() || finalMsgNames.contains(model.getMessageName()))
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
