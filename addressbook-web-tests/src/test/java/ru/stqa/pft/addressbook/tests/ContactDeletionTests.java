package ru.stqa.pft.addressbook.tests;

import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {
    app.goTo().goToHomePage();
    if (!app.getContactHelper().isThereAGroup()) {
      app.goTo().goToAddNewContactPage();
      app.getContactHelper().createContact(
          new ContactData("Татьяна", "Садовская", "8-913-668-69-60", "qa.tatiana.qa@gmail.com",
              "test1"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size()-1);
    app.getContactHelper().deleteSelectedContacts();
    app.goTo().goToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size()-1);

    before.remove(before.size()-1);
    Assert.assertEquals(before, after);
  }
}
