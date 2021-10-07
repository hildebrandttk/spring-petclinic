import io.github.bonigarcia.wdm.WebDriverManager
import io.github.bonigarcia.wdm.config.DriverManagerType
import org.openqa.selenium.chrome.ChromeDriver

cacheDriver = true
baseUrl = 'http://localhost:8080'

waiting {
   timeout = 5
}

driver = {
   WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
   new ChromeDriver()
}
