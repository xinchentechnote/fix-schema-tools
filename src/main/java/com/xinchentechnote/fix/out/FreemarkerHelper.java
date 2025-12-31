package com.xinchentechnote.fix.out;

import com.xinchentechnote.fix.gen.MsgCodeModel;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FreemarkerHelper {

  private static final String TEMPLATE_DIR = "/template";
  private static Configuration configuration;

  static {
    try {
      initConfiguration();
    } catch (IOException e) {
      throw new RuntimeException("Failed to initialize FreeMarker configuration", e);
    }
  }

  private static void initConfiguration() throws IOException {
    configuration = new Configuration(Configuration.VERSION_2_3_32);

    configuration.setClassForTemplateLoading(FreemarkerHelper.class, TEMPLATE_DIR);

    configuration.setDefaultEncoding("UTF-8");
    configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    configuration.setLogTemplateExceptions(false);
    configuration.setWrapUncheckedExceptions(true);
    configuration.setFallbackOnNullLoopVariable(false);
  }

  public static void renderAndWriteToFile(
      String templateName, MsgCodeModel model, String outDir, String packageName)
      throws IOException, TemplateException {

    Path filePath = generateJavaFilePath(outDir, packageName, model.getMessageName());

    Files.createDirectories(filePath.getParent());

    Template template = configuration.getTemplate(templateName);

    try (Writer writer = new FileWriter(filePath.toFile())) {
      template.process(model, writer);
      System.out.println("✓ Generated: " + filePath.toAbsolutePath());
    }
  }

  public static Path generateJavaFilePath(String outDir, String packageName, String className) {
    if (outDir == null || packageName == null || className == null) {
      throw new IllegalArgumentException("outDir, packageName and className cannot be null");
    }

    outDir = outDir.trim();
    packageName = packageName.trim();
    className = className.trim();

    if (!className.endsWith(".java")) {
      className = className + "Codec.java";
    }

    String packagePath = packageName.replace('.', File.separatorChar);

    return Paths.get(outDir).resolve(packagePath).resolve(className);
  }

  public static void renderAndWriteMultiple(
      String templateName, String outDir, String basePackage, Iterable<MsgCodeModel> models)
      throws IOException, TemplateException {

    for (MsgCodeModel model : models) {
      renderAndWriteToFile(templateName, model, outDir, basePackage);
    }
  }
}
