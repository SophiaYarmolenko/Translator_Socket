package com.company;

import java.io.*;
import java.net.*;

public class Server
{
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    Translator translator = new Translator();

    private static final int PORT = 1033;

    public Server() throws IOException { }

    public void start(int port) throws IOException
    {
        try
        {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String word;
            String language;
            while (true)
            {
                word = in.readLine();
                language = in.readLine();
                if( translator.getTranslation(language, word) == null )
                {
                    out.println("Sorry, we can`t find this word in our dictionary");
                }
                else
                    {
                        String translation = translator.getTranslation(language, word);
                        out.println("Ok, your result: " + word + " + " + language + " -> " + translation);
                    }
            }
        }
        finally
        {
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        }

    }
    public static void main(String[] args) throws IOException
    {
        Server server = new Server();
        server.start(PORT);
    }
}
