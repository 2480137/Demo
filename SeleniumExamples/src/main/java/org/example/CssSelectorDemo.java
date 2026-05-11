package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class CssSelectorDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver = new EdgeDriver();
		driver.get("https://demo.opencart.com/index.php?route=common/home");
		driver.manage().window().maximize();
		
		driver.findElement(By.cssSelector(".form-control.mb-1"));
		driver.findElement(By.cssSelector("input.form-control form-control-lg")).sendKeys("Macbook");
		//driver.close();
	}

}
