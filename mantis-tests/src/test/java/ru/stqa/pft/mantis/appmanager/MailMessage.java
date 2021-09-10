package ru.stqa.pft.mantis.appmanager;

public class MailMessage {

  public final String to;
  public final String text;

  public MailMessage (String to, String text) {
    this.to = to;
    this.text = text;
  }

}
