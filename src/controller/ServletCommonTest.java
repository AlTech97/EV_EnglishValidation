package controller;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;

class ServletCommonTest {
  private ServletCommon servlet;
  private HttpServletRequest request;
  private HttpServletResponse response;


  @Test
  void testFlag1() {
    ServletCommon ad = new ServletCommon();
   //assertEquals("aaa@sss.it", ad.doGet(request, response));
    

  }

  @Test
  void testName() throws ServletException,  IOException {
    //request.addParameter("","");
    


  }
}
