package ru.stqa.pft.addressbook.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.stream.Collectors;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactPhoneTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().list().isEmpty()) {
      app.goTo().newContactPage();
      app.contact().create(
          new ContactData().setFirstname("Татьяна").setLastname("Садовская")
              .setMobilePhone("8-900-660-60-60").setEmail("qa.tatiana.qa@gmail.com"));
//      .setGroup("test1"));
    }
  }

  @Test
  public void testContactPhones() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditPage = app.contact().infoFromEditPage(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditPage)));
  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
        .stream().filter(s -> !s.isEmpty())
        .map(ContactPhoneTests::cleaned)
        .collect(Collectors.joining("\n"));
  }

  public static String cleaned (String phone) {
      return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }

}
