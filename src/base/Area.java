package base;
import java.util.HashMap;
import java.util.Collection;

public class Area {
	static int DEFAULT_DIMENSION = 300;
	
	int key;
	String name;
	int width, height;

	HashMap<Integer,SeatStructure> structMap;

	public Area(Integer key, String name) {
		this(key, name, DEFAULT_DIMENSION, DEFAULT_DIMENSION);
	}
	
	public Area(Integer key, String name, Integer width, Integer height) {
		structMap = new HashMap<Integer,SeatStructure>();
		this.key = key;
		this.name = name;
		this.width = width;
		this.height = height;
	}
	
	public SeatStructure findEmptyStruct() {
		SeatStructure result = null;
		Collection<SeatStructure> smValues = structMap.values();
		for (SeatStructure ss : smValues) {
			if(ss.seatAvailable()) {
				result = ss;
				break;
			}
		}
		return result;
	}
	
	public Integer getWidth() {
		return width;
	}
	public Integer getHeight() {
		return height;
	}
	public Integer getKey() {
		return key;
	}
	public String toString() {
		return key + ": " + name;
	}
}
