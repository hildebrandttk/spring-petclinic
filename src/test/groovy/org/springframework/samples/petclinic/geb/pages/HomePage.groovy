package org.springframework.samples.petclinic.geb.pages

import geb.Page

class HomePage extends Page {

   static url = '/'

   static at = { wellcomePetsImage.displayed }

   static content = {
      navElementHomeActive(wait: true) { $('li.active span.glyphicon-home') }
      wellcomePetsImage(wait: true) { $('img', src: '/resources/images/pets.png') }
      navElementSearch(wait: true) { $('li.nav-item a[href="/owners/find"]') }
   }

   OwnerSearchPage toOwnerSearch(){
      navElementSearch.click(OwnerSearchPage)
      browser.at(OwnerSearchPage)
   }
}
