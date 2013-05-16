import javax.swing.ImageIcon;

import ntu.csie.oop13spring.POOAction;
import ntu.csie.oop13spring.POOArena;
import ntu.csie.oop13spring.POOCoordinate;
import ntu.csie.oop13spring.POOPet;


class Mypet extends POOPet {
    protected int hp,maxhp,ap,maxap,mp,maxmp,state,agility,nation,patk,matk,pdef,mdef;
    protected int[] turn =new int[32];
    String subscription,name;
    ImageIcon portrait, fullportrait;
    private Mycoordinate pos;
    @Override
    protected POOAction act(POOArena arena) {
	// TODO Auto-generated method stub
	return null;
    }
    @Override
    protected POOCoordinate move(POOArena arena) {
	// TODO Auto-generated method stub
	return null;
    }
    public void setstate(int a)
    {
	this.state=a;
    }
    public int getstate()
    {
	return this.state;
    }
    public void setap(int a)
    {
	this.ap=a;
    }
    public int getap()
    {
	return this.ap;
    }
    public void sethp(int a)
    {
	this.hp=a;
    }
    public int gethp()
    {
	return this.hp;
    }
    public void setmaxap(int a)
    {
	this.maxap=a;
    }
    public int getmaxap()
    {
	return this.maxap;
    }
    public void setmp(int a)
    {
	this.mp=a;
    }
    public int getmp()
    {
	return this.mp;
    }
    public void setmaxmp(int a)
    {
	this.maxmp=a;
    }
    public int maxmp()
    {
	return this.maxmp;
    }
}
