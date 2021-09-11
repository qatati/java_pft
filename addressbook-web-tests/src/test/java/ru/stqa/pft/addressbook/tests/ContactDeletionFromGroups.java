package ru.stqa.pft.addressbook.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class ContactDeletionFromGroups extends TestBase {
  Groups groups;
  @BeforeMethod
  public void ensurePreconditions() {
    groups = app.db().groups();
    if (groups.isEmpty()) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("new Group"));
      groups = app.db().groups();
    }
    app.goTo().homePage();
    if (app.contact().list().isEmpty()) {
      app.goTo().newContactPage();
      app.contact().create(
          new ContactData().setFirstname("Татьяна").setLastname("Садовская")
              .setMobilePhone("8-900-660-60-60").setEmail("qa.tatiana.qa@gmail.com")
              .setAddress("address").setHomePhone("5566").setWorkPhone("")
              .setEmail2("qa.tatiana.qa2@gmail.com").setEmail3("qa.tatiana.qa3@gmail.com")
              .inGroup(groups.iterator().next()));
    }
  }

  @Test
  public void testContactDeletionFromGroups() {
    Contacts beforeDeletionContacts = app.db().contacts();
    ContactData userSelect = null;
    ContactData userAfter = null;
    for (ContactData contactData:beforeDeletionContacts) {
      userSelect = contactData;
      if (!contactData.getGroups().isEmpty()){
        app.contact().deletionfromGroups(contactData, contactData.getGroups().iterator().next().getId());
        app.goTo().homePage();
      } else {
        app.contact().additionToGroups(contactData, groups.iterator().next().getId());
        app.goTo().homePage();
      }
    }
    Contacts afterAddtionContacts = app.db().contacts();
    for (ContactData contactData:afterAddtionContacts) {
      if (contactData.getId() == userSelect.getId()) {
        userAfter = contactData;
      }
    }
    assertThat(afterAddtionContacts, equalTo(beforeDeletionContacts.without(userSelect).withAdded(userAfter)));
  }
}
