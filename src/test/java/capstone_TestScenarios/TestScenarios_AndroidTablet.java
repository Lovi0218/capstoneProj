package capstone_TestScenarios;


	import java.io.IOException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
	import org.testng.annotations.Test;

import capstone_ObjectsRepo.ObjRepo_AndroidPhone;
import capstone_ObjectsRepo.ObjRepo_AndroidTablet;
	import utils.CommonFunctions;

	public class TestScenarios_AndroidTablet {
		
		@BeforeSuite
		public static void iFiniboLaunch() {
			System.out.println("Launching iFinibo");
			CommonFunctions.configAndroidTablet();
			
		}
		@Test(priority = 1)
		public static void Fin_01() throws IOException {
			System.out.println("loan and emi button should collapse");
			ObjRepo_AndroidPhone.clickLoanAndEMI();
			System.out.println("validating options");
			ObjRepo_AndroidPhone.validateOptions();
		}
		
		
		@Test(priority = 2)
		public static void Fin_02() throws IOException {
			System.out.println("options:");
			ObjRepo_AndroidPhone.printOptions();
			System.out.println("validating loan");
			ObjRepo_AndroidPhone.clickLoanBasic();
			System.out.println("validating the results");
			ObjRepo_AndroidPhone.validateResult();
			
		}
		
		@Test(priority = 3)
		public static void Fin_03() throws IOException {
			System.out.println("validation vihicle loan");
			ObjRepo_AndroidPhone.vihicleLoanValidation();
		}

		@AfterSuite
		public static void closeDriver() {
			System.out.println("closing the driver");
			CommonFunctions.closeExtentReport();
			CommonFunctions.ad.quit();
	    
	}

	}
	
//	public class TestScenarios_AndroidTablet {
//		
//		@BeforeSuite
//		public static void launchiFinibo() {
//			System.out.println("Launching iFinibo");
//			CommonFunctions.configAndroidTablet();
//			
//		}
//		@Test(priority = 1)
//		public static void validateLoanAndEmi(){
//			System.out.println("loan and emi button should collapse");
//			ObjRepo_AndroidTablet.clickLoanAndEMI();
//		}
//		@Test(priority = 2)
//		public static void optionsDisplayed() {
//			System.out.println("validating options");
//			ObjRepo_AndroidTablet.validateOptions();
//		}
//		
//		@Test(priority = 3)
//		public static void optionPrint() {
//			System.out.println("options:");
//			ObjRepo_AndroidTablet.printOptions();
//		}
//		
//		@Test(priority = 4)
//		public static void loanBasic() throws IOException {
//			System.out.println("validating loan");
//			ObjRepo_AndroidTablet.clickLoanBasic();
//		}
//		
//		@Test(priority = 5)
//		public static void calculate() {
//			System.out.println("validating the results");
//			ObjRepo_AndroidTablet.validateResult();
//		}
//		@Test(priority = 6)
//		public static void vihicleLoanValidation() throws IOException {
//			System.out.println("validation vihicle loan");
//			ObjRepo_AndroidTablet.vihicleLoanValidation();
//		}
//
//	}

