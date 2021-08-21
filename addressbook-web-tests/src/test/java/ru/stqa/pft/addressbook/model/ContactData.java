package ru.stqa.pft.addressbook.model;

public class ContactData {

  private int id = Integer.MAX_VALUE;
  private String firstName;
  private String lastName;
  private String mobilePhone;
  private String email;

  private String group;

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getEmail() {
    return email;
  }

  public String getGroup() { return group;
  }

  public int getId() {
    return id;
  }

  public ContactData setId(int id) {
    this.id = id;
    return this;
  }

  public ContactData setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public ContactData setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public ContactData setMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
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

  @Override
  public String toString() {
    return "ContactData{" +
        "id=" + id +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
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
    if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) {
      return false;
    }
    if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) {
      return false;
    }
    if (mobilePhone != null ? !mobilePhone.equals(that.mobilePhone) : that.mobilePhone != null) {
      return false;
    }
    if (email != null ? !email.equals(that.email) : that.email != null) {
      return false;
    }
    return group != null ? group.equals(that.group) : that.group == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    result = 31 * result + (group != null ? group.hashCode() : 0);
    return result;
  }
}
