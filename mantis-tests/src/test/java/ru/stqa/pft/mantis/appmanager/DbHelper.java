package ru.stqa.pft.mantis.appmanager;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class DbHelper {

  private final SessionFactory sessionFactory;

  public DbHelper() {
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
        .configure() // configures settings from hibernate.cfg.xml
        .build();
      sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

//    public Users users() {
//      Session session = sessionFactory.openSession();
//      session.beginTransaction();
//      List<UserData> result = session.createQuery("from UserData").list();
//      for (UserData userData : result) {
//        System.out.println(userData);
//      }
//      session.getTransaction().commit();
//      session.close();
//      return new Users(result);
//    }

//  public Contacts contactsNotInGroup() {
//    Session session = sessionFactory.openSession();
//    session.beginTransaction();
//    List<ContactData> result = session.createQuery("from ContactData where deprecated = 0000-00-00").list();
//    for (ContactData contacts : result) {
//      System.out.println(contacts);
//    }
//    session.getTransaction().commit();
//    session.close();
//    return new Contacts(result);
//  }
  }