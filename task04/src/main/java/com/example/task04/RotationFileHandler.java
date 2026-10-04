package com.example.task04;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class RotationFileHandler implements MessageHandler{
    private final String baseName;
    private final ChronoUnit rotationUnit;
    private final DateTimeFormatter formatter;
    private String currentSuffix;

    public RotationFileHandler(String baseName, ChronoUnit rotationUnit) {
        this.baseName = baseName;
        this.rotationUnit = rotationUnit;
        this.formatter = formatterFor(rotationUnit);
        this.currentSuffix = LocalDateTime.now().format(formatter);
    }

    private DateTimeFormatter formatterFor(ChronoUnit unit) {
        switch (unit) {
            case HOURS:  return DateTimeFormatter.ofPattern("yyyyMMdd_HH");
            case MINUTES: return DateTimeFormatter.ofPattern("yyyyMMdd_HHmm");
            case DAYS:   return DateTimeFormatter.ofPattern("yyyyMMdd");
            default:     return DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        }
    }

    @Override
    public void handle(String message) {
        String suffix = LocalDateTime.now().format(formatter);
        if (!suffix.equals(currentSuffix)) {
            currentSuffix = suffix;
        }
        String filename = baseName + "_" + currentSuffix + ".log";
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(message + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
