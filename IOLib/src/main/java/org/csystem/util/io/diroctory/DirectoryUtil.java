package org.csystem.util.io.diroctory;

    import java.io.File;

    /**
     * Utility class for directory operations.
     * <p>
     * This class provides static methods to create directories and check if a path is a directory.
     * It cannot be instantiated.
     */
    public final class DirectoryUtil {
        /**
         * Private constructor to prevent instantiation.
         *
         * @throws UnsupportedOperationException always thrown to prevent instantiation
         */
        private DirectoryUtil()
        {
            throw new UnsupportedOperationException("DirectoryUtil can not be instantiated");
        }

        /**
         * Creates a directory at the specified path.
         *
         * @param path the path of the directory to create
         * @return {@code true} if the directory was created, {@code false} otherwise
         */
        public static boolean createDirectory(String path)
        {
            return createDirectory(new File(path));
        }

        /**
         * Creates a directory represented by the given {@link File} object.
         *
         * @param dir the {@link File} object representing the directory to create
         * @return {@code true} if the directory was created, {@code false} otherwise
         */
        public static boolean createDirectory(File dir)
        {
            return dir.mkdir();
        }

        /**
         * Creates the directory named by the specified path, including any necessary but nonexistent parent directories.
         *
         * @param path the path of the directory to create
         * @return {@code true} if the directory and all nonexistent parent directories were created, {@code false} otherwise
         */
        public static boolean creatAlleDirectory(String path)
        {
            return creatAlleDirectory(new File(path));
        }

        /**
         * Creates the directory named by the given {@link File} object, including any necessary but nonexistent parent directories.
         *
         * @param dir the {@link File} object representing the directory to create
         * @return {@code true} if the directory and all nonexistent parent directories were created, {@code false} otherwise
         */
        public static boolean creatAlleDirectory(File dir)
        {
            return dir.mkdirs();
        }

        /**
         * Checks if the specified path is a directory.
         *
         * @param path the path to check
         * @return {@code true} if the path is a directory, {@code false} otherwise
         */
        public static boolean isDirectory(String path)
        {
            return new File(path).isDirectory();
        }
    }