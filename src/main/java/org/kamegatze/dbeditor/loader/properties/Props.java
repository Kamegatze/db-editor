package org.kamegatze.dbeditor.loader.properties;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Props {

    public static Properties getProperties() throws IOException {
        FileReader fileReader = new FileReader("./src/main/resources/application.properties");
        Properties properties = new Properties();
        properties.load(fileReader);
        return properties;
    }

}
