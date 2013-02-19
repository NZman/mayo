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
		personStack.push(new Person(1,"Samantha Green"));
		personStack.push(new Person(2,"Arnold Palmer"));
		personStack.push(new Person(3,"Paul Vorne"));
		areaStack.push(new Area(1,"Floor One", 100, 300));
		areaStack.push(new Area(2,"Floor Two", 200, 700));
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

}
