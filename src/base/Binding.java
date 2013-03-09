package base;

import java.util.List;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "Organization")
@XmlSeeAlso({Area.class, Person.class })
public class Binding {

  @XmlElement
  private String name;

  @XmlElement
  private List<Area> area = new ArrayList<Area>();
  
  @XmlElement
  private List<Person> person = new ArrayList<Person>();

  public Binding() { }

  public final void addArea(final Area a) {
    this.area.add(a);
  }

  public final void addPerson(final Person p) {
    this.person.add(p);
  }

  @XmlTransient
  public final void setName(final String n) {
    this.name = n;
  }

  public final Area getAreaByIndex(final int index) {
    return area.get(index);
  }

  public final Person getPersonByIndex(final int index) {
    return person.get(index);
  }

  public final int getAreaSize() {
    return area.size();
  }

  public final int getPersonSize() {
    return person.size();
  }

  public final String getName() {
    return name;
  }
}
