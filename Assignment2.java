package week4.day2homework;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Assignment2 {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.get("https://www.nykaa.com/");
		driver.manage().window().maximize();
		
		WebElement brands = driver.findElement(By.xpath("//a[text()='brands']"));
		
		Actions builder = new Actions(driver);
		
		builder.moveToElement(brands).perform();
		
		//Search option
		
		driver.findElement(By.id("brandSearchBox")).sendKeys("L'Oreal Paris");
		driver.findElement(By.linkText("L'Oreal Paris")).click();
		String title = driver.getTitle();
		if (title.contains("L'Oreal Paris")) {
			
			System.out.println("Title contains L'Oreal Paris");
			
		} else {
			System.out.println("Fail");

		}
		
		WebElement sortBy = driver.findElement(By.className("sort-name"));
		sortBy.click();
		
		//Sort by customer top rated
		
		driver.findElement(By.xpath("//span[text()='customer top rated']")).click();
		
		//Click category
		
		driver.findElement(By.xpath("//span[text()='Category']")).click();
		driver.findElement(By.xpath("//span[text()='Hair']")).click();
		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();		
		driver.findElement(By.xpath("//span[text()='Shampoo']")).click();
		driver.findElement(By.xpath("//span[text()='Concern']")).click();
		driver.findElement(By.xpath("//span[text()='Color Protection']")).click();
		
		//Checking filter applied
		WebElement filter = driver.findElement(By.xpath("//span[@class='filter-value' and text()='Shampoo']"));
		String filterText = filter.getText();
		System.out.println("Filter applied" + " " + "is: "+filterText);
		
		driver.findElement(By.xpath("(//div[contains(text(),'Oreal Paris Colour Protect Shampoo')])[1]")).click();
		
		
		//Switch to new window
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> child = new ArrayList<String>(windowHandles);
		String childWindow = child.get(1);
		driver.switchTo().window(childWindow);
		
		//Getting MRP
				WebElement mrp = driver.findElement(By.xpath(" //span[text()='MRP:']/following-sibling::span"));
				String mrpText = mrp.getText();
				System.out.println("MRP: "+mrpText);
		
		driver.findElement(By.xpath("//span[text()='ADD TO BAG']")).click();
		
		Thread.sleep(2000);
		
		// go to shopping bag
		WebElement cart = driver.findElement(By.className("cart-count"));
		cart.click();
		
		driver.switchTo().frame(0);
		Thread.sleep(2000);
		WebElement grandTotal = driver.findElement(By.xpath("//span[text()='Grand Total']/parent::div//div"));
		String total = grandTotal.getText();
		System.out.println("Grand Total "+"is: "+total);
		
		driver.findElement(By.xpath("//span[text()='PROCEED']")).click();
		driver.findElement(By.xpath("//button[@class='btn full big']")).click();
		WebElement gTotal = driver.findElement(By.xpath("//div[text()='Grand Total']/parent::div//div/span"));
		String gt = gTotal.getText();
		System.out.println("Guest page total is: "+gt);
		
		if (total.contains(gt)) {
			System.out.println("grand total is the same");
			
		} else {
			System.out.println("grand total is not same");

		}
		driver.quit();
		
	}

}
