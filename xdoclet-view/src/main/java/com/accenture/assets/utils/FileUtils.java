package com.accenture.assets.utils;

import static com.accenture.assets.utils.StreamUtils.copyAndCloseStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.lang.StringUtils;


/**
 * Utils for Files.
 * 
 * @author Adrian Musante
 * 
 */
public class FileUtils {
	
    /**
     * The System property key for the Java IO temporary directory.
     */
    private static final String JAVA_IO_TMPDIR_KEY = "java.io.tmpdir";
	
	/**
	 * 
	 * @see java.lang.Class#getResource(String)
	 * 
	 * @param name
	 *            name of the desired resource
	 * @return A java.net.URL object or null if no resource with this name is
	 *         found
	 */
	public static URL getResource(String name) {
		return Class.class.getResource(name);
	}
	
    /**
     * Convert from a <code>URL</code> to a <code>File</code>.
     * <p>
     * This method will decode the URL.
     * Syntax such as <code>file:///my%20docs/file.txt</code> will be
     * correctly decoded to <code>/my docs/file.txt</code>.
     *
     * @param url  the file URL to convert, <code>null</code> returns <code>null</code>
     * @return the equivalent <code>File</code> object, or <code>null</code>
     *  if the URL's protocol is not <code>file</code>
     * @throws IllegalArgumentException if the file is incorrectly encoded
     */
    public static File toFile(URL url) {
        if (url == null || !url.getProtocol().equals("file")) {
            return null;
        } else {
            String filename = url.getFile().replace('/', File.separatorChar);
            int pos =0;
            while ((pos = filename.indexOf('%', pos)) >= 0) {
                if (pos + 2 < filename.length()) {
                    String hexStr = filename.substring(pos + 1, pos + 3);
                    char ch = (char) Integer.parseInt(hexStr, 16);
                    filename = filename.substring(0, pos) + ch + filename.substring(pos + 3);
                }
            }
            return new File(filename);
        }
    }
	
	/**
	 * Creates a new directory in the temporal folder.
	 * 
	 * @param deleteOnShutdown
	 *            <code>true</code>, If the directory has to be erased on
	 *            shutdown of the <i>Java virtual machine</i>, otherwise
	 *            <code>false</code>.
	 * @return a new directory in the temporal folder.
	 * 
	 * @throws SecurityException
	 *             If a security manager exists and its <code>{@link
	 *          java.lang.SecurityManager#checkWrite(java.lang.String)}</code>
	 *             method does not permit the named directory to be created.
	 *             
	 * @see Runtime#addShutdownHook(Thread)
	 */
	public static File createTempDirectory(boolean deleteOnShutdown) throws SecurityException {
		final File temp = new File(System.getProperty(JAVA_IO_TMPDIR_KEY),
				Long.toString(System.nanoTime()));
		temp.mkdir();
		if(deleteOnShutdown){
			Runtime.getRuntime().addShutdownHook(new Thread() {
				@Override
				public void run() {
					try {
						FileUtils.forceDelete(temp);
					} catch (IOException e) {/*Ignore*/}
				}
			});
		}
		return temp;
	}
    
	/**
	 * Ensures that <code>f</code> exists.
	 * 
	 * @param f
	 *            file
	 * @return <b>true:</b> If file exists, otherwise It will be try to create
	 *         directory with this abstract pathname.<br/>
	 * 
	 *         <b>false:</b> otherwise.
	 * @see java.io.File#exists()
	 * @see java.io.File#mkdir()
	 * 
	 */
	public static boolean ensureDirectoryExists(final File f) {
		return f.exists() || f.mkdir();
	}
	
	// Copy
	// -----------------------------------------------------------------------
	
    /**
     * Convenience method to copy a file from a source to a destination. No filtering is performed.
     *
     * @param toCopy the file to copy from. Must not be <code>null</code>.
     * @param destFile the file to copy to. Must not be <code>null</code>.
     * 
     * @throws IOException if an IO error occurs during copying
     *
     */
	public static void copyFile(final File toCopy, final File destFile) throws IOException {
        if (toCopy == null) {
            throw new NullPointerException("Source must not be null");
        }
        if (destFile == null) {
            throw new NullPointerException("Destination must not be null");
        }
        if (!toCopy.exists()) {
            throw new FileNotFoundException("Source '" + toCopy + "' does not exist");
        }
        if (toCopy.isDirectory()) {
            throw new IOException("Source '" + toCopy + "' exists but is a directory");
        }
        if (toCopy.getCanonicalPath().equals(destFile.getCanonicalPath())) {
            throw new IOException("Source '" + toCopy + "' and destination '" + destFile + "' are the same");
        }
        if (destFile.getParentFile() != null && !destFile.getParentFile().exists()) {
            if (!destFile.getParentFile().mkdirs()) {
                throw new IOException("Destination '" + destFile + "' directory cannot be created");
            }
        }
        if (destFile.exists() && !destFile.canWrite()) {
            throw new IOException("Destination '" + destFile + "' exists but is read-only");
        }	
        copyAndCloseStream(openInputStream(toCopy), openOutputStream(destFile));
	}
	
    /**
     * Copies a file to a directory preserving the file date.
     * <p>
     * This method copies the contents of the specified source file
     * to a file of the same name in the specified destination directory.
     * The destination directory is created if it does not exist.
     * If the destination file exists, then this method will overwrite it.
     *
     * @param srcFile  an existing file to copy, must not be <code>null</code>
     * @param destDir  the directory to place the copy in, must not be <code>null</code>
     *
     * @throws NullPointerException if source or destination is null
     * @throws IOException if source or destination is invalid
     * @throws IOException if an IO error occurs during copying
     * @see #copyFile(File, File)
     */
	public static void copyFileToDirectory(File srcFile, File destDir) throws IOException{
		 if (destDir.exists() && !destDir.isDirectory()) {
	            throw new IllegalArgumentException("Destination '" + destDir + "' is not a directory");
	      }
		 
		 copyFile(srcFile, new File(destDir, srcFile.getName()));
	}
	
	public static void copyResourcesRecursively(final URL originUrl,
			final File destination) throws IOException {
		final URLConnection urlConnection = originUrl.openConnection();
		if (urlConnection instanceof JarURLConnection) {
			FileUtils.copyJarResourcesRecursively(destination,
					(JarURLConnection) urlConnection);
		} else {
			FileUtils.copyFilesRecusively(new File(originUrl.getPath()),
					destination);
		}
	}

	public static void copyJarResourcesRecursively(final File destDir,
			final JarURLConnection jarConnection) throws IOException {

		final JarFile jarFile = jarConnection.getJarFile();

		for (final Enumeration<JarEntry> e = jarFile.entries(); e
				.hasMoreElements();) {
			final JarEntry entry = e.nextElement();
			if (entry.getName().startsWith(jarConnection.getEntryName())) {
				final String filename = StringUtils.removeStart(
						entry.getName(), jarConnection.getEntryName());

				final File f = new File(destDir, filename);
				if (!entry.isDirectory()) {
					copyAndCloseStream(jarFile.getInputStream(entry),
							openOutputStream(f));
				} else if (!FileUtils.ensureDirectoryExists(f)) {
					throw new IOException("Could not create directory: "
							+ f.getAbsolutePath());
				}
			}
		}
	}

	private static void copyFilesRecusively(final File toCopy,
			final File destDir) throws IOException {
		assert destDir.isDirectory();
		
		if (!toCopy.isDirectory()) {
			FileUtils.copyFile(toCopy, new File(destDir, toCopy.getName()));
		} else {
			final File newDestDir = new File(destDir, toCopy.getName());
			if (!newDestDir.exists() && !newDestDir.mkdir()) {
				return;
			}
			for (final File child : toCopy.listFiles()) {
				FileUtils.copyFilesRecusively(child, newDestDir);
			}
		}
	}
	
	// Move
	// -----------------------------------------------------------------------
	
	/**
	 * Move a file from one location to another.  An attempt is made to rename
	 * the file and if that fails, the file is copied and the old file deleted.
	 *
	 * If the destination file already exists, an exception will be thrown.
	 *
	 * @param from file which should be moved.
	 * @param to desired destination of the file.
	 * @throws IOException if an error occurs.
	 *
	 */
	public static void move(File from, File to) throws IOException {
		move(from, to, false);
	}
	
	/**
	 * Move a file from one location to another. An attempt is made to rename
	 * the file and if that fails, the file is copied and the old file deleted.
	 * 
	 * @param from file which should be moved.
	 * @param to desired destination of the file.
	 * @param overwrite If false, an exception will be 
	 * 						thrown rather than overwrite a file.
	 * @throws IOException
	 *             if an error occurs.
	 * 
	 */
	public static void move(File from, File to, boolean overwrite)
			throws IOException {
		if (to.exists()) {
			if (overwrite) {
				forceDelete(to);
			} else {
				throw new IOException(to.toString() + " already exists.");
			}
		}

		if (!from.renameTo(to)){
			copyFile(from, to);
			try {
				forceDelete(from);
			} catch (IOException e) {
				IOException _e = new IOException(MessageFormat.format(
						"{0} copied to {1} but original could not be deleted.",
						from.toString(), to.toString()));
				_e.initCause(e);//Compatibility with jdk5
				throw _e;
			}
		}
	}
	
	// Delete
	// -----------------------------------------------------------------------
	
    /**
     * Delete a file. If file is a directory, delete it and all sub-directories.
     * <p>
     * The difference between File.delete() and this method are:
     * <ul>
     * <li>A directory to be deleted does not have to be empty.</li>
     * <li>You get exceptions when a file or directory cannot be deleted.
     *      (java.io.File methods returns a boolean)</li>
     * </ul>
     *
     * @param file  file or directory to delete, must not be <code>null</code>
     * @throws NullPointerException if the directory is <code>null</code>
     * @throws IOException in case deletion is unsuccessful
     */
    public static void forceDelete(File file) throws IOException {
        if (file.isDirectory()) {
            deleteDirectory(file);
        } else {
            if (!file.exists()) {
                throw new FileNotFoundException("File does not exist: " + file);
            }
            if (!file.delete()) {
                throw new IOException("Unable to delete file: " + file);
            }
        }
    }
	
    /**
     * Clean a directory without deleting it.
     *
     * @param directory directory to clean
     * @throws IOException in case cleaning is unsuccessful
     */
    public static void cleanDirectory(File directory) throws IOException {
        if (!directory.exists()) {
            String message = directory + " does not exist";
            throw new IllegalArgumentException(message);
        }

        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory + " is not a directory");
        }

        File[] files = directory.listFiles();
        if (files == null) {  // null if security restricted
            throw new IOException("Failed to list contents of " + directory);
        }

        IOException exception = null;
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            try {
                forceDelete(file);
            } catch (IOException ioe) {
                exception = ioe;
            }
        }

        if (null != exception) {
            throw exception;
        }
    }
    
    /**
     * Recursively delete a directory.
     *
     * @param directory  directory to delete
     * @throws IOException in case deletion is unsuccessful
     */
	private static void deleteDirectory(File directory) throws IOException {
		if (directory.exists()) {
			cleanDirectory(directory);
			if (!directory.delete()) {
				throw new IOException("Unable to delete directory " + directory);
			}
		}
	}

	// File Stream
	// -----------------------------------------------------------------------
	
    /**
     * Opens a {@link FileInputStream} for the specified file, providing better
     * error messages than simply calling <code>new FileInputStream(file)</code>.
     * <p>
     * At the end of the method either the stream will be successfully opened,
     * or an exception will have been thrown.
     * <p>
     * An exception is thrown if the file does not exist.
     * An exception is thrown if the file object exists but is a directory.
     * An exception is thrown if the file exists but cannot be read.
     * 
     * @param file  the file to open for input, must not be <code>null</code>
     * @return a new {@link FileInputStream} for the specified file
     * @throws FileNotFoundException if the file does not exist
     * @throws IOException if the file object is a directory
     * @throws IOException if the file cannot be read
     */
    public static FileInputStream openInputStream(File file) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File '" + file + "' exists but is a directory");
            }
            if (!file.canRead()) {
                throw new IOException("File '" + file + "' cannot be read");
            }
        } else {
            throw new FileNotFoundException("File '" + file + "' does not exist");
        }
        return new FileInputStream(file);
    }

    /**
     * Opens a {@link FileOutputStream} for the specified file, checking and
     * creating the parent directory if it does not exist.
     * <p>
     * At the end of the method either the stream will be successfully opened,
     * or an exception will have been thrown.
     * <p>
     * The parent directory will be created if it does not exist.
     * The file will be created if it does not exist.
     * An exception is thrown if the file object exists but is a directory.
     * An exception is thrown if the file exists but cannot be written to.
     * An exception is thrown if the parent directory cannot be created.
     * 
     * @param file  the file to open for output, must not be <code>null</code>
     * @return a new {@link FileOutputStream} for the specified file
     * @throws IOException if the file object is a directory
     * @throws IOException if the file cannot be written to
     * @throws IOException if a parent directory needs creating but that fails
     */
    public static FileOutputStream openOutputStream(File file) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File '" + file + "' exists but is a directory");
            }
            if (!file.canWrite()) {
                throw new IOException("File '" + file + "' cannot be written to");
            }
        } else {
            File parent = file.getParentFile();
            if (parent != null && !parent.exists() && !parent.mkdirs()) {
                throw new IOException("File '" + file + "' could not be created");
            }
        }
        return new FileOutputStream(file);
    }
    
    
    //nn
    /**
     * Clean a directory iff contains files.
     *
     * @param directory directory to clean
     * @throws IOException in case cleaning is unsuccessful
     */
    public static void resetDirectory(File directory) {

        if(directory.exists()){
	        File[] files = directory.listFiles();
	        if (files != null) 
	        	for (int i = 0; i < files.length; i++) files[i].delete();
        }
    }
    
}