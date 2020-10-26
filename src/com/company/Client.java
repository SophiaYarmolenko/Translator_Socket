package com.company;
import java.io.*;
import java.net.Socket;

public class Client
{

    private static Socket clientSocket;
    private static BufferedReader reader;
    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args)
    {
        try
        {
            try
            {
                clientSocket = new Socket("localhost", 1033);
                reader = new BufferedReader(new InputStreamReader(System.in));
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                System.out.println("Hi! Give me a word to translate ( you can choose one of this language: Ukrainian, Russian, English, French, German ): " );
                String word = reader.readLine();
                out.write(word + "\n");

                System.out.println("Ok! Now I need a language to translate into ( Ukrainian, Russian, English, French, German ): " );
                String language = reader.readLine();
                out.write(language + "\n");

                out.flush();

                String serverResult = in.readLine();
                System.out.println(serverResult);
            }
            finally
            {
                System.out.println("Bye!");
                clientSocket.close();
                in.close();
                out.close();
            }
        }
        catch (IOException e)
        {
            System.err.println(e);
        }

    }
}