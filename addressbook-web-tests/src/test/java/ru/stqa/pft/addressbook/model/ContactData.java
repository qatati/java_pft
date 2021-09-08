package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import java.io.File;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "addressbook")
public class ContactData {

  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;
  @Expose
  @Column(name = "firstname")
  private String firstname;
  @Expose
  @Column(name = "lastname")
  private String lastname;
  @Expose
  @Type(type = "text")
  private String address;
  @Expose
  @Column(name = "home")
  @Type(type = "text")
  private String homePhone;
  @Expose
  @Column(name = "mobile")
  @Type(type = "text")
  private String mobilePhone;
  @Expose
  @Column(name = "work")
  @Type(type = "text")
  private String workPhone;
  @Expose
  @Type(type = "text")
  private String email;
  @Expose
  @Type(type = "text")
  private String email2;
  @Expose
  @Type(type = "text")
  private String email3;
  @Expose
  @Transient
  private String group;
  @Transient
  private String allPhones;
  @Transient
  private String allEmail;
  @Expose
  @Column(name = "photo")
  @Type(type = "text")
  private String photo;

  public int getId() {
    return id;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public String getEmail() {
    return email;
  }

  public String getGroup() { return group; }

  public String getAddress() {
    return address;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  public String getAllEmail() {
    return allEmail;
  }

  public File getPhoto() {
    return new File(photo);
  }

  public ContactData setId(int id) {
    this.id = id;
    return this;
  }

  public ContactData setFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData setLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData setMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public ContactData setHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public ContactData setWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public ContactData setEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData setGroup(String group) {
    this.group = group;
    return this;
  }

  public ContactData setAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactData setAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData setEmail2(String email2) {
    this.email2 = email2;
    return this;
  }
  public ContactData setEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ContactData setAllEmail(String allEmail) {
    this.allEmail = allEmail;
    return this;
  }

  public ContactData setPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  @Override
  public String toString() {
    return "ContactData{" +
        "id=" + id +
        ", firstName='" + firstname + '\'' +
        ", lastName='" + lastname + '\'' +
        '}';
  }
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ContactData that = (ContactData) o;

    if (id != that.id) {
      return false;
    }
    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) {
      return false;
    }
    if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) {
      return false;
    }
    if (address != null ? !address.equals(that.address) : that.address != null) {
      return false;
    }
    if (homePhone != null ? !homePhone.equals(that.homePhone) : that.homePhone != null) {
      return false;
    }
    if (mobilePhone != null ? !mobilePhone.equals(that.mobilePhone) : that.mobilePhone != null) {
      return false;
    }
    if (workPhone != null ? !workPhone.equals(that.workPhone) : that.workPhone != null) {
      return false;
    }
    if (email != null ? !email.equals(that.email) : that.email != null) {
      return false;
    }
    if (email2 != null ? !email2.equals(that.email2) : that.email2 != null) {
      return false;
    }
    return email3 != null ? email3.equals(that.email3) : that.email3 == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    result = 31 * result + (address != null ? address.hashCode() : 0);
    result = 31 * result + (homePhone != null ? homePhone.hashCode() : 0);
    result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode() : 0);
    result = 31 * result + (workPhone != null ? workPhone.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    result = 31 * result + (email2 != null ? email2.hashCode() : 0);
    result = 31 * result + (email3 != null ? email3.hashCode() : 0);
    return result;
  }
}
