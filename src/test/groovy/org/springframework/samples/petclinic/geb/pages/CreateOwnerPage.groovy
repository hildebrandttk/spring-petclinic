package org.springframework.samples.petclinic.geb.pages

import geb.Page
import geb.module.TextInput
import org.openqa.selenium.By

class CreateOwnerPage extends Page {

   static url = 'http://localhost:8080/'

   static at = { navElementSearchActive.displayed && createOwnerButton.displayed && firstNameInput.displayed }

   static content = {
      navElementHome { $('li span.glyphicon-home') }
      navElementSearchActive { $('li.active span.glyphicon-search') }
      firstNameInput { $(By.name('firstName')).module(TextInput) }
      lastNameInput { $(By.name('lastName')).module(TextInput) }
      addressInput { $(By.name('address')).module(TextInput) }
      cityInput { $(By.name('city')).module(TextInput) }
      telephoneInput { $(By.name('telephone')).module(TextInput) }
      createOwnerButton { $('button', type: "submit") }
   }

   OwnerDetailPage createOwner(String firstName, String lastName, String address, String city, String telephone) {
      firstNameInput.setText(firstName)
      lastNameInput.setText(lastName)
      addressInput.setText(address)
      cityInput.setText(city)
      telephoneInput.setText(telephone)
      createOwnerButton.click()
      browser.at(OwnerDetailPage)
   }
}
