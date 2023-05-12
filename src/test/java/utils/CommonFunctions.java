package utils;
import java.io.File;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.SessionNotCreatedException;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class CommonFunctions {
	
	public static AndroidDriver ad;
	public static IOSDriver iosDriver;
	static ConfigFileReader configFileReader = new ConfigFileReader();
	static ExtentTest test;
	static ExtentReports report;

	
	public static void configAndroidPhone() {
		JsonFileReader jsonFileReaderPhone = new JsonFileReader("/Users/collabera/eclipse-workspace/capstoneProj/src/test/java/files/androidPhoneCapabilities.json");
        
        UiAutomator2Options cap = new UiAutomator2Options();

        cap.setDeviceName(jsonFileReaderPhone .getDeviceName());
        cap.setApp(jsonFileReaderPhone .getAppPath());
        cap.setPlatformVersion(jsonFileReaderPhone .getPlatformVersion());
        cap.setAutomationName(jsonFileReaderPhone .getAutomationName());
        cap.setPlatformName(jsonFileReaderPhone.getPlatformName());

        try {
            ad = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
            ad.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            System.out.println("Session created successfully.");
        } catch (SessionNotCreatedException e) {
            System.out.println("Session could not be created. Please check the capabilities and device settings.");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public static void configAndroidTablet() {
		JsonFileReader jsonFileReaderTablet = new JsonFileReader("/Users/collabera/eclipse-workspace/capstoneProj/src/test/java/files/androidTabletCapabilities.json");
            
        UiAutomator2Options cap = new UiAutomator2Options();

        cap.setDeviceName(jsonFileReaderTablet.getDeviceName());
        cap.setApp(jsonFileReaderTablet.getAppPath());
        cap.setPlatformVersion(jsonFileReaderTablet.getPlatformVersion());
        cap.setAutomationName(jsonFileReaderTablet.getAutomationName());
        cap.setPlatformName(jsonFileReaderTablet.getPlatformName()); 

        try {
            ad = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
            ad.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            System.out.println("Session created successfully.");
        } catch (SessionNotCreatedException e) {
            System.out.println("Session could not be created. Please check the capabilities and device settings.");
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}
	public static void configureIOSPhone() throws MalformedURLException{
		
		IOSJsonFileReader jsonFileReaderIPhone = new IOSJsonFileReader("/Users/collabera/eclipse-workspace/capstoneProj/src/test/java/files/IOSPhoneCapabilities.json");
		
		XCUITestOptions cap = new XCUITestOptions();
		cap.setDeviceName(jsonFileReaderIPhone.getDeviceName());
		cap.setApp(jsonFileReaderIPhone.getAppPath());
		cap.setPlatformVersion(jsonFileReaderIPhone.getPlatformVersion());
		cap.setAutomationName(jsonFileReaderIPhone.getAutomationName()); 
		cap.setUdid(jsonFileReaderIPhone.getUUID());
		try {
			
		iosDriver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"),cap);
		iosDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		}catch (SessionNotCreatedException e) {
			
            System.out.println("Session could not be created. Please check the capabilities and device settings.");
        } catch (Exception e) {
        	
            e.printStackTrace();
        }
	}
	public static ExtentTest generateExtentReport() {
		report = new ExtentReports("capstoneExtentReport.html");
        test = report.startTest("captone_test");
		return test;
	}
	
	public static void closeExtentReport() {
		report.endTest(test);
		report.flush();
	}

}
