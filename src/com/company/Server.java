package com.company;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    private static Socket clientSocket;
    private static ServerSocket server;
    private static BufferedReader in;
    private static BufferedWriter out;
    private static Translator translator;

    static
    {
        try
        {
            translator = new Translator();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        try
        {
            try
            {
                server = new ServerSocket(1033);
                System.out.println("Ready to translate!");

                clientSocket = server.accept();
                try
                {
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                    String word = in.readLine();
                    String language = in.readLine();

                    if( translator.getTranslation(language, word) == null )
                    {
                        out.write("Sorry, we did not find this word in our dictionary");
                    }
                    out.write("Ok, your result: " + word + " + " + language + " -> " + translator.getTranslation(language, word));
                    out.flush();
                }
                finally
                {
                    clientSocket.close();
                    in.close();
                    out.close();
                }
            }
            finally
            {
                System.out.println("Bye!");
                server.close();
            }
        }
        catch (IOException e)
        {
            System.err.println(e);
        }
    }
}