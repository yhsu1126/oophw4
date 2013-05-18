import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import ntu.csie.oop13spring.POOAction;
import ntu.csie.oop13spring.POOArena;
import ntu.csie.oop13spring.POOCoordinate;


public class harcher extends Mypet{
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
	portrait=new ImageIcon();
	fullportrait=new ImageIcon();
	for(i=0;i<32;i++)
	{
	    turn[i]=0;
	}
	this.setcoordinate(new Mycoordinate(1,0));
	name="Holy archer";
	subscription="Archers that are skillful in range combat<br/>";
	portrait.setImage(ImageIO.read(new File("./images/archerportrait.jpg")));
	fullportrait.setImage(ImageIO.read(new File("./images/archer.jpg")));
    }
}