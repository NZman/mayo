package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import base.DefaultPerson;

public class PersonTest {

  @Test
  public void testPersonString() {
    assertEquals(new DefaultPerson("Carlos P Weathers").getUserName(),"cpweat");
    assertEquals(new DefaultPerson("Ann").getUserName(),"axxxxx");
    assertEquals(new DefaultPerson("").getUserName(),"xxxxxx");
  }

  @Test
  public void testGetName() {
    assertEquals(new DefaultPerson("Paul Genner").getName(), "Paul Genner");
    assertEquals(new DefaultPerson("Ann").getName(), "Ann");
    assertEquals(new DefaultPerson("").getName(), "");
    assertEquals(new DefaultPerson("Octa Neo Zo Eval").getName(), "Octa Neo Zo Eval");
  }
}
