package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.testng.Assert;

public class EditUserHelper extends BaseHelper{

  public EditUserHelper(ApplicationManager app) {
    super(app);
  }

  public void resetPassword() {
    click(By.xpath("//input[@value='Reset Password']"));
  }
}
