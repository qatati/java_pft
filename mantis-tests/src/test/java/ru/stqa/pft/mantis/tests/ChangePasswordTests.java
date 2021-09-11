package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

public class ChangePasswordTests extends TestBase{

@Test
  public void testChangePassword() {
  app.webLogin().login(app.getProperty("web.adminLogin"), app.getProperty("web.getPassword"));
  app.navigationHelper.goToManagePage();
  app.navigationHelper.goToManageUsersPage();


}

}
