package org.springframework.samples.petclinic.geb

import geb.junit5.GebTest
import org.junit.jupiter.api.Test
import org.openqa.selenium.chrome.ChromeOptions
import org.slf4j.LoggerFactory
import org.springframework.samples.petclinic.geb.pages.CreateOwnerPage
import org.springframework.samples.petclinic.geb.pages.HomePage
import org.springframework.samples.petclinic.geb.pages.OwnerDetailPage
import org.springframework.samples.petclinic.geb.pages.OwnerSearchPage
import org.testcontainers.containers.BrowserWebDriverContainer
import org.testcontainers.containers.VncRecordingContainer
import org.testcontainers.containers.output.Slf4jLogConsumer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
class CreateOwnerTest extends GebTest {

   @Container
   static BrowserWebDriverContainer webDriverContainer = (BrowserWebDriverContainer) new BrowserWebDriverContainer()
      .withCapabilities(new ChromeOptions())
      .withRecordingMode(
         BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL,
         new File("target"),
         VncRecordingContainer.VncRecordingFormat.MP4)
      .withLogConsumer(new Slf4jLogConsumer(LoggerFactory.getLogger("Selenium-Container")))

   @Test
   void testCreateOwner(){
      HomePage homePage = browser.to(HomePage)
      OwnerSearchPage ownerSearch = homePage.toOwnerSearch()
      CreateOwnerPage createOwner = ownerSearch.toCreateOwner()
      OwnerDetailPage ownerDetailPage = createOwner.createOwner(
         "firstname", "lastName", "Route 666",
         "Radiator Springs", "001432323")
      ownerDetailPage.assertOwnerAttributes("firstname", "lastName", "Route 666", "Radiator Springs", "001432323")
   }
}
