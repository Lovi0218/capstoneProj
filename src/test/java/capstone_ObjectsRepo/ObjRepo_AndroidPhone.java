package capstone_ObjectsRepo;

import java.io.File;




import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import utils.CommonFunctions;
import utils.ConfigFileReader;

public class ObjRepo_AndroidPhone {
	
	static AndroidDriver driver = CommonFunctions.ad;
	static ConfigFileReader configFileReader = new ConfigFileReader();
	static ExtentTest test;
	static ExtentReports report;
	
	public static void clickLoanAndEMI() throws IOException{
		test = CommonFunctions.generateExtentReportforAndroid();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		By xpathLocator = By.xpath("//android.widget.TextView[@text='Loan and EMI']");
		wait.until(ExpectedConditions.and(
			    ExpectedConditions.visibilityOfElementLocated(xpathLocator),
			    ExpectedConditions.elementToBeClickable(xpathLocator)
			));
		WebElement loanAndEmi = driver.findElement(xpathLocator);
		loanAndEmi.click();
		test.log(LogStatus.PASS, "click on Loan and EMI passed", "success");
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
//		driver.findElement(By.xpath("//android.widget.TextView[@text = 'Loan and EMI']")).click();	
		}
	
	public static void validateOptions() {
		
		 try {
		        WebElement loanAdvance = driver.findElement(By.id("com.ajra.emicalculator"));
		        if(loanAdvance.isDisplayed()) {
		        System.out.println("failed: options are still displayed");
		        test.log(LogStatus.FAIL, "validation failed: options are still displayed", "failed");
		        }
		    } catch (NoSuchElementException e) {
		        System.out.println("passed: no options under loan and emi");
		        test.log(LogStatus.PASS, "passed: no options under loan and emi displayed", "success");
		    }	
		
	}
	
	public static void printOptions() {
		
		driver.findElement(By.xpath("//*[@resource-id='com.ajra.emicalculator:id/cat_emi_img']")).click();
		
		WebElement loanBasic = driver.findElement(By.xpath("//android.widget.TextView[@text='Loan Basic']"));
		String LoanBasic = loanBasic.getText();
		WebElement loanAdvance = driver.findElement(By.xpath("//android.widget.TextView[@text='Loan Advance']"));
		String LoanAdvance = loanAdvance.getText();
		WebElement compareLoan = driver.findElement(By.xpath("//android.widget.TextView[@text='Compare Loan']"));
		String CompareLoan = compareLoan.getText();
		WebElement loanAmountEligibility = driver.findElement(By.xpath("//android.widget.TextView[@text='Loan Amount Eligibility']"));
		String LoanAmountEligibility = loanAmountEligibility.getText();
		WebElement homeLoanDocu = driver.findElement(By.xpath("//android.widget.TextView[@text='Home Loan Documents']"));
		String HomeLoanDocu = homeLoanDocu.getText();
		
		test.log(LogStatus.INFO, "options displayed");
		System.out.println(LoanBasic);
		System.out.println(LoanAdvance);
		System.out.println(CompareLoan);
		System.out.println(LoanAmountEligibility);
		System.out.println(HomeLoanDocu);         
	}
	
	public static void clickLoanBasic() throws IOException {
			
		WebElement loanBasic = driver.findElement(By.xpath("//android.widget.TextView[@text='Loan Basic']"));
		loanBasic.click();
		WebElement loanAmount = driver.findElement(By.xpath("//android.widget.TextView[@text='Loan Amount']"));
		loanAmount.click();
		
		File testData = new File(configFileReader.getTestDataFilePath());
		FileInputStream fis = new FileInputStream(testData);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Sheet1");
		
		double mnthlyRepaymentDouble = sheet.getRow(1).getCell(0).getNumericCellValue();
		double annualInterestDouble = sheet.getRow(1).getCell(1).getNumericCellValue();
		double loanTermDouble = sheet.getRow(1).getCell(2).getNumericCellValue();
		
		int mnthlyRepayment = (int) mnthlyRepaymentDouble;
		int annualInterest = (int) annualInterestDouble;
		int loanTerm = (int) loanTermDouble;
		
		WebElement monthlyRepayment = driver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.ajra.emicalculator:id/control_principal_amount']"));
		monthlyRepayment.sendKeys(Integer.toString(mnthlyRepayment));
		
		WebElement annualInterestRate = driver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.ajra.emicalculator:id/control_rate_of_interest']"));
		annualInterestRate.sendKeys(Integer.toString(annualInterest));
		
		WebElement LoanTerm = driver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.ajra.emicalculator:id/controlInvestmentPeriod']"));
		LoanTerm.sendKeys(Integer.toString(loanTerm));
		
		wb.close();
		try {
		WebElement clearBtn = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.ajra.emicalculator:id/button_reset']"));
		clearBtn.click();
		
		
		 Assert.assertTrue(monthlyRepayment.getAttribute("text").isEmpty(), "Monthly Repayment field is cleared");
		 Assert.assertTrue(annualInterestRate.getAttribute("text").isEmpty(), "Annual Interest Rate field is cleared");
		 Assert.assertTrue(LoanTerm.getAttribute("text").isEmpty(), "Loan term field is cleared");
		 test.log(LogStatus.PASS, "Validation passed: all fields are cleared", "success");
		}catch (AssertionError e) {
            // Handle the assertion failure
            System.out.println("Validation failed: Monthly Repayment field is not cleared");
            test.log(LogStatus.FAIL, "Validation failed: fields are not cleared", "failed");
		}
//			WebElement calculateBtn = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.ajra.emicalculator:id/button_calculate']"));
//			calculateBtn.click();
		 
	}
	
	public static void validateResult() {
		
		WebElement calculateBtn = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.ajra.emicalculator:id/button_calculate']"));
		calculateBtn.click();
		
		WebElement detailsBtn = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.ajra.emicalculator:id/button_statistics']"));
		detailsBtn.click();
		
		try {
			
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement yearlyData = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@index = '1']")));
		Assert.assertTrue(yearlyData.isDisplayed(), "Validation passed: yearly data is displayed");
		System.out.println("Validation passed: yearly data is displayed");
		test.log(LogStatus.PASS, "Validation passed: yearly data is displayed", "success");
		}catch (StaleElementReferenceException e) {
			e.printStackTrace();
		}catch (AssertionError e) {
			System.out.println("validation failed: yearly data is not displayed");
			test.log(LogStatus.FAIL, "Validation failed: yearly data is not", "failed");
		}
		
		WebElement monthlyBtn = driver.findElement(By.xpath("//android.widget.TextView[@text = 'MONTHLY']"));
		monthlyBtn.click();
		
		try {
		
		WebElement monthlyData = driver.findElement(By.xpath("//android.widget.TextView[@index = '1']"));
		Assert.assertTrue(monthlyData.isDisplayed(), "validation passed: monthly data is displayed");
		System.out.println("validation passed: monthly data is displayed");
		test.log(LogStatus.PASS, "Validation passed: monthly data is displayed", "success");	
		}catch (AssertionError e) {
			System.out.println("validation failed: monthly data is not displayed");
			test.log(LogStatus.FAIL, "Validation failed: monthly data is not displayed", "failed");
		}
		
		WebElement graphBtn = driver.findElement(By.xpath("//android.widget.TextView[@text = 'GRAPH']"));
		graphBtn.click();
		
		try {
			WebElement chart = driver.findElement(By.xpath("//android.view.ViewGroup[@resource-id = 'com.ajra.emicalculator:id/chart']"));
			Assert.assertTrue(chart.isDisplayed(), "validation passed: chart data is displayed");
			System.out.println("validation passed: chart data is displayed");
			test.log(LogStatus.PASS, "Validation passed: chart data is displayed", "success");
			
		}catch (AssertionError e) {
			System.out.println("validation failed: graph is not displayed");
			test.log(LogStatus.FAIL, "Validation failed: chart data is displayed", "failed");
		}
		
	}
	
	public static void vihicleLoanValidation() throws IOException {
		
		
		WebElement backToMonthlyRepayment = driver.findElement(AppiumBy.accessibilityId("Navigate up"));
		backToMonthlyRepayment.click();
		
		driver.navigate().back();
//		WebElement backToBasicLoan = driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"));
//		backToBasicLoan.click();
		
		WebElement vihicleLoanBtn = driver.findElement(By.xpath("//android.widget.TextView[@text = 'Vehicle Loan']"));
		vihicleLoanBtn.click();
		
		File testData = new File(configFileReader.getTestDataFilePath());
		FileInputStream fis = new FileInputStream(testData);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Sheet1");
		
		double vehiclePriceDouble = sheet.getRow(1).getCell(3).getNumericCellValue();
		double DPAmountDouble = sheet.getRow(1).getCell(5).getNumericCellValue();
		double AIRateDouble = sheet.getRow(1).getCell(6).getNumericCellValue();
		double loanTermDouble = sheet.getRow(1).getCell(7).getNumericCellValue();
		
		int vihiclePrice = (int) vehiclePriceDouble;
		int DPAmount = (int) DPAmountDouble;
		int AIRate = (int) AIRateDouble;
		int loanTerm = (int) loanTermDouble;
		
		WebElement VihiclePrice = driver.findElement(By.xpath("//android.widget.EditText[@text='Vehicle Price']"));
		VihiclePrice.sendKeys(Integer.toString(vihiclePrice));
		
		WebElement downPaymentAmount = driver.findElement(By.xpath("//android.widget.EditText[@text='Down Payment Amount']"));
		downPaymentAmount.sendKeys(Integer.toString(DPAmount));
		
		WebElement annualInterestRate = driver.findElement(By.xpath("//android.widget.EditText[@text='Annual Interest Rate (%)']"));
		annualInterestRate.sendKeys(Integer.toString(AIRate));
		
		WebElement LoanTerm = driver.findElement(By.xpath("//android.widget.EditText[@text='Loan Term']"));
		LoanTerm.sendKeys(Integer.toString(loanTerm));
		
		WebElement calculateBtn = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.ajra.emicalculator:id/button_calculate']"));
		calculateBtn.click();
		
		wb.close();
		try {
		WebElement mnthlyEMI = driver.findElement(By.xpath("//android.widget.TextView[@text='Monthly EMI']"));
		Assert.assertTrue(mnthlyEMI.isDisplayed(), "validation passed: monthly EMI is displayed");
		System.out.println("validation passed: monthly EMI data is displayed");
		test.log(LogStatus.PASS, "Validation passed: monthly EMI data is displayed", "success");
		
		}catch (AssertionError e) {
			test.log(LogStatus.FAIL, "Validation passed: monthly EMI data is displayed", "failed");
			System.out.println("validation failed: monthly EMI is not displayed");
		}
		
		WebElement clearBtn = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.ajra.emicalculator:id/button_reset']"));
		clearBtn.click();
		
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement mnthlyEMI = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Monthly EMI']")));
			if(mnthlyEMI.isDisplayed()) {
				System.out.println("validation failed: results are not cleared");
				test.log(LogStatus.FAIL, "Validation failed: results are not cleared", "failed");
			}
			
		}catch (TimeoutException e) {
			System.out.println("validation passed: the results are cleared");
			test.log(LogStatus.PASS, "Validation passed: results are cleared", "success");
		}
		
	}
	
	
	
	
	
}
