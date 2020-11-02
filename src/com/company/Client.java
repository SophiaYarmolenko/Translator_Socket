package com.company;
import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class Client
{
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private FileWriter fileWriter;

    private static final String EXIT = "exit";
    private static final String HOST = "localhost";
    private static final int PORT = 1033;

    public Client() throws IOException {
    }

    public void start(String host, int port) throws IOException
    {
        try
        {
            clientSocket = new Socket(host, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        }
        catch (IOException e)
        {
            in.close();
            out.close();
            clientSocket.close();
        }
    }

    public String sendWordAndLanguage(String word, String language) throws IOException
    {
        out.println(word + "\n" + language);
        String serverTranslation = in.readLine();
        return serverTranslation;
    }

    public static void main(String[] args) throws IOException
    {
        Date date;
        Scanner scanner = new Scanner((System.in));

        Client client = new Client();
        client.start(HOST, PORT);
        client.fileWriter = new FileWriter("history.txt");
        String word = "";
        String language = "";

        date = new Date();
        client.fileWriter.write(date.toString() + " > " + "word for translation (English, French, German, Russian, Ukrainian):" +"\n");
        System.out.println("word for translation (English, French, German, Russian, Ukrainian):");

        while (scanner.hasNext())
        {
            word = scanner.nextLine();
            date = new Date();
            client.fileWriter.write(date.toString() + " > " + word + "\n");
            if(word.equals(EXIT))
                break;
            System.out.println("translation language (English, French, German, Russian, Ukrainian):");
            language = scanner.nextLine();
            date = new Date();
            client.fileWriter.write(date.toString() + " > " + language + "\n");
            if(language.equals(EXIT))
                break;
            String serverResult = client.sendWordAndLanguage(word, language);
            System.out.println(serverResult);
            date = new Date();
            client.fileWriter.write(date.toString() + " > " + serverResult+"\n");
            System.out.println("word for translation (English, French, German, Russian, Ukrainian):");
            date = new Date();
            client.fileWriter.write(date.toString() + " > " + "word for translation (English, French, German, Russian, Ukrainian):"+"\n");
        }
        client.clientSocket.close();
        client.fileWriter.close();
    }
}
