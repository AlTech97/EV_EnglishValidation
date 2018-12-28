package model;

import java.util.HashMap;

public class SystemAttribute {

  private HashMap<String, String> hashMap = new HashMap<String, String>();
  private static SystemAttribute instance;

  /**
   * Constructor.
   */
  public SystemAttribute() {
    hashMap.put("http://localhost/Admin.jsp", "1");
    hashMap.put("http://localhost/Segretary.jsp", "2");
    hashMap.put("http://localhost/Student.jsp", "3");
  }

  public HashMap<String, String> getHashMap() {
    return hashMap;
  }

  public void setHashMap(HashMap<String, String> hashMap) {
    this.hashMap = hashMap;
  }

  /**
   * Returns the value to which the specified key is mapped, or null if this map contains no mapping
   * for the Key.
   * @param slug is the key of the hashmap
   * @return value associated to the slug.
   */
  public String getValueByKey(String slug) {
    return this.hashMap.get(slug);
  }
  
  /**
   * Method static and synchronized.
   * @return the instance of the singleton.
   */ 
  public static synchronized SystemAttribute getInstance() {
    if (instance == null) {
      instance = new SystemAttribute();
      System.out.println("SystemAtribue Class created ");
    }
    return instance;
  }

}
