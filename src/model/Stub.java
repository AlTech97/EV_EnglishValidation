package model;

import java.util.ArrayList;

public class Stub {
  ArrayList<Student> database;
  Student st = new Student("aaa@sss.it", "","",'x',"",0);
  Student st2 = new Student("xxx", "","",'t',"",1);
  
  //public Stub() {}
  /** 
   * list
   * Return virtual database.
   */
  
  public Stub() {
    database = new ArrayList<Student>();
    database.add(st);
    database.add(st2);
  }
}
