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
}
