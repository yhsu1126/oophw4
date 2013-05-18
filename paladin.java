import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import ntu.csie.oop13spring.POOAction;
import ntu.csie.oop13spring.POOArena;
import ntu.csie.oop13spring.POOCoordinate;


public class paladin extends Mypet{
    public paladin() throws IOException
    {
	int i;
	hp=100;
	maxhp=100;
	agility=80;
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
	portrait=new ImageIcon();
	fullportrait=new ImageIcon();
	name="Peacebringer";
	for(i=0;i<32;i++)
	{
	    turn[i]=0;
	}
	this.setcoordinate(new Mycoordinate(3,0));
	name="Holy paladin";
	subscription="Knight that know the knowledge of<br/>light magic<br/>";
	portrait.setImage(ImageIO.read(new File("./images/paladinportrait.jpg")));
	fullportrait.setImage(ImageIO.read(new File("./images/paladin.jpg")));
    }
}