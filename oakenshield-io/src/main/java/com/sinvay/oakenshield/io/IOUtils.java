package com.sinvay.oakenshield.io;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * IO工具
 *
 * @author pizzalord
 * @since 1.0
 */
public class IOUtils {
    /**
     * 1KB
     */
    public static final int ONE_KB = 1024;
    /**
     * 1MB
     */
    public static final int ONE_MB = ONE_KB * ONE_KB;
    /**
     * 1GB
     */
    public static final int ONE_GB = ONE_KB * ONE_MB;

    /**
     * Line break for windows
     */
    public static final String LINE_BREAK_WIN = "\r\n";

    /**
     * Line break for unix
     */
    public static final String LINE_BREAK_UNIX = "\n";

    /**
     * Line break for mac
     */
    public static final String LINE_BREAK_MAC = "\r";

    /**
     * Illegal strings for filename
     */
    private static Pattern FILENAME_PATTERN = Pattern.compile("[\\s\\\\/:\\*\\?\\\"<>\\|]");

    /**
     * Create a directory by path if it is not exists
     *
     * @param path The path of the directory
     */
    public static void createDirectoryIfNotExists(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /**
     * Create a file by path if it is not exists
     *
     * @param path The path of the file
     */
    public static void createFileIfNotExists(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    /**
     * Clear a folder by path
     *
     * @param folderPath The folder path
     * @return successful
     */
    public static boolean clearFolder(String folderPath) {
        if (StringUtils.isEmpty(folderPath)) {
            return false;
        }
        File folderFile = new File(folderPath);
        if (!folderFile.exists()) {
            createDirectoryIfNotExists(folderPath);
            return true;
        }
        File[] fileList = folderFile.listFiles();
        for (File childFile : fileList) {
            if (childFile.isFile()) {
                if (!childFile.delete())
                    return false;
            } else if (childFile.isDirectory()) {
                clearFolder(childFile.getPath());
                if (!childFile.delete())
                    return false;
            }
        }
        return true;
    }

    /**
     * Let filename to be legal
     *
     * @param originFilename The origin filename
     * @return A legal filename based on the origin one
     */
    public static String getLegalFilename(String originFilename) {
        return FILENAME_PATTERN.matcher(originFilename).replaceAll("");
    }

    /**
     * Get system temporary directory
     *
     * @return The system temporary directory
     */
    public static String getSysTmpDir() {
        return System.getProperty("java.io.tmpdir");
    }

    /**
     * Get file output stream
     *
     * @param filePath Input file path
     * @return File output stream
     * @throws FileNotFoundException file not found exception
     */
    public static FileOutputStream getFileOutputStream(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        return new FileOutputStream(file);
    }

    /**
     * Determine file path exists or not
     *
     * @param filePath file path
     * @return file path exists or not
     */
    public static boolean exists(String filePath) {
        return new File(filePath).exists();
    }
}
