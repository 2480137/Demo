package TestNgDemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GoogleTest {
    @Test(priority=0)
    public void openGoogle(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com");
        Assert.assertEquals(driver.getTitle(),"Google");
        driver.quit();
    }
    @Test(priority=1)
    public void test1(){
        System.out.println("test1 executed");
    }
    @Test(priority =-1)
    public void test2(){
        System.out.println("test2 executed");
    }
}

