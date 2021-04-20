package test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.TimeZone;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ReportGen {
	
	ExtentReports extent= null;
	ExtentTest logger=null;
	
	@BeforeClass
	public void beforeClass() {
		
		

		SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MMM-dd hh:mm:ss");
		//Setting the time zone
		dateTimeInGMT.setTimeZone(TimeZone.getTimeZone("EDT"));
		String date=dateTimeInGMT.format(new Date());
		
		extent= new ExtentReports(System.getProperty("user.dir")+"\\Reports\\Build-2\\ExtentReports-1.html",true);
			
	}
	
	@Test
	public void Transaction(){
		//extent.startTest("TestCaseName", "Description")
		//TestCaseName – Name of the test
		//Description – Description of the test
		//Starting test
		logger = extent.startTest("Transaction");
		Assert.assertTrue(true);
		//To generate the log when the test case is passed
		logger.log(LogStatus.PASS, "Test Case Passed is passTest");
	}
	
	@Test
	public void VerifyTransaction(){
		logger = extent.startTest("VerifyTransaction");
		Assert.assertTrue(false);
		logger.log(LogStatus.FAIL, "Test Case (failTest) Status is passed");
	}
	
	@Test
	public void deleteTransaction(){
		logger = extent.startTest("deleteTransaction");
		throw new SkipException("Skipping - This is not ready for testing ");
	}
	
	@Test
	public void VerifydeleteTransaction(){
		logger = extent.startTest("VerifydeleteTransaction");
		throw new NoSuchElementException("No Such element ");
	}
	
	
	
	
	@AfterMethod
	public void getResult(ITestResult result){
		if(result.getStatus() == ITestResult.FAILURE){
			//logger.log(LogStatus.FAIL, result.getName());
			logger.log(LogStatus.FAIL, result.getName()+" ----> "+result.getThrowable());
		}else if(result.getStatus() == ITestResult.SKIP){
			logger.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
		}
		// ending test
		//endTest(logger) : It ends the current test and prepares to create HTML report
		extent.endTest(logger);
	}
	
	@AfterClass
	public void afClass() throws IOException {
		
		//Write extent reports to Html file
		extent.flush();
		
//		//Write extent report object to file
//		 FileOutputStream fout=new FileOutputStream("ExetRepobject.txt");  
//		  ObjectOutputStream out=new ObjectOutputStream(fout);  
//		  out.writeObject(extReports);  
//		  out.flush();  
//		  //closing the stream  
//		  out.close(); 
		
	}

}
