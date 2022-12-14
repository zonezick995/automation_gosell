package utilities.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

public class InitWebdriver {

    private WebDriver driver;

    public WebDriver getDriver(String browser, String headless) {
        if (driver == null) {
            switch (browser) {
                case "firefox" -> {
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.setHeadless(headless.equals("true"));
                    driver = new FirefoxDriver(firefoxOptions);
                }
                case "edge" -> {
                    WebDriverManager.edgedriver().setup();
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.setHeadless(headless.equals("true"));
                    driver = new EdgeDriver(edgeOptions);
                }
                case "safari" -> {
                    WebDriverManager.safaridriver().setup();
                    driver = new SafariDriver();
                }
                default -> {
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.setHeadless(headless.equals("true"));
                    // fix org.openqa.selenium.WebDriverException: unknown error: cannot determine loading status from no such window
                    chromeOptions.addArguments("--disable-site-isolation-trials");
                    driver = new ChromeDriver(chromeOptions);
                }
            }
        }
        driver.manage().window().setSize(new Dimension(1920,1080));
        return driver;
    }
}
