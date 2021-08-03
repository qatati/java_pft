package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class AddNewContactTests extends TestBase {

  @Test
  public void testAddNewContact() throws Exception {
    app.getNavigationHelper().goToAddNewContactPage();
    app.getContactHelper().fillContactForm(
        new ContactData("Татьяна", "Садовская", "8-913-668-69-60", "qa.tatiana.qa@gmail.com"));
    app.getContactHelper().submitNewContact();
    app.getContactHelper().returnToHomePage();
  }
}