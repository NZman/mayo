package base;

import java.util.Collection;

/**
 *
 * @author Oren
 */
public interface Organization {

  /**
   * Adds an Area to the Organization.
   * @param area The Area to add to the Organization.
   */
  void addArea(final Area area);

  /**
   * Adds a person to the Organization.
   * @param person The Person to add.
   */
  void addPerson(final Person person);

  /**
   * Removes the specified Area.
   * @param areaName the identifier of the Area to be removed.
   * @return The removed Area. Null if the Area didn't exist.
   */
  Area removeArea(final String areaName);

  /**
   * Removes the specified Person.
   * @param userName the identifier of the Person to be removed.
   * @return The removed Person. Null if the Person didn't exist.
   */
  Person removePerson(final String userName);

  /**
   * Sets the name of the Organization.
   * @param oName The new name of the Organization.
   */
  void setName(final String oName);
  
  /**
   * Returns true if the specified Area exists within the Organization.
   * @param areaName The name of the Area
   * @return True if the Area exists. False otherwise.
   */
  boolean hasArea(final String areaName);
  
  /**
   * Returns true if the specified Person exists within the Organization.
   * @param personName The name of the Person
   * @return True if the Person exists. False otherwise.
   */
  boolean hasPerson(final String personName);

  /**
   * Returns the area matching the specified identifier.
   * @param areaName the name of the Area.
   * @return The Area.
   */
  Area getArea(final String areaName);
  
  /**
   * Returns the person matching the specified identifier.
   * @param personName the name of the Person.
   * @return the Person.
   */
  Person getPerson(final String personName);

  /**
   * Returns the complete list of Areas as a Collection.
   * @return A Collection containing the Areas of this Organization.
   */
  Collection<Area> getAreas();

  /**
   * Returns the complete list of Persons as a Collection.
   * @return A Collection containing the Persons of this Organization.
   */
  Collection<Person> getPeople();
  
  /** @return the name of the Organization. */
  String getName();
}
