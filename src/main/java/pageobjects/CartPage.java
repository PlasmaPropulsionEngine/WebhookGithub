package pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resusable_utility.Utility;

public class CartPage extends Utility {

	
	WebDriver driver;
	
	public CartPage(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;

		PageFactory.initElements(driver,this);
		
	}

	@FindBy(xpath = "//button[normalize-space()='Checkout']")
	WebElement checkOutBtn;
	
	@FindBy(css="div.infoWrap h3")
	List<WebElement>displayedProducts;
	
	
	public boolean verifyProductDisplyOnCart(String productName)
	{
		
		boolean anyMatch = displayedProducts.stream().anyMatch(p->p.getText().equalsIgnoreCase(productName));
		
		return anyMatch;
	}
	
	
	public CheckOutPage clickOnCheckOutBtn()
	{
			checkOutBtn.click();
			CheckOutPage checkOutPage=new CheckOutPage(driver);
			return checkOutPage;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
