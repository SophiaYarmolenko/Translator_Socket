
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Translator
{
    private TST< HashMap<String, String> > dictionary = new TST<>();

    public Translator() throws IOException
    {
        fillDictionary("Dictionary.txt");
    }

    private void fillDictionary(String fileName) throws IOException
    {
        FileReader fileReader = new FileReader(fileName);
        Scanner scanner = new Scanner(fileReader);

        String[] languageName = scanner.nextLine().replaceAll("\\s+", " ").split(" ");

        while( scanner.hasNext() )
        {
            String[] line = scanner.nextLine().replaceAll("\\s+", " ").split(" ");
            HashMap <String, String> translateMap = new HashMap<>();
            for( int i = 0; i < line.length; i++)
            {
                translateMap.put( languageName[i], line[i] );
            }
            for( String word : line)
            {
                dictionary.put(word, translateMap);
            }
        }
        fileReader.close();
    }

    public String getTranslation (String requiredLanguage, String word)
    {
        if(dictionary.get(word) == null || dictionary.get(word).get(requiredLanguage) == null)
            return null;
        return dictionary.get(word).get(requiredLanguage);
    }
}
