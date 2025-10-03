package org.csystem.util.io.directory;

import org.junit.jupiter.api.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

class DirectoryUtilTest {
    private Path tempDir;

    @BeforeEach
    void setUp() throws Exception {
        tempDir = Files.createTempDirectory("dirutiltest");
    }

    @AfterEach
    void tearDown() throws Exception {
        Files.walk(tempDir)
            .map(Path::toFile)
            .forEach(File::delete);
    }

    @Test
    void createDirectoryShouldReturnTrueWhenDirectoryIsCreated() {
        var dirPath = tempDir.resolve("newDir").toString();

        Assertions.assertTrue(org.csystem.util.io.directory.DirectoryUtil.createDirectory(dirPath));
        Assertions.assertTrue(Files.isDirectory(Path.of(dirPath)));
    }

    @Test
    void createDirectoryShouldReturnFalseWhenDirectoryAlreadyExists() {
        var dirPath = tempDir.resolve("existingDir").toString();

        new File(dirPath).mkdir();
        Assertions.assertFalse(org.csystem.util.io.directory.DirectoryUtil.createDirectory(dirPath));
    }

    @Test
    void createDirectoryShouldReturnFalseWhenParentDoesNotExist() {
        var dirPath = tempDir.resolve("nonexistentParent/newDir").toString();
        Assertions.assertFalse(org.csystem.util.io.directory.DirectoryUtil.createDirectory(dirPath));
    }

    @Test
    void creatAlleDirectoryShouldReturnTrueWhenAllDirectoriesAreCreated() {
        var dirPath = tempDir.resolve("parent/child/grandchild").toString();

        Assertions.assertTrue(org.csystem.util.io.directory.DirectoryUtil.creatAllDirectories(dirPath));
        Assertions.assertTrue(Files.isDirectory(Path.of(dirPath)));
    }

    @Test
    void creatAlleDirectoryShouldReturnFalseWhenDirectoryAlreadyExists() {
        var dirPath = tempDir.resolve("alreadyExists").toString();

        new File(dirPath).mkdirs();
        Assertions.assertFalse(org.csystem.util.io.directory.DirectoryUtil.creatAllDirectories(dirPath));
    }

    @Test
    void isDirectoryShouldReturnTrueForExistingDirectory() {
        var dirPath = tempDir.resolve("dirCheck").toString();

        new File(dirPath).mkdir();
        Assertions.assertTrue(org.csystem.util.io.directory.DirectoryUtil.isDirectory(dirPath));
    }

    @Test
    void isDirectoryShouldReturnFalseForNonExistingPath() {
        var dirPath = tempDir.resolve("nonexistentDir").toString();

        Assertions.assertFalse(org.csystem.util.io.directory.DirectoryUtil.isDirectory(dirPath));
    }

    @Test
    void isDirectoryShouldReturnFalseForFilePath() throws Exception {
        var filePath = tempDir.resolve("file.txt").toString();

        Files.createFile(Path.of(filePath));
        Assertions.assertFalse(org.csystem.util.io.directory.DirectoryUtil.isDirectory(filePath));
    }
}
