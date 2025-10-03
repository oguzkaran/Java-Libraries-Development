package org.csystem.util.io.file;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipInputStream;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilTests {
    private static final String TEST_DIR = "testDir";
    private static final String SRC_FILE = TEST_DIR + "/source.txt";
    private static final String ZIP_FILE = TEST_DIR + "/archive.zip";
    private static final String GZIP_FILE = TEST_DIR + "/archive.gz";
    private static final String UNZIP_DIR = TEST_DIR + "/unzipped";
    private static final String GUNZIP_FILE = TEST_DIR + "/gunzipped.txt";

    @BeforeEach
    void setUp() throws IOException {
        Files.createDirectories(Paths.get(TEST_DIR));
        Files.writeString(Paths.get(SRC_FILE), "Hello, world!");
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.walk(Paths.get(TEST_DIR))
                .map(Path::toFile)
                .sorted((a, b) -> b.toPath().getNameCount() - a.toPath().getNameCount())
                .forEach(File::delete);
    }

    @Test
    void zipFile_createsZipWithSingleFile() throws IOException {
        FileUtil.zipFile(SRC_FILE, ZIP_FILE);
        try (var zis = new ZipInputStream(new FileInputStream(ZIP_FILE))) {
            var entry = zis.getNextEntry();

            assertNotNull(entry);
            assertEquals("source.txt", entry.getName());
            assertNull(zis.getNextEntry());
        }
    }

    @Test
    void gzipFile_createsGzipFile() throws IOException {
        FileUtil.gzipFile(SRC_FILE, GZIP_FILE);
        try (var gzis = new GZIPInputStream(new FileInputStream(GZIP_FILE))) {
            var content = new String(gzis.readAllBytes());

            assertEquals("Hello, world!", content);
        }
    }

    @Test
    void unzipFile_extractsFileFromZip() throws IOException {
        FileUtil.zipFile(SRC_FILE, ZIP_FILE);
        FileUtil.unzipFile(ZIP_FILE, UNZIP_DIR);
        var extracted = Paths.get(UNZIP_DIR, "source.txt");
        assertTrue(Files.exists(extracted));
        assertEquals("Hello, world!", Files.readString(extracted));
    }

    @Test
    void gunzipFile_decompressesGzipFile() throws IOException {
        FileUtil.gzipFile(SRC_FILE, GZIP_FILE);
        FileUtil.gunzipFile(GZIP_FILE, GUNZIP_FILE);
        assertTrue(Files.exists(Paths.get(GUNZIP_FILE)));
        assertEquals("Hello, world!", Files.readString(Paths.get(GUNZIP_FILE)));
    }

    @Test
    void zipFile_throwsIOExceptionForNonExistentSource() {
        assertThrows(IOException.class, () -> FileUtil.zipFile("nonexistent.txt", ZIP_FILE));
    }

    @Test
    void gzipFile_throwsIOExceptionForNonExistentSource() {
        assertThrows(IOException.class, () -> FileUtil.gzipFile("nonexistent.txt", GZIP_FILE));
    }

    @Test
    void unzipFile_throwsIOExceptionForNonExistentZip() {
        assertThrows(IOException.class, () -> FileUtil.unzipFile("nonexistent.zip", UNZIP_DIR));
    }

    @Test
    void gunzipFile_throwsIOExceptionForNonExistentGzip() {
        assertThrows(IOException.class, () -> FileUtil.gunzipFile("nonexistent.gz", GUNZIP_FILE));
    }

    @Test
    void zipFile_createsZipWithEmptyFile() throws IOException {
        var emptyFile = TEST_DIR + "/empty.txt";
        Files.createFile(Paths.get(emptyFile));
        FileUtil.zipFile(emptyFile, ZIP_FILE);
        try (var zis = new ZipInputStream(new FileInputStream(ZIP_FILE))) {
            var entry = zis.getNextEntry();

            assertNotNull(entry);
            assertEquals("empty.txt", entry.getName());
            assertEquals(-1, zis.read());
        }
    }

    @Test
    void gzipFile_createsGzipWithEmptyFile() throws IOException {
        var emptyFile = TEST_DIR + "/empty.txt";
        Files.createFile(Paths.get(emptyFile));
        FileUtil.gzipFile(emptyFile, GZIP_FILE);
        try (var gzis = new GZIPInputStream(new FileInputStream(GZIP_FILE))) {
            assertEquals(-1, gzis.read());
        }
    }
}
