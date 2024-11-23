package tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.github.dockerjava.api.command.WaitContainerCmd;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {
//this is stand alone test for pageobject model
	public static void main(String[] args) {


		WebDriverManager.chromedriver().setup();
		
		WebDriver driver=new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		
		driver.manage().window().maximize();
		
		driver.get("https://rahulshettyacademy.com/client");
		
		driver.findElement(By.id("userEmail")).sendKeys("docker@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Selenium@11");
		
		driver.findElement(By.id("login")).click();		
		String productname="ZARA COAT 3";
		
		
		
		WebElement loginToastMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		
			String text = loginToastMsg.getText();
		
		Assert.assertEquals(text,"Login Successfully");
		
		List<WebElement> products = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.card")));
		
		
		WebElement singleProduct = products.stream().filter(s->s.findElement(By.cssSelector("div.card-body b"))
				.getText().equalsIgnoreCase(productname)).findFirst().orElse(null);
		
		System.out.println(singleProduct.getText());
		
		singleProduct.findElement(By.cssSelector("div.card-body button.w-10")).click();
		
		//.ng-animating css for loading and wait for loading
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
//		//wait for add to cart and message show added product	
//		WebElement addProductMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.toast-bottom-right")));
//			
//		String addProductMs = addProductMsg.getText();
//		
//		Assert.assertEquals(addProductMs,"Product Added To Cart");
//		
		driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
		
		List<WebElement> displayedProducts = driver.findElements(By.cssSelector("div.infoWrap h3"));
		
		boolean anyMatch = displayedProducts.stream().anyMatch(s->s.getText().equalsIgnoreCase(productname));
		Assert.assertTrue(anyMatch);
		
		driver.findElement(By.xpath("//button[normalize-space()='Checkout']")).click();
		
		driver.findElement(By.cssSelector("div.form__cc div:nth-child(2) div:nth-child(2) input")).sendKeys("123");
			
		Actions a=new Actions(driver);
		a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")),"ind").build().perform();
		
		List<WebElement> options = driver.findElements(By.cssSelector("section.list-group "));
		
		for(WebElement w:options)
		{
			  w.getText().startsWith("ind");
			  w.click();
			
		}
		
		driver.findElement(By.cssSelector("a.action__submit")).click();
		
		String ordersuccessMsg = driver.findElement(By.cssSelector("h1.hero-primary")).getText();
		
		Assert.assertEquals(ordersuccessMsg,"THANKYOU FOR THE ORDER.");
		driver.close();
		
		
		
		
		

	}

}
