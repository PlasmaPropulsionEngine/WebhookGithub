package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import resusable_utility.Utility;

public class LoginPage extends Utility 
{
	WebDriver driver;

	public	LoginPage(WebDriver driver)
	{
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver,this);
//pagefactory used for @findby annotationswwww	  		
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
		//assertions		
		Assert.assertEquals(succesMsg,"Login Successfully");
		
		ProductCatalouge productCatalouge=new ProductCatalouge(driver);
		return productCatalouge;	
	}
	
	

	public void goToUrl()
	{
		driver.get("https://rahulshettyacademy.com/client");
		
	}
	

	
	
	
	
	
	
	
	
	
	
	
}
