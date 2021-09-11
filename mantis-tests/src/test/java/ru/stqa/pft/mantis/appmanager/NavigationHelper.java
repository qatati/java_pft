package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.testng.Assert;

public class NavigationHelper extends BaseHelper{

  public NavigationHelper(ApplicationManager app) {
    super(app);
  }

  public void goToManagePage() {
    click(By.linkText("Manage"));
    String title = wd.findElement(By.xpath("//td[@class='form-title']")).getText();
    Assert.assertTrue(title.contains("Site Information"), "Заголовок не совпадает!");
  }

    public void goToManageUsersPage() {
      click(By.linkText("Manage Users"));
      String title = wd.findElement(By.xpath("//td[@class='form-title']")).getText();
      Assert.assertTrue(title.contains("Manage Accounts"), "Заголовок не совпадает!");
    }


}
