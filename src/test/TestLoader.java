package test;

import base.Area;
import base.OrgLoader;
import base.Person;
import java.util.Stack;

public class TestLoader implements OrgLoader {
	Stack<Person> personStack;
	Stack<Area> areaStack;
	
	public TestLoader() {
		personStack = new Stack<Person>();
		areaStack = new Stack<Area>();
		personStack.push(new Person("Samantha Green"));
		personStack.push(new Person("Arnold Palmer"));
		personStack.push(new Person("Paul Vorne"));
		Area area = new Area("Floor One", 100, 300);
		area.addSeat("101",12, 31);
		area.addSeat("102",33, 25);
		areaStack.push(area);
		area = new Area("Floor Two",200,700);
		area.addSeat("201",50,75);
		areaStack.push(area);
	}
	
	@Override
	public Person popPerson() {
		return personStack.pop();
	}

	@Override
	public Area popArea() {
		return areaStack.pop();
	}

	@Override
	public boolean personStackEmpty() {
		return personStack.empty();
	}

	@Override
	public boolean areaStackEmpty() {
		return areaStack.empty();
	}
	public String getName() {
		return "Test Inc.";
	}
	
	// For backwards compatibility
	public void pushPerson(Person person){};
	public void pushArea(Area area){};
	public void pushName(String name){};
}
