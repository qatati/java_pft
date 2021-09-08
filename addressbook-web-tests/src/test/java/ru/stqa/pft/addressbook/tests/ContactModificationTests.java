package ru.stqa.pft.addressbook.tests;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

import java.io.File;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

public class ContactModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().isEmpty()) {
      app.goTo().homePage();
      app.goTo().newContactPage();
      app.contact().create(
          new ContactData().setFirstname("Татьяна").setLastname("Садовская")
              .setMobilePhone("8-900-660-60-60").setEmail("qa.tatiana.qa@gmail.com").setGroup("test1"));
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    File photo = new File("src/test/resources/michael.jpeg");
    ContactData contactData = new ContactData().setId(modifiedContact.getId())
        .setFirstname("Анна").setLastname("Иванова").setMobilePhone("8-900-660-60-61")
        .setEmail("qa.tatiana.qa@gmail.com").setGroup("test1").setPhoto(photo)
        .setAddress("address").setHomePhone("5566").setEmail2("qa.tatiana.qa2@gmail.com").setEmail3("qa.tatiana.qa3@gmail.com");
    app.goTo().homePage();
    app.contact().modify(contactData);
    Contacts after = app.db().contacts();
    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contactData)));
  }
}
