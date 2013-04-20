package base;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Oren
 */
 

@XmlJavaTypeAdapter(DefaultSeat.Adapter.class) 
public interface Seat extends Structure{
  public void setOccupant(Person person);
  public Person getOccupant();
  public boolean isOccupied();
  public void removeOccupant();
  
}

