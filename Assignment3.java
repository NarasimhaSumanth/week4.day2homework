package week4.day2homework;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Assignment3 {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();
		
		WebElement menFashion = driver.findElement(By.xpath("//span[@class='catText' and contains(text(),'Men')]"));
		Actions builder = new Actions(driver);
		builder.moveToElement(menFashion).perform();
		driver.findElement(By.xpath("//span[text()='Sports Shoes']")).click();
	
		WebElement shoeCount = driver.findElement(By.xpath("//h1[@class='category-name']/following-sibling::span"));
		String count = shoeCount.getText();
		System.out.println("Shoe Count is: "+count);
		
		//Click Training shoes
		
		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
		WebElement sort = driver.findElement(By.xpath("//span[text()='Sort by:']"));
		sort.click();
		sort.findElement(By.xpath("//ul[@class='sort-value']/li[2]")).click();
		
	WebElement fromPrice = driver.findElement(By.name("fromVal"));
	fromPrice.clear();
	fromPrice.sendKeys("900");
	fromPrice.sendKeys(Keys.TAB);
	
	WebElement toPrice = driver.findElement(By.name("toVal"));
	toPrice.clear();
	toPrice.sendKeys("1200");
	toPrice.sendKeys(Keys.TAB);
	driver.findElement(By.xpath("//div[normalize-space(text())='GO']")).click();
	Thread.sleep(3000);
	driver.findElement(By.xpath("//label[@for='Color_s-Navy']")).click();
	
	// CHeck for applied filters
	
	//String filter1 = driver.findElement(By.xpath("//div[@class='filters']/div[1]")).getText();
	//String filter2 = driver.findElement(By.xpath("//div[@class='filters']/div[2]")).getText();
	//System.out.println(filter1);
	//System.out.println(filter2);	
	int size = driver.findElements(By.xpath("//div[@class='filters']/div")).size();
	List<String> filters = new ArrayList<String>();

for (int i = 1; i <= size; i++) {
	
	String filterText = driver.findElement(By.xpath("//div[@class='filters']/div["+ i +"]")).getText();
	filters.add(filterText);
	
	
}
System.out.println("Filters applied: " + filters);

	// mouse hover on product & click quick view

		Actions builder1 = new Actions(driver);
		WebElement product = driver.findElement(By.xpath("//div[contains(@class,'product') and contains(@class,'tuple')]"));
		builder1.moveToElement(product).build().perform();
		
		Thread.sleep(3000);
		
		WebElement quickView = driver.findElement(By.xpath("//div[contains(text(),'Quick')]"));
		
		builder1.moveToElement(quickView).click().build().perform();
		
	// MRP & discount range
		
		String amount = driver.findElement(By.className("payBlkBig")).getText();		
		String percent = driver.findElement(By.xpath("//span[@class='percent-desc ']")).getText();
		
		System.out.println("Cost is: " +amount);
		System.out.println("Discount percent is: " +percent);
		
		//Screenshot
		File screenshot = driver.getScreenshotAs(OutputType.FILE);
		File target = new File("./images/shoe.png");
		FileUtils.copyFile(screenshot, target);
		driver.close();
	}
	
	}

