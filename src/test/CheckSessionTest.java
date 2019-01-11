package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import controller.CheckSession;
import model.Attached;

public class CheckSessionTest {

  @Test
  void testCheckSessionCostructor() {
    MockHttpServletRequest request = new MockHttpServletRequest();
    HttpSession session = request.getSession();
    CheckSession check = new CheckSession("","",session);
    assertNotNull(check);
  }
}
