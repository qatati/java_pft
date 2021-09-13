package ru.stqa.pft.mantis.tests;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import javax.xml.rpc.ServiceException;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

public class TestBase {
//  Logger logger = LoggerFactory.getLogger(TestBase.class);

  protected static final ApplicationManager app =
      new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws Exception {
    app.stop();
  }

//  @BeforeMethod
//  public void logTestStart(Method m) {
//    logger.info("Start testGroupCreation");
//  }
//
//  @AfterMethod(alwaysRun = true)
//  public void logTestStop(Method m) {
//    logger.info("Stop testGroupCreation");
//  }

  protected boolean isIssueOpen(int issueId)
      throws MalformedURLException, ServiceException, RemoteException {
    String status = app.soap().getStatusIssue(issueId);
    return !"resolved".equals(status);
  }

  public void skipIfNotFixed(int issueId)
      throws MalformedURLException, ServiceException, RemoteException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }
}
