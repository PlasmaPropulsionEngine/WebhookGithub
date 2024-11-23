package resusable_utility;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageobjects.CartPage;
import pageobjects.OrderPage;

public class Utility {

	WebDriver driver;
	public Utility(WebDriver driver) 
	{
	
		this.driver=driver;
		PageFactory.initElements(driver,this);		
	}

	
	@FindBy(xpath ="//button[@routerlink='/dashboard/cart']")
	WebElement cartBtn;
	
	
	@FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
	WebElement orderBtn; 
	
	
	public CartPage clickOnCartBtn()
	{
		cartBtn.click();
		CartPage cartPage=new CartPage(driver);
		return cartPage;
	}
	
	
	public OrderPage ClickonOderBtn()
	{
		orderBtn.click();
		OrderPage orderPage=new OrderPage(driver);
		return orderPage;		
	}
	
		
	public String waitForLoginSuccesMsg(WebElement loginMsgToast)
	{		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		WebElement loginToastMsg = wait.until(ExpectedConditions.visibilityOf(loginMsgToast));
		
		
		
		return	loginToastMsg.getText();
		
	}


	public List<WebElement> waitForAllCatlougeProducts(List<WebElement> w)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		List<WebElement> products = wait.until(ExpectedConditions.visibilityOfAllElements(w));
		return products;		
	}
	
	
	public void waitForSpinerDisapper(WebElement spiner)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(spiner));
				
	}
	
	
	public WebElement waitForProductToastMsg(WebElement w)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		
		WebElement productToastMsg = wait.until(ExpectedConditions.visibilityOf(w));
		
		return productToastMsg;
	}
	
	
	public void	waitForLoginSuccessMsgDisappear(WebElement loginMsgToast)
	{
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(loginMsgToast));

	}
	

	
	
	
	
	
	
	
	
}
