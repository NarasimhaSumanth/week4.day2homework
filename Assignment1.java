package week4.day2homework;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Assignment1 {
	
public void draggable() {
	
	WebDriverManager.chromedriver().setup();
	ChromeDriver driver = new ChromeDriver();
	driver.get("https://jqueryui.com/draggable/");
	driver.manage().window().maximize();
	driver.switchTo().frame(0);
	System.out.println("switched to frame");
	WebElement source = driver.findElement(By.id("draggable"));	
	
	//Location of the object
	
	Point location = source.getLocation();
	int x = location.getX();
	int y = location.getY();
	
	System.out.println(x +" "+ y);
	
	Actions builder = new Actions(driver);
	builder.dragAndDropBy(source,40,40).perform();
	System.out.println("Dragged successfully");
	driver.close();
	
}

public void droppable() {
	WebDriverManager.chromedriver().setup();
	ChromeDriver driver = new ChromeDriver();
	driver.get("https://jqueryui.com/droppable/");
	driver.manage().window().maximize();
	driver.switchTo().frame(0);
	System.out.println("switched to frame");
	WebElement source = driver.findElement(By.id("draggable"));	
	WebElement target = driver.findElement(By.id("droppable"));
	
	Actions builder =  new Actions(driver);
	builder.dragAndDrop(source, target).perform();
	System.out.println("Drag & dropped successfully");
	driver.close();

}

public void reSizable() throws InterruptedException {
	WebDriverManager.chromedriver().setup();
	ChromeDriver driver = new ChromeDriver();
	driver.get("https://jqueryui.com/resizable/");
	driver.manage().window().maximize();
	driver.switchTo().frame(0);
	System.out.println("switched to frame");
	WebElement source = driver.findElement(By.xpath("//div[@class='ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se']"));
	
	//Location of the object
	
	Point location = source.getLocation();
	int x = location.getX();
	int y = location.getY();
	
	System.out.println(x +" "+ y);
	
	Actions builder = new Actions(driver);
	builder.dragAndDropBy(source, 158, 300);
	System.out.println("Done resizing");
	driver.close();
	

}

public void selectable() {
	WebDriverManager.chromedriver().setup();
	ChromeDriver driver = new ChromeDriver();
	driver.get("https://jqueryui.com/selectable/");
	driver.manage().window().maximize();
	driver.switchTo().frame(0);
	System.out.println("switched to frame");
	
	WebElement source = driver.findElement(By.xpath("//li[text()='Item 1']"));
	WebElement target = driver.findElement(By.xpath("//li[text()='Item 4']"));
	
	
	Actions builder = new Actions(driver);
	builder.keyDown(Keys.CONTROL).click(source).click(target).keyUp(Keys.CONTROL).perform();
	System.out.println("selected "+ " "+ source.getText() +" "+ target.getText());
	driver.close();

}

public void sortable() {
	WebDriverManager.chromedriver().setup();
	ChromeDriver driver = new ChromeDriver();
	driver.get("https://jqueryui.com/sortable/");
	driver.manage().window().maximize();
	driver.switchTo().frame(0);
	System.out.println("switched to frame");
	
	WebElement source = driver.findElement(By.xpath("//li[text()='Item 1']"));
	WebElement target = driver.findElement(By.xpath("//li[text()='Item 4']"));
	Actions builder = new Actions(driver);
	builder.click(source).clickAndHold().moveToElement(target).release().build().perform();
	System.out.println("sorted successfully");
	driver.close();

}
	public static void main(String[] args) throws InterruptedException {
		
		Assignment1 obj = new Assignment1();
		obj.draggable();
		obj.droppable();
		obj.reSizable();
		obj.selectable();
		obj.sortable();

	}

}
