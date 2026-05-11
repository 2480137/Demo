package org.example;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BlockingAds {

public static void main(String[] args) throws InterruptedException {
		
		ChromeOptions options=new ChromeOptions();
		
		File file=new File("C:\\Users\\2480137\\IdeaProjects\\SeleniumExamples\\uBlock-Origin-Lite-Chrome-Web-Store.crx");
		options.addExtensions(file);
	
	
		WebDriver driver=new ChromeDriver(options);
		
		driver.get("https://text-compare.com/");

	    Thread.sleep(3000);

// Refresh the page — this is important!
// uBlock often needs a page reload to fully kick in on first launch
	driver.navigate().refresh();
	   Thread.sleep(2000);
	
	}
	
	
}
