package base;

/**
 * This interface is implemented by classes that read data from some source
 * and generate Area and Person instances and make them available through
 * getNextPerson and getNextArea method calls.  These methods are reminiscent
 * of a set of stacks, with pops and checks.
 * @author Oren Ely
 */
public interface OrgLoader {
  /** @return the next Person in the input stream. */
  Person popPerson();
  /** @return the next Area in the input stream. */
  Area popArea();
  /** @return true if there are no more Persons to load. */
  boolean personStackEmpty();
  /** @return true if there are no more Areas to load. */
  boolean areaStackEmpty();
  /** @return the name to apply to the Organization. */
  String getName();
  /**
   *  TODO.
   *  @param person TODO
   */
  void pushPerson(DefaultPerson person);
  /**
   *  TODO.
   *  @param area TODO
   */
  void pushArea(DefaultArea area);
  /**
   *  TODO.
   *  @param name TODO
   */
  void pushName(String name);
}
