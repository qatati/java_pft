package ru.stqa.pft.addressbook.tests;


import java.util.Comparator;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class AddNewContactTests extends TestBase {

  @Test
  public void testAddNewContact() throws Exception {
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contactData = new ContactData("Татьяна", "Садовская",
        "8-913-668-69-60", "qa.tatiana.qa@gmail.com", "test1");
    app.getContactHelper().createContact(contactData);
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    contactData.setId(after.stream().mapToInt(c -> c.getId()).max().getAsInt());
    before.add(contactData);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
