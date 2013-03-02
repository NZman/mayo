package base;

import java.util.HashMap;
import java.util.Collection;

/**
* The Area class represents the floor plan of a single location, such as a
* single floor of an office complex or an auditorium.  Areas are currently
* defined as rectangular areas with a set of seats located within it.
*
*/
public class Area {
  /**
   * The default size of an area.
   */
  private static final int DEFAULT_DIMENSION = 300;
  /**
   * The name of this Area.
   */
  private String name;
  /**
   * The dimensions of this area.
   */
  private int width, height;
  /**
   * This hashMap contains all the seats within this Area.
   */
  private HashMap<String, Seat> seatMap;
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
    seatMap = new HashMap<String, Seat>();
    name = aName;
    width = aWidth;
    height = aHeight;
  }

  /**
  * Searches the set of seats and returns an empty seat.  Use this method if
  * need an empty seat and don't care where it is.  Returns null if all
  * seats are occupied.
  * 
  * @return An empty seat if one exists.  Otherwise null.
  */
  public final Seat findEmptySeat() {
    Seat result = null;
    Collection<Seat> smValues = seatMap.values();
    for (Seat s : smValues) {
      if(!s.isOccupied()) {
        result = s;
        break;
	  }
	}
	return result;
  }

  /**
  *  Adds a seat to the area at the specified coordinates. Returns the key of 
  *  the added seat.
  */
  public final void addSeat(final String name, final int x, final int y) {
    seatMap.put(name, new Seat(x,y));
  }

  /**
  *  Removes the seat that matches the associated key.  Does nothing if no
  *  seat matches the key.
  */
  public final void removeSeat(final int key) {
    seatMap.remove(key);
  }

  /**
  *  Returns the width of the area.
  */
  public final Integer getWidth() {
    return width;
  }

  /**
  *  Returns the height of the area.
  */
  public final Integer getHeight() {
    return height;
  }

  public final String getName() {
    return name;
  }
  /**
  *  Returns the string representation of this object.  Meaning the key and
  *  name.
  */
  public final String toString() {
    return name;
//		String result = name + "["+width+"x"+height+"]";
//		for(Seat s : seatMap.values()) {
//			result +="\n"+ s;
//		}
//		return result;
  }
}
