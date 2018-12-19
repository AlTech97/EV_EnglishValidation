package model;

public class Tester {
  /**
   * Tester.
   * args
   */
  public static void main(String[] args) {
    Student st = new Student("aaa", "xxx", "bho", 'x', "ppp", 1);
    st.setEmail("aaa1@sss.it");
    System.out.println(st.validate());
  }
}
