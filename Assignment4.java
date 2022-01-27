package week4.day2homework;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Assignment4 {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.get("https://www.myntra.com/");
		driver.manage().window().maximize();
		
	// mouse hover on men
		
		WebElement men = driver.findElement(By.xpath(" //div[@class='desktop-navLink']/a"));
		Actions builder = new Actions(driver);
		builder.moveToElement(men).build().perform();
		
		driver.findElement(By.linkText("Jackets")).click();
		
		String jacketsCount = driver.findElement(By.className("title-count")).getText();
		System.out.println("Jackets count is: "+jacketsCount);
		
		String category1 = driver.findElement(By.xpath("//input[@value='Jackets']/following-sibling::span")).getText();
		String category2 = driver.findElement(By.xpath("//input[@value='Rain Jacket']/following-sibling::span")).getText();
		
		System.out.println(category1+category2);
		
	//	click on Jackets check box
		driver.findElement(By.xpath("//input[@value='Jackets']/following-sibling::div")).click();
		
		driver.findElement(By.className("brand-more")).click();
		driver.findElement(By.className("FilterDirectory-searchInput")).sendKeys("Duke");
		driver.findElement(By.xpath("//input[@value='Duke']/following-sibling::div")).click();
		driver.findElement(By.xpath("//input[@class='FilterDirectory-searchInput']/following-sibling::span")).click();
		
	//checking brand name of all products
		
		List<WebElement> brandList = driver.findElements(By.xpath("//h3[@class='product-brand']"));
		Thread.sleep(3000);
		int size = brandList.size();
		System.out.println(size);
		
	// Creating a Set as this will eliminates duplicate brand names	
		Set<String> brandName = new HashSet<String>();
		
		for (int i = 1; i <= size; i++) {
			
			String text = driver.findElement(By.xpath("(//h3[@class='product-brand'])["+i+"]")).getText();
			
			brandName.add(text);		
			
		}
		System.out.println("List of All brand names: "+brandName);
		
		// Sort by better discount
		
		WebElement sort = driver.findElement(By.className("sort-sortBy"));				
				
		builder.moveToElement(sort).build().perform();
		
		driver.findElement(By.xpath("//ul[@class='sort-list']//li[3]")).click();
		
		Thread.sleep(2000);
		
		// getting price of 1st product
		
		String price = driver.findElement(By.xpath("//span[@class='product-discountedPrice']")).getText();
		System.out.println("price of first displayed item: "+price);
		
		driver.findElement(By.xpath("//div[@class='product-imageSliderContainer']")).click();
		
		//switching windows
		
				Set<String> windowHandles = driver.getWindowHandles();
				List<String> window = new ArrayList<String>(windowHandles);
				String child = window.get(1);		
				driver.switchTo().window(child);		
				String childTitle = driver.getTitle();
				System.out.println(childTitle);
				
				//Screenshot
				
				File screenshotAs = driver.getScreenshotAs(OutputType.FILE);
				File dest = new File("./images/jacket.png");
				FileUtils.copyFile(screenshotAs, dest);
				
				driver.findElement(By.xpath("//span[text()='WISHLIST']")).click();
				
				driver.quit();
	}

}
