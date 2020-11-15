package com.company;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetTranslate
{
    public static void main(String args[]) throws IOException
    {
        GetTranslate translate = new GetTranslate();
        String temp = translate.getTranslation("en", "ru", "cat");

        System.out.println(temp);
    }

    public String getTranslation(String languageFrom, String languageTo, String word) throws IOException
    {
        URL url;
        HttpURLConnection connection;
        BufferedReader reader = null;
        String line;
        StringBuilder result = new StringBuilder();
        try
        {
            StringBuilder link = new StringBuilder("http://translate.google.ru/translate_a/t?client=p&text={" +
                    word +
                    "}&hl=en&sl="+
                    languageFrom+
                    "&tl="+
                    languageTo);
            url = new URL(link.toString());
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");

            try
            {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                while ((line = reader.readLine()) != null)
                {
                    result.append(line + "\n");
                }
            }
            finally
            {
                connection.disconnect();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally 
        {
            if(reader != null)
                reader.close();
        }
        return result.toString();
    }
}