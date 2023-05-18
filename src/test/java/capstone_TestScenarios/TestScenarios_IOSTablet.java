package capstone_TestScenarios;

import java.io.IOException;
import java.net.MalformedURLException;



import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import utils.CommonFunctions;
import org.testng.Reporter;
import capstone_ObjectsRepo.ObjRepo_IOSTablet;

public class TestScenarios_IOSTablet {
	public static Reporter report;

	@BeforeSuite
	public static void launchUIKitCatalog() throws MalformedURLException {
		System.out.println("launching UIKitCatalog");
		CommonFunctions.configureIOSTablet();
		Reporter.log("launching UIKitCatalog");
		
	}

	@Test(priority = 1)
	public static void UI_01() throws IOException {
		System.out.println("performing scroll");	
		ObjRepo_IOSTablet.scrollPage();
		ObjRepo_IOSTablet.getOptionSize();	
		
		
	}

	@Test(priority = 2)
	public static void UI_02() {
		System.out.println("click Activity Indicators");
		ObjRepo_IOSTablet.clickAI();

	}

	@Test(priority = 3)
	public static void UI_03() throws IOException {
		System.out.println("validating date picker");
		ObjRepo_IOSTablet.datePickerValidate();
	}

	@Test(priority = 4)
	public static void UI_04() throws InterruptedException, IOException {
		System.out.println("validating images");
		ObjRepo_IOSTablet.imagesValidation();
	}

	@Test(priority = 5)
	public static void UI_05() throws IOException {
		System.out.println("validating pageControl");
		ObjRepo_IOSTablet.clickPageControl();
		ObjRepo_IOSTablet.validateColors();
	}

	@Test(priority = 6)
	public static void UI_06() throws IOException {
		System.out.println("validation pickerview");
		ObjRepo_IOSTablet.validatePickerView();
	}

	@Test(priority = 7)
	public static void UI_07() throws InterruptedException, IOException {
		System.out.println("validating progress view");
		ObjRepo_IOSTablet.validatingprogressView();
	}

	@Test(priority = 8)
	public static void UI_08() {
		System.out.println("search..");
		ObjRepo_IOSTablet.search();
	}

	@Test(priority = 9)
	public static void UI_09() {
		System.out.println();
		ObjRepo_IOSTablet.segmentedControls();
	}

	@Test(priority = 10)
	public static void UI_10() {
		System.out.println("Sliders");
		ObjRepo_IOSTablet.sliders();
	}

	@Test(priority = 11)
	public static void UI_11() throws IOException {
		System.out.println("Stack Views");
		ObjRepo_IOSTablet.stackViewsValidations();
		System.out.println("validating box color");
		ObjRepo_IOSTablet.validateRedBox();
	}

	@Test(priority = 12)
	public static void UI_12() {
		System.out.println("Switches");
		ObjRepo_IOSTablet.swtiches();
	}

	@Test(priority = 13)
	public static void UI_13() {
		System.out.println("text fields");
		ObjRepo_IOSTablet.textField();
	}
	
	@Test(priority = 14)
	public static void UI_14() {
		System.out.println("Toolbars");
		ObjRepo_IOSTablet.toolBars();
	}
	@Test(priority = 15)
	public static void UI_15() throws IOException {
		System.out.println("web views");
		ObjRepo_IOSTablet.webViews();
	}
	@Test(priority = 16)
	public static void UI_16() throws IOException {
		System.out.println("alert views");
		ObjRepo_IOSTablet.alertViews();
	}
	@AfterSuite
	public static void closeDriver() {
	    System.out.println("closing the driver");
	    CommonFunctions.closeExtentReport();
	    CommonFunctions.ad.quit();
	    
	}

}

