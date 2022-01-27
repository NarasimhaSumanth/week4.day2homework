package week4.day2homework;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Assignment6 {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.get("http://www.leafground.com/pages/sorttable.html");
		driver.manage().window().maximize();
		
		List<WebElement> names = driver.findElements(By.xpath("//tbody/tr/td[2]"));
		int size = names.size();
		System.out.println("row count is: "+size);
		List<String> namesList =  new ArrayList<String>();
		
		for (int i = 1; i <= size; i++) {
			
			String nameText = driver.findElement(By.xpath("//tbody/tr["+i+"]"+"/td[2]")).getText();
			namesList.add(nameText);
			
		}
		System.out.println("Original order of names: "+namesList);		
		Collections.sort(namesList);	
		System.out.println("Sorted order of names through collections.Sort: "+namesList);
		
		driver.findElement(By.xpath("//th[text()='Name']")).click();
		List<WebElement> namesSorted = driver.findElements(By.xpath("//tbody/tr/td[2]"));
		int sizeSorted = namesSorted.size();
		System.out.println(sizeSorted);
		List<String> namesListSorted =  new ArrayList<String>();
		
		for (int i = 1; i <= sizeSorted; i++) {
			
			String nameTextSorted = driver.findElement(By.xpath("//tbody/tr["+i+"]"+"/td[2]")).getText();
			namesListSorted.add(nameTextSorted);
			
		}
		
		System.out.println("Names sorted on webpage: " + namesListSorted);
		
	if (namesListSorted.equals(namesList)) {
		
		System.out.println("Sorting done");
		
	} else {
		System.out.println("Sorting not done");

	}
		
		driver.close();

	}


}
