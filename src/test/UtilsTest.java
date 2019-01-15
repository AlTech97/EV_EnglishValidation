package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import controller.Utils;

import interfacce.UserInterface;

import java.time.Year;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import model.Attached;
import model.Ente;
import model.Request;
import model.State;
import model.Student;

import org.junit.jupiter.api.Test;

class UtilsTest {

  @Test
  void testGetRequestState() {
    Utils ut = new Utils();
    UserInterface ui = new Student();
    GregorianCalendar rd = new GregorianCalendar(2001, 00, 01);
    GregorianCalendar ed = new GregorianCalendar(2001, 8, 01);
    Request req = new Request(60, 6, "A1", rd, ed, 0, 
        512106597, Year.of(0), new ArrayList<Attached>(),
        new State(6, "Accepted"), new Ente(12, "", "Ascentis", ""), ui);
    assertEquals("0", ut.getRequestState(60));
  }

}
