package base;

import java.util.HashMap;
import java.util.Collection;

import test.TestLoader;

/**
* Organizations are the top level component of the SeatApp.  Organizations
* are made up of a set of areas and a set of people.
*/
public class Organization {
	private HashMap<Integer,Area> areaHash;
	private HashMap<Integer,Person> personHash;
	private int person_count;
	private int area_count;	
	private String name;

	/**
	* Initializes a new empty Organization.
	*/
	public Organization(String name) {
		areaHash = new HashMap<Integer,Area>();
		personHash = new HashMap<Integer,Person>();
		person_count = 0;
		area_count = 0;
		this.name = name;
	}
	
	/**
	* Uses an {@link OrgLoader} to create an Organization with predefined Areas
	* and People.
	*/
	public Organization(OrgLoader ol) {
		this(ol.getName());
		while(!ol.areaStackEmpty()) {
			addArea(ol.popArea());
		}
		while(!ol.personStackEmpty()) {
			addPerson(ol.popPerson());
		}
	}
	
	/**
	*  Places a person into an area based on a person key and an area key. This
	*  method can't be used to specify a specific chair in the selected Area.
	*/
	public void seatPerson(int personKey, int AreaKey) {
		if(areaExists(AreaKey)) {
			Area area = areaHash.get(AreaKey);
			Person person = personHash.get(personKey);
			Seat s = area.findEmptySeat();
			s.setOccupant(person);
		}
	}
	
	/**
	*	Adds a person to the Organization.
	*/
	public void addPerson(String name) {
		addPerson(new Person(name));
	}

	public void addPerson(Person person) {
		personHash.put(person_count, person);
		person_count++;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	/**
	*	
	*/
	public Area getArea(Integer areaKey) {
		Area result = null;
		if(areaHash.containsKey(areaKey)) {
			result = areaHash.get(areaKey);
		}
		return result;
	}
	
	public Collection<Person> getPeople() {
		return personHash.values();
	}
	
	public Collection<Area> getAreas() {
		return areaHash.values();
	}
	
	public void addArea(Area area) {
		areaHash.put(area_count, area);
		area_count++;
	}
	
	public boolean areaExists(int AreaKey) {
		return areaHash.containsKey(AreaKey);
	}

	public Area removeArea(Integer key) {
		Area result = null;
		if (areaHash.containsKey(key)) {
			result = areaHash.remove(key);
			area_count--;
		}
		return result;
	}

	public Person removePerson(Integer key) {
		Person result = null;
		if (personHash.containsKey(key)) {
			result = personHash.remove(key);
			person_count--;
		}
		return result;
	}

	public String toString() {
		String result = "";
		result = name+"\n\nPeople:\n";
		for (Person p : personHash.values()) {
			result += p+"\n";
		}
		result += "\nAreas:\n";
		for (Area a : areaHash.values()) {
			result += a+"\n";
		}
		return result;
	}

	public static void main (String args[]) {
		Organization org = new Organization(new TestLoader());
		System.out.print(org);
	}	
}
