package Model;

public class State {

  private int idState;
  private String description;
  
  public State(){}
  
  public State(int idState,String description) {
    
    this.idState = idState;
    this.description = description;
  }

  public int getIdState() {
    return idState;
  }

  public void setIdState(int idState) {
    this.idState = idState;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
  
}
