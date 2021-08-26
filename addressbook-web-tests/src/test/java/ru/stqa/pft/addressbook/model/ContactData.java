package ru.stqa.pft.addressbook.model;

public class ContactData {

  private int id = Integer.MAX_VALUE;
  private String firstName;
  private String lastName;
  private String address;
  private String homePhone;
  private String mobilePhone;
  private String workPhone;
  private String email;
  private String email2;
  private String email3;
  private String group;
  private String allPhones;
  private String allEmail;

  public int getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
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

  public String getGroup() { return group;
  }

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
    return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    return result;
  }
}
