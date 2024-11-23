package test_components;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import extentreportConfig.ExtentReportConfiguration;


public class Listeners extends BaseTest implements ITestListener
{
	ExtentTest extentTest;
	
	ExtentReports extent=ExtentReportConfiguration.getExtentReportObject();
	
	ThreadLocal<ExtentTest> threadtest=new ThreadLocal<ExtentTest>(); 
	
	@Override
	public void onTestStart(ITestResult result) {
		
		 extentTest = extent.createTest(result.getMethod().getMethodName());
		
		 threadtest.set(extentTest);
		
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		threadtest.get().log(Status.PASS,"Test Passed");
		
		
	}

	@Override
	public void onTestFailure(ITestResult result) {

		//extentTest.log(Status.FAIL,"Test Fail");
		threadtest.get().fail(result.getThrowable());
		try
		{
		driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} 
		catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e1) {
		
		e1.printStackTrace();
		}
		String pathScreenshot=null;
		try 
		{
			 pathScreenshot = getScreenShot(result.getMethod().getMethodName(),driver);
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		threadtest.get().addScreenCaptureFromPath(pathScreenshot,result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		
		threadtest.get().log(Status.SKIP,"Test skipped");

	}

	@Override
	public void onFinish(ITestContext context) {
		
		extent.flush();
		
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
