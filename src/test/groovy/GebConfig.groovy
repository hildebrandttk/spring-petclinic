import io.github.bonigarcia.wdm.WebDriverManager
import io.github.bonigarcia.wdm.config.DriverManagerType
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver

cacheDriver = true
waiting {
   timeout = 5
}

driver = {
   WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
   new ChromeDriver()
}
