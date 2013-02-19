package base;

import java.util.HashMap;
import java.util.Collection;

public class Organization {
	HashMap<Integer,Area> areaHash;
	HashMap<Integer,Person> personHash;
	
	public Organization() {
		areaHash = new HashMap<Integer,Area>();
		personHash = new HashMap<Integer,Person>();
	}
	
	public Organization(OrgLoader ol) {
		this();
		while(!ol.areaStackEmpty()) {
			addArea(ol.popArea());
		}
		while(!ol.personStackEmpty()) {
			addPerson(ol.popPerson());
		}
	}
	
	public void seatPerson(int personKey, int AreaKey) {
		if(areaExists(AreaKey)) {
			Area area = areaHash.get(AreaKey);
			Person person = personHash.get(personKey);
			Seat s = area.findEmptySeat();
			s.setOccupant(person);
		}
	}
	
	public void addPerson(Person person) {
		int key = person.getKey();
		if(!personHash.containsKey(key)) {
			personHash.put(key, person);
		}
	}
	
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
		int key = area.getKey();
		if(!areaHash.containsKey(key)) {
			areaHash.put(key, area);
		}
	}
	
	public boolean areaExists(int AreaKey) {
		return areaHash.containsKey(AreaKey);
	}
}
