package model;

import static org.junit.jupiter.api.Assertions.assertEquals; 

import org.junit.jupiter.api.Test;

class AttachedTest {

  // Test Metodi GET.

  @Test
  void testidAttached() {
    Attached att = new Attached(02, "");
    assertEquals(02, att.getIdAttached());
  }

  @Test
  void testFilename() {
    Attached att = new Attached(02, "nome file");
    assertEquals("nome file", att.getFilename());
  }

  // FINE TEST GET

  // INIZIO TEST SET

  @Test
  void testSetIdAttached() {
    Attached att = new Attached(02, "");
    att.setIdAttached(03);
    assertEquals(03, att.getIdAttached());
  }

  @Test
  void testSetfilename() {
    Attached att = new Attached(02, "nome file");
    att.setFilename("Angelo");
    assertEquals("Angelo", att.getFilename());
  }

  // FINE TEST SET


}
