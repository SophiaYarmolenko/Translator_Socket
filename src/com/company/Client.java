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
    public static final String GOOGLE_LANGUAGES = " [\"ps\",\"пушту\"]\n " +
            "[\"pt\",\"португальский\"]\n" +
            "[\"hmn\",\"хмонг\"]\n" +
            "[\"hr\",\"хорватский\"]\n" +
            "[\"ht\",\"креольский (Гаити)\"]\n" +
            "[\"hu\",\"венгерский\"]\n" +
            "[\"yi\",\"идиш\"]\n" +
            "[\"hy\",\"армянский\"]\n" +
            "[\"yo\",\"йоруба\"]\n" +
            "[\"id\",\"индонезийский\"]\n" +
            "[\"ig\",\"игбо\"]\n" +
            "[\"af\",\"африкаанс\"]\n" +
            "[\"is\",\"исландский\"]\n" +
            "[\"it\",\"итальянский\"]\n" +
            "[\"am\",\"амхарский\"]\n" +
            "[\"iw\",\"иврит\"]\n" +
            "[\"ar\",\"арабский\"]\n" +
            "[\"ja\",\"японский\"]\n" +
            "[\"az\",\"азербайджанский\"]\n" +
            "[\"zu\",\"зулу\"]\n" +
            "[\"ro\",\"румынский\"]\n" +
            "[\"ceb\",\"себуанский\"]\n" +
            "[\"be\",\"белорусский\"]\n" +
            "[\"ru\",\"русский\"]\n" +
            "[\"bg\",\"болгарский\"]\n" +
            "[\"rw\",\"руанда\"]\n" +
            "[\"bn\",\"бенгальский\"]\n" +
            "[\"jw\",\"яванский\"]\n" +
            "[\"bs\",\"боснийский\"]\n" +
            "[\"sd\",\"синдхи\"]\n" +
            "[\"ka\",\"грузинский\"]\n" +
            "[\"si\",\"сингальский\"]\n" +
            "[\"sk\",\"словацкий\"]\n" +
            "[\"sl\",\"словенский\"]\n" +
            "[\"sm\",\"самоанский\"]\n" +
            "[\"sn\",\"шона\"]\n" +
            "[\"so\",\"сомалийский\"]\n" +
            "[\"sq\",\"албанский\"]\n" +
            "[\"ca\",\"каталанский\"]\n" +
            "[\"sr\",\"сербский\"]\n" +
            "[\"kk\",\"казахский\"]\n" +
            "[\"st\",\"сесото\"]\n" +
            "[\"km\",\"кхмерский\"]\n" +
            "[\"su\",\"суданский\"]\n" +
            "[\"kn\",\"каннада\"]\n" +
            "[\"sv\",\"шведский\"]\n" +
            "[\"ko\",\"корейский\"]\n" +
            "[\"sw\",\"суахили\"]\n" +
            "[\"ku\",\"курманджи\"]\n" +
            "[\"co\",\"корсиканский\"]\n" +
            "[\"ta\",\"тамильский\"]\n" +
            "[\"ky\",\"киргизский\"]\n" +
            "[\"cs\",\"чешский\"]\n" +
            "[\"te\",\"телугу\"]\n" +
            "[\"tg\",\"таджикский\"]\n" +
            "[\"th\",\"тайский\"]\n" +
            "[\"la\",\"латинский\"]\n" +
            "[\"cy\",\"валлийский\"]\n" +
            "[\"lb\",\"люксембургский\"]\n" +
            "[\"tk\",\"туркменский\"]\n" +
            "[\"tl\",\"филиппинский\"]\n" +
            "[\"da\",\"датский\"]\n" +
            "[\"tr\",\"турецкий\"]\n" +
            "[\"tt\",\"татарский\"]\n" +
            "[\"de\",\"немецкий\"]\n" +
            "[\"auto\",\"Определить язык\"]\n" +
            "[\"lo\",\"лаосский\"]\n" +
            "[\"lt\",\"литовский\"]\n" +
            "[\"lv\",\"латышский\"]\n" +
            "[\"zh-CN\",\"китайский\"]\n" +
            "[\"ug\",\"уйгурский\"]\n" +
            "[\"uk\",\"украинский\"]\n" +
            "[\"mg\",\"малагасийский\"]\n" +
            "[\"mi\",\"маори\"]\n" +
            "[\"ur\",\"урду\"]\n" +
            "[\"mk\",\"македонский\"]\n" +
            "[\"haw\",\"гавайский\"]\n" +
            "[\"ml\",\"малаялам\"]\n" +
            "[\"mn\",\"монгольский\"]\n" +
            "[\"mr\",\"маратхи\"]\n" +
            "[\"uz\",\"узбекский\"]\n" +
            "[\"ms\",\"малайский\"]\n" +
            "[\"el\",\"греческий\"]\n" +
            "[\"mt\",\"мальтийский\"]\n" +
            "[\"en\",\"английский\"]\n" +
            "[\"eo\",\"эсперанто\"]\n" +
            "[\"my\",\"бирманский\"]\n" +
            "[\"es\",\"испанский\"]\n" +
            "[\"et\",\"эстонский\"]\n" +
            "[\"eu\",\"баскский\"]\n" +
            "[\"vi\",\"вьетнамский\"]\n" +
            "[\"ne\",\"непальский\"]\n" +
            "[\"fa\",\"персидский\"]\n" +
            "[\"nl\",\"нидерландский\"]\n" +
            "[\"no\",\"норвежский\"]\n" +
            "[\"fi\",\"финский\"]\n" +
            "[\"ny\",\"чева\"]\n" +
            "[\"fr\",\"французский\"]\n" +
            "[\"fy\",\"фризский\"]\n" +
            "[\"ga\",\"ирландский\"]\n" +
            "[\"gd\",\"шотландский (гэльский)\"]\n" +
            "[\"or\",\"ория\"]\n" +
            "[\"gl\",\"галисийский\"]\n" +
            "[\"gu\",\"гуджарати\"]\n" +
            "[\"xh\",\"кхоса\"]\n" +
            "[\"pa\",\"панджаби\"]\n" +
            "[\"ha\",\"хауса\"]\n" +
            "[\"pl\",\"польский\"]\n";

    public Client() throws IOException {}

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

    public String sendWordAndLanguage(String languageFrom, String languageTo, String word) throws IOException
    {
        out.println( languageFrom + "\n" + languageTo + "\n" + word );
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
        String languageFrom = "";
        String languageTo = "";
        String word = "";

        System.out.println("Our translator knows such languages: " + GOOGLE_LANGUAGES + "\n");
        System.out.println("select the language from which you want to translate (Example: \"en\" without quotes):");

        while (scanner.hasNext())
        {
            languageFrom = scanner.nextLine();
            date = new Date();
            client.fileWriter.write(date.toString() + " > " + languageFrom + "\n");
            if(languageFrom.equals(EXIT))
            {
                String serverResult = client.sendWordAndLanguage("exit", "exit", "exit");
                break;
            }

            System.out.println("select the language to which you want to translate (Example: \"en\" without quotes):");
            languageTo = scanner.nextLine();
            date = new Date();
            client.fileWriter.write(date.toString() + " > " + languageTo + "\n");
            if(languageTo.equals(EXIT))
            {
                String serverResult = client.sendWordAndLanguage("exit", "exit", "exit");
                break;
            }

            System.out.println("write the text to translate:\n");
            word = scanner.nextLine();
            date = new Date();
            client.fileWriter.write(date.toString() + " > " + word + "\n");
            if(word.equals(EXIT))
            {
                String serverResult = client.sendWordAndLanguage("exit", "exit", "exit");
                break;
            }

            String serverResult = client.sendWordAndLanguage(languageFrom, languageTo, word);
            System.out.println(serverResult);
            date = new Date();
            client.fileWriter.write(date.toString() + " > " + serverResult+"\n");

            System.out.println("select the language from which you want to translate (Example: \"en\" without quotes):");
        }

        client.clientSocket.close();
        client.fileWriter.close();
    }
}
