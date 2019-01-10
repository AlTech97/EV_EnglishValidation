package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.security.SecureRandom;
import javax.servlet.ServletException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

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
  public void testRegistrationFail() throws ServletException, IOException {    
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
    String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    SecureRandom rnd = new SecureRandom();
    StringBuilder sb = new StringBuilder( 10 );
    // create a random string with len 10;
    for( int i = 0; i < 10; i++ ) 
       sb.append(AB.charAt(rnd.nextInt(AB.length())));
    request.addParameter("name", "Giuseppe");
    request.addParameter("surname", "Cirino");
    request.addParameter("email", sb.toString()+"@unisa.it");
    request.addParameter("sex", "M");
    request.addParameter("password", "password");
    request.addParameter("flag", "1");
    servlet.doPost(request, response);
  } 
}
