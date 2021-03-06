package ru.stqa.pft.mantis.tests;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;
import javax.xml.rpc.ServiceException;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

public class SoapTests extends TestBase{

  @Test
  public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
    Set<Project> projects = app.soap().getProjects();
    System.out.println(projects.size());
    for (Project project:projects) {
      System.out.println(project.getName());
    }
  }

  @Test
  public void testCreateIssue() throws MalformedURLException, ServiceException, RemoteException {
    Set <Project> projects = app.soap().getProjects();
    Issue issue = new Issue().setSummary("Test issue").setDescription("Test description").setProject(projects.iterator()
        .next());
    Issue created = app.soap().addIssue(issue);
    Assert.assertEquals(issue.getSummary(), created.getSummary());
  }

  @Test
  public void testStatusIssue() throws MalformedURLException, ServiceException, RemoteException {
    skipIfNotFixed(Integer.parseInt("0000001"));
    System.out.println("hi");
  }
}
