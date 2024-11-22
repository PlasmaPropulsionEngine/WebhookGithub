package pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage {

	
WebDriver driver;

public CheckOutPage(WebDriver driver)
{
	this.driver=driver;
	
	PageFactory.initElements(driver,this);
	
}
	
	
@FindBy(css="div.form__cc div:nth-child(2) div:nth-child(2) input")
WebElement cvvfield;


@FindBy(xpath = "//input[@placeholder='Select Country']")
WebElement countryField;
	
@FindBy(css="section.list-group ")
List<WebElement>countryOptions;

@FindBy(css="a.action__submit")
WebElement placeOrderBtn;

public void EnterCvvNo(String a)
{
	cvvfield.sendKeys(a);
}
	
	
public void addCountry(String country)
{
	Actions a=new Actions(driver);
	
	a.sendKeys(countryField,country).build().perform();
	
	for(WebElement w:countryOptions)
	{
		  w.getText().startsWith(country);
		  w.click();
		
	}
	
}
	
public ConformationPage placeOrder()
{
	placeOrderBtn.click();
	ConformationPage conformationPage=new ConformationPage(driver);
	return conformationPage;
}
	
	
	
	
	
	
	
}
