package com.restassured.framework.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class for file operations
 */
public class FileUtil {
    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    private FileUtil() {
        // Private constructor to prevent instantiation
    }

    /**
     * Read file content as string
     * 
     * @param filePath Path to the file
     * @return File content as string
     */
    public static String readFileAsString(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            logger.error("Error reading file: {}", filePath, e);
            throw new RuntimeException("Error reading file: " + filePath, e);
        }
    }

    /**
     * Write string content to file
     * 
     * @param filePath Path to the file
     * @param content  Content to write
     */
    public static void writeStringToFile(String filePath, String content) {
        try {
            Files.write(Paths.get(filePath), content.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            logger.error("Error writing to file: {}", filePath, e);
            throw new RuntimeException("Error writing to file: " + filePath, e);
        }
    }

    /**
     * Check if file exists
     * 
     * @param filePath Path to the file
     * @return true if file exists, false otherwise
     */
    public static boolean fileExists(String filePath) {
        return Files.exists(Paths.get(filePath));
    }

    /**
     * Create directory if it doesn't exist
     * 
     * @param dirPath Path to the directory
     */
    public static void createDirectoryIfNotExists(String dirPath) {
        File directory = new File(dirPath);
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (created) {
                logger.info("Created directory: {}", dirPath);
            } else {
                logger.warn("Failed to create directory: {}", dirPath);
            }
        }
    }
}
