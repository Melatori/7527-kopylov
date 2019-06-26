package ru.ctf.focusstart.kopylov;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class PropertiesReader {
    private Properties properties;

    PropertiesReader() throws IOException {
        properties = new Properties();
        try (InputStream propertiesStream = Server.class.getClassLoader().getResourceAsStream("server.properties")) {
            properties.load(propertiesStream);
        } catch (IOException e) {
            System.err.println("Can't find \"server.properties\"");
            throw new IOException("File \"server.properties\" not found");
        }
    }

    int getSocket() {
        return Integer.valueOf(properties.getProperty("server.port"));
    }
}
