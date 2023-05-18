package capstone_TestScenarios;

import java.io.IOException;
import java.net.MalformedURLException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import utils.CommonFunctions;
import org.testng.Reporter;
import capstone_ObjectsRepo.ObjRepo_IOSPhone;

public class TestScenarios_IOSPhone {
	public static Reporter report;

	@BeforeSuite
	public static void launchUIKitCatalog() throws MalformedURLException {
		System.out.println("launching UIKitCatalog");
		CommonFunctions.configureIOSPhone();
		Reporter.log("launched UIKitCatalog");
		
	}

	@Test(priority = 1)
	public static void UI_01() throws IOException {
		System.out.println("performing scroll");	
		ObjRepo_IOSPhone.scrollPage();
		ObjRepo_IOSPhone.getOptionSize();	
		
		
	}

	@Test(priority = 2)
	public static void UI_02() {
		System.out.println("click Activity Indicators");
		ObjRepo_IOSPhone.clickAI();

	}

	@Test(priority = 3)
	public static void UI_03() throws IOException, InterruptedException {
		System.out.println("validating date picker");
		ObjRepo_IOSPhone.datePickerValidate();
	}

	@Test(priority = 4)
	public static void UI_04() throws IOException {
		System.out.println("validating images");
		ObjRepo_IOSPhone.imagesValidation();
	}

	@Test(priority = 5)
	public static void UI_05() throws IOException {
		System.out.println("validating pageControl");
		ObjRepo_IOSPhone.clickPageControl();
		ObjRepo_IOSPhone.validateColors();
	}

	@Test(priority = 6)
	public static void UI_06() throws IOException {
		System.out.println("validation pickerview");
		ObjRepo_IOSPhone.validatePickerView();
	}

	@Test(priority = 7)
	public static void UI_07() throws InterruptedException, IOException {
		System.out.println("validating progress view");
		ObjRepo_IOSPhone.validatingprogressView();
	}

	@Test(priority = 8)
	public static void UI_08() throws IOException {
		System.out.println("search..");
		ObjRepo_IOSPhone.search();
	}

	@Test(priority = 9)
	public static void UI_09() throws IOException {
		System.out.println();
		ObjRepo_IOSPhone.segmentedControls();
	}

	@Test(priority = 10)
	public static void UI_10() throws IOException {
		System.out.println("Sliders");
		ObjRepo_IOSPhone.sliders();
	}

	@Test(priority = 11)
	public static void UI_11() throws IOException {
		System.out.println("Stack Views");
		ObjRepo_IOSPhone.stackViewsValidations();
		System.out.println("validating box color");
		ObjRepo_IOSPhone.validateRedBox();
	}

	@Test(priority = 12)
	public static void UI_12() throws IOException {
		System.out.println("Switches");
		ObjRepo_IOSPhone.swtiches();
	}

	@Test(priority = 13)
	public static void UI_13() throws IOException {
		System.out.println("text fields");
		ObjRepo_IOSPhone.textField();
	}
	
	@Test(priority = 14)
	public static void UI_14() throws IOException {
		System.out.println("Toolbars");
		ObjRepo_IOSPhone.toolBars();
	}
	@Test(priority = 15)
	public static void UI_15() throws IOException {
		System.out.println("web views");
		ObjRepo_IOSPhone.webViews();
	}
	@Test(priority = 16)
	public static void UI_16() throws IOException {
		System.out.println("alert views");
		ObjRepo_IOSPhone.alertViews();
	}
	@AfterSuite
	public static void closeDriver() {
	    System.out.println("closing the driver");
	    CommonFunctions.closeExtentReport();
	    CommonFunctions.ad.quit();
	    
	}

}
