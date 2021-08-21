package ru.stqa.pft.addressbook.tests;

import java.util.Set;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().list().isEmpty()) {
      app.goTo().newContactPage();
      app.contact().create(
          new ContactData().setFirstName("Татьяна").setLastName("Садовская")
              .setMobilePhone("8-900-660-60-60").setEmail("qa.tatiana.qa@gmail.com").setGroup("test1"));
    }
  }

  @Test
  public void testContactModification() {
    Set<ContactData> before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contactData = new ContactData().setId(modifiedContact.getId())
        .setFirstName("Татьяна").setLastName("Садовская").setMobilePhone("8-900-660-60-60")
        .setEmail("qa.tatiana.qa@gmail.com").setGroup("test1");
    app.contact().modify(contactData);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifiedContact);
    before.add(contactData);
    Assert.assertEquals(before, after);
  }
}
