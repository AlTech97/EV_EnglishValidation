package model;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import controller.DbConnection;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.collection.IsMapContaining;

import org.junit.jupiter.api.Test;

public class SystemAttributeTest {

  @Test
  void testGetHashMap() {
    SystemAttribute sa = new SystemAttribute();
    HashMap<String, String> hashMap = new HashMap<>();
    hashMap.put("request-partially-completed", "1");
    HashMap<String, String> expected = new HashMap<>();
    expected.put("request-partially-completed", "1");

    assertThat(hashMap, is(expected));
    assertThat(hashMap.size(), is(1));
    assertThat(hashMap, IsMapContaining.hasEntry("request-partially-completed", "1"));
    assertThat(hashMap, not(IsMapContaining.hasEntry("aaa", "a")));
    assertThat(hashMap, IsMapContaining.hasKey("request-partially-completed"));
    assertThat(hashMap, IsMapContaining.hasValue("1"));
    assertEquals("request-partially-completed" + hashMap.get("request-partially-completed"), sa.getHashMap());
  }
  
  @Test
  void testGetInstance() {
    SystemAttribute sa = SystemAttribute.getInstance();
    SystemAttribute sa1 = SystemAttribute.getInstance();
    assertEquals(sa, sa1);
  }

}
