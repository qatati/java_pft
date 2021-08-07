package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().goToHomePage();
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getNavigationHelper().goToAddNewContactPage();
      app.getContactHelper().createContact(
          new ContactData("Татьяна", "Садовская", "8-913-668-69-60", "qa.tatiana.qa@gmail.com",
              "test1"));
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContacts();
  }
}
