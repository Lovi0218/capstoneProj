package capstone_ObjectsRepo;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.appium.java_client.ios.IOSDriver;
import utils.CommonFunctions;
import utils.ConfigFileReader;

import org.testng.Assert;
import org.testng.Reporter;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ObjRepo_IOSTablet {
	
	static IOSDriver driver = CommonFunctions.iosDriver;
	ConfigFileReader cfr = new ConfigFileReader();
	static ExtentTest test;
	static ExtentReports report;
	
	@SuppressWarnings({ "rawtypes", "deprecation" })
	public static void scrollPage() {
		
		test = CommonFunctions.generateExtentReportforIOS();
		
        Dimension size = driver.manage().window().getSize();
        int startY = (int) (size.height * 0.9);
		int endY = (int) (size.height * 0.2);
		int startX = size.width / 2;
		
		TouchAction action = new TouchAction(driver);
		action.longPress(PointOption.point(startX, startY))
		      .moveTo(PointOption.point(startX, endY))
		      .release()
		      .perform();
		
		
    }
	
	public static int getOptionSize() throws IOException {
		
		List <WebElement> options = driver.findElements(By.xpath("//XCUIElementTypeStaticText"));	
		
		
		CommonFunctions.ScreenShotsIOS("Available options", "INFO");
		return options.size() / 2;
		
		
		
	}
	
	public static void clickAI() {
		WebElement activityIndicators = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='Activity Indicators']"));
		activityIndicators.click();
		
	}
	
	public static void datePickerValidate() throws IOException {

		WebElement datePickerBtn = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='Date Picker']"));
		datePickerBtn.click();
		
		WebElement datePicker = driver.findElements(By.xpath("//XCUIElementTypeButton")).get(3);
		datePicker.click();
		
		WebElement dateToSelect = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@index='0']"));
		dateToSelect.click();
		driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='22']")).click();
		
		WebElement selectMnthAndYear = driver.findElement(By.xpath("//XCUIElementTypeButton[@name='Show year picker']"));
		selectMnthAndYear.click();
		

		WebElement month = driver.findElement(By.xpath("//XCUIElementTypeDatePicker//XCUIElementTypePickerWheel[1]"));
		month.sendKeys("June");
		
		String monthVal = month.getAttribute("value");
		
		if(monthVal.equals("May")) {
			CommonFunctions.ScreenShotsIOS("Date picker is disable", "FAIL");
			Reporter.log("validation failed: month and year pickerwheel is disabled");
			System.out.println("validation failed: month and year pickerwheel is disabled");
		}else {
			CommonFunctions.ScreenShotsIOS("successfully set the date", "PASS");
			Reporter.log("validation passed: June");
		}
			
	
	}
	
	public static void imagesValidation() throws InterruptedException, IOException {

		WebElement releaseDatePicker = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='Image View']"));
		releaseDatePicker.click();
		
		WebElement clickImageView = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='Image View']"));
		clickImageView.click();
		
		try {
			WebElement animatedImage = driver.findElement(By.name("Animated"));
//		WebElement animatedImage = driver.findElement(By.xpath("//XCUIElementTypeImage[@name='Animated']"));
			
		Assert.assertTrue(animatedImage.isDisplayed(), "Validation passed: image is displayed");
		System.out.println("Validation passed: image is displayed");
		CommonFunctions.ScreenShotsIOS("image is displayed", "PASS");
		Reporter.log("validation passed: image is displayed");
		
		
	}catch (StaleElementReferenceException e) {
		e.printStackTrace();
	}catch (AssertionError e) {
		System.out.println("validation failed: image is not displayed");
		CommonFunctions.ScreenShotsIOS("image is not displayed", "FAIL");
	}
	}
	
	public static void clickPageControl() {

		WebElement clickPageControl = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='Page Control']"));
		clickPageControl.click();
	}
	
	public static void validateColors() throws IOException {
		
		String dotValue1 = "page 3 of 10";
		WebElement dotElement1 = driver.findElement(By.xpath("//XCUIElementTypePageIndicator[@type='XCUIElementTypePageIndicator' and @value='" + dotValue1 + "']"));
		
		String initialSelectedState1 = dotElement1.getAttribute("value");
		dotElement1.click();

		String finalSelectedState1 = dotElement1.getAttribute("value");

		if (!initialSelectedState1.equals(finalSelectedState1)) {
		    System.out.println("Color1 change is validated. Dot state changed.");
		    CommonFunctions.ScreenShotsIOS("Color1 successfully changed and dot state changed", "PASS");
		    
		} else {
		    System.out.println("Color1 change is not validated. Dot state did not change.");
		    CommonFunctions.ScreenShotsIOS("Color1 didnt change and Dot state did not change.", "FAIL");
		}
		
		String dotValue2 = "page 4 of 10";
		WebElement dotElement2 = driver.findElement(By.xpath("//XCUIElementTypePageIndicator[@type='XCUIElementTypePageIndicator' and @value='" + dotValue2 + "']"));
		
		String initialSelectedState2 = dotElement2.getAttribute("value");
		dotElement2.click();

		String finalSelectedState2 = dotElement2.getAttribute("value");

		if (!initialSelectedState2.equals(finalSelectedState2)) {
		    System.out.println("Color2 change is validated. Dot state changed.");
		    CommonFunctions.ScreenShotsIOS("Color2 successfully changed and dot state changed", "PASS");
		} else {
		    System.out.println("Color2 change is not validated. Dot state did not change.");
		    CommonFunctions.ScreenShotsIOS("Color2 didnt change and Dot state did not change.", "FAIL");
		}		
	}
	
	public static void validatePickerView() throws IOException {

		WebElement pickerViewBtn = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='Picker View']"));
		pickerViewBtn.click();
		
		WebElement pickerWheel1 = driver.findElement(By.xpath("//XCUIElementTypePickerWheel[@name='Red color component value']"));
		String initialPickerWheel1Value = pickerWheel1.getAttribute("value");
		pickerWheel1.sendKeys("250");
		
		WebElement pickerWheel2 = driver.findElement(By.xpath("//XCUIElementTypePickerWheel[@name='Green color component value']"));
		String initialPickerWheel2Value = pickerWheel2.getAttribute("value");
		pickerWheel2.sendKeys("145");
		
		WebElement pickerWheel3 = driver.findElement(By.xpath("//XCUIElementTypePickerWheel[@name='Blue color component value']"));
		String initialPickerWheel3Value = pickerWheel3.getAttribute("value");
		pickerWheel3.sendKeys("225");
		
		String finalPickerWheel1Value = pickerWheel1.getAttribute("value");
		String finalPickerWheel2Value = pickerWheel2.getAttribute("value");
		String finalPickerWheel3Value = pickerWheel3.getAttribute("value");
	
//		Assert.assertNotEquals("pickerwheel 1 value has not change.", initialPickerWheel1Value, finalPickerWheel1Value);
//		Assert.assertNotEquals("pickerwheel 2 value has not change.", initialPickerWheel2Value, finalPickerWheel2Value);
//		Assert.assertNotEquals("pickerwheel 3 value has not change.", initialPickerWheel3Value, finalPickerWheel3Value);
		if (initialPickerWheel1Value != finalPickerWheel1Value && initialPickerWheel2Value != finalPickerWheel2Value &&
			initialPickerWheel3Value != finalPickerWheel3Value ) {
			
			System.out.println("Color changed");
			CommonFunctions.ScreenShotsIOS("Color changed.", "PASS");
			Reporter.log("validation passed: color changed");
		}else {
			System.out.println("Color did not change");
			CommonFunctions.ScreenShotsIOS("Color did not changed.", "FAIL");		
		}		
	}
	
	public static void validatingprogressView() throws InterruptedException, IOException {
		

		WebElement progressViewBtn = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='Progress Views']"));
		progressViewBtn.click();
		
		WebElement intProgressBarValue = driver.findElement(By.xpath("(//XCUIElementTypeProgressIndicator[@name='Progress'])[1]"));
		String defaultVal = intProgressBarValue.getAttribute("value");
		defaultVal = defaultVal.replace("%", "");
		
		Thread.sleep(5000);
		
		WebElement finProgressBarValue = driver.findElement(By.xpath("(//XCUIElementTypeProgressIndicator[@name='Progress'])[1]"));
		String finalVal = finProgressBarValue.getAttribute("value");
		finalVal = finalVal.replace("%", "");
		
		int defaultIntVal = Integer.parseInt(defaultVal);
		int finalIntVal = Integer.parseInt(finalVal);
		
		if(finalIntVal > defaultIntVal) {
			System.out.println("Progress bar has increased over time.");
			Reporter.log("validation passed: progress has incread over time.");
			CommonFunctions.ScreenShotsIOS("Progress bar has increased over time.", "PASS");
		}else {
			System.out.println("Progress bar has not changed over time.");
			Reporter.log("validation failed: Progress bar did not changed over time.");
			CommonFunctions.ScreenShotsIOS("Progress bar did not change.", "FAIL");
		}	
	}
	
		public static void search() {
			
			driver.navigate().back();
			
			WebElement searchBtn = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='Search']"));
			searchBtn.click();
			
			WebElement defaultBtn = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='Default']"));
			defaultBtn.click();
			
			WebElement scope1Btn = driver.findElement(By.xpath("//XCUIElementTypeButton[@name='Scope One']"));
			scope1Btn.click();
			
			WebElement scope2Btn = driver.findElement(By.xpath("//XCUIElementTypeButton[@name='Scope Two']"));
			scope2Btn.click();
			

			
			WebElement customBtn = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='Custom']"));
			customBtn.click();
			
			WebElement searchBar = driver.findElement(By.xpath("//XCUIElementTypeSearchField"));
			searchBar.click();
			
			WebElement clickT = driver.findElement(By.xpath("//XCUIElementTypeKey[@name='T']"));
			clickT.click();
			
			WebElement clicke = driver.findElement(By.xpath("//XCUIElementTypeKey[@name='e']"));
			clicke.click();
			
			WebElement clickS = driver.findElement(By.xpath("//XCUIElementTypeKey[@name='s']"));
			clickS.click();
			
			WebElement clickt = driver.findElement(By.xpath("//XCUIElementTypeKey[@name='t']"));
			clickt.click();
			
		}
		
		public static void segmentedControls() {

			driver.navigate().back();
			
			WebElement segmentedControlsBtn = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='Segmented Controls']"));
			segmentedControlsBtn.click();
			
			WebElement default_Tools = driver.findElement(By.xpath("(//XCUIElementTypeButton[@name='Tools'])[1]"));
			default_Tools.click();
			
			WebElement tinted_Check = driver.findElement(By.xpath("(//XCUIElementTypeButton[@name='Check'])[2]"));
			tinted_Check.click();
			
			WebElement custom_Gift = driver.findElement(By.xpath("//XCUIElementTypeButton[@name='Gift']"));
			custom_Gift.click();
			
		}
		
		public static void sliders() {

			WebElement slidersBtn = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='Sliders']"));
			slidersBtn.click();
			
			WebElement defaultSlider = driver.findElement(By.xpath("//XCUIElementTypeSlider[@value='42%']"));
			defaultSlider.sendKeys("0.2");
			
			WebElement tintedSlider = driver.findElement(By.xpath("//XCUIElementTypeSlider[@value='50%']"));
			tintedSlider.sendKeys("0.73");
			
			WebElement customSlider = driver.findElement(By.xpath("//XCUIElementTypeSlider[@value='84%']"));
			customSlider.sendKeys("0.5");
			
		}
		
		public static void stackViewsValidations() throws IOException {

			WebElement stackViewsBtn = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='Stack Views']"));
			stackViewsBtn.click();
			
			WebElement detailsPlusBtn = driver.findElement(By.xpath("(//XCUIElementTypeButton[@name='stepper increment'])[1]"));
			detailsPlusBtn.click();
			
			WebElement furtherDetails = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='Further Detail']"));
			Assert.assertTrue(furtherDetails.isDisplayed(), "Further Details element is not displayed.");
			System.out.println("validation passed: further details is displayed/present");
			Reporter.log("validation passed: further details is displayed/present");
			CommonFunctions.ScreenShotsIOS("Further details is displayed.", "PASS");
			
		}
		public static void validateRedBox() throws IOException {
			WebElement plusBtn = driver.findElement(By.xpath("//XCUIElementTypeButton[@name='stepper increment']"));
			plusBtn.click();
			
			WebElement box = driver.findElement(By.xpath("//XCUIElementTypeOther[@width='38' and @height='39']"));
			if (box.isDisplayed()) {
				CommonFunctions.ScreenShotsIOS("Box is displayed", "PASS");
				
			} else {
				CommonFunctions.ScreenShotsIOS("Box is not displayed", "FAIL");
			}

		}
		public static void swtiches() {

			WebElement switchesBtn = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='Switches']"));
			switchesBtn.click();
			
			WebElement defaultSwitch = driver.findElement(By.xpath("//XCUIElementTypeSwitch[@value='1'][1]"));
			defaultSwitch.click();
		
		}
		public static void textField() {

			WebElement textFieldBtn = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='Text Fields']"));
			textFieldBtn.click();
			
			WebElement defaultText = driver.findElement(By.xpath("//XCUIElementTypeTextField[@value='Placeholder text'][1]"));
			defaultText.sendKeys("Default");
			driver.findElement(By.xpath("//XCUIElementTypeButton[@name='Done']")).click();
			
			WebElement tintedText = driver.findElement(By.xpath("//XCUIElementTypeTextField[@value='Placeholder text'][1]"));
			tintedText.click();
			driver.findElement(By.xpath("//XCUIElementTypeKey[@name='t']")).click();
			driver.findElement(By.xpath("//XCUIElementTypeKey[@name='i']")).click();
			driver.findElement(By.xpath("//XCUIElementTypeKey[@name='n']")).click();
			driver.findElement(By.xpath("//XCUIElementTypeKey[@name='t']")).click();
			driver.findElement(By.xpath("//XCUIElementTypeKey[@name='e']")).click();
			driver.findElement(By.xpath("//XCUIElementTypeKey[@name='d']")).click();
			driver.findElement(By.xpath("//XCUIElementTypeButton[@name='Done']")).click();
			
			WebElement secureText = driver.findElement(By.xpath("//XCUIElementTypeSecureTextField[@value='Placeholder text']"));
			secureText.sendKeys("Secure");
			driver.findElement(By.xpath("//XCUIElementTypeButton[@name='Done']")).click();
			
			WebElement specificText = driver.findElement(By.xpath("//XCUIElementTypeTextField[@value='Placeholder text'][1]"));
			specificText.sendKeys("Specific");
			driver.findElement(By.xpath("//XCUIElementTypeButton[@name='Done']")).click();
			
			WebElement customText = driver.findElement(By.xpath("//XCUIElementTypeTextField[@value='Placeholder text'][1]"));
			customText.sendKeys("Custom");
			driver.findElement(By.xpath("//XCUIElementTypeButton[@name='Done']")).click();
			
		}
		public static void toolBars() {

			WebElement toolBarBtn = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='Toolbars']"));
			toolBarBtn.click();
			
			WebElement customBtn = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='Custom']"));
			customBtn.click();
			

			
			WebElement defaultBtn = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='Default']"));
			defaultBtn.click();
			
			WebElement deleteBtn = driver.findElement(By.xpath("//XCUIElementTypeButton[@name='Delete']"));
			deleteBtn.click();
		
		}
		@SuppressWarnings({ "rawtypes", "deprecation" })
		public static void webViews() throws IOException {

			driver.navigate().back();
			Dimension size = driver.manage().window().getSize();
	        int startY = (int) (size.height * 0.9);
			int endY = (int) (size.height * 0.2);
			int startX = size.width / 2;
			
			TouchAction action = new TouchAction(driver);
			action.longPress(PointOption.point(startX, startY))
			      .moveTo(PointOption.point(startX, endY))
			      .release()
			      .perform();
			WebElement webViewsBtn = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='Web View']"));
			webViewsBtn.click();
			
			WebElement htmlContent = driver.findElement(By.xpath("//XCUIElementTypeOther[@name='This is HTML content inside a WKWebView .']"));
			Assert.assertTrue(htmlContent.isDisplayed(), "HTML content found.");
			Reporter.log("validation passed: HTML content found");
			System.out.println("validation passed: HTML content found!");
			CommonFunctions.ScreenShotsIOS("HTML content found.", "PASS");
			
		}
		@SuppressWarnings({ "deprecation", "rawtypes" })
		public static void alertViews() throws IOException {

			Dimension size = driver.manage().window().getSize();
	        int startY = (int) (size.height * 0.2);
			int endY = (int) (size.height * 0.9);
			int startX = size.width / 2;
			
			TouchAction action = new TouchAction(driver);
			action.longPress(PointOption.point(startX, startY))
			      .moveTo(PointOption.point(startX, endY))
			      .release()
			      .perform();
			
			WebElement alertViewsBtn = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='Alert Views']"));
			alertViewsBtn.click();
			
			WebElement okayCancelBtn = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='Okay / Cancel']"));
			okayCancelBtn.click();
			
			WebElement alertBox = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='A Short Title Is Best']"));
			String title = alertBox.getText();
			Assert.assertTrue(alertBox.isDisplayed(), "alert box found.");
			Reporter.log("validation passed: alertbox found");
			CommonFunctions.ScreenShotsIOS("Alertbox found.", "PASS");
			System.out.println("validation passed: alertbox found");
			Assert.assertTrue(title.contains("A Short Title Is Best"), "title is equal");
			Reporter.log("validation passed: title contains A Short Title Is Best");
			CommonFunctions.ScreenShotsIOS("Title contains A Short Title Is Best.", "PASS");
			System.out.println("validation passed: title contains:" +  title);
			
			WebElement cancelBtn = driver.findElement(By.xpath("//XCUIElementTypeButton[@name='Cancel']"));
			cancelBtn.click();
			
			
			
		}

}
