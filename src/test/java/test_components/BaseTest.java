package test_components;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageobjects.LoginPage;

public class BaseTest
{

public WebDriver driver;	

public LoginPage loginPage;
	
	
public WebDriver initializeDriver() throws IOException
{
	
Properties pro=new Properties();

InputStream fin=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\GlobalData.properties");

pro.load(fin);
	
//get parameter from maven top level command 

String browser =System.getProperty("Browser")!=null ? System.getProperty("Browser") : pro.getProperty("browser"); 

String options= System.getProperty("Options")!=null ? System.getProperty("Options") : "visible";

//String browser = pro.getProperty("browser");	
	
if(browser.equalsIgnoreCase("chrome"))
{
	WebDriverManager.chromedriver().setup();
	ChromeOptions option=new ChromeOptions();
	
	if(options.equalsIgnoreCase("headless"))
	{
		option.addArguments("headless");
	}
	option.addArguments("--disable-notifications");
	driver=new ChromeDriver(option);
	driver.manage().window().setSize(new Dimension(1440,900));
}
	
else if(browser.equalsIgnoreCase("edge"))
{
	WebDriverManager.edgedriver().setup();
	EdgeOptions option=new EdgeOptions();
	if(options.equalsIgnoreCase("headless"))
	{
		option.addArguments("headless");
	}
	option.addArguments("--disable-notifications");
	driver=new EdgeDriver(option);
	driver.manage().window().setSize(new Dimension(1440,900));
}
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
driver.manage().window().maximize();
return driver;
	
	
}
	
@BeforeMethod(alwaysRun = true)
public LoginPage startApplication() throws IOException
{
	 driver = initializeDriver();
	
	 loginPage=new LoginPage(driver);
	 
	 loginPage.goToUrl();
	 
	 return loginPage;
	
}
	


@AfterMethod(alwaysRun=true)
public void closebrowser()
{
	driver.close();
	
}
	
//metho to get json data to hashmap

public List<HashMap<String, String>> getJsonToHashMap(String path) throws IOException
{

			String jsoncontent = FileUtils.readFileToString(new File(path),StandardCharsets.UTF_8);
	
			//object mapper class used to conver the jsonstring into hashmap 
			ObjectMapper mapper=new ObjectMapper();
			
			List<HashMap<String, String>> data = mapper.readValue(jsoncontent,new TypeReference<List<HashMap<String,String>>>(){});
			return data;
			
}
	
//get screenshot and path of it	

public String getScreenShot(String testcaseName,WebDriver driver) throws IOException
{
	
	TakesScreenshot ts=(TakesScreenshot)driver;
	File screenshot = ts.getScreenshotAs(OutputType.FILE);
	File file=new File(System.getProperty("user.dir")+"//reports//"+testcaseName+".png");
	
	FileUtils.copyFile(screenshot,file);
	
	return System.getProperty("user.dir")+"//reports//"+testcaseName+".png";
	
	
}





	
	
	
	
	
	
	
	
	
	
}
