package util;



import java.io.*;
import java.util.Properties;

public class PropertiesHandler {

    private  final String  file = "properties.txt";
    private Properties properties;
    public PropertiesHandler() throws IOException
    {
        try(Reader reader = new FileReader(file))
        {
            properties= new Properties();
            properties.load(reader);
        }
    }
    public String getProperty(String key)
    {
        return properties.getProperty(key);
    }

}
