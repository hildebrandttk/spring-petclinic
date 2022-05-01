package org.springframework.samples.petclinic.geb.pages

import geb.Page

class OwnerSearchPage extends Page {

   static at = { navElementSearchActive.displayed && toCreateOwnerButton.displayed }

   static content = {
      navElementSearchActive(wait: true) { $('li.nav-item a.active[href="/owners/find"]') }
      toCreateOwnerButton { $('a.btn', href: "/owners/new") }
   }

   CreateOwnerPage toCreateOwner() {
      toCreateOwnerButton.click(CreateOwnerPage)
      browser.at(CreateOwnerPage)
   }
}
