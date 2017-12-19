package core.seleniumWebriver;

import core.util.SystemProperties;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class WebdriverFactor {
    // ------------------------------ FIELDS ------------------------------
    private static final Logger log = LoggerFactory.getLogger(WebdriverFactor.class);
    protected static WebDriver driver;
    protected WebDriverWait wait;

    // -------------------------- PROTECTED METHODS --------------------------
    protected static void openBrowser(String browser, String headless) {
        SystemProperties.set("Update", "N");

        if (browser.equals("GoogleChrome"))
            chromeBrowser(headless);

        if (browser.equals("GeckoDriver"))
            geckoDriverBrowser();
    }

    // -------------------------- PRIVATE METHODS --------------------------
    private static void chromeBrowser(String headless) {

        if (System.getProperty("os.name").contains("Windows")) {
            if (SystemProperties.get("Update").equals("S"))
                ChromeDriverManager.getInstance().setup();
            else
                ChromeDriverManager.getInstance().forceCache().setup();
        } else
            ChromeDriverManager.getInstance().setup();

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");

        if (headless.equals("Sim"))
            chromeOptions.addArguments("headless");

        driver = new ChromeDriver(new ChromeDriverService.Builder().usingAnyFreePort().build(), chromeOptions);
    }

    private static void geckoDriverBrowser() {
       if (SystemProperties.get("Update").equals("S"))
            FirefoxDriverManager.getInstance().setup();

        else FirefoxDriverManager.getInstance().forceCache().setup();

        driver = new org.openqa.selenium.firefox.FirefoxDriver();
    }

    protected void closeDriver() {
        screenShot();
        driver.quit();
    }

    private void screenShot() {
        Integer countScreenshot = 0;
        File file = null;
        try {
            while (countScreenshot++ <= 100) {
                file = new File("target/screenShot/" + SystemProperties.get("Scenario"), getClass().getSimpleName() + "_" + countScreenshot + "_" + ".png");
                if (!file.exists()) {
                    file = new File("target/screenShot/" + SystemProperties.get("Scenario"), getClass().getSimpleName() + "_" + countScreenshot + "_" + ".png");
                    break;
                }
            }
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(srcFile, file);
            System.out.println("ScreenShot: " + file);
        } catch (Exception e) {
            log.debug("Something given wrong, when I tried get the screeshot");
        }
    }
    // -------------------------- END OF METHODS --------------------------
}