package base;
import javax.xml.bind.annotation.*;

public class Person {
	@XmlElement
	String username;
	@XmlElement
	String name;
	
	public Person(String name) {
		this.username = name;
		this.name = name;
	}
	
	public Person(String username, String name) {
		this.username = username;
		this.name = name;
	}
	
	public Person() {}		// JAXB needs a default blank constructor
	
	
	public String getName() {
		return name;
	}
	
	public String getUserName() {
		return username;
	}
	
	public String toString() {
		return name;
	}
}
