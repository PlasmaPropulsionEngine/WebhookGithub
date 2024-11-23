package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConformationPage {

WebDriver driver;


public ConformationPage(WebDriver driver)
{
	this.driver=driver;
	
	PageFactory.initElements(driver,this);
	
}


@FindBy(css="h1.hero-primary")
WebElement ordersuccessMsg;
	
public String verifyConformationMessage()
{
	
	String ordeMsg = ordersuccessMsg.getText();
	
	return ordeMsg;
	//Assert.assertEquals(ordeMsg,"THANKYOU FOR THE ORDER.");
		
}
	
	
	
	
	
	
	
	
	
	
	
	
}
