package extentreportConfig;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExtentReportBasic
{

	
	WebDriver driver;
	ExtentReports extent;
	
@BeforeTest	
public void configForExtentReport()
{
	
	String path=System.getProperty("user.dir")+"\\reports\\index.html";
	
	ExtentSparkReporter extentSparkReporter=new ExtentSparkReporter(path);
	
	extentSparkReporter.config().setReportName("Web Automation Test");
	extentSparkReporter.config().setDocumentTitle("Test Report");
	extentSparkReporter.config().setTheme(Theme.DARK);
	extentSparkReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
	extentSparkReporter.config().setEncoding("utf-8");
	extentSparkReporter.config().setProtocol(Protocol.HTTPS);
	 extent=new ExtentReports();	
	
	extent.attachReporter(extentSparkReporter);
	extent.setSystemInfo("Tester","Karan");
	
}

@Test
public void test() throws IOException
{
	ExtentTest extentTest = extent.createTest("open browser");
	
	WebDriverManager.chromedriver().setup();
	driver=new ChromeDriver();
	
	driver.get("https://demo.automationtesting.in/Register.html");
	System.out.println(driver.getTitle());
	
	File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	
	FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir")+"\\reports\\screenshot.png"));
	

	driver.close();
	extent.flush();
	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
