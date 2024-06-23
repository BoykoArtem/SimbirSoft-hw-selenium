import com.aptekaeconom.BaseConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    private WebDriver driver;
    protected WebDriver getDriver() {
        return driver;
    }
    private final BaseConfig config = ConfigFactory.create(BaseConfig.class, System.getenv());

    @BeforeMethod
    protected void setUp() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--window-size=1920,1080");
        chromeOptions.addArguments("--headless");
        driver = new ChromeDriver(chromeOptions);
        Cookie cityCookie = new Cookie("current_region", "103006");
        driver.get(config.url());
        driver.manage().addCookie(cityCookie);
        driver.navigate().refresh();
    }
    @AfterMethod
    protected void tearDown() {
        driver.close();
    }
}