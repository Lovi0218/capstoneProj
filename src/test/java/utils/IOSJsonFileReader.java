package utils;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class IOSJsonFileReader {

    private JSONObject jsonObject;

    public IOSJsonFileReader(String filePath) {
        loadJsonFile(filePath);
    }

    private void loadJsonFile(String filePath) {
        try {
            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader(filePath);
            jsonObject = (JSONObject) parser.parse(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getDeviceName() {
        return (String) jsonObject.get("deviceName");
    }

    public String getAppPath() {
        return (String) jsonObject.get("app");
    }

    public String getPlatformVersion() {
        return (String) jsonObject.get("platformVersion");
    }

    public String getAutomationName() {
        return (String) jsonObject.get("automationName");
    }

    public String getPlatformName() {
        return (String) jsonObject.get("platformName");
    }
    public String getUUID() {
    	return (String) jsonObject.get("UUID");
    }
}
