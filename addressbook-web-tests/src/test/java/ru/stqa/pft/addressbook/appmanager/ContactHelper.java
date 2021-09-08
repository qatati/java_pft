package ru.stqa.pft.addressbook.appmanager;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class ContactHelper extends BaseHelper {

  private ApplicationManager app;

  public ContactHelper(ApplicationManager app) {
    super(app.wd);
    this.app = app;
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    attach(By.name("photo"), contactData.getPhoto());
    if (creation) {
      new Select(wd.findElement(By.name("new_group")))
//          .selectByVisibleText(contactData.getGroups());
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

  public void selectContactById(int id) {
    wd.findElement(By.id(String.valueOf(id))).click();
  }

  public void deleteSelectedContacts() {
    click(By.xpath("//input[@value='Delete']"));
    wd.switchTo().alert().accept();
  }

  public void initContactModification(int i) {
    wd.findElement(By.xpath("//a[@href='edit.php?id=" + i + "']")).click();
  }

  public void updateModification() {
    click(By.name("update"));
  }

  public void create(ContactData contactData) {
    fillContactForm(contactData, true);
    submitNewContact();
    returnToHomePage();
  }

  public void modify(ContactData contactData) {
    selectContactById(contactData.getId());
    initContactModification(contactData.getId());
    fillContactForm(contactData, false);
    updateModification();
    returnToHomePage();
  }

  public void delete(ContactData contactData) {
    selectContactById(contactData.getId());
    deleteSelectedContacts();
  }

  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));
  }

  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<>();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String lastName = cells.get(1).getText();
      String firstName = cells.get(2).getText();
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("id"));
      contacts.add(new ContactData().setId(id).setFirstname(firstName).setLastname(lastName));
    }
    return contacts;
  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String lastName = cells.get(1).getText();
      String firstName = cells.get(2).getText();
      String address = cells.get(3).getText();
      String allEmail = cells.get(4).getText();
      String allPhones = cells.get(5).getText();
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("id"));
      contacts.add(new ContactData().setId(id).setFirstname(firstName).setLastname(lastName)
      .setAddress(address).setAllEmail(allEmail).setAllPhones(allPhones));
    }
    return contacts;
  }

  public ContactData infoFromEditPage(ContactData contact) {
    initContactModification(contact.getId());
    String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().setId(contact.getId()).setFirstname(firstName).setLastname(lastName)
        .setAddress(address).setHomePhone(home).setWorkPhone(work).setMobilePhone(mobile)
        .setEmail(email).setEmail2(email2).setEmail3(email3);
  }
}
