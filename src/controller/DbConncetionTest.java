package controller;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.DriverManager;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DbConnectionTest {
/*
 // @Test
  void testgetDatabaseName() {
    DbConnection db = new DbConnection(null,"English Validation","","", 3306,"");
    assertEquals("English Validation",db.getDatabaseName());  
  }
 
  //@Test
  void testgetUserName() {
    DbConnection db = new DbConnection(null,"","Luigi","", 3306,"");
    assertEquals("Luigi",db.getUserName());  
  }
  
 // @Test
  void testgetPassword() {
    DbConnection db = new DbConnection(null,"","","pass1", 3306,"");
    assertEquals("pass1",db.getPassword());  
  }
  
 // @Test
  void testgetHostPort() {
    DbConnection db = new DbConnection(null,"","","", 3306,"");
    assertEquals(3306,db.getHostPort());  
  }
  
  //@Test
  void testgetHostName() {
    DbConnection db = new DbConnection(null,"","","", 3306,"localhost");
    assertEquals("localhost",db.getHostName());  
  }
  
  //@Test
  void testgetConn() {
    DbConnection db = new DbConnection(null,"","","", 3306,"");
    assertEquals(null,db.getConn());  
  }
  
  //@Test
  void testgetInstance() {
    
  }
  

  //@Test
  void testsetDatabaseName() {
    DbConnection db = new DbConnection(null,"aaa","","", 3306,"");
    db.setDatabaseName("English Validation");
    assertEquals("English Validation", db.getDatabaseName());
  }
  
  //@Test
  void testsetUserName() {
    DbConnection db = new DbConnection(null,"","aaa","", 3306,"");
    db.setUserName("Luigi");
    assertEquals("Luigi", db.getUserName());
  }
  
  //@Test
  void testsetPassword() {
    DbConnection db = new DbConnection(null,"","","pass1", 3306,"");
    db.setPassword("password123");
    assertEquals("password123", db.getPassword());
  }
  
  //@Test
  void testsetHostPort() {
    DbConnection db = new DbConnection(null,"","","", 33060,"");
    db.setHostPort(3306);
    assertEquals(3306, db.getHostPort());
  }
  
  //@Test
  void testsetHostName() {
    DbConnection db = new DbConnection(null,"","","", 3306,"localhosto");
    db.setHostName("localhost");
    assertEquals("localhost", db.getHostName());
  }
  */
  
  @Test
  void testsetConn() throws Exception {
    DbConnection db = new DbConnection().getIstance();
    /*
    DbConnection db = new DbConnection();
    db.setConn(nect);
    assertEquals(nect, db.getConn()); */
  }
}