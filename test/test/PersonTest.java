package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import base.Person;

public class PersonTest {

  @Test
  public void testPersonString() {
    assertEquals(new Person("Carlos P Weathers").getUserName(),"cpweat");
    assertEquals(new Person("Ann").getUserName(),"axxxxx");
    assertEquals(new Person("").getUserName(),"xxxxxx");
  }

  @Test
  public void testGetName() {
    assertEquals(new Person("Paul Genner").getName(), "Paul Genner");
    assertEquals(new Person("Ann").getName(), "Ann");
    assertEquals(new Person("").getName(), "");
    assertEquals(new Person("Octa Neo Zo Eval").getName(), "Octa Neo Zo Eval");
  }
}
