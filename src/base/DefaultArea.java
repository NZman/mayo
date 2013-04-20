package base;

import java.util.HashMap;
import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
* The Area class represents the floor plan of a single location, such as a
* single floor of an office complex or an auditorium.  Areas are currently
* defined as rectangular areas with a set of seats located within it.
*
*/
@XmlAccessorType(XmlAccessType.FIELD)
public class DefaultArea implements Area{
  /** The default size of an area. */
  private static final int DEFAULT_DIMENSION = 300;
  /** The name of this Area. */
  private String name;
  /** The dimensions of this area. */
  private int width, height;
  /** This hashMap contains all the seats within this Area. */

  private HashMap<String, Seat> seatMap;

  	static class Adapter extends XmlAdapter<DefaultArea, Area> {
	  public Area unmarshal(DefaultArea v) { return v; }
	  public DefaultArea marshal(Area v) { return (DefaultArea) v; }
	}
	




  /**
  *  Initializes an Area with a blank slate.
  */
  public DefaultArea() {
    this("New Area", DEFAULT_DIMENSION, DEFAULT_DIMENSION);
  }

  /**
  *  Initializes an Area with the specified key and name.
  *  @param aName The name for the created Area
  */
  public DefaultArea(final String aName) {
    this(aName, DEFAULT_DIMENSION, DEFAULT_DIMENSION);
  }

  /**
  *  Initializes an Area with the specified key, name, and dimensions.
  *  @param aName The name for the created Area
  *  @param aWidth The horizontal size of the created Area
  *  @param aHeight The vertical size of the created Area
  */
  public DefaultArea(final String aName, 
          final Integer aWidth, final Integer aHeight) {
    seatMap = new HashMap<>();
    name = aName;
    width = aWidth;
    height = aHeight;
  }
  /** @param aName the new name of the Area. */
  @Override
  public void setName(String aName) {
    name = aName;
  }
  /** @param aWidth the new width of the Area. */
  @Override
  public void setWidth(Integer aWidth) {
    width = aWidth;
  }
  /** @param aHeight the new height of the Area. */
  @Override
  public void setHeight(Integer aHeight) {
    
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
  
  @Override
  public final void addSeat(final Seat sSeat) {
    seatMap.put(sSeat.getName(), sSeat);
  }

  /**
  *  Removes the seat that matches the associated key.  Does nothing if no
  *  seat matches the key.
  *  @param key The unique identifier for the seat to be removed.
  */
  @Override
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
  @Override
  public final Integer getWidth() {
    return width;
  }

  /**
  *  Returns the height of the area.
  *  @return the height of the Area.
  */
  @Override
  public final Integer getHeight() {
    return height;
  }

  /**
   * Returns the name of the Area.
   * @return name;
   */
  @Override
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
