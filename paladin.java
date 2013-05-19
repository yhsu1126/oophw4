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
	hp=150;
	maxhp=150;
	agility=80;
	ap=6;
	mp=4;
	maxap=6;
	maxmp=4;
	nation=1;
	patk=30;
	matk=10;
	pdef=5;
	mdef=5;
	portrait=new ImageIcon();
	fullportrait=new ImageIcon();
	this.setcoordinate(new Mycoordinate(3,0));
	name="Holy paladin";
	subscription="Knight that know the knowledge of<br/>light magic<br/>";
	portrait.setImage(ImageIO.read(new File("./images/paladinportrait.jpg")));
	fullportrait.setImage(ImageIO.read(new File("./images/paladin.jpg")));
	action.add((Myskill)new Swordattack());
	action.add((Myskill)new Jump());
	action.add((Myskill)new light_heal());
    }
}