package base;

/**A Seat is an object in 2d space that is pointed in a specific direction
 * and can be occupied by a Person.
 */
public class Seat implements Structure {
  /**The Person occupying the seat.  Null if empty. */
  private Person occupant;
  /**The position of the seat. */
  private int x, y;
  /**The direction in which the seat is pointing in degrees. */
  private int angle;

  /** Creates a seat in the specified position, and makes
   * person the occupant.
   * @param sX the x position of the seat.
   * @param sY the y position of the seat.
   * @param person the occupant.  Null if empty.
   * */
  public Seat(final int sX, final int sY, final Person person) {
    x = sX;
    y = sY;
    this.angle = 0;
    occupant = person;
  }

  /**
   * Creates an empty seat in the specified location.
   * @param sX the x position of the seat.
   * @param sY the y position of the seat.
   */
  public Seat(final int sX, final int sY) {
    this(sX, sY, null);
  }
  /**
   * Creates an empty seat located at 0,0.
   */
  public Seat() {
    x = 0;
    y = 0;
  }

  /**
   * Sets the chair to it's new position.
   * @param sX the x position of the seat.
   * @param sY the y position of the seat.
   */
  public final void setPosition(final int sX, final int sY) {
    x = sX;
    y = sY;
  }

  /**@param sAngle sets the angle of the chair */
  public final void setAngle(final int sAngle) {
    angle = sAngle;
  }
  /**@param sOccupant the person who will occupy the seat. */
  public final void setOccupant(final Person sOccupant) {
    occupant = sOccupant;
  }
  /** Frees a seat of it's occupant. */
  public final void removeOccupant() {
    occupant = null;
  }

  /** @return the x position of the seat */
  public final int getX() {
    return x;
  }

  /** @return the y position of the seat. */
  public final int getY() {
    return y;
  }
  /** @return the angle of the seat. */
  public final int getAngle() {
    return angle;
  }
  /** @return the person occupying the chair */
  public final Person getOccupant() {
    Person result = null;
    if (occupant != null) {
      result = occupant;
    }
    return result;
  }
  /**@return true if the seat is occupied.*/
  public final boolean isOccupied() {
    return occupant != null;
  }
  /**@return a string giving a detailed rundown of the Seat. */
  public final String toString() {
    String occString = "";
    if (isOccupied()) { occString = "{" + occupant + "}"; }
    return "(" + x + "," + y + ")[" + angle + "o]" + occString;
  }
}
