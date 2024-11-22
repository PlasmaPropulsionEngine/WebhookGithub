package tests;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.CartPage;
import pageobjects.CheckOutPage;
import pageobjects.ConformationPage;
import pageobjects.ProductCatalouge;
import test_components.BaseTest;
import test_components.Retry;

public class DataProviderHashMapTest extends BaseTest
{

	

	
	@Test(dataProvider = "dataset",retryAnalyzer = Retry.class)	
	public void EndTEndTest(HashMap<String,String>input)
	{

		ProductCatalouge productCatalouge = loginPage.login(input.get("email"),input.get("pass"));
		
		productCatalouge.addProductToCart(input.get("product"));	
		
		CartPage cartPage = productCatalouge.clickOnCartBtn();
		
		boolean verifyProductDisplyOnCart = cartPage.verifyProductDisplyOnCart(input.get("product"));
		
		Assert.assertTrue(verifyProductDisplyOnCart);
		
		CheckOutPage checkOutPage = cartPage.clickOnCheckOutBtn();
		
		checkOutPage.addCountry("ind");
		
		ConformationPage conformationPage = checkOutPage.placeOrder();
		
		String ConformationMessage = conformationPage.verifyConformationMessage();
		
		
		Assert.assertEquals(ConformationMessage,"THANKYOU FOR THE ORDER.");
		
		
	}
		
			
@DataProvider(name = "dataset")	
public Object[][] dataset()
{
	
	HashMap<String,String> map=new HashMap<String,String>();
	
	map.put("email","watch@gmail.com");
	map.put("pass", "Karan@13");
	map.put("product","ZARA COAT 3");
	
	HashMap<String,String> map1=new HashMap<String,String>();
	
	map1.put("email","spider@gmail.com");
	map1.put("pass", "Karan@12");
	map1.put("product","ADIDAS ORIGINAL");
	
	return new Object[][] {{map},{map1}};

	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
