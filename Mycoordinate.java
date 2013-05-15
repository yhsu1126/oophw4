import ntu.csie.oop13spring.POOCoordinate;


public class Mycoordinate extends POOCoordinate {
    Mycoordinate(int tx, int ty)
    {
	super();
	this.x=tx;
	this.y=ty;
    }
    public boolean equals(POOCoordinate other)
    {
	return true;
    }
    public int getx()
    {
	return this.x;
    }
    public int gety()
    {
	return this.y;
    }
    public boolean myequals(Mycoordinate other)
    {
	if(other.getx()==this.getx() && other.gety()==this.gety())
	{
	    return true;
	}
	else
	{
	    return false;
	}
    }
}
