package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstTestCase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://nopcommerce.com/");
		driver.manage().window().maximize();
		String title = driver.getTitle();
		
		if(title.equals("Free and open-source eCommerce platform. ASP.NET Core based shopping cart. - nopCommerce")) {
			System.out.println("Passed");
		}
		else {
			System.out.println("Fail");
		}
		
		//driver.close();
		//driver.quit();
	}

}
