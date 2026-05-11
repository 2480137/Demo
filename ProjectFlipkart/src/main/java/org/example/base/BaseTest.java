package org.example.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.time.Duration;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseTest {

    public static final String SCREENSHOTS_DIR = "screenshots/";
    public static WebDriver driver;

    private static void suppressLogs() {
        // Suppress handlers on the root logger — this is what actually prints to console
        Logger rootLogger = Logger.getLogger("");
        for (Handler handler : rootLogger.getHandlers()) {
            handler.setLevel(Level.SEVERE);
        }
        rootLogger.setLevel(Level.SEVERE);

        // Also suppress specific Selenium loggers explicitly
        Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);
        Logger.getLogger("org.openqa.selenium.devtools").setLevel(Level.SEVERE);
        Logger.getLogger("org.openqa.selenium.chromium").setLevel(Level.SEVERE);
    }

    public static WebDriver launchBrowser(String browser) {
        suppressLogs();

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            options.setExperimentalOption("useAutomationExtension", false);
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
        }
        else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        else if (browser.equalsIgnoreCase("edge")) {
            EdgeOptions options = new EdgeOptions();
            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            options.setExperimentalOption("useAutomationExtension", false);
            driver = new EdgeDriver(options);
        }
        else {
            throw new RuntimeException("Invalid Browser Name");
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    public static String takeScreenshot(WebDriver driver, String name) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File(SCREENSHOTS_DIR + name + ".png");
            dest.getParentFile().mkdirs();
            FileUtils.copyFile(src, dest);
            return dest.getAbsolutePath();
        } catch (Exception ignored) {}
        return null;
    }
}
