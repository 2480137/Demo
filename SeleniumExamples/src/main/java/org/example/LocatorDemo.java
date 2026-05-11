package org.example;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LocatorDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.demoblaze.com/index.html");
		driver.manage().window().maximize();
		
		List<WebElement>  link_size = driver.findElements(By.tagName("a"));
		System.out.println("No of Links - " + link_size.size());
		
		List<WebElement>  img_size = driver.findElements(By.tagName("img"));
		System.out.println("No of Images - " + img_size.size());
		
		WebElement link = driver.findElement(By.linkText("Cart"));
		link.click();
		
	}

}
