package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.CartPage;
import pageobjects.CheckOutPage;
import pageobjects.ConformationPage;
import pageobjects.OrderPage;
import pageobjects.ProductCatalouge;
import resusable_utility.Utility;
import test_components.BaseTest;

public class DataProviderTest extends BaseTest{

	
	
	String productName="ZARA COAT 3";	
	
	
	@Test(enabled = false,dataProvider = "dataset")	
	public void EndTEndTest(String username,String password,String productName)
	{

		ProductCatalouge productCatalouge = loginPage.login(username,password);
		
		productCatalouge.addProductToCart(productName);	
		
		CartPage cartPage = productCatalouge.clickOnCartBtn();
		
		boolean verifyProductDisplyOnCart = cartPage.verifyProductDisplyOnCart(productName);
		
		Assert.assertTrue(verifyProductDisplyOnCart);
		
		CheckOutPage checkOutPage = cartPage.clickOnCheckOutBtn();
		
		checkOutPage.addCountry("ind");
		
		ConformationPage conformationPage = checkOutPage.placeOrder();
		
		String ConformationMessage = conformationPage.verifyConformationMessage();
		
		
		Assert.assertEquals(ConformationMessage,"THANKYOU FOR THE ORDER.");
		
		
	}
		

@Test
public void OrderHistoryTest()
{
	ProductCatalouge productCatalouge = loginPage.login("watch@gmail.com","Karan@13");
		
	OrderPage orderPage = productCatalouge.ClickonOderBtn();
	
	Boolean verifyOrderDispay = orderPage.VerifyOrderDispay(productName);
	
	Assert.assertTrue(verifyOrderDispay);
	
}
	
@DataProvider(name = "dataset")			
public Object[][] dataset()
{
	
//	Object data[][]=new Object[2][3];
//	
//	data[0][0]="docker@gmail.com";
//	data[0][1]="Selenium@11";
//	data[0][2]="ZARA COAT 3";
//	data[1][0]="watch@gmail.com";
//	data[1][1]="Karan@13";
//	data[1][2]="ZARA COAT 3";
//	
//	return data;
	return new Object[][] {{"watch@gmail.com" ,"Karan@13","ZARA COAT 3"},{"spider@gmail.com","Karan@12","ADIDAS ORIGINAL"}};

}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
