package base;

/**
* The Area class represents the floor plan of a single location, such as a
* single floor of an office complex or an auditorium.  Areas are currently
* defined as rectangular areas with a set of seats located within it.
*
* @author Oren
*/

public interface Area {
  /** @param name the new name of the Area. */
  public void setName(String name);
  /** @param width the new width of the Area. */
  public void setWidth(Integer width);
  /** @param height the new height of the Area. */
  public void setHeight(Integer height);
  /**
   * Adds a seat to the Area.
   * @param name the name of the added seat. 
   * Each seat in an Area must have a unique name.
   * @param seat the Seat to be added.
   */
  public void addSeat(Seat seat);
  /**
   * Removes a seat from the Area.
   * @param name the name of the seat to be removed.
   */
  public void removeSeat(String name);
  
  /** @return the name of the Area. */
  public String getName();
  /** @return the width of the Area. */
  public Integer getWidth();
  /** @return the height of the Area. */
  public Integer getHeight();
  /** 
   * @param name the name of the Seat to be returned.
   * @return the seat that matches the given name. 
   */
  public Seat getSeat(String name);
}
