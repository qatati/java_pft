package ru.stqa.pft.addressbook.tests;


import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.ContactData;

public class AddNewContactTests extends TestBase {

  @Test
  public void testAddNewContact() throws Exception {
    goToAddNewPage();
    fillContactForm(
        new ContactData("Татьяна", "Садовская", "8-913-668-69-60", "qa.tatiana.qa@gmail.com"));
    submitNewContact();
    returnToHomePage();
  }

  private void goToAddNewPage() {
    app.wd.get("http://localhost/addressbook/edit.php");
  }

  private void fillContactForm(ContactData contactData) {
    app.wd.findElement(By.name("firstname")).click();
    app.wd.findElement(By.name("firstname")).clear();
    app.wd.findElement(By.name("firstname")).sendKeys(contactData.getFirstName());
    app.wd.findElement(By.name("lastname")).click();
    app.wd.findElement(By.name("lastname")).clear();
    app.wd.findElement(By.name("lastname")).sendKeys(contactData.getLastName());
    app.wd.findElement(By.name("mobile")).click();
    app.wd.findElement(By.name("mobile")).clear();
    app.wd.findElement(By.name("mobile")).sendKeys(contactData.getMobilePhone());
    app.wd.findElement(By.name("email")).click();
    app.wd.findElement(By.name("email")).clear();
    app.wd.findElement(By.name("email")).sendKeys(contactData.getEmail());
  }

  private void submitNewContact() {
    app.wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
  }

  private void returnToHomePage() {
    app.wd.findElement(By.linkText("home page")).click();
  }
}
