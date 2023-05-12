package utils;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

    private Properties properties;

    public ConfigFileReader() {
        loadProperties();
    }

    private void loadProperties() {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream("/Users/collabera/eclipse-workspace/capstoneProj/src/test/java/utils/config.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getTestDataFilePath() {
        return properties.getProperty("testdata.filepath");
    }
    public String getApkFilePath() {
        return properties.getProperty("apk.filepath");
    }
    public String getAppFilePath() {
        return properties.getProperty("app.filepath");
    }

}

