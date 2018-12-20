package model;

public class Ente {
  // INIZIO COSTRUTTORE
  public Ente(int idEnte, String email, String name, String site) {
    this.id = idEnte;
    this.e = email;
    this.n = name;
    this.s = site;
  }
  // FINE COSTRUTTORE

  // GET
  public int getIdEnte() {

    return id;
  }

  public String getEmail() {

    return e;
  }

  public String getName() {

    return n;
  }

  public String getSite() {

    return s;
  }


  // SET
  public void setIdEnte(int idEnte) {
    this.id = idEnte;

  }

  public void setEmail(String email) {
    this.e = email;

  }

  public void setName(String name) {
    this.n = name;

  }

  public void setSite(String site) {
    this.s = site;

  }

  private int id; // idEnte
  private String e; // email
  private String n; // name
  private String s; // site

}
