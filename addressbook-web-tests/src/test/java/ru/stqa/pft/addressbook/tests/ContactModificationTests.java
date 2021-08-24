package ru.stqa.pft.addressbook.tests;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

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
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contactData = new ContactData().setId(modifiedContact.getId())
        .setFirstName("Анна").setLastName("Иванова").setMobilePhone("8-900-660-60-61")
        .setEmail("qa.tatiana.qa@gmail.com").setGroup("test1");
    app.contact().modify(contactData);
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contactData)));
  }
}
