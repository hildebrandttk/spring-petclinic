package org.springframework.samples.petclinic.geb.pages

import geb.Page
import geb.module.TextInput

class OwnerDetailPage extends Page {

   static url = 'http://localhost:8080/'

   static at = { navElementSearchActive.displayed && toAddPetButton.displayed }

   static content = {
      navElementHome { $('li span.glyphicon-home') }
      navElementSearchActive(wait: true) { $('li.nav-item a.active[href="/owners/find"]') }
      toEditOwnerButton { $('a', href: endsWith('/edit')) }
      toAddPetButton { $('a', href: endsWith('/pets/new')) }
      resultTable { $('table.table') }
      nameColumn { resultTable.find('tbody tr th', text: 'Name').siblings('td') }
      addressColumn { resultTable.find('tbody tr th', text: 'Address').siblings('td') }
      cityColumn { resultTable.find('tbody tr th', text: 'City').siblings('td') }
      telephoneColumn { resultTable.find('tbody tr th', text: 'Telephone').siblings('td') }
   }

   OwnerDetailPage assertOwnerAttributes(String firstName, String lastName, String address, String city, String telephone) {
      assert nameColumn.text() == "${firstName} ${lastName}" &&
         addressColumn.text() == address &&
         cityColumn.text() == city &&
         telephoneColumn.text() == telephone
      this
   }
}
