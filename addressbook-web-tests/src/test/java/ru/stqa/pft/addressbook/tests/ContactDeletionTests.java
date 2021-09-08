package ru.stqa.pft.addressbook.tests;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().isEmpty()) {
      app.goTo().homePage();
      app.goTo().newContactPage();
      app.contact().create(
          new ContactData().setFirstname("Татьяна").setLastname("Садовская")
              .setMobilePhone("8-900-660-60-60").setEmail("qa.tatiana.qa@gmail.com"));
//              .setGroup("test1"));
    }
  }

  @Test
  public void testContactDeletion() {
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    int index = before.size()-1;
    app.goTo().homePage();
    app.contact().delete(deletedContact);
    app.goTo().homePage();
    Contacts after = app.db().contacts();
    assertEquals(after.size(), before.size()-1);
    assertThat(after, equalTo(before.without(deletedContact)));
  }
}
