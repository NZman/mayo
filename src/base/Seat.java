package base;

/**
 *
 * @author Oren
 */
public interface Seat extends Structure{
  public void setOccupant(Person person);
  public Person getOccupant();
  public boolean isOccupied();
  public void removeOccupant();
}
