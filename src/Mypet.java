import java.util.ArrayList;

import javax.swing.ImageIcon;

import ntu.csie.oop13spring.POOAction;
import ntu.csie.oop13spring.POOArena;
import ntu.csie.oop13spring.POOCoordinate;
import ntu.csie.oop13spring.POOPet;


class Mypet extends POOPet {
    protected int hp,maxhp,ap,maxap,mp,maxmp,agility,nation,patk,matk,pdef,mdef,dead;
    protected int[] stateaccu=new int[32];
    public ArrayList<Myskill> action=new ArrayList<Myskill>();
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
    public int getnation()
    {
	return this.nation;
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
    public void setdead(int a)
    {
	this.dead=a;
    }
    public int getdead()
    {
	return this.dead;
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
    public int getmaxmp()
    {
	return this.maxmp;
    }
    public void setagility(int a)
    {
	this.agility=a;
    }
    public int getagility()
    {
	return this.agility;
    }
    public Mycoordinate getcoordinate()
    {
	return this.pos;
    }
    public void setcoordinate(Mycoordinate a)
    {
	pos=a;
    }
    public String getname()
    {
	return this.name;
    }
    public ImageIcon getportrait()
    {
	return this.portrait;
    }
    public int getpatk()
    {
	return this.patk;
    }
    public int getmatk()
    {
	return this.matk;
    }
    public int getmaxhp()
    {
	return this.maxhp;
    }
    public String showdescrption()
    {
	String ans;
	ans="<html><font color='black'>"+this.name+"<br/>"+this.subscription;
	if(this.maxhp/this.hp>=2)
	{
	    ans+="hp: <font color='red'>"+this.hp+" / "+this.maxhp+"</font><br/>";
	}
	else if(this.maxhp>this.hp)
	{
	    ans+="hp: <font color='yellow'>"+this.hp+" / "+this.maxhp+"</font><br/>";
	}
	else
	{
	    ans+="hp: <font color='black'>"+this.hp+" / "+this.maxhp+"</font><br/>";
	}
	if(this.ap==0)
	{
	    ans+="<font color='blue'>ap: <font color='red'>"+this.ap+" / "+this.maxap+"</font></font><br/>";
	}
	else
	{
	    ans+="<font color='blue'>ap: "+this.ap+" / "+this.maxap+"</font><br/>";
	}
	if(this.mp==0)
	{
	    ans+="<font color='green'>mp: <font color='red'>"+this.mp+" / "+this.maxmp+"</font></font><br/>";
	}
	else
	{
	    ans+="<font color='green'>mp: "+this.mp+" / "+this.maxmp+"</font><br/>";
	}
	ans+="</font></html>";
	return ans;
    }
}
