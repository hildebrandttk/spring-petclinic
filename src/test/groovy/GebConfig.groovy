import org.springframework.samples.petclinic.geb.CreateOwnerTest

cacheDriver = true
//baseUrl = 'http://localhost:8080'
baseUrl = "http://host.testcontainers.internal:8080"
waiting {
   timeout = 5
}

driver = {
   CreateOwnerTest.webDriverContainer.getWebDriver()
}
