package base;

/**A Seat is an object in 2d space that is pointed in a specific direction
 * and can be occupied by a Person.
 */
public class DefaultSeat implements Seat {
  /**The Person occupying the seat.  Null if empty. */
  private Person occupant;
  /**The position of the seat. */
  private int x, y;
  /**The direction in which the seat is pointing in degrees. */
  private int angle;
  /**The name of the seat. */
  private String name;

  /** Creates a seat in the specified position, and makes
   * person the occupant.
   * @param sX the x position of the seat.
   * @param sY the y position of the seat.
   * @param person the occupant.  Null if empty.
   * */
  public DefaultSeat(String name, 
          final Integer sX, final Integer sY, final Person person) {
    x = sX;
    y = sY;
    this.angle = 0;
    occupant = person;
  }
  
  public DefaultSeat(String name, final Integer sX, final Integer sY) {
    this(name,sX,sY,null);
  }

  /**
   * Creates an empty seat in the specified location.
   * @param sX the x position of the seat.
   * @param sY the y position of the seat.
   */
  public DefaultSeat(final int sX, final int sY) {
    this("New Seat", sX, sY, null);
  }
  /**
   * Creates an empty seat located at 0,0.
   */
  public DefaultSeat() {
    this("New Seat", 0, 0, null);
  }

  /**
   * Sets the chair to it's new position.
   * @param sX the x position of the seat.
   * @param sY the y position of the seat.
   */
  @Override
  public final void setPosition(final int sX, final int sY) {
    x = sX;
    y = sY;
  }

  /**@param sAngle sets the angle of the chair */
  @Override
  public final void setAngle(final int sAngle) {
    angle = sAngle;
  }
  /**@param sOccupant the person who will occupy the seat. */
  @Override
  public final void setOccupant(final Person sOccupant) {
    occupant = sOccupant;
  }
  /** Frees a seat of it's occupant. */
  @Override
  public final void removeOccupant() {
    occupant = null;
  }

  /** @return the name of the seat. */
  @Override
  public final String getName() {
    return name;
  }
  
  /** @return the x position of the seat. */
  @Override
  public final Integer getX() {
    return x;
  }

  /** @return the y position of the seat. */
  @Override
  public final Integer getY() {
    return y;
  }
  /** @return the angle of the seat. */
  @Override
  public final Integer getAngle() {
    return angle;
  }
  /** @return the person occupying the chair */
  @Override
  public final Person getOccupant() {
    Person result = null;
    if (occupant != null) {
      result = occupant;
    }
    return result;
  }
  /**@return true if the seat is occupied.*/
  @Override
  public final boolean isOccupied() {
    return occupant != null;
  }
  /**@return a string giving a detailed rundown of the Seat. */
  @Override
  public final String toString() {
    String occString = "";
    if (isOccupied()) { occString = "{" + occupant + "}"; }
    return "(" + x + "," + y + ")[" + angle + "o]" + occString;
  }
}
