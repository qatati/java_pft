package ru.stqa.pft.mantis.appmanager;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import org.apache.commons.net.telnet.TelnetClient;
import ru.stqa.pft.mantis.model.MailMessage;

public class JamesHelper {

  private final ApplicationManager app;
  private final TelnetClient telnet;
  private final Session mailSession;
  private String mailServer;
  private InputStream in;
  private PrintStream out;
  private Store store;

  public JamesHelper(ApplicationManager app) {
    this.app = app;
    telnet = new TelnetClient();
    mailSession = Session.getDefaultInstance(app.properties);
  }

  public void createUser(String user, String password) {
    initTelnetSession();
    write ("adduser " + user + " " + password);
    String result = readUntil("User " + user + " added");
    closeTelnetSession();
  }

  private void initTelnetSession() {
    mailServer = app.getProperty("mailserver.host");
    int port = Integer.parseInt(app.getProperty("mailserver.port"));
    String login = app.getProperty("mailserver.adminlogin");
    String password = app.getProperty("mailserver.adminpassword");

    try {
      telnet.connect(mailServer, port);
      in = telnet.getInputStream();
      out = new PrintStream(telnet.getOutputStream());

    } catch (IOException e) {
      e.printStackTrace();
    }
    readUntil("Login id:");
    write(login);
    readUntil("Password:");
    write(password);

    readUntil("Welcome " + login + ". HELP for a list of commands");
  }

  private String readUntil(String pattern) {
    try {
    char lastChar = pattern.charAt(pattern.length() - 1);
    StringBuffer sb = new StringBuffer();
    char ch = (char) in.read();
    while (true) {
      System.out.println(ch);
      sb.append(ch);
      if (ch == lastChar) {
        if (sb.toString().endsWith(pattern)) {
          return sb.toString();
        }
      }
      ch = (char) in.read();
    }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }


  private void write(String value) {
    try {
      out.println(value);
      out.flush();
      System.out.println(value);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void closeTelnetSession() {
    write("quit");
  }

  public boolean doesUserExist(String name) {
    initTelnetSession();
    write ("verify " + name);
    String result = readUntil("exist");
    closeTelnetSession();
    return result.trim().equals("User " + name + " exist");
  }

  public void deleteUser(String name) {
    initTelnetSession();
    write ("deluser " + name);
    String result = readUntil("User " + name + " deleted");
    closeTelnetSession();
  }

  public List<MailMessage> waitForMail(String user, String password, long timeout)
      throws MessagingException {
    long start = System.currentTimeMillis();
    while (System.currentTimeMillis() < start + timeout) {
      List<MailMessage> allMail = getAllMail(user, password);
      if (!allMail.isEmpty()) {
       return allMail;
      }
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    throw new Error("No mail :(");
  }

  private List<MailMessage> getAllMail(String user, String password) throws MessagingException {
    Folder inbox = openInbox(user, password);
    List<MailMessage> messages = Arrays.asList(inbox.getMessages()).stream().map(m -> toModelMail(m)).collect(Collectors.toList());
    closeFolder(inbox);
    return messages;
  }

  private void closeFolder(Folder folder) throws MessagingException {
    folder.close(true);
    store.close();
  }

  private Folder openInbox(String user, String password) throws MessagingException {
    store = mailSession.getStore("pop3");
    store.connect(mailServer, user, password);
    Folder folder = store.getDefaultFolder().getFolder("INBOX");
    folder.open(Folder.READ_WRITE);
    return folder;
  }

  public static MailMessage toModelMail(Message m) {
    try {
      return new MailMessage(m.getAllRecipients()[0].toString(), (String) m.getContent());
    } catch (MessagingException | IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public void drainEmail(String user, String password) throws MessagingException {
    Folder inbox = openInbox(user, password);
    for (Message message:inbox.getMessages()) {
      message.setFlag(Flags.Flag.DELETED, true);
    }
    closeFolder(inbox);
  }
}