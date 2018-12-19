package model;

import java.util.HashMap;

public class Stub {
  HashMap<String, String> database;
  Student st = new Student("aaa@sss.it", "","",'x',"",0);
  Student st2 = new Student("xxx", "","",'t',"",1);
  
  //public Stub() {}
  /** 
   * list
   * Return virtual database.
   */
  
  public Stub() {
    database = new HashMap<>();
    database.put("aaa@sss.it", "pass");
    database.put("xxx", "pwd");
    
  }
}
