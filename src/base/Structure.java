package base;

/**
 * A Structure is an object that exists in a 2D space with an angle.
 * Classes that implement this interface need to have accessors and
 * modifiers to the position and angle of these objects.
 * @author Oren
 *
 */
public interface Structure {
  /**
   * Sets the position of the Structure.
   * @param x the new x location of the Structure
   * @param y the new y location of the Structure
   */
  void setPosition(int x, int y);
  /**
   *The angle parameter represents whole degrees. 180 represents a halfway turn.
   *360 is effectively the same as 0.
   *@param angle new angle of the Structure.
   */
  void setAngle(int angle);
  /** @return the name of the Structure. */
  public String getName();
  /** @return the x coordinate of the Structures location. */  
  public Integer getX();
  /** @return the y coordinate of the Structures location. */
  public Integer getY();
  /** @return the angle of the Structure. */
  public Integer getAngle();
}
