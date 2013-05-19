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
	nation=1;
	patk=10;
	matk=20;
	pdef=5;
	mdef=5;
	portrait=new ImageIcon();
	fullportrait=new ImageIcon();
	this.setcoordinate(new Mycoordinate(1,0));
	name="Holy archer";
	subscription="<b><font color='brown'>(Player)</font></b><br/>Archers that are skillful in range combat<br/>";
	portrait.setImage(ImageIO.read(new File("./images/archerportrait.jpg")));
	fullportrait.setImage(ImageIO.read(new File("./images/archer.jpg")));
	action.add((Myskill)new Light_Arrow());
	action.add((Myskill)new Bullshot());
	action.add((Myskill)new Explode_Arrow());
    }
}