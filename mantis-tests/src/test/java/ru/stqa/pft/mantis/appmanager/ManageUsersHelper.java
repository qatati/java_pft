package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.UserData;

public class ManageUsersHelper extends BaseHelper {

  public ManageUsersHelper(ApplicationManager app) {
    super(app);
  }

  public void clickOnUser(UserData userForChangePassword) {
    click(By.xpath("//a[@href = 'manage_user_edit_page.php?user_id=" + userForChangePassword.getId() + "']"));
  }
}
