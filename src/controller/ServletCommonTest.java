package controller;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


public class ServletCommonTest extends Mockito {



  @Mock
  HttpServletRequest request;
  @Mock
  HttpServletResponse response;
  @Mock
  HttpSession session;

  @Mock
  RequestDispatcher rd;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void test() throws Exception {

    /*
     * HttpServletRequest request = mock(HttpServletRequest.class); HttpServletResponse response =
     * mock(HttpServletResponse.class); HttpSession session = mock(HttpSession.class);
     * RequestDispatcher rd=mock(RequestDispatcher.class);
     */
    int flag = 1;
    if (flag == 1) {
      when(request.getParameter("email")).thenReturn("abhinav");
      when(request.getParameter("password")).thenReturn("passw0rd");
      // when(request.getParameter("rememberMe")).thenReturn("Y");
      // when(request.getSession()).thenReturn(session);
      // when(request.getRequestDispatcher("/HelloWorld.do")).thenReturn(rd);


      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);

      when(response.getWriter()).thenReturn(pw);

      new ServletCommon().doPost(request, response);

      // Verify the session attribute value
      verify(session).setAttribute("email", "abhinav");

      verify(rd).forward(request, response);

      String result = sw.getBuffer().toString().trim();

      System.out.println("Result: " + result);

      assertEquals("Login successfull...", result);
    }
  }

}



/*
 * HttpServletRequest request = mock(HttpServletRequest.class); HttpServletResponse response =
 * mock(HttpServletResponse.class); int flag = 0; if (flag == 0) {
 * when(request.getParameter("email")).thenReturn("parametri corretti");
 * when(request.getParameter("password")).thenReturn("password");
 * 
 * StringWriter stringWriter = new StringWriter(); PrintWriter writer = new
 * PrintWriter(stringWriter); when(response.getWriter()).thenReturn(writer);
 * 
 * new ServletCommon().doPost(request, response);
 * 
 * verify(request, atLeast(1)).getParameter("email"); // only if you want to verify username was
 * called.
 * 
 * writer.flush(); // it may not have been flushed yet...
 * assertTrue(stringWriter.toString().contains("My expected string")); }
 */