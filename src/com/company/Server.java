package com.company;

import java.io.*;
import java.net.*;

public class Server
{
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    GetTranslate translator = new GetTranslate();

    private static final int PORT = 1033;
    private static final String EXIT = "exit";

    public Server() throws IOException {}

    public void start(int port) throws IOException
    {
        try
        {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String languageFrom;
            String languageTo;
            String word;
            while (!EXIT.equals(languageFrom = in.readLine()))
            {
                languageTo = in.readLine();
                word = in.readLine();

                if( translator.getTranslation(languageFrom, languageTo, word) == null )
                {
                    out.println("Sorry, we can`t translate");
                }
                else
                    {
                        String translation = translator.getTranslation(languageFrom, languageTo, word);
                        out.println("Ok, your result: " + translation);
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
