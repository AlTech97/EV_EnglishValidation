package controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {

  private static DbConnection istance = null; 
  private Connection conn;
  private String databaseName;
  private String userName;
  private String password;
  private int hostPort;
  private String hostName;


  public DbConnection(String dbName,String u,String p,int hp,String hn) {
    this.conn = null;
    this.databaseName = "englishvalidation";
    this.userName = "root";
    this.password = "";
    this.hostPort = 3306;
    this.hostName = "localhost";
    
    try {
      Class.forName("com.mysql.jdbc.Driver");
      String url = "jdbc:mysql://"+this.hostName+":"+this.hostPort+"/"+this.databaseName+"?useSSL=false";
      this.conn = DriverManager.getConnection(url, this.userName, this.password);
      this.conn.setAutoCommit(false);
    }
    catch(Exception exc) {
        System.out.println(exc.getMessage());
    }      
  }
  

  public DbConnection() {
  }
 

  public static DbConnection getIstance() {
    if (istance == null) {
      istance = new DbConnection();
    }
    return istance;
  }
 

  public Connection getConn() {
    return this.conn;
  }
  

  public void setConn(Connection conn) {
    this.conn = conn;
  }
  

  public String getDatabaseName() {
    return this.databaseName;
  }

  public void setDatabaseName(String databaseName) {
    this.databaseName = databaseName;
  }
  

  public String getUserName() {
    return this.userName;
  }
  

  public void setUserName(String userName) {
    this.userName = userName;
  }
  

  public String getPassword() {
    return this.password;
  }
  
  
  public void setPassword(String password) {
    this.password = password;
  }

  public int getHostPort() {
    return this.hostPort;
  }

  public void setHostPort(int hostPort) {
    this.hostPort = hostPort;
  }
  

  public String getHostName() {
    return this.hostName;
  }
  

  public void setHostName(String hostName) {
    this.hostName = hostName;
  }
}