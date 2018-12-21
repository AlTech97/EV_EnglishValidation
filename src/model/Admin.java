package model;

import interfacce.UserInterface;

public class Admin implements UserInterface {

  private String email; //Email
  private String name; //Name
  private String surname;//Surname
  private char sex; //Sex
  private String password;//Password
  private int userType;//UserType
  /** 
   *  email
   *  name
   *  surname
   *  sex
   *  password
   *  userType
   *  return Admin.
   */
  
  public Admin(String email, String name, String surname, char sex, String password, int userType) {
    this.email = email;
    this.name = name;
    this.surname = surname;
    this.sex = sex;
    this.password = password;
    this.userType = userType;

  }


  // Get
  public String getEmail() {
    return email;
  }

  public String getName() {
    return name;
  }

  public String getSurname() {
    return surname;
  }

  public char getSex() {
    return sex;
  }

  public String getPassword() {
    return password;
  }

  public int getUserType() {

    return userType;
  }


  // Set
  public void setEmail(String email) {
    this.email = email;

  }

  public void setName(String name) {
    this.name = name;

  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public void setSex(char sex) {
    this.sex = sex;

  }

  public void setPassword(String password) {
    this.password = password;

  }

  public void setUserType(int userType) {
    this.userType = userType;

  }
  
  @Override
  public boolean validate() {
    Stub stub = new Stub();
    if (stub.database.containsKey(getEmail()) == true) {
      return true;
    }
    return false;
  }

}
