package ru.stqa.pft.mantis.appmanager;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

public class ApplicationManager {

  private WebDriver wd;

  public String browser;
  public Properties properties;
  public RegistrationHelper registrationHelper;
  public FtpHelper ftp;
  public MailHelper mailHelper;
  public JamesHelper jamesHelper;
  public LoginHelper loginHelper;
  public NavigationHelper navigationHelper;
  private DbHelper dbHelper;
  private ManageUsersHelper manageUsersHelper;
  private EditUserHelper editUserHelper;
  private SoapHelper soapHelper;

  public ApplicationManager(String browser) {
    this.browser = browser;
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties = new Properties();
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    dbHelper = new DbHelper();
  }

  public void stop() {
    if (wd!=null) {
      wd.quit();
    }
  }

  public boolean isElementPresent(By by) {
    try {
      wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  public HttpSession newSession() {
    return new HttpSession(this);
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }

  public RegistrationHelper registration() {
    if (registrationHelper ==null) {
      registrationHelper = new RegistrationHelper(this);
    }
    return registrationHelper;
  }

  public LoginHelper webLogin() {
    if (loginHelper ==null) {
      loginHelper = new LoginHelper(this);
    }
    return loginHelper;
  }

  public NavigationHelper navigate() {
    if (navigationHelper ==null) {
      navigationHelper = new NavigationHelper(this);
    }
    return navigationHelper;
  }

  public ManageUsersHelper manageUsers() {
    if (manageUsersHelper ==null) {
      manageUsersHelper = new ManageUsersHelper(this);
    }
    return manageUsersHelper;
  }

  public EditUserHelper edit() {
    if (editUserHelper ==null) {
      editUserHelper = new EditUserHelper(this);
    }
    return editUserHelper;
  }

  public FtpHelper ftp() {
    if (ftp ==null) {
      ftp = new FtpHelper(this);
    }
    return ftp;
  }

  public MailHelper mail() {
    if (mailHelper ==null) {
      mailHelper = new MailHelper(this);
    }
    return mailHelper;
  }

  public JamesHelper james() {
    if (jamesHelper ==null) {
      jamesHelper = new JamesHelper(this);
    }
    return jamesHelper;
  }

  public DbHelper db() { return dbHelper;}

  public SoapHelper soap() {
    if (soapHelper ==null) {
      soapHelper = new SoapHelper(this);
    }
    return soapHelper;
  }

  public WebDriver getDriver() {
    if (wd==null) {
      if (browser.equals(BrowserType.FIREFOX)) {
        wd = new FirefoxDriver();
      } else if (browser.equals(BrowserType.CHROME)) {
        wd = new ChromeDriver();
      }
      wd.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
      wd.get(properties.getProperty("web.baseUrl"));
    }
    return wd;
  }
}
