package org.springframework.samples.petclinic.geb

import geb.junit5.GebTest
import org.junit.jupiter.api.Test
import org.openqa.selenium.chrome.ChromeOptions
import org.slf4j.LoggerFactory
import org.springframework.samples.petclinic.geb.pages.CreateOwnerPage
import org.springframework.samples.petclinic.geb.pages.HomePage
import org.springframework.samples.petclinic.geb.pages.OwnerDetailPage
import org.springframework.samples.petclinic.geb.pages.OwnerSearchPage
import org.testcontainers.containers.*
import org.testcontainers.containers.output.Slf4jLogConsumer
import org.testcontainers.images.builder.ImageFromDockerfile
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
class CreateOwnerTest extends GebTest {

   private static final Network NETWORK = Network.newNetwork();

   @Container
   static MySQLContainer mySQLContainer = (MySQLContainer) new MySQLContainer("mysql:8")
      .withInitScript("mysql-db-init-file.sql")
      .withNetwork(NETWORK)
      .withNetworkAliases("mysql")
      .withLogConsumer(new Slf4jLogConsumer(LoggerFactory.getLogger("Mysql-Container")))

   @Container
   static GenericContainer petClinicContainer = (GenericContainer) new GenericContainer(
      new ImageFromDockerfile()
         .withFileFromPath(".", new File("target").toPath())
         .withDockerfileFromBuilder(
            builder -> builder
               .from("adoptopenjdk/openjdk11")
               .copy("spring-petclinic-2.6.0-SNAPSHOT.jar", "/app.jar")
               .expose(8080)
               .cmd("java", "-jar", "/app.jar")
         )
   )
      .withLogConsumer(new Slf4jLogConsumer(LoggerFactory.getLogger("PetClinic-Container")))
      .withEnv("spring_profiles_active", "mysql")
      .withEnv("MYSQL_URL", "jdbc:mysql://mysql/${mySQLContainer.getDatabaseName()}")
      .withEnv("MYSQL_USER", mySQLContainer.getUsername())
      .withEnv("MYSQL_PASS", mySQLContainer.getPassword())
      .withExposedPorts(8080)
      .withNetwork(NETWORK)
      .withNetworkAliases("petclinic")
      .dependsOn(mySQLContainer)

   @Container
   static BrowserWebDriverContainer webDriverContainer = (BrowserWebDriverContainer) new BrowserWebDriverContainer()
      // https://github.com/testcontainers/testcontainers-java/issues/2552
      .withCapabilities(new ChromeOptions().addArguments("--disable-dev-shm-usage")) // For wsl2 only
      .withRecordingMode(
         BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL,
         new File("target"),
         VncRecordingContainer.VncRecordingFormat.MP4)
      .withSharedMemorySize(2147483648L) // For wsl2 only
      .withLogConsumer(new Slf4jLogConsumer(LoggerFactory.getLogger("Selenium-Container")))
      .withNetwork(NETWORK)
      .dependsOn(petClinicContainer)

   @Test
   void testCreateOwner() {
      HomePage homePage = browser.to(HomePage)
      OwnerSearchPage ownerSearch = homePage.toOwnerSearch()
      CreateOwnerPage createOwner = ownerSearch.toCreateOwner()
      OwnerDetailPage ownerDetailPage = createOwner.createOwner(
         "firstname", "lastName", "Route 666",
         "Radiator Springs", "001432323")
      ownerDetailPage.assertOwnerAttributes("firstname", "lastName", "Route 666", "Radiator Springs", "001432323")
   }
}
