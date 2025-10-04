package com.karandev.util.net.http;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.security.Permission;
import java.util.List;
import java.util.Map;

/**
 * Utility class for managing HTTP connections using {@link HttpURLConnection}.
 * <p>Provides methods for opening, configuring, and interacting with HTTP connections, including header management, stream access, and connection properties.</p>
 * <p>Copyleft (c) 1993 by C and System Programmers Association (CSD) All Rights Free</p>
 *
 * @author JavaApp2-Jan-2024 Group
 * @version 1.0.0
 */
public final class HttpConnection implements Closeable {
    private final HttpURLConnection m_httpURLConnection;

    /**
     * Creates a new HttpConnection from a URL string.
     * @param urlStr the URL as a string
     * @throws IOException if an I/O error occurs
     */
    public HttpConnection(String urlStr) throws IOException
    {
        this(new URL(urlStr));
    }

    /**
     * Creates a new HttpConnection from a URL object.
     * @param url the URL
     * @throws IOException if an I/O error occurs
     */
    public HttpConnection(URL url) throws IOException
    {
        m_httpURLConnection = (HttpURLConnection)url.openConnection();
    }

    /**
     * Returns the underlying HttpURLConnection instance.
     * @return the HttpURLConnection
     */
    public HttpURLConnection getHttpURLConnection()
    {
        return m_httpURLConnection;
    }

    /**
     * Returns the key for the nth header field.
     * @param n the header field index
     * @return the header field key
     */
    public String getHeaderFieldKey(int n)
    {
        return m_httpURLConnection.getHeaderFieldKey(n);
    }

    /**
     * Sets the fixed content length for streaming mode.
     * @param contentLength the content length
     */
    public void setFixedLengthStreamingMode(int contentLength)
    {
        m_httpURLConnection.setFixedLengthStreamingMode(contentLength);
    }

    /**
     * Sets the fixed content length for streaming mode (long version).
     * @param contentLength the content length
     */
    public void setFixedLengthStreamingMode(long contentLength)
    {
        m_httpURLConnection.setFixedLengthStreamingMode(contentLength);
    }

    /**
     * Sets the chunked streaming mode with the specified chunk length.
     * @param chunklen the chunk length
     */
    public void setChunkedStreamingMode(int chunklen)
    {
        m_httpURLConnection.setChunkedStreamingMode(chunklen);
    }

    /**
     * Returns the value for the nth header field.
     * @param n the header field index
     * @return the header field value
     */
    public String getHeaderField(int n)
    {
        return m_httpURLConnection.getHeaderField(n);
    }

    /**
     * Sets whether HTTP redirects should be automatically followed for all connections.
     * @param set true to follow redirects, false otherwise
     */
    public static void setFollowRedirects(boolean set)
    {
        HttpURLConnection.setFollowRedirects(set);
    }

    /**
     * Returns whether HTTP redirects are automatically followed for all connections.
     * @return true if redirects are followed, false otherwise
     */
    public static boolean getFollowRedirects()
    {
        return HttpURLConnection.getFollowRedirects();
    }

    /**
     * Sets whether HTTP redirects should be automatically followed for this connection instance.
     * @param followRedirects true to follow redirects, false otherwise
     */
    public void setInstanceFollowRedirects(boolean followRedirects)
    {
        m_httpURLConnection.setInstanceFollowRedirects(followRedirects);
    }

    /**
     * Returns whether HTTP redirects are automatically followed for this connection instance.
     * @return true if redirects are followed, false otherwise
     */
    public boolean getInstanceFollowRedirects()
    {
        return m_httpURLConnection.getInstanceFollowRedirects();
    }

    /**
     * Sets the HTTP request method (e.g., "GET", "POST").
     * @param method the HTTP method
     * @throws ProtocolException if the method cannot be reset or is invalid
     */
    public void setRequestMethod(String method) throws ProtocolException
    {
        m_httpURLConnection.setRequestMethod(method);
    }

    /**
     * Returns the HTTP request method.
     * @return the HTTP method
     */
    public String getRequestMethod()
    {
        return m_httpURLConnection.getRequestMethod();
    }

    /**
     * Returns the HTTP response code from the server.
     * @return the HTTP response code
     * @throws IOException if an I/O error occurs
     */
    public int getResponseCode() throws IOException
    {
        return m_httpURLConnection.getResponseCode();
    }

    /**
     * Returns the HTTP response message from the server.
     * @return the HTTP response message
     * @throws IOException if an I/O error occurs
     */
    public String getResponseMessage() throws IOException
    {
        return m_httpURLConnection.getResponseMessage();
    }

    /**
     * Returns the date value of the specified header field as a long.
     * @param name the header field name
     * @param Default the default value if the field is not found
     * @return the header field date value, or Default if not found
     */
    public long getHeaderFieldDate(String name, long Default)
    {
        return m_httpURLConnection.getHeaderFieldDate(name, Default);
    }

    /**
     * Disconnects the HTTP connection.
     */
    public void disconnect()
    {
        m_httpURLConnection.disconnect();
    }

    /**
     * Returns true if the connection is using a proxy.
     * @return true if using a proxy, false otherwise
     */
    public boolean usingProxy()
    {
        return m_httpURLConnection.usingProxy();
    }

    /**
     * Returns the permission required to connect to the destination host and port.
     * @return the required Permission
     * @throws IOException if an I/O error occurs
     */
    public Permission getPermission() throws IOException
    {
        return m_httpURLConnection.getPermission();
    }

    /**
     * Returns the error stream if the connection failed but the server sent useful data.
     * @return the error stream, or null if no error
     */
    public InputStream getErrorStream()
    {
        return m_httpURLConnection.getErrorStream();
    }

    /**
     * Returns the system-wide FileNameMap.
     * @return the FileNameMap
     */
    public static FileNameMap getFileNameMap()
    {
        return URLConnection.getFileNameMap();
    }

    /**
     * Sets the system-wide FileNameMap.
     * @param map the FileNameMap to set
     */
    public static void setFileNameMap(FileNameMap map)
    {
        URLConnection.setFileNameMap(map);
    }

    /**
     * Opens a communications link to the resource referenced by this URL, if not already open.
     * @throws IOException if an I/O error occurs
     */
    public void connect() throws IOException
    {
        m_httpURLConnection.connect();
    }

    /**
     * Sets the connection timeout value in milliseconds.
     * @param timeout the timeout value in milliseconds
     */
    public void setConnectTimeout(int timeout)
    {
        m_httpURLConnection.setConnectTimeout(timeout);
    }

    /**
     * Returns the connection timeout value in milliseconds.
     * @return the timeout value in milliseconds
     */
    public int getConnectTimeout()
    {
        return m_httpURLConnection.getConnectTimeout();
    }

    /**
     * Sets the read timeout value in milliseconds.
     * @param timeout the timeout value in milliseconds
     */
    public void setReadTimeout(int timeout)
    {
        m_httpURLConnection.setReadTimeout(timeout);
    }

    /**
     * Returns the read timeout value in milliseconds.
     * @return the timeout value in milliseconds
     */
    public int getReadTimeout()
    {
        return m_httpURLConnection.getReadTimeout();
    }

    /**
     * Returns the URL for this connection.
     * @return the URL
     */
    public URL getURL()
    {
        return m_httpURLConnection.getURL();
    }

    /**
     * Returns the content length of the response.
     * @return the content length, or -1 if not known
     */
    public int getContentLength()
    {
        return m_httpURLConnection.getContentLength();
    }

    /**
     * Returns the content length of the response as a long.
     * @return the content length, or -1 if not known
     */
    public long getContentLengthLong()
    {
        return m_httpURLConnection.getContentLengthLong();
    }

    /**
     * Returns the content type of the response.
     * @return the content type, or null if not known
     */
    public String getContentType()
    {
        return m_httpURLConnection.getContentType();
    }

    /**
     * Returns the content encoding of the response.
     * @return the content encoding, or null if not known
     */
    public String getContentEncoding()
    {
        return m_httpURLConnection.getContentEncoding();
    }

    /**
     * Returns the expiration date of the resource.
     * @return the expiration date, or 0 if not known
     */
    public long getExpiration()
    {
        return m_httpURLConnection.getExpiration();
    }

    /**
     * Returns the date the resource was sent.
     * @return the date, or 0 if not known
     */
    public long getDate()
    {
        return m_httpURLConnection.getDate();
    }

    /**
     * Returns the last modified date of the resource.
     * @return the last modified date, or 0 if not known
     */
    public long getLastModified()
    {
        return m_httpURLConnection.getLastModified();
    }

    /**
     * Returns the value of the named header field.
     * @param name the header field name
     * @return the header field value, or null if not known
     */
    public String getHeaderField(String name)
    {
        return m_httpURLConnection.getHeaderField(name);
    }

    /**
     * Returns an unmodifiable map of the header fields.
     * @return a map of header fields
     */
    public Map<String, List<String>> getHeaderFields()
    {
        return m_httpURLConnection.getHeaderFields();
    }

    /**
     * Returns the value of the named header field as an int.
     * @param name the header field name
     * @param Default the default value if the field is not found
     * @return the header field value as an int, or Default if not found
     */
    public int getHeaderFieldInt(String name, int Default)
    {
        return m_httpURLConnection.getHeaderFieldInt(name, Default);
    }

    /**
     * Returns the value of the named header field as a long.
     * @param name the header field name
     * @param Default the default value if the field is not found
     * @return the header field value as a long, or Default if not found
     */
    public long getHeaderFieldLong(String name, long Default)
    {
        return m_httpURLConnection.getHeaderFieldLong(name, Default);
    }

    /**
     * Returns the content of the response as an Object.
     * @return the content object
     * @throws IOException if an I/O error occurs
     */
    public Object getContent() throws IOException
    {
        return m_httpURLConnection.getContent();
    }

    /**
     * Returns the content of the response as an Object, using the specified classes for content negotiation.
     * @param classes the content handler classes
     * @return the content object
     * @throws IOException if an I/O error occurs
     */
    public Object getContent(Class<?>[] classes) throws IOException
    {
        return m_httpURLConnection.getContent(classes);
    }

    /**
     * Returns an input stream that reads from this open connection.
     * @return the input stream
     * @throws IOException if an I/O error occurs
     */
    public InputStream getInputStream() throws IOException
    {
        return m_httpURLConnection.getInputStream();
    }

    /**
     * Returns an output stream that writes to this open connection.
     * @return the output stream
     * @throws IOException if an I/O error occurs
     */
    public OutputStream getOutputStream() throws IOException
    {
        return m_httpURLConnection.getOutputStream();
    }

    /**
     * Sets the value of the doInput field for this connection to the specified value.
     * @param doinput true if the connection is used for input
     */
    public void setDoInput(boolean doinput)
    {
        m_httpURLConnection.setDoInput(doinput);
    }

    /**
     * Returns the value of this connection's doInput field.
     * @return true if the connection is used for input
     */
    public boolean getDoInput()
    {
        return m_httpURLConnection.getDoInput();
    }

    /**
     * Sets the value of the doOutput field for this connection to the specified value.
     * @param dooutput true if the connection is used for output
     */
    public void setDoOutput(boolean dooutput)
    {
        m_httpURLConnection.setDoOutput(dooutput);
    }

    /**
     * Returns the value of this connection's doOutput field.
     * @return true if the connection is used for output
     */
    public boolean getDoOutput()
    {
        return m_httpURLConnection.getDoOutput();
    }

    /**
     * Sets the value of the allowUserInteraction field for this connection to the specified value.
     * @param allowuserinteraction true if user interaction is allowed
     */
    public void setAllowUserInteraction(boolean allowuserinteraction)
    {
        m_httpURLConnection.setAllowUserInteraction(allowuserinteraction);
    }

    /**
     * Returns the value of this connection's allowUserInteraction field.
     * @return true if user interaction is allowed
     */
    public boolean getAllowUserInteraction()
    {
        return m_httpURLConnection.getAllowUserInteraction();
    }

    /**
     * Sets the default value of the allowUserInteraction field for all connections.
     * @param defaultallowuserinteraction true if user interaction is allowed by default
     */
    public static void setDefaultAllowUserInteraction(boolean defaultallowuserinteraction)
    {
        URLConnection.setDefaultAllowUserInteraction(defaultallowuserinteraction);
    }

    /**
     * Returns the default value of the allowUserInteraction field for all connections.
     * @return true if user interaction is allowed by default
     */
    public static boolean getDefaultAllowUserInteraction()
    {
        return URLConnection.getDefaultAllowUserInteraction();
    }

    /**
     * Sets the value of the useCaches field for this connection to the specified value.
     * @param usecaches true if caching is enabled
     */
    public void setUseCaches(boolean usecaches)
    {
        m_httpURLConnection.setUseCaches(usecaches);
    }

    /**
     * Returns the value of this connection's useCaches field.
     * @return true if caching is enabled
     */
    public boolean getUseCaches()
    {
        return m_httpURLConnection.getUseCaches();
    }

    /**
     * Sets the value of the ifModifiedSince field for this connection to the specified value.
     * @param ifmodifiedsince the modification time in milliseconds
     */
    public void setIfModifiedSince(long ifmodifiedsince)
    {
        m_httpURLConnection.setIfModifiedSince(ifmodifiedsince);
    }

    /**
     * Returns the value of this connection's ifModifiedSince field.
     * @return the modification time in milliseconds
     */
    public long getIfModifiedSince()
    {
        return m_httpURLConnection.getIfModifiedSince();
    }

    /**
     * Returns the default value of the useCaches field for this connection.
     * @return true if caching is enabled by default
     */
    public boolean getDefaultUseCaches()
    {
        return m_httpURLConnection.getDefaultUseCaches();
    }

    /**
     * Sets the default value of the useCaches field for this connection.
     * @param defaultusecaches true if caching is enabled by default
     */
    public void setDefaultUseCaches(boolean defaultusecaches)
    {
        m_httpURLConnection.setDefaultUseCaches(defaultusecaches);
    }

    /**
     * Sets a general request property specified by a key-value pair.
     * @param key the property key
     * @param value the property value
     */
    public void setRequestProperty(String key, String value)
    {
        m_httpURLConnection.setRequestProperty(key, value);
    }

    /**
     * Adds a general request property specified by a key-value pair.
     * @param key the property key
     * @param value the property value
     */
    public void addRequestProperty(String key, String value)
    {
        m_httpURLConnection.addRequestProperty(key, value);
    }

    /**
     * Returns the value of the named request property.
     * @param key the property key
     * @return the property value, or null if not set
     */
    public String getRequestProperty(String key)
    {
        return m_httpURLConnection.getRequestProperty(key);
    }

    /**
     * Returns an unmodifiable map of the request properties.
     * @return a map of request properties
     */
    public Map<String, List<String>> getRequestProperties()
    {
        return m_httpURLConnection.getRequestProperties();
    }

    /**
     * Sets the system-wide ContentHandlerFactory.
     * @param fac the ContentHandlerFactory to set
     */
    public static void setContentHandlerFactory(ContentHandlerFactory fac)
    {
        URLConnection.setContentHandlerFactory(fac);
    }

    /**
     * Tries to determine the content type of a file based on its name.
     * @param fname the file name
     * @return the guessed content type, or null if not known
     */
    public static String guessContentTypeFromName(String fname)
    {
        return URLConnection.guessContentTypeFromName(fname);
    }

    /**
     * Tries to determine the content type of a file based on the input stream.
     * @param is the input stream
     * @return the guessed content type, or null if not known
     * @throws IOException if an I/O error occurs
     */
    public static String guessContentTypeFromStream(InputStream is) throws IOException
    {
        return URLConnection.guessContentTypeFromStream(is);
    }

    /**
     * Closes this HTTP connection and releases any system resources associated with it.
     */
    @Override
    public void close()
    {
        m_httpURLConnection.disconnect();
    }
}
