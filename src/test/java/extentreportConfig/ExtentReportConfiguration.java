package extentreportConfig;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportConfiguration {

	
	
	
public static ExtentReports getExtentReportObject()
{
	
	String path=System.getProperty("user.dir")+"\\reports\\index.html";
	
	ExtentSparkReporter extentSparkReporter=new ExtentSparkReporter(path);
	
	extentSparkReporter.config().setReportName("Web Automation Test");
	extentSparkReporter.config().setDocumentTitle("Test Report");
	extentSparkReporter.config().setTheme(Theme.DARK);
	extentSparkReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
	extentSparkReporter.config().setEncoding("utf-8");
	extentSparkReporter.config().setProtocol(Protocol.HTTPS);
	ExtentReports extent=new ExtentReports();	
	
	extent.attachReporter(extentSparkReporter);
	extent.setSystemInfo("Tester","Karan");
	
	
	return extent;
	
	
	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
}
