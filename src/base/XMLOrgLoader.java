/*

This class uses the JAXB method, which binds XML to classes 
instead of using a parser like SAX or DOM.

"Using JAXB [instead of SAX or DOM], you would:

Bind the schema for the XML document. Unmarshal the 
document into Java content objects. The Java content 
objects represent the content and organization of the 
XML document, and are directly available to your program.

After unmarshalling, your program can access and 
display the data in the XML document simply by accessing 
the data in the Java content objects and then 
displaying it. There is no need to create and use 
a parser and no need to write a content handler with 
callback methods. What this means is that developers can
access and process XML data without having to know XML or
XML processing."

http://www.oracle.com/technetwork/articles/javase/index-140168.html

For more information, see the "unofficial JAXB Guide" hosted by Project JAXB:
http://jaxb.java.net/guide/
*/

package base;

import java.io.File;
import java.io.IOException;


import javax.xml.bind.*;


public class XMLOrgLoader implements OrgLoader { 

	final int NO_FILE = 1;

	JAXBContext jc; 
	Marshaller marshaller;
	Unmarshaller unmarshaller; 

	Binding binding;	// this is the top-level content object
	File file;
	
	int nPerson = 0;
	int nArea = 0;
		
	public XMLOrgLoader() {}	// blank construct
	
	
	
	public void clear()
	{
	binding = new Binding();
	}
	
	public void test(){
	
	pushArea(new DefaultArea("Test",100,300));
	pushArea(new DefaultArea("Test2", 300, 400));
	pushPerson(new Person("Bob","1"));
	pushPerson(new Person("Jane","2"));
	write("test2.xml");
	
	}
	
	
	public int write(String path){

		int status = 0;
	try{
		jc = JAXBContext.newInstance(Binding.class);
		marshaller = jc.createMarshaller();
	
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		file = new File(path);
		
		marshaller.marshal(binding, file);
		
		// also print to System.out 
		marshaller.marshal(binding, System.out);
 		}
 		
 		catch (JAXBException e) {
			e.printStackTrace();
	    }
	    return status;
	}
		
	public int read(String path){
		
		int status = 0;
		Binding bind = new Binding();
		try{	
			jc = JAXBContext.newInstance(Binding.class);
			unmarshaller = jc.createUnmarshaller();
		
			file = new File(path);
			if(!file.exists()) 
			{
			System.out.println("\n\nError:  File does not exist!");
			status = NO_FILE;
			System.exit(0);
			}

			binding = (Binding) unmarshaller.unmarshal (file);
			}
		
		catch (JAXBException e) {
			e.printStackTrace();
		}

		return status;
	}

// Implement the OrgLoader Interface

	public Person popPerson(){
		nPerson++;	
		System.out.println("Person: " + nPerson);
		return binding.getPersonByIndex(nPerson-1);
	}

	public Area popArea(){
		nArea++;
		return binding.getAreaByIndex(nArea-1);
	}

	public boolean personStackEmpty(){

		if (this.nPerson > binding.getPersonSize())
		{
			System.out.println(binding.getPersonSize());
			return true;	
		}
		else
		{
			System.out.println(binding.getPersonSize());
			return false;
		}
	}

	public boolean areaStackEmpty(){
	
		if (this.nArea > binding.getAreaSize())
		{
			return true;	
		}
		else
		{	
			return false;
    }
	}

  @Override
  public String getName(){
    return binding.getName();
  }	
  @Override
  public void pushPerson(Person person){
    binding.addPerson(person);
  }
  @Override
  public void pushArea(Area area){
    binding.addArea(area);
  }
  public void pushName(String name){
    binding.setName(name);
  }
}
 
 


