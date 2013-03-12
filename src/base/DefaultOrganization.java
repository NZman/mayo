package base;

import java.util.HashMap;
import java.util.Collection;

/**
 * Organizations are the top level component of the SeatApp.  Organizations
 * are made up of a set of areas and a set of people.
 */
public class DefaultOrganization implements Organization {
  /** A hashMap containing all of the Areas in the Organization. */
  private HashMap<String, Area> areaHash;
  /** A hashMap containing all of the Persons in the Organization. */
  private HashMap<String, Person> personHash;
  /** The name of this Organization. */
  private String name;

  /**
   * Initializes a new empty Organization.
   * @param oName The name of the Organization.
   */
  public DefaultOrganization(final String oName) {
    areaHash = new HashMap<>();
    personHash = new HashMap<>();
    this.name = oName;
  }

  /**
   * Uses an {@link OrgLoader} to create an Organization with predefined
   * Areas and People.
   * @param ol the OrgLoader which will build the Organization.
   */
  public DefaultOrganization(final OrgLoader ol) {
    this(ol.getName());
    while (!ol.areaStackEmpty()) {
      addArea(ol.popArea());
    }
    while (!ol.personStackEmpty()) {
      addPerson(ol.popPerson());
    }
  }

  /**
   *Adds a person to the Organization.
   * @param pName The identifier of the person.
   */
  public final void addPerson(final String pName) {
    addPerson(new DefaultPerson(pName));
  }

  /**
   * Adds a person to the Organization.
   * @param person The Person to add.
   */
  @Override
  public final void addPerson(final Person person) {
    if (!personHash.containsKey(person.getUserName())) {
      personHash.put(person.getUserName(), person);
    }
  }

  /**
   * Sets the name of the Organization.
   * @param oName The new name of the Organization.
   */
  @Override
  public final void setName(final String oName) {
    this.name = oName;
  }

  /**
   * Returns the area matching the specified identifier.
   * @param areaName the name of the Area.
   * @return The Area.
   */
  @Override
  public final Area getArea(final String areaName) {
    Area result = null;
    if (areaHash.containsKey(areaName)) {
      result = areaHash.get(areaName);
    }
    return result;
  }
  
  /**
   * Returns the person matching the specified identifier.
   * @param personName the name of the Person.
   * @return The Person.
   */
  @Override
  public final Person getPerson(final String personName) {
    Person result = null;
    if (personHash.containsKey(personName)) {
      result = personHash.get(personName);
    }
    return result;
  }

  /**
   * Returns the complete list of Persons as a Collection.
   * @return A Collection containing the Persons of this Organization.
   */
  @Override
  public final Collection<Person> getPeople() {
    return personHash.values();
  }

  /**
   * Returns the complete list of Areas as a Collection.
   * @return A Collection containing the Areas of this Organization.
   */
  @Override
  public final Collection<Area> getAreas() {
    return areaHash.values();
  }

  /**
   * Adds an Area to the Organization.
   * @param area The Area to add to the Organization.
   */
  @Override
  public final void addArea(final Area area) {
    areaHash.put(area.getName(), area);
  }

  /**
   * Returns true if the specified Area exists within the Organization.
   * @param areaName The name of the Area
   * @return True if the Area exists. False otherwise.
   */
  @Override
  public final boolean hasArea(final String areaName) {
    return areaHash.containsKey(areaName);
  }
  
  /**
   * Returns true if the specified Person exists within the Organization.
   * @param personName The name of the Person
   * @return True if the Person exists. False otherwise.
   */
  @Override
  public final boolean hasPerson(final String personName) {
    return personHash.containsKey(personName);
  }

  /**
   * Removes the specified Area.
   * @param areaName the identifier of the Area to be removed.
   * @return The removed Area. Null if the Area didn't exist.
   */
  @Override
  public final Area removeArea(final String areaName) {
    Area result = null;
    if (areaHash.containsKey(areaName)) {
      result = areaHash.remove(areaName);
    }
    return result;
  }

  /**
   * Removes the specified Person.
   * @param userName the identifier of the Person to be removed.
   * @return The removed Person. Null if the Person didn't exist.
   */
  @Override
  public final Person removePerson(final String userName) {
    Person result = null;
    if (personHash.containsKey(userName)) {
      result = personHash.remove(userName);
    }
    return result;
  }

  /** @return the name of the Organization. */
  @Override
  public final String getName() {
    return name;
  }

  /** @return A detailed listing of the Organization structure */
  @Override
  public final String toString() {
    String result = name + "\n\nPeople:\n";
    for (Person p : personHash.values()) {
      result += p + "\n";
    }
    result += "\nAreas:\n";
    for (Area a : areaHash.values()) {
      result += a + "\n";
    }
    return result;
  }
}
