package org.springframework.samples.petclinic.geb

import geb.junit5.GebTest
import org.junit.jupiter.api.Test
import org.springframework.samples.petclinic.geb.pages.CreateOwnerPage
import org.springframework.samples.petclinic.geb.pages.HomePage
import org.springframework.samples.petclinic.geb.pages.OwnerDetailPage
import org.springframework.samples.petclinic.geb.pages.OwnerSearchPage

class CreateOwnerTest extends GebTest {

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
