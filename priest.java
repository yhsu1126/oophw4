import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import ntu.csie.oop13spring.POOAction;
import ntu.csie.oop13spring.POOArena;
import ntu.csie.oop13spring.POOCoordinate;


public class priest extends Mypet{
    public priest() throws IOException
    {
	int i;
	hp=70;
	maxhp=70;
	agility=50;
	ap=6;
	mp=4;
	maxap=6;
	maxmp=4;
	nation=1;
	patk=10;
	matk=30;
	pdef=5;
	mdef=5;
	portrait=new ImageIcon();
	fullportrait=new ImageIcon();
	this.setcoordinate(new Mycoordinate(5,0));
	name="Holy priest";
	subscription="Priest that holds the secret of<br/>elemental magic<br/>";
	portrait.setImage(ImageIO.read(new File("./images/priestportrait.jpg")));
	fullportrait.setImage(ImageIO.read(new File("./images/priest.jpg")));
	action.add((Myskill)new heal());
	action.add((Myskill)new Groupheal());
	action.add((Myskill)new Smite());
    }
}