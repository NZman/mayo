package base;

public interface SQLEditor{

    // not yet implemented
    
// reading
	Binding read(String type, int position) // e.g., ("Person", 1)
	Binding batchRead(String type, int start, int finish)
	
// delete
	boolean delete(String type, int position)
	boolean batchDelete(String type, int start, int finish)

// modify 
	void modify(Binding b) 	// to be considered

// count
	int count(String type)
	int countAll() 	

}

// we need to be able to get the position and other values when we know some of them.