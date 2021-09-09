package ru.stqa.pft.addressbook.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class ContactAdditionToGroups extends TestBase {

  Groups groups;
  @BeforeMethod
  public void ensurePreconditions() {
    groups = app.db().groups();
    if (groups.isEmpty()) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("new Group"));
      groups = app.db().groups();
    }
    File photo = new File("src/test/resources/michael.jpeg");
    app.goTo().homePage();
    if (app.contact().list().isEmpty()) {
      app.goTo().newContactPage();
      app.contact().create(
          new ContactData().setFirstname("Татьяна").setLastname("Садовская")
              .setMobilePhone("8-900-660-60-60").setEmail("qa.tatiana.qa@gmail.com")
              .setPhoto(photo).setAddress("address").setHomePhone("5566").setWorkPhone("")
              .setEmail2("qa.tatiana.qa2@gmail.com").setEmail3("qa.tatiana.qa3@gmail.com")
              .inGroup(groups.iterator().next()));
    }
  }

  @Test
  public void testContactAdditionToGroups() {
    ContactData modifyingContact = app.db().contacts().iterator().next();
    app.contact().additionToGroups(modifyingContact, groups);
    app.goTo().homePage();
    int contactId = modifyingContact.getId();
    ContactData modifiedContact = app.db().contactWithId(contactId).iterator().next();
    assertThat(modifiedContact.getGroups(), equalTo(modifyingContact.inGroup(groups.iterator().next())));
  }

}
