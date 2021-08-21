package ru.stqa.pft.addressbook.tests;


import java.util.Comparator;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class AddNewContactTests extends TestBase {

  @Test
  public void testAddNewContact() throws Exception {
    List<ContactData> before = app.contact().list();
    ContactData contactData = new ContactData().setFirstName("Татьяна").setLastName("Садовская")
        .setMobilePhone("8-900-660-60-60").setEmail("qa.tatiana.qa@gmail.com").setGroup("test1");
    app.goTo().addNewContactPage();
    app.contact().create(contactData);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    contactData.setId(after.stream().mapToInt(c -> c.getId()).max().getAsInt());
    before.add(contactData);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
