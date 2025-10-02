package org.csystem.util.io.file;

import org.csystem.util.io.diroctory.DirectoryUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.*;

/**
 * Utility class for file operations such as zipping, gzipping, and unzipping files.
 * <p>
 * This class provides static methods to compress and decompress files using ZIP and GZIP formats.
 * It cannot be instantiated.
 */
public final class FileUtil {
    /**
     * Default buffer size used for file operations.
     */
    private static final int DEFAULT_BUFFER_SIZE = 1024;

    /**
     * Private constructor to prevent instantiation.
     *
     * @throws UnsupportedOperationException always thrown to prevent instantiation
     */
    private FileUtil()
    {
        throw new UnsupportedOperationException("FileUtil can not be instantiated");
    }

    /**
     * Compresses a file into a ZIP archive.
     *
     * @param srcPath the path of the source file to zip
     * @param destPath the path of the destination ZIP file
     * @param bufSize the buffer size to use during compression
     * @throws IOException if an I/O error occurs
     */
    public static void zipFile(String srcPath, String destPath, int bufSize) throws IOException
    {
        zipFile(new File(srcPath), new File(destPath), bufSize);
    }

    /**
     * Compresses a file into a ZIP archive.
     *
     * @param srcFile the source file to zip
     * @param destFile the destination ZIP file
     * @param bufSize the buffer size to use during compression
     * @throws IOException if an I/O error occurs
     */
    public static void zipFile(File srcFile, File destFile, int bufSize) throws IOException
    {
        try (var fis = new FileInputStream(srcFile);
             var fos = new FileOutputStream(destFile);
             var zos = new ZipOutputStream(fos)) {

            var entry = new java.util.zip.ZipEntry(srcFile.getName());
            zos.putNextEntry(entry);

            var buffer = new byte[bufSize];
            int result;

            while ((result = fis.read(buffer)) > 0)
                zos.write(buffer, 0, result);

            zos.closeEntry();
        }
    }

    /**
     * Compresses a file into a ZIP archive using the default buffer size.
     *
     * @param srcPath the path of the source file to zip
     * @param destPath the path of the destination ZIP file
     * @throws IOException if an I/O error occurs
     */
    public static void zipFile(String srcPath, String destPath) throws IOException
    {
        zipFile(new File(srcPath), new File(destPath));
    }

    /**
     * Compresses a file into a ZIP archive using the default buffer size.
     *
     * @param srcFile the source file to zip
     * @param destFile the destination ZIP file
     * @throws IOException if an I/O error occurs
     */
    public static void zipFile(File srcFile, File destFile) throws IOException
    {
        zipFile(srcFile, destFile, DEFAULT_BUFFER_SIZE);
    }

    /**
     * Compresses a file into a GZIP archive.
     *
     * @param srcPath the path of the source file to gzip
     * @param destPath the path of the destination GZIP file
     * @param bufSize the buffer size to use during compression
     * @throws IOException if an I/O error occurs
     */
    public static void gzipFile(String srcPath, String destPath, int bufSize) throws IOException
    {
        gzipFile(new File(srcPath), new File(destPath), bufSize);
    }

    /**
     * Compresses a file into a GZIP archive.
     *
     * @param srcFile the source file to gzip
     * @param destFile the destination GZIP file
     * @param bufSize the buffer size to use during compression
     * @throws IOException if an I/O error occurs
     */
    public static void gzipFile(File srcFile, File destFile, int bufSize) throws IOException
    {

        try (var fis = new FileInputStream(srcFile);
             var fos = new FileOutputStream(destFile);
             var gzos = new GZIPOutputStream(fos)) {

            var buffer = new byte[bufSize];
            int result;

            while ((result = fis.read(buffer)) > 0)
                gzos.write(buffer, 0, result);
        }
    }

    /**
     * Compresses a file into a GZIP archive using the default buffer size.
     *
     * @param srcPath the path of the source file to gzip
     * @param destPath the path of the destination GZIP file
     * @throws IOException if an I/O error occurs
     */
    public static void gzipFile(String srcPath, String destPath) throws IOException
    {
        gzipFile(new File(srcPath), new File(destPath));
    }

    /**
     * Compresses a file into a GZIP archive using the default buffer size.
     *
     * @param srcFile the source file to gzip
     * @param destFile the destination GZIP file
     * @throws IOException if an I/O error occurs
     */
    public static void gzipFile(File srcFile, File destFile) throws IOException
    {
        gzipFile(srcFile, destFile, DEFAULT_BUFFER_SIZE);
    }

    /**
     * Extracts files from a ZIP archive.
     *
     * @param zipPath the path of the ZIP file to extract
     * @param destPath the path of the destination directory
     * @param bufSize the buffer size to use during extraction
     * @throws IOException if an I/O error occurs
     */
    public static void unzipFile(String zipPath, String destPath, int bufSize) throws IOException
    {
        unzipFile(new File(zipPath), new File(destPath), bufSize);
    }

    /**
     * Extracts files from a ZIP archive.
     *
     * @param zipFile the ZIP file to extract
     * @param destDir the destination directory
     * @param bufSize the buffer size to use during extraction
     * @throws IOException if an I/O error occurs
     */
    public static void unzipFile(File zipFile, File destDir, int bufSize) throws IOException
    {
        if (!destDir.exists())
            DirectoryUtil.createDirectory(destDir);

        try (var zis = new ZipInputStream(new FileInputStream(zipFile))) {
            ZipEntry entry;

            var buffer = new byte[bufSize];
            while ((entry = zis.getNextEntry()) != null) {
                var newFile = new File(destDir, entry.getName());

                if (!entry.isDirectory()) {
                    DirectoryUtil.createDirectory(newFile.getParent());
                    try (var fos = new FileOutputStream(newFile)) {
                        int result;

                        while ((result = zis.read(buffer)) > 0)
                            fos.write(buffer, 0, result);
                    }

                }
                else
                    DirectoryUtil.createDirectory(newFile);

                zis.closeEntry();
            }
        }
    }

    /**
     * Extracts files from a ZIP archive using the default buffer size.
     *
     * @param zipPath the path of the ZIP file to extract
     * @param destPath the path of the destination directory
     * @throws IOException if an I/O error occurs
     */
    public static void unzipFile(String zipPath, String destPath) throws IOException
    {
        unzipFile(new File(zipPath), new File(destPath));
    }

    /**
     * Extracts files from a ZIP archive using the default buffer size.
     *
     * @param zipFile the ZIP file to extract
     * @param destDir the destination directory
     * @throws IOException if an I/O error occurs
     */
    public static void unzipFile(File zipFile, File destDir) throws IOException
    {
        unzipFile(zipFile, destDir, DEFAULT_BUFFER_SIZE);
    }

    /**
     * Decompresses a GZIP file.
     *
     * @param zipPath the path of the GZIP file to decompress
     * @param destPath the path of the destination file
     * @param bufSize the buffer size to use during decompression
     * @throws IOException if an I/O error occurs
     */
    public static void gunzipFile(String zipPath, String destPath, int bufSize) throws IOException
    {
        gunzipFile(new File(zipPath), new File(destPath), bufSize);
    }

    /**
     * Decompresses a GZIP file.
     *
     * @param zipFile the GZIP file to decompress
     * @param destDir the destination file
     * @param bufSize the buffer size to use during decompression
     * @throws IOException if an I/O error occurs
     */
    public static void gunzipFile(File zipFile, File destDir, int bufSize) throws IOException
    {
        try (var fis = new FileInputStream(zipFile); var gzis = new GZIPInputStream(fis);
             var fos = new FileOutputStream(destDir)) {

            var buffer = new byte[bufSize];
            int result;

            while ((result = gzis.read(buffer)) > 0)
                fos.write(buffer, 0, result);
        }
    }

    /**
     * Decompresses a GZIP file using the default buffer size.
     *
     * @param zipPath the path of the GZIP file to decompress
     * @param destPath the path of the destination file
     * @throws IOException if an I/O error occurs
     */
    public static void gunzipFile(String zipPath, String destPath) throws IOException
    {
        gunzipFile(new File(zipPath), new File(destPath));
    }

    /**
     * Decompresses a GZIP file using the default buffer size.
     *
     * @param zipFile the GZIP file to decompress
     * @param destDir the destination file
     * @throws IOException if an I/O error occurs
     */
    public static void gunzipFile(File zipFile, File destDir) throws IOException
    {
        gunzipFile(zipFile, destDir, DEFAULT_BUFFER_SIZE);
    }
}
