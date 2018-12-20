package model;

import Interface.UserInterface;

public class Admin implements UserInterface {

  private String e; // Email
  private String n; // Name
  private String su;// Surname
  private char s; // Sesso
  private String p;// Password
  private int u;// UserType

  public Admin(String email, String name, String surname, char sex, String password, int userType) {
    this.e = email;
    this.n = name;
    this.su = surname;
    this.s = sex;
    this.p = password;
    this.u = userType;

  }


  // Get
  public String getEmail() {
    return e;
  }

  public String getName() {
    return n;
  }

  public String getSurname() {
    return su;
  }

  public char getSex() {
    return s;
  }

  public String getPassword() {
    return p;
  }

  public int getUserType() {

    return u;
  }


  // Set
  public void setEmail(String email) {
    this.e = email;

  }

  public void setName(String name) {
    this.n = name;

  }

  public void setSurname(String surname) {
    this.su = surname;
  }

  public void setSex(char sex) {
    this.s = sex;

  }

  public void setPassword(String password) {
    this.p = password;

  }

  public void setUserType(int userType) {
    this.u = userType;

  }

    public boolean validate() {
      Stub stub = new Stub();
      if (stub.database.containsKey(getEmail()) == true) {
        return true;
      } 
      return false;
    }

}
