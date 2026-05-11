package TestNgDemo;
import org.testng.annotations.*;

public class AnnotationDemo {



    @BeforeSuite
    public void beforeSuite() {
        System.out.println("BeforeSuite → Setup reports, environment, DB connection, file paths");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("AfterSuite → Flush reports, close DB, clean environment");
    }



    @BeforeTest
    public void beforeTest() {
        System.out.println("BeforeTest → Read browser & parameters (Chrome/Firefox)");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("AfterTest → After all test execution");
    }



    @BeforeClass
    public void beforeClass() {
        System.out.println("BeforeClass → Start browser & launch application");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("AfterClass → Close browser");
    }



    @BeforeMethod
    public void beforeMethod() {
        System.out.println("BeforeMethod → Login (write once, reuse for all tests)");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("AfterMethod → Logout & take screenshot on failure");
    }



    @Test
    public void addToCartTest() {
        System.out.println("TEST → Add product to cart");
    }

    @Test
    public void viewProductTest() {
        System.out.println("TEST → View product details");
    }
}