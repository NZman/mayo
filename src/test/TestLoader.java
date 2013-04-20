package test;

import base.DefaultArea;
import base.Area;
import base.OrgLoader;
import base.DefaultPerson;
import base.Person;
import base.DefaultSeat;
import java.util.Stack;

public class TestLoader implements OrgLoader {
	Stack<Person> personStack;
	Stack<Area> areaStack;
	
	public TestLoader() {
		personStack = new Stack<>();
		areaStack = new Stack<>();
		personStack.push(new DefaultPerson("Samantha Green"));
		personStack.push(new DefaultPerson("Arnold Carlos Palmer"));
		personStack.push(new DefaultPerson("Bob R. Handly"));
		DefaultArea area = new DefaultArea("Floor One", 100, 300);
		area.addSeat(new DefaultSeat("101", 12, 31));
    area.addSeat(new DefaultSeat("102", 33, 25));
		areaStack.push(area);
		area = new DefaultArea("Floor Two",200,700);
		area.addSeat(new DefaultSeat("201", 50, 75));
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
	@Override
	public String getName() {
		return "Test Inc.";
	}
	
	// For backwards compatibility

	public void pushPerson(DefaultPerson person){};

	public void pushArea(DefaultArea area){};

	public void pushName(String name){};
}
