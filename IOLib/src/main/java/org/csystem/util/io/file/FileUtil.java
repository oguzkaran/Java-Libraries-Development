package org.csystem.util.io.file;

import java.io.File;

public final class FileUtil {
    private FileUtil()
    {
        throw new UnsupportedOperationException("FileUtil can not be instantiated");
    }

    public static void zipFile(String srcPath, String destPath)
    {
        zipFile(new File(srcPath), new File(destPath));
    }

    public static void zipFile(File srcFile, File destFile)
    {
        throw new UnsupportedOperationException("Not yet implemented!...");
    }

    public static void gzipFile(String srcPath, String destPath)
    {
        gzipFile(new File(srcPath), new File(destPath));
    }

    public static void gzipFile(File srcFile, File destFile)
    {
        throw new UnsupportedOperationException("Not yet implemented!...");
    }
}
