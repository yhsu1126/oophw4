import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import ntu.csie.oop13spring.POOAction;
import ntu.csie.oop13spring.POOArena;
import ntu.csie.oop13spring.POOCoordinate;
import ntu.csie.oop13spring.POOPet;


public class harcher extends POOPet{
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
    public harcher() throws IOException
    {
	int i;
	hp=100;
	maxhp=100;
	agility=100;
	ap=6;
	mp=4;
	maxap=6;
	maxmp=4;
	state=0;
	nation=1;
	patk=20;
	matk=0;
	pdef=5;
	mdef=5;
	for(i=0;i<32;i++)
	{
	    turn[i]=0;
	}
	name="<html>Holy&nbsp;archer</html>";
	subscription="<html>Archers&nbsp;that&nbsp;are&nbsp;skillful&nbsp;in&nbsp;range&nbsp;combat</html>";
	portrait.setImage(ImageIO.read(new File("./images/archer.jpg")));
	fullportrait.setImage(ImageIO.read(new File("./images/archerportrait.jpg")));
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
