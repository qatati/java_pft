package ru.stqa.pft.addressbook.tests;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

public class AddNewContactTests extends TestBase {

  @Test
  public void testAddNewContact() throws Exception {
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().setFirstName("Татьяна").setLastName("Садовская")
        .setMobilePhone("8-900-660-60-60").setEmail("qa.tatiana.qa@gmail.com").setGroup("test1");
    app.goTo().newContactPage();
    app.contact().create(contact);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(before.withAdded(contact.setId(after.stream().mapToInt(c -> c.getId()).max().getAsInt()))));
  }
}
