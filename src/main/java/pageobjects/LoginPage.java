package pageobjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import resusable_utility.Utility;

public class LoginPage extends Utility 
{
	WebDriver driver;

	public	LoginPage(WebDriver driver)
	{
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver,this);
			
	}
		
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement loginBtn;
	
	@FindBy(id="toast-container")
	WebElement loginMsgToast;
	

	public ProductCatalouge login(String userName,String password)
	{
		userEmail.sendKeys(userName);
		userPassword.sendKeys(password);
		loginBtn.click();
		String succesMsg = waitForLoginSuccesMsg(loginMsgToast);
		Assert.assertEquals(succesMsg,"Login Successfully");
		
		ProductCatalouge productCatalouge=new ProductCatalouge(driver);
		return productCatalouge;	
	}
	
	

	public void goToUrl()
	{
		driver.get("https://rahulshettyacademy.com/client");
		
	}
	

	
	
	
	
	
	
	
	
	
	
	
}
