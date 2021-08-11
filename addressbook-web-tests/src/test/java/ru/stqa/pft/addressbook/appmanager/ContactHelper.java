package ru.stqa.pft.addressbook.appmanager;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends BaseHelper {
  private ApplicationManager app;

  public ContactHelper(ApplicationManager app) {
    super(app.wd);
    this.app=app;
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("email"), contactData.getEmail());
    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void submitNewContact() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void selectContact(int i) {
    wd.findElements(By.name("selected[]")).get(i).click();
  }

  public void deleteSelectedContacts() {
    click(By.xpath("//input[@value='Delete']"));
    wd.switchTo().alert().accept();
  }

  public void initContactModification(int i) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(i).click();
  }

  public void updateModification() {
    click(By.name("update"));
  }

  public void createContact(ContactData contactData) {
    app.getNavigationHelper().goToAddNewContactPage();
    fillContactForm(contactData, true);
    submitNewContact();
    returnToHomePage();
  }

  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<>();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
    for (WebElement element:elements ) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String firstName = cells.get(1).getText();
      String name = cells.get(2).getText();
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("id"));
      ContactData contact = new ContactData(id, name, firstName, null, null, null);
      contacts.add(contact);
    }
    return contacts;
  }
}
