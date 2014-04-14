package com.accenture.assets.utils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Utils for Streams.
 * 
 * @author adrian.musante
 * 
 */
public class StreamUtils {

	/**
	 * Buffer size when reading from input stream.
	 * 
	 */
	private final static int BUFFER_SIZE = 1024;

	/**
	 * 
	 * @see java.lang.Class#getResourceAsStream(String)
	 * 
	 * @param name
	 *            name of the desired resource
	 * @return A java.io.InputStream object or null if no resource with this
	 *         name is found
	 */
	public static InputStream getResource(String name) {
		return Class.class.getResourceAsStream(name);
	}

	/**
	 * Closes this stream and releases any system resources associated with it.
	 * If the stream is already closed then invoking this method has no effect.
	 * 
	 * @param resource
	 *            Stream
	 * 
	 * @return <b>true:</b> If the stream is closed.<br/>
	 * 
	 *         <b>false:</b> If an I/O error occurs.
	 * 
	 */
	public static Boolean close(Closeable resource) {
		Boolean closed = Boolean.TRUE;
		if (resource != null) {
			try {
				resource.close();
			} catch (IOException e) {
				closed = Boolean.FALSE;
			}
		}
		return closed;
	}

	/**
	 * Copy the data from the input stream to the output stream.
	 * 
	 * @param in
	 *            data source
	 * @param out
	 *            data destination
	 * @throws IOException
	 *             in an input or output error occurs
	 * 
	 */
	public static void copy(final InputStream in, final OutputStream out)
			throws IOException {
		final byte[] buffer = new byte[BUFFER_SIZE];
		int read;
		while ((read = in.read(buffer)) > 0) {
			out.write(buffer, 0, read);
		}
	}

	/**
	 * Copy the data from the input stream to the output stream and closes the
	 * streams with which it worked.
	 * 
	 * @param in
	 *            data source
	 * @param out
	 *            data destination
	 * @throws IOException
	 *             in an input or output error occurs
	 * 
	 */
	public static void copyAndCloseStream(final InputStream in,
			final OutputStream out) throws IOException {
		try {
			copy(in, out);
		} finally {
			close(in);
			close(out);
		}
	}

}
