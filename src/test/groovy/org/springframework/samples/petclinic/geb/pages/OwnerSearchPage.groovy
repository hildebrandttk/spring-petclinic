package org.springframework.samples.petclinic.geb.pages

import geb.Page

class OwnerSearchPage extends Page {

   static url = 'http://localhost:8080/'

   static at = { navElementSearchActive.displayed && toCreateOwnerButton.displayed }

   static content = {
      navElementHome { $('li span.glyphicon-home') }
      navElementSearchActive { $('li.active span.glyphicon-search') }
      toCreateOwnerButton { $('a', href: "/owners/new") }
   }

   CreateOwnerPage toCreateOwner() {
      toCreateOwnerButton.click(CreateOwnerPage)
      browser.at(CreateOwnerPage)
   }
}
