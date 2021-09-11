package ru.stqa.pft.mantis.tests;

import java.io.IOException;
import java.util.List;
import javax.mail.MessagingException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

public class ChangePasswordTests extends TestBase{

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

@Test
  public void testChangePassword() throws MessagingException, IOException {
  String password = "password";
  String newPassword = "newPassword";
  app.webLogin().login(app.getProperty("web.adminLogin"), app.getProperty("web.getPassword"));
  app.navigate().goToManagePage();
  app.navigate().goToManageUsersPage();
  Users users = app.db().users();
  UserData userForChangePassword = users.iterator().next();
  app.manageUsers().clickOnUser(userForChangePassword);
  app.edit().resetPassword();
//  if (!app.james().doesUserExist(userForChangePassword.getUsername())) {
//    long now = System.currentTimeMillis();
//    String user = String.format("user%s", now);
//    String email = String.format("user%s@localhost", now);
//    app.james().createUser(user, password);
//    app.registration().start(user, email);
//  }
  List<MailMessage> mailMessages = app.mail().waitForMail(1, 60000);
//  List<MailMessage> mailMessages = app.james().waitForMail(userForChangePassword.getUsername(), "password", 60000);
  String confirmationLink = findConfirmationLink(mailMessages, userForChangePassword.getEmail());
//  app.navigate().logout();
//  app.webLogin().login(userForChangePassword.getUsername(), password);
  app.registration().finish(confirmationLink, newPassword);
  Assert.assertTrue (app.newSession().login(userForChangePassword.getUsername(), newPassword));
}

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter(m -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://localhost/mantisbt-1.2.20/verify.php").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}
