package org.example;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CaptureScreenshots {

	public static void main(String[] args) {
	
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://demo.nopcommerce.com/");
		driver.manage().window().maximize();
		
		//1) full page screenshot
		TakesScreenshot ts=(TakesScreenshot)driver;
		File sourcefile=ts.getScreenshotAs(OutputType.FILE);
		File targetfile=new File(System.getProperty("user.dir")+"\\screenshots\\fullpage.png");
		sourcefile.renameTo(targetfile); // copy sourcefile to target file

		
		//2) capture the screenshot of specific section
		WebElement featuredProducts=driver.findElement(By.xpath("//section[@class='product-grid home-page-product-grid']//div[@class='item-grid']"));
		
		File source=featuredProducts.getScreenshotAs(OutputType.FILE);
		File target=new File(System.getProperty("user.dir")+"\\screenshots\\featredproducts.png");
		sourcefile.renameTo(target); // copy sourcefile to target file

		
		//3) capture the screenshot of webelement
		WebElement logo=driver.findElement(By.xpath("//img[@alt='nopCommerce demo store']"));
		File source2=logo.getScreenshotAs(OutputType.FILE);
		File target2=new File(System.getProperty("user.dir")+"\\screenshots\\logo.png");
		sourcefile.renameTo(target2); // copy sourcefile to target file
		
		//driver.quit();
		
		
	
	}

}
