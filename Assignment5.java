package week4.day2homework;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Assignment5 {

	public static void main(String[] args) throws InterruptedException, IOException {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		
		//Search box
		
		WebElement search = driver.findElement(By.id("twotabsearchtextbox"));
		
		search.sendKeys("oneplus 9 pro");
		search.sendKeys(Keys.ENTER);
		String price = driver.findElement(By.className("a-price-whole")).getText();
		System.out.println("1st product price is: "+price);
		
		String reviews = driver.findElement(By.xpath("(//a[@class='a-link-normal s-link-style'])[2]")).getText();
		System.out.println("number of customer ratings: "+reviews);
		
		WebElement ratings = driver.findElement(By.xpath("//a[@class='a-popover-trigger a-declarative']/i"));
		
		//mouse hover to ratings
		Actions builder = new Actions(driver);
		builder.moveToElement(ratings).click().build().perform();
		
		Thread.sleep(3000);
		
		String ratingPercent = driver.findElement(By.xpath("//table[@id='histogramTable']//tr/td[3]/span[2]/a")).getText();
		System.out.println("percentage of ratings for the 5 star: "+ratingPercent);
		
		WebElement product = driver.findElement(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']"));
		String productText = product.getText();
		System.out.println("1st product displayed: "+productText);
		product.click();
		
		//switching windows
		
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> window = new ArrayList<String>(windowHandles);
		String child = window.get(1);		
		driver.switchTo().window(child);		
		String childTitle = driver.getTitle();
		System.out.println(childTitle);
		
		//Screenshot
		
		File screenshotAs = driver.getScreenshotAs(OutputType.FILE);
		File dest = new File("./images/product.png");
		FileUtils.copyFile(screenshotAs, dest);
		
		//Add to cart
		driver.findElement(By.id("add-to-cart-button")).click();
		Thread.sleep(5000);
		String cartSubTotal = driver.findElement(By.id("attach-accessory-cart-subtotal")).getText();
		System.out.println("cart subtotal value: "+cartSubTotal);
		
		if (cartSubTotal.contains(price)) {
			
			System.out.println("Pass");
			
		} else {
			System.out.println("Fail");

		}
		driver.close();
	}

}
