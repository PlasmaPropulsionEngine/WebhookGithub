package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import pageobjects.CartPage;
import pageobjects.CheckOutPage;
import pageobjects.ConformationPage;
import pageobjects.ProductCatalouge;
import test_components.BaseTest;

public class StandAlonePageObject extends BaseTest {

	
String productName="ZARA COAT 3";	
	
@Test
public void EndTEndTest() throws InterruptedException
{

	ProductCatalouge productCatalouge = loginPage.login("spider@gmail.com","Karan@12");
	
	productCatalouge.addProductToCart(productName);	
	
	Thread.sleep(2000);
	CartPage cartPage = productCatalouge.clickOnCartBtn();
	
	boolean verifyProductDisplyOnCart = cartPage.verifyProductDisplyOnCart(productName);
	
	Assert.assertTrue(verifyProductDisplyOnCart);
	
	CheckOutPage checkOutPage = cartPage.clickOnCheckOutBtn();
	
	checkOutPage.addCountry("ind");
	
	ConformationPage conformationPage = checkOutPage.placeOrder();
	
	String ConformationMessage = conformationPage.verifyConformationMessage();
	
	
	Assert.assertEquals("THANKYOU FOR THE ORDER.",ConformationMessage);
	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
