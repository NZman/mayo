package base;

public class Person {
	final String username;
	String name;
	
	public Person(String name) {
		this.username = name;
		this.name = name;
	}
	
	public Person(String username, String name) {
		this.username = username;
		this.name = name;
	}
	
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
