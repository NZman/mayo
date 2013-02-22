package base;
import java.util.HashMap;
import java.util.Collection;

/**
* The Area class represents the floorplan of a single location, such as a
* single floor of an office complex or an auditorium.  Areas are currently
* defined as rectangular areas with a set of seats located within it.
*
*/
public class Area {
	private static final int DEFAULT_DIMENSION = 300;
	private static final int SEAT_LIMIT = 1000;
	
	private int seats;

	private String name;
	private int width, height;

	private HashMap<Integer,Seat> seatMap;
  
	/**
  *  Initializes an Area with the specified key and name.
  *
	*/
	public Area(String name) {
		this(name, DEFAULT_DIMENSION, DEFAULT_DIMENSION);
	}
	
  /**
	*  Initializes an Area with the specified key, name, and dimensions.
	*
	*/
	public Area(String name, Integer width, Integer height) {
		seatMap = new HashMap<Integer,Seat>();
		this.name = name;
		this.width = width;
		this.height = height;
		this.seats = 0;
	}
	
	/**
	* Searches the set of seats and returns an empty seat.  Use this method if
	* need an empty seat and don't care where it is.  Returns null if all
	* seats are occupied.
	*/
	public Seat findEmptySeat() {
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
	public int addSeat(int x, int y) {
		int result = seats;
		seatMap.put(seats, new Seat(x,y));
		seats += 1;
		return result;
	}
	
	/**
	*  Removes the seat that matches the associated key.  Does nothing if no
	*  seat matches the key.
	*/
	public void removeSeat(int key) {
		seatMap.remove(key);
		seats -= 1;
	}
	
	/**
	*  Returns the width of the area.
	*/
	public Integer getWidth() {
		return width;
	}

	/**
	*  Returns the height of the area.
	*/
	public Integer getHeight() {
		return height;
	}

	/**
	*  Returns the string representation of this object.  Meaning the key and
	*  name.
	*/
	public String toString() {
		String result = name + "["+width+"x"+height+"]";
		for(Seat s : seatMap.values()) {
			result +="\n"+ s;
		}
		return result;
	}
}
