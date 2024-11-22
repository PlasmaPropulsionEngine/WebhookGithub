package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import resusable_utility.Utility;

public class ProductCatalouge extends Utility  {

	WebDriver driver;

	public	ProductCatalouge(WebDriver driver)
	{			
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver,this);
			
	}	
	
	
@FindBy(css="div.card")	
List<WebElement> items;


@FindBy(css=".ng-animating")
WebElement spiner;

By addToCart=By.cssSelector("div.card-body button.w-10");

@FindBy(id="toast-container")
WebElement loginMsgToast;

@FindBy(css="div.toast-bottom-right")
WebElement productToastMsg; 
	
public List<WebElement> getProductList()
{
	List<WebElement> products = waitForAllCatlougeProducts(items);
	return products;
}


public WebElement getProductByName(String productName)
{
		
	WebElement singleProduct = getProductList().stream().filter(product->product.findElement(By.cssSelector("b"))
					.getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
	
	return singleProduct;
}
	
public void addProductToCart(String productName)
{
	//waitForLoginSuccessMsgDisappear(loginMsgToast);
	
	WebElement productByName = getProductByName( productName);
	productByName.findElement(addToCart).click();
	waitForSpinerDisapper(spiner);
//	WebElement productToastMsg2 = waitForProductToastMsg(productToastMsg);
//	
//	Assert.assertEquals(productToastMsg2.getText(),"Product Added To Cart");
	
}
	

	
	
	
	
	
	
}
