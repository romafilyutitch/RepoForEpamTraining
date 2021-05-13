package com.epam.jwd.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class IOUtil {
    private static final String RESOURCES_INPUT_DIRECTORY_PATH = "src\\main\\resources\\input\\";
    private static final String RESOURCES_OUTPUT_DIRECTORY_PATH = "src\\main\\resources\\output\\";

    public static String readTextFromFileInResources(String fileName) throws IOException {
        final StringBuilder builder = new StringBuilder();
        final Path filePath = Paths.get(RESOURCES_INPUT_DIRECTORY_PATH + fileName + ".txt");
        Files.lines(filePath).forEach(line -> builder.append(line).append("\n"));
        return builder.toString();
    }

    public static void writeFileToOutputInResources(String text, String fileName) throws IOException {
        final Path filePath = Paths.get(RESOURCES_OUTPUT_DIRECTORY_PATH + fileName + ".txt");
        Files.writeString(filePath, text, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
    }
}
