package tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.CartPage;
import pageobjects.CheckOutPage;
import pageobjects.ConformationPage;
import pageobjects.ProductCatalouge;
import test_components.BaseTest;
import test_components.Retry;

public class Json_Test  extends BaseTest{

	

	@Test(dataProvider = "dataset")	
	public void EndTEndTest(HashMap<String,String>input) throws InterruptedException
	{

		ProductCatalouge productCatalouge = loginPage.login(input.get("email"),input.get("pass"));
		
		productCatalouge.addProductToCart(input.get("product"));	
		Thread.sleep(2000);	
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
	public Object[][] dataset() throws IOException
	{
		List<HashMap<String, String>> dataJson = getJsonToHashMap(System.getProperty("user.dir")+"\\src\\test\\java\\json_data\\data.json");
		
		return new Object[][] {{dataJson.get(0)},{dataJson.get(1)}};
		
		
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
