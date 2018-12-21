package model;

public class Attached {

  private int idAttached;
  private String filename;

  public Attached() {}

  public Attached(int idAttached, String filename) {


    this.idAttached = idAttached;
    this.filename = filename;
  }

  public int getIdAttached() {
    return idAttached;
  }

  public void setIdAttached(int idAttached) {
    this.idAttached = idAttached;
  }

  public String getFilename() {
    return filename;
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }


}
