package model;

public class Ente {
  private int id;//idEnte
  private String email;//email
  private String name;//name
  private String site;//site
  
  /**
   * idEnte
   * email
   * name
   * site
   * return Ente.
   */
  // INIZIO COSTRUTTORE
  public Ente(int idEnte, String email, String name, String site) {
    this.id = idEnte;
    this.email = email;
    this.name = name;
    this.site = site;
  }
  // FINE COSTRUTTORE
  
  
  // GET
  public int getIdEnte() {
    return id;
  }

  public String getEmail() {

    return email;
  }

  public String getName() {

    return name;
  }

  public String getSite() {

    return site;
  }


  // SET
  public void setIdEnte(int idEnte) {
    this.id = idEnte;

  }

  public void setEmail(String email) {
    this.email = email;

  }

  public void setName(String name) {
    this.name = name;

  }

  public void setSite(String site) {
    this.site = site;

  }



}
