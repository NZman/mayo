package base;

import java.util.HashMap;
import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;

/**
* The Area class represents the floor plan of a single location, such as a
* single floor of an office complex or an auditorium.  Areas are currently
* defined as rectangular areas with a set of seats located within it.
*
*/
@XmlAccessorType(XmlAccessType.FIELD)
public class Area {
  /** The default size of an area. */
  private static final int DEFAULT_DIMENSION = 300;
  /** The name of this Area. */
  private String name;
  /** The dimensions of this area. */
  private int width, height;
  /** This hashMap contains all the seats within this Area. */
  private HashMap<String, Seat> seatMap;

  /**
  *  Initializes an Area with a blank slate.
  */
  public Area() {
    this("New Area", DEFAULT_DIMENSION, DEFAULT_DIMENSION);
  }

  /**
  *  Initializes an Area with the specified key and name.
  *  @param aName The name for the created Area
  */
  public Area(final String aName) {
    this(aName, DEFAULT_DIMENSION, DEFAULT_DIMENSION);
  }

  /**
  *  Initializes an Area with the specified key, name, and dimensions.
  *  @param aName The name for the created Area
  *  @param aWidth The horizontal size of the created Area
  *  @param aHeight The vertical size of the created Area
  */
  public Area(final String aName, final Integer aWidth, final Integer aHeight) {
    seatMap = new HashMap<>();
    name = aName;
    width = aWidth;
    height = aHeight;
  }

  /**
  * Searches the set of seats and returns an empty seat.  Use this method if
  * need an empty seat and don't care where it is.  Returns null if all
  * seats are occupied.
  * @return An empty seat if one exists.  Otherwise null.
  */
  public final Seat findEmptySeat() {
    Seat result = null;
    Collection<Seat> smValues = seatMap.values();
    for (Seat s : smValues) {
      if (!s.isOccupied()) {
        result = s;
        break;
      }
    }
    return result;
  }

  /**
  *  Adds a seat to the area at the specified coordinates. Returns the key of
  *  the added seat.
  *  @param sName The unique String id of the seat.
  *  @param x The x coordinate of the seats location.
  *  @param y The y coordinate of the seats location.
  */
  public final void addSeat(final String sName, final int x, final int y) {
    seatMap.put(sName, new Seat(x, y));
  }

  /**
  *  Removes the seat that matches the associated key.  Does nothing if no
  *  seat matches the key.
  *  @param key The unique identifier for the seat to be removed.
  */
  public final void removeSeat(final String key) {
    seatMap.remove(key);
  }
  
  /**
   * Returns a seat that matches the specified key.
   * @param key the identifier of the seat.
   * @return 
   */
  public final Seat getSeat(final String key) {
    return seatMap.get(key);
  }

  /**
  *  Returns the width of the area.
  *  @return the width of the Area.
  */
  public final Integer getWidth() {
    return width;
  }

  /**
  *  Returns the height of the area.
  *  @return the height of the Area.
  */
  public final Integer getHeight() {
    return height;
  }

  /**
   * Returns the name of the Area.
   * @return name;
   */
  public final String getName() {
    return name;
  }

  /**
  *  Returns the string representation of this object.  Meaning the key and
  *  name.
  *  @return the name of the Area.
  */
  @Override
  public final String toString() {
    return name;
  }
}
