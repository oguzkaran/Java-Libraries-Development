package com.karandev.util.net.ip.protocol.standard.text;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Client class for performing POP3 protocol operations.
 * <p>This class provides methods for connecting to a POP3 server, logging in, listing emails, and logging out using text-based communication over TCP sockets.</p>
 * <p>Copyleft (c) 1993 by C and System Programmers Association (CSD) All Rights Free</p>
 *
 * @author JavaApp2-Jan-2024 Group
 * @version 1.0.0
 */
public final class Pop3Client extends Client implements ILogin {
    private final String m_username;
    private final String m_password;
    private final BufferedWriter m_bw;
    private final BufferedReader m_br;

    /**
     * Reads the result lines from the POP3 server until a single dot (".") line is encountered.
     *
     * @return a list of result lines from the server.
     * @throws IOException if an I/O error occurs while reading from the server.
     */
    private List<String> getResult() throws IOException
    {
        String text;
        var result = new ArrayList<String>();

        while (!(text = m_br.readLine()).equals("."))
            result.add(text);

        return result;
    }

    /**
     * Attempts to log in to the POP3 server with the specified username and password.
     *
     * @param username the username to log in with.
     * @param password the password to log in with.
     * @return true if login is successful, false otherwise.
     * @throws IOException if an I/O error occurs during login.
     */
    private boolean login(String username, String password) throws IOException
    {
        m_bw.write(String.format("USER %s\r\n", username));
        m_bw.flush();
        var result = m_br.readLine();

        if (result.startsWith("-ERR"))
            return false;

        m_bw.write(String.format("PASS %s\r\n", password));
        m_bw.flush();
        result = m_br.readLine();

        return result.startsWith("+OK");
    }

    /**
     * Constructs a Pop3Client and connects to the specified POP3 server.
     *
     * @param server the POP3 server address.
     * @param username the username for authentication.
     * @param password the password for authentication.
     * @throws IOException if an I/O error occurs during connection.
     */
    public Pop3Client(String server, String username, String password) throws IOException
    {
        m_username = username;
        m_password = password;
        socket = new Socket(server, 110);
        m_br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        m_bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    /**
     * Logs in to the POP3 server using the credentials provided at construction.
     *
     * @return true if login is successful, false otherwise.
     * @throws IOException if an I/O error occurs during login.
     */
    @Override
    public boolean login() throws IOException
    {
        return login(m_username, m_password);
    }

    /**
     * Logs out from the POP3 server.
     *
     * @return true if logout is successful, false otherwise.
     * @throws IOException if an I/O error occurs during logout.
     */
    @Override
    public boolean logout() throws IOException
    {
        m_bw.write("QUIT %s\r\n");
        m_bw.flush();
        m_br.readLine();

        return true;
    }

    /**
     * Retrieves a list of emails from the POP3 server.
     *
     * @return a list of email information strings.
     * @throws IOException if an I/O error occurs during retrieval.
     */
    public List<String> listEmail() throws IOException
    {
        m_bw.write("LIST\r\n");
        m_bw.flush();

        return getResult();
    }

    /**
     * Retrieves the content of an email with the specified number.
     *
     * @param no the email number to retrieve.
     * @return a list of strings representing the email content.
     * @throws IOException if an I/O error occurs during retrieval.
     */
    public List<String> retreiveEmail(int no) throws IOException
    {
        m_bw.write(String.format("RETR %d\r\n", no));
        m_bw.flush();

        return getResult();
    }

    //...

    @Override
    public void close() throws IOException
    {
        logout();
        super.close();
    }
}
