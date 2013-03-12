package base;

import javax.xml.bind.annotation.XmlElement;

/**
 * A Person has an actual name and a user name.
 * @author Oren Ely
 */
public class DefaultPerson implements Person {
  /**Default user name generation settings
   * "Ferdinand Mercello Lastowitz" becomes fmlast".*/
  /**Characters generated for user name from first name. */
  static final int UN_FIRST_CHARS = 1;
  /**Characters generated for user name from middle name. */
  static final int UN_MIDDLE_CHARS = 1;
  /**Characters generated for user name from last name. */
  static final int UN_LAST_CHARS = 4;
  /**The number of strings that make up a name.*/
  static final int NAME_PARTS = 3;
  /**A wrapper class containing a first, middle, and last name.*/
  private static final class Name {
    /** The three components of a name.
     * There can be multiple names in middle.
     **/
    private String first, middle, last;

    /**
     * Standard Name constructor.
     * @param nFirst First Name.
     * @param nMiddle Middle Name(s).
     * @param nLast Last Name.
     */
    private Name(final String nFirst, final String nMiddle,
        final String nLast) {
      this.first = nFirst;
      this.middle = nMiddle;
      this.last = nLast;
    }
  }
  /**This method generates a Name object based on the given String.
   * Allowed inputs include: "First Last" and "First Middle Last".
   * TODO: Add checks to make sure input is valid.
   * @param name the String from which to generate a Name.
   * @return a Name representation of the given String.
   * */
  private static Name makeName(final String name) {
    String[] nameSet = name.split(" ");
    String first = nameSet[0];
    String middle, last;
    
    if(nameSet.length > 1) {
      last = nameSet[nameSet.length - 1];
    } else last = "";
    if(nameSet.length > 2) {
      middle = name.substring(
          first.length()+1, name.length() - (last.length() + 1));
    } else middle = "";
    
    return new Name(first, middle, last);
  }

  /** The unique identifier of the Person. */
  @XmlElement
  private String username;
  /** The full name of the Person. */
  @XmlElement
  private String firstName, middleName, lastName;

  /**Creates a new nameless Person. User name will be a string of x's*/
  public DefaultPerson() {
    this("");
  }

  /**Creates a new Person with the given name.
   * A user name will be generated based on the name.
   * @param pName the Person's name
   * */
  public DefaultPerson(final String pName) {
    Name name = makeName(pName);
    setName(name);
    username = makeUserName(name,
        UN_FIRST_CHARS, UN_MIDDLE_CHARS, UN_LAST_CHARS);
  }

  /**
   * Creates a new Person with the given name and user name.
   * @param pName the Person's name
   * @param pUsername the Person's user name.
   */
  public DefaultPerson(final String pName, final String pUsername) {
    setName(makeName(pName));
    username = pUsername;
  }

  /**
   * Generates a user name from a given name.  The other three parameters
   * how the user name is composed. Names shorter than the specification will
   * be filled with x's until the length is reached.
   * ("Carl Pat Weathers", 1,0,4) should result in cweat.
   * ("Patricia Lemont Ro", 1,2,5) returns pleroxxx.
   * @param name the Name from which to generate a username.
   * @param firstChars the number of characters used from the first name.
   * @param midChars the number of characters used from the middle name(s).
   * @param lastChars the number of characters used from the last name.
   * @return a user name!
   */
  private String makeUserName(final Name name, final int firstChars,
      final int midChars, final int lastChars) {
    String[] names = {name.first, name.middle, name.last};
    int[] charLim = {firstChars, midChars, lastChars};

    for (int i = 0; i < NAME_PARTS; i++) {
      while (names[i].length() <= charLim[i]) {
        names[i] += "x";
      }
    }

    return (names[0].substring(0, charLim[0])
        + names[1].substring(0, charLim[1])
        + names[2].substring(0, charLim[2])).toLowerCase();
  }

  @Override
  public void setName(String pName) {
    setName(makeName(pName));
  }
  
  private void setName(Name pName) {
    firstName = pName.first;
    middleName = pName.middle;
    lastName = pName.last;
  }
  
  @Override
  public void setUserName(String uName) {
    username = uName;
  }
  
  /**@return A single string containing the Person's name */
  @Override
  public final String getName() {
    String result = firstName;
    if (middleName.length() > 0) { result += " "; }
    result += middleName;
    if (lastName.length() > 0) { result += " "; }
    return result + lastName;
  }

  /**@return the Person's user name */
  @Override
  public final String getUserName() {
    return username;
  }

  /**@return the Person's user name */
  @Override
  public final String toString() {
    return getName();
  }
}
