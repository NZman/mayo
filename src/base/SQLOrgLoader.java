package base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Types;		// for some rudimentary mapping

public class SQLOrgLoader {	
	
	// database manipulation variables
	private Connection con = null;
	private Statement st = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	// database access configuration variables
	private String url = null;	    // for example "jdbc:mysql://localhost:3306/testdb"
	private String user = null;		// db user; for example "testuser"
	private final String BASIC_USER = "testuser";
	private String password = null;	// db user password; for example "test623"
	private String dbname = null;	// potentially useful for prepared statements

/* 		
+----------------+
| Tables_in_test |
+----------------+
| area           |	aid, width, height
| assigns        |	pid, sid
| person         |	pid, first, middle, last
| places         |	aid, sid
| seat           |	sid, x, y, angle
+----------------+
*/

	public enum Relations {
		AREA,
		ASSIGNS,
		PERSON,
		PLACES,
		SEAT
	}
	public enum Attributes {
		PID,			// varchar(255) unless otherwise specified
	//	FIRST,			
	//	MIDDLE,
	//	LAST,
		AID,
		WIDTH,			// int
		HEIGHT,			// int
		SID,	
		X,				// int
		Y,				// int
		ANGLE			// int
	}
	

	
/************		Constructors		**********/

	public SQLOrgLoader () {} 
		// empty constructor; use registerDriver() and changeConnection()
	
	public SQLOrgLoader (String new_url, String new_password, String database) {
		registerDriver();
		changeConnection(new_url, BASIC_USER, new_password, true);
		this.dbname = database;
	}
	
	
/************		Methods				**********/
	
	void registerDriver() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			} 
		catch (Exception ex) {
			//
		}
	}
			// accessor functions 
			
	void setURL (String new_url) { this.url = new_url; }
	void setUser (String new_user) { this.user = new_user;} // for esoteric cases
	void setPassword (String new_password) { this.password = new_password; } 
	void setDatabase (String database) { this.dbname = database; }
	String getURL () {return this.url;}
	String getUser() {return this.user;}
	String getDatabase () { return this.dbname; }

			// a generic error handler
			
	void handleException (SQLException e) {
	    Logger lgr = Logger.getLogger(SQLOrgLoader.class.getName());
        lgr.log(Level.SEVERE, e.getMessage(), e);	
    }
		
			// database access functions
			
	void changeConnection (String new_url, String new_user, String new_password, boolean doConnect) {
		setURL(new_url);
		setUser(new_user);
		setPassword(new_password);
		if (doConnect) {
			boolean result = connect();
			if (result == false) {
				System.out.println("Could not connect to database!");
				System.exit(0); // TBD - implement soft crash on the UI.
			}
		}

	}
	
	boolean connect() {
		try { 
		System.out.println(url + user + password);
		con = DriverManager.getConnection(url, user, password); 
		//st = con.createStatement();  // init the statement
		con.setAutoCommit(false);
		}
	        catch (SQLException e) {
            handleException(e);
            return false;
    	    }    	    
	return true; 
    }
   
    void commit() {		
    	try {
    		con.commit(); }
    		catch (SQLException e) {
    			handleException (e); }
    }

    void setAutoCommit(boolean setting){	
    	try {
    	con.setAutoCommit(setting);
    	}
    	catch (SQLException e) {
    		handleException(e);
    	}
    }
	
	void close() {
	    try {
                if (rs != null) {  
                    rs.close();	
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } 
            catch (SQLException e) {
                handleException(e);
            }
    }
    

/************	I/O Methods		**********/
    
    			// 	READ	//
    			
    
    public Binding readAllByType(String type) { 
	String query = "select * from " + type;    
	
    	try {
    		st = con.createStatement();
    		rs = st.executeQuery(query);
			while (rs.next()) {    		
		    	switch (type){
    			case "person":	// tbd - add other types
    				String pid  = rs.getString("pid"); // it is better to reference col #
    				String first = rs.getString("first");
    				String middle = rs.getString("middle");
    				String last = rs.getString("last");
    				System.out.println(pid + "\t" + first + "\t" + middle + "\t" + last);   	
    				break;
    			default:
    				System.out.println("Invalid read type.");
    				break;
    			}	
    		}
    	}
    	catch (SQLException e) {
    		handleException(e);
    	}
    
    return new Binding();
    } // not implemented
    
    
    public Binding readByType(String type, String id) {		// partially implemented
    String query = "select * from " + type + " where pid=?";
    	try {
    		ps = con.prepareStatement(query);
    		ps.setString(1,id);
    		rs = ps.executeQuery();
    		if (rs.first() == true)
    		{
	    		String pid  = rs.getString("pid"); // it is better to reference col #
    			String first = rs.getString("first");
    			String middle = rs.getString("middle");
    			String last = rs.getString("last");
    			System.out.println(pid + "\t" + first + "\t" + middle + "\t" + last);   		
    		}
    	}
    	catch (SQLException e) {
    		handleException(e);
    	}
    	return new Binding();
	}
    		


				// 	WRITE	//
				
	public boolean ifExists(String type, String id) {
	// All assigned relation ids should be 1:1 assignments
	
	Relations r = Relations.valueOf(type.toUpperCase());
	String statement = null;
		switch (r) {
			case AREA: statement = "select aid from area where aid=?"; break;
			case ASSIGNS: statement = "select pid from assigns where pid=?"; break;
			case PERSON: statement = "select pid from person where pid=?"; break;
			case PLACES: statement = "select aid from places where aid=?"; break;
			case SEAT: statement = "select sid from seat where sid=?"; break;
			default: System.out.println("Error:  ifExists() requires a valid type.");
			break;
		}
		try {
				ps = con.prepareStatement(statement);
				ps.setString(1, id);
				rs = ps.executeQuery();
			if (rs.next() == true) {
				System.out.println("Error:  ID already exists!  Type is " 
					+ type + " and id is " + id);
				return true;
			}
			else
			{ return false; }
		}
		catch (SQLException e) {
			handleException(e);
			return false;
		}
	}
	
	private PreparedStatement createInsertStatement(String type) {

		Relations r = Relations.valueOf(type.toUpperCase());
				
			try {
		
		switch (r) {
		
		case AREA:
			ps = con.prepareStatement("INSERT INTO area VALUES (?, ?, ?)");
			break;
		case ASSIGNS:
			ps = con.prepareStatement("INSERT INTO assigns VALUES (?, ?)");		
			break;
		case PERSON:
			ps = con.prepareStatement("INSERT INTO person VALUES (?, null, null, null)");
			break;
		case PLACES:
			ps = con.prepareStatement("INSERT INTO places VALUES (?, ?)");
			break;
		case SEAT:
			ps = con.prepareStatement("INSERT INTO seat VALUES (?, ?, ?, ?)");
			break;
		default:
			System.out.println("Error:  Incorrect relation.");
			break;
		}
			
			}
			
			catch (SQLException e) { 
				handleException(e);
			}
			
		return ps;
	}
			// insert functions
	
	public boolean addPerson(Person p) {
	try 
	{
	
		if (ifExists("person",p.getName()))
		{
			System.out.println("Error:  PID already exists!  PID is " + p.getName());
			return true;
		}
		else
		{
			ps = createInsertStatement("person");
			ps.setString(1, p.getName());
			ps.executeUpdate();
			commit();
		}
	}
		catch (SQLException e) {
			handleException(e);
			return false;
		}
		return true;
	}
	
	
/*

+----------------+
| Tables_in_test |
+----------------+
| area           |	aid, width, height
| assigns        |	pid, sid	
| person         |	pid, first, middle, last
| places         |	aid, sid	
| seat           |	sid, x, y, angle
+----------------+
	INSERT INTO area values (aid, width, height)
	DELELE FROM area where aid = (aid to delete) 
	UPDATE area SET attribute=value, attribute2=value2 where aid = (aid to update)


*/

  
}


