package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.security.SecureRandom;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import controller.Uploader;
import interfacce.UserInterface;
import model.Request;
import model.Student;

public class UploaderTest extends Mockito {
  private Uploader servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;

  @Before
  public void setUp() {
    servlet = new Uploader();
    request = new MockHttpServletRequest();
    response = new MockHttpServletResponse();
  }
}