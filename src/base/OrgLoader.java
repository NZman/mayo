package base;


/**
 * This interface is implemented by classes that read data from some source
 * and generate Area and Person instances and make them available through
 * getNextPerson and getNextArea method calls.  These methods are reminscent
 * of a set of stacks, with pops and checks.
 * 
 * @author Oren Ely
 *
 */
public interface OrgLoader {

	public Person popPerson();
	public Area popArea();
	public boolean personStackEmpty();
	public boolean areaStackEmpty();
	public String getName();
	
	 
	public void pushPerson(Person person);
	public void pushArea(Area area);
	public void pushName(String name);
	
	 
	
}
