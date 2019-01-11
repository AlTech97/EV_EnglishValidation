package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.security.SecureRandom;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import controller.ServletCommon;
import controller.ServletStudent;
import interfacce.UserInterface;
import model.Request;
import model.Student;

public class ServletStudentTest extends Mockito {
  private ServletStudent servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;

  @Before
  public void setUp() {
    servlet = new ServletStudent();
    request = new MockHttpServletRequest();
    response = new MockHttpServletResponse();
  }
  
  @Test
  public void testAlreadyRegistered() throws ServletException, IOException {    
    request.addParameter("name", "Giuseppe");
    request.addParameter("surname", "Cirino");
    request.addParameter("email", "g.cirino2@unisa.it");
    request.addParameter("sex", "M");
    request.addParameter("password", "password");
    request.addParameter("flag", "1");
    servlet.doPost(request, response);
    assertEquals("json", response.getContentType());
  }
  
  @Test
  public void addNewStudent() throws ServletException, IOException  {
    String Ab = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    SecureRandom rnd = new SecureRandom();
    StringBuilder sb = new StringBuilder(10);
    // Crea una stringa random per l'email di lunghezza 10
    for (int i = 0; i < 10; i++) {
      sb.append(Ab.charAt(rnd.nextInt(Ab.length()))); 
    }
    request.addParameter("name", "Giuseppe");
    request.addParameter("surname", "Cirino");
    request.addParameter("email", sb.toString() + "@unisa.it");
    request.addParameter("sex", "M");
    request.addParameter("password", "password");
    request.addParameter("flag", "1");
    servlet.doPost(request, response);
  } 
  
  @Test
  public void testFirstForm() throws ServletException, IOException {   
    MockHttpServletRequest request2 = new MockHttpServletRequest();
    ServletCommon common = new ServletCommon();
    request.addParameter("releaseDate", "2015-02-14");
    request.addParameter("expiryDate", "2020-02-14");
    request.addParameter("year", "2018");
    request.addParameter("certificateSerial", "A00000001");
    request.addParameter("level", "A2");
    request.addParameter("requestedCfu", "6");
    request.addParameter("serial", "512104365");
    request.addParameter("idEnte", "1");
    request.addParameter("flag", "2");
    
  
    
    UserInterface user = new Student("8hecbexqp1u@unisa.it", "fdgb", "surname", 'M', "password", 0);
    request.getSession().setAttribute("user", user);
    /*String [] x= ;
    request.addParameter("filenames[]", x);*/
    servlet.doPost(request, response);
    assertEquals("json", response.getContentType());
  }
  
  @Test
  public void testRegistrationFail() throws ServletException, IOException {    
    request.addParameter("name", "Giuseppe");
    request.addParameter("surname", "Cirino");
    request.addParameter("sex", "M");
    request.addParameter("password", "password");
    request.addParameter("flag", "1");
    servlet.doPost(request, response);
    assertEquals("json", response.getContentType());
  }
}
