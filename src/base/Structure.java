package base;

public interface Structure {
	void setX(int x);
	void setY(int y);
	/**
  *setAngle(int angle). 
	*The angle parameter represents whole degrees. 180 represents a halfway turn.
  *360 is effectively the same as 0.
	*
	*/
	void setAngle(int angle);
	int getX();
	int getY();
	int getAngle();
}
