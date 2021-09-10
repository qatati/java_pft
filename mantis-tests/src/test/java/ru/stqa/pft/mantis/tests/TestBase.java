package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.tests.appmanager.ApplicationManager;

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

}
