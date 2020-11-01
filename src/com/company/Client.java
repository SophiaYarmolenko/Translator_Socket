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
    private FileWriter fileWriter = new FileWriter(new File("history.txt"));

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
        Date date = new Date();
        Scanner scanner = new Scanner((System.in));

        Client client = new Client();
        client.start(HOST, PORT);
        String word;
        String language;

        client.fileWriter.append(date.toString() + " > " + "word for translation (English, French, German, Russian, Ukrainian):" );
        System.out.println("word for translation (English, French, German, Russian, Ukrainian):");
        while (scanner.hasNext())
        {
            word = scanner.nextLine();
            client.fileWriter.append(date.toString() + " > " + word);
            System.out.println("translation language (English, French, German, Russian, Ukrainian):");
            language = scanner.nextLine();
            client.fileWriter.append(date.toString() + " > " + language);
            String serverResult = client.sendWordAndLanguage(word, language);
            System.out.println(serverResult);
            client.fileWriter.append(date.toString() + " > " + serverResult);
            System.out.println("word for translation (English, French, German, Russian, Ukrainian):");
            client.fileWriter.append(date.toString() + " > " + "word for translation (English, French, German, Russian, Ukrainian):");
        }
        client.fileWriter.close();
    }
}
