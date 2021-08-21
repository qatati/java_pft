package ru.stqa.pft.addressbook.tests;

import java.util.Comparator;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{

  @Test
  public void testContactModification() {
    app.goTo().goToHomePage();
    if (!app.getContactHelper().isThereAGroup()) {
      app.goTo().goToAddNewContactPage();
      app.getContactHelper().createContact(
          new ContactData("Татьяна", "Садовская", "8-913-668-69-60", "qa.tatiana.qa@gmail.com",
              "test1"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size()-1);
    app.getContactHelper().initContactModification(before.size()-1);
    ContactData contactData = new ContactData(before.get(before.size()-1).getId(),
        "Анна",
        "Иванова",
        "8-913-668-69-60",
        "qa.tatiana.qa@gmail.com",
        "test1");
    app.getContactHelper().fillContactForm(contactData, false);
    app.getContactHelper().updateModification();
    app.getContactHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size()-1);
    before.add(contactData);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
