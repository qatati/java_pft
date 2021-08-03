package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper {

  public WebDriver wd;
  private GroupHelper groupHelper;

  NavigationHelper(WebDriver wd) {
    this.wd = wd;
  }

  public void goToGroupPage() {
    wd.findElement(By.linkText("groups")).click();
  }

  public void goToAddNewContactPage() {
    wd.get("http://localhost/addressbook/edit.php");
  }
}
