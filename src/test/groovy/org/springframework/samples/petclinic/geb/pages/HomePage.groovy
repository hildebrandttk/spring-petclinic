package org.springframework.samples.petclinic.geb.pages

import geb.Page

class HomePage extends Page {

   static url = 'http://localhost:8080/'

   static at = { navElementHomeActive.displayed }

   static content = {
      navElementHomeActive(wait: true) { $('li.active span.glyphicon-home') }
      navElementSearch { $('li span.glyphicon-search') }
   }

   OwnerSearchPage toOwnerSearch(){
      navElementSearch.click(OwnerSearchPage)
      browser.at(OwnerSearchPage)
   }
}
