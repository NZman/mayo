package base;

import java.util.List;
import java.util.ArrayList;

import javax.xml.bind.annotation.*;

@XmlRootElement(name="Organization")
@XmlSeeAlso( {Area.class, Person.class} )
public class Binding {

@XmlElement
private String name;
@XmlElement
private List<Area> area = new ArrayList<Area>();
@XmlElement
private List<Person> person = new ArrayList<Person>();


public Binding() {}


	public void addArea(Area a){
		this.area.add(a);
	}

	public void addPerson(Person p){
		this.person.add(p);
	}
	@XmlTransient
	public void setName(String n){
		this.name = n;
	}	

	public Area getAreaByIndex(int index){
		return area.get(index);
	}

	public Person getPersonByIndex(int index){
		return person.get(index);
	}

	public int getAreaSize(){
		return area.size();
	}

	public int getPersonSize(){
		return person.size();
	}

	public String getName(){
		return name;
	}
	
}
