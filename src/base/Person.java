package base;

public class Person {
	int key;
	String name;
	public Person(int key, String name) {
		this.key = key;
		this.name = name;
	}
	
	public Integer getKey() {
		return key;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return key + ": " + name;
	}
}
