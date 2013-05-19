import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.text.DefaultCaret;

import ntu.csie.oop13spring.POOArena;
import ntu.csie.oop13spring.POOCoordinate;
import ntu.csie.oop13spring.POOPet;


public class Myarena extends POOArena{
    private JFrame frame;
    private Board place;
    private ArrayList<JButton> skillbutton = new ArrayList<JButton>();
    private JLabel lportrait,infopanel,skillpanel;
    private JTextArea log;
    private JScrollPane gameprogress;
    private ImageIcon fullportrait;
    private String subscription;
    private int state,finished,index,mindamage,maxdamage,minrange,maxrange,pattern,cost,type,special,shift;
    private int[] nationtotal=new int[2];
    private Mycoordinate focuspos,cursorpos;
    private ArrayList<Mypet> Mypetlist= new ArrayList<Mypet>();
    private boolean end;
    private String skillname;
    Random randonumber=new Random();
    GCFrame scene;
    String gamelog="Welcome to POOArena!!\n When the player kill the boss\n or boss kill all the players\n the game is over\n Have Fun !!\n";
    public Myarena() throws IOException
    {
	super();
	frame = new JFrame();
	end=true;
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	place=new Board(192,192,192,200,1);
	int i,j;
	frame = new JFrame("Simple RPG");
	frame.setLayout(new BorderLayout());
	scene=new GCFrame();
	frame.setContentPane(scene);
	frame.setLayout(new GridBagLayout());
	GridBagConstraints c1 = new GridBagConstraints();
	// add player state stuff
	// portrait
	c1.gridwidth = 3;
	c1.gridheight = 4;
	c1.anchor = GridBagConstraints.WEST;
	c1.ipadx=10;
	c1.ipady=10;
	c1.gridx=0;
	c1.gridy=0;
	lportrait=new JLabel();
	lportrait.setPreferredSize(new Dimension(215,300));
	lportrait.setOpaque(false);
	lportrait.setBackground(new Color(192,192,192,225));
	frame.add(lportrait,c1);
	// intro... state
	c1.gridwidth = 3;
	c1.gridheight = 4;
	c1.anchor = GridBagConstraints.WEST;
	c1.ipadx=10;
	c1.ipady=0;
	c1.gridx=0;
	c1.gridy=4;
	infopanel=new JLabel(){ public void paintComponent(Graphics g) {
	    if(fullportrait!=null)
	    {
	        g.drawImage(fullportrait.getImage(), 0, 0, null);
	    }
	        super.paintComponent(g);
	      }};
	infopanel.setVerticalAlignment(SwingConstants.TOP);
	infopanel.setPreferredSize(new Dimension(215,300));
	infopanel.setOpaque(true);
	infopanel.setBackground(new Color(192,192,192,150));
	frame.add(infopanel,c1);
	//add the board
	c1.gridwidth = 8;
	c1.gridheight = 8;
	c1.anchor = GridBagConstraints.CENTER;
	c1.gridx=4;
	c1.gridy=0;
	c1.ipadx=10;
	c1.ipady=10;
	/*JLabel upper=Board.CreateBoard(25, 25, 25, 185,0);
	JLabel lower=Board.CreateBoard(192, 192, 192, 185,1);*/
	//layeredPane.add(lower, 0);
	frame.add(place,c1);
	place.setarena(this);
	//add choose panel
	skillpanel = new JLabel();
	c1.gridwidth = 3;
	c1.gridheight = 4;
	c1.anchor = GridBagConstraints.EAST;
	c1.gridx=11;
	c1.gridy=0;
	c1.ipadx=10;
	c1.ipady=10;
	skillpanel.setPreferredSize(new Dimension(215,290));
	skillpanel.setOpaque(true);
	skillpanel.setBackground(new Color(192,192,192,225));
	frame.add(skillpanel,c1);
	//add game scrollpanel
	c1.gridwidth = 3;
	c1.gridheight = 4;
	c1.anchor = GridBagConstraints.EAST;
	c1.gridx=11;
	c1.gridy=4;
	c1.ipadx=10;
	c1.ipady=10;
	log=new JTextArea();
	DefaultCaret caret = (DefaultCaret)log.getCaret();
	caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
	log.setText(gamelog);
	log.setOpaque(true);
	log.setVisible(true);
	gameprogress=new JScrollPane(log);
	gameprogress.setPreferredSize(new Dimension(215,300));
	gameprogress.setOpaque(true);
	gameprogress.setBackground(new Color(192,192,192,200));
	frame.add(gameprogress,c1);
	frame.setSize(1258, 710);
	frame.setResizable(false);
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);
	nationtotal[0]=0;
	nationtotal[1]=0;
    }
    public boolean fight()
    {
	if(end==false)
	{
	    try {
		Thread.sleep(3000);
	    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		System.exit(0);
	    }
	    System.exit(0);
	}
	return end;
    }
    
    @Override
    public void addPet(POOPet p)
    {
	Mypetlist.add((Mypet)p);
    }
    public void show()
    {
	Collections.sort(Mypetlist,new petOrder());
	int i;
	// finish placing
	for(i=0;i<Mypetlist.size();i++)
	{
	    place.placeicon(Mypetlist.get(i).getcoordinate().getx(), Mypetlist.get(i).getcoordinate().gety(), Mypetlist.get(i).getportrait(),i+1);
	    nationtotal[Mypetlist.get(i).getnation()]=nationtotal[Mypetlist.get(i).getnation()]+Mypetlist.get(i).getmaxhp();
	}
	index=0;
	frame.revalidate();
	JLabel paneltitle=new JLabel();
	paneltitle.setAlignmentX(Component.CENTER_ALIGNMENT);
	skillpanel.setLayout(new BoxLayout(skillpanel,BoxLayout.Y_AXIS));
	skillpanel.add(paneltitle);
	while(end==true)
	{
	    if(Mypetlist.get(index).getdead()==1)
	    {
		index++;
		index=index%Mypetlist.size();
		continue;
	    }
	    focuspos=Mypetlist.get(index).getcoordinate();
	    paneltitle.setText("Skill of "+Mypetlist.get(index).getname());
	    place.buttonmanager[focuspos.getx()*8+focuspos.gety()].setBorder(new LineBorder(Color.GREEN, 4));
	    this.appendlog(Mypetlist.get(index).getname()+" turn\n");
	    this.fullportrait=Mypetlist.get(index).fullportrait;
	    this.lportrait.setIcon(fullportrait);
	    this.infopanel.setText(Mypetlist.get(index).showdescrption());
	    this.infopanel.repaint();
	    for(i=0;i<skillbutton.size();i++)
	    {
		skillpanel.remove(skillbutton.get(i));
	    }
	    skillbutton.clear();
	    skillpanel.revalidate();
	    skillpanel.repaint();
	    skillpanel.invalidate();
	    if(Mypetlist.get(index).action.size()!=0)
	    {
		for(i=0;i<Mypetlist.get(index).action.size();i++)
		{
		    JButton innertmp=new JButton(Mypetlist.get(index).action.get(i).name);
		    innertmp.setToolTipText(Mypetlist.get(index).action.get(i).description);
		    innertmp.setAlignmentX(Component.CENTER_ALIGNMENT);
		    final Myskill tmpskill=Mypetlist.get(index).action.get(i);
		    if(tmpskill.special==4)
		    {
			if(Mypetlist.get(index).getmaxhp()/Mypetlist.get(index).gethp()<tmpskill.minmulti)
			{
			    continue;
			}
		    }
		    innertmp.addActionListener(new ActionListener(){

			    @Override
			    public void actionPerformed(ActionEvent e) {
				if(Mypetlist.get(place.buttonmanager[focuspos.getx()*8+focuspos.gety()].getstate()-1).getap()>=tmpskill.cost)
				{
				    changetostate2(tmpskill.minmulti,tmpskill.maxmulti,tmpskill.pattern,tmpskill.minrange,tmpskill.maxrange,tmpskill.cost,tmpskill.type,tmpskill.name,tmpskill.special);
				}
				else
				{
				    appendlog("Not enough action point to do the action\n");
				    Object tmp=e.getSource();
				    if(tmp instanceof JButton)
				    {
					((JButton) tmp).setEnabled(false);
				    }
				}
			    }
			});
			skillpanel.add(innertmp);
			skillbutton.add(innertmp);
			skillpanel.add(Box.createRigidArea(new Dimension(30,0)));
		    }
		}
		JButton tmp=new JButton("Cancel");
		tmp.setToolTipText("Cancel the action");
		tmp.setAlignmentX(Component.CENTER_ALIGNMENT);
		tmp.addActionListener(new ActionListener(){

		    @Override
		    public void actionPerformed(ActionEvent e) {
			if(state==2)
			{
			place.removeblanket();
			state=1;
			}
		    }
		});
		skillpanel.add(tmp);
		skillbutton.add(tmp);
		tmp=new JButton("End turn");
		tmp.setToolTipText("End the turn immediately");
		tmp.setAlignmentX(Component.CENTER_ALIGNMENT);
		tmp.addActionListener(new ActionListener(){

		    @Override
		    public void actionPerformed(ActionEvent e) {
			place.removeblanket();
			state=1;
			setfinished(1);
		    }
		    
		});
		skillpanel.add(tmp);
		skillbutton.add(tmp);
		skillpanel.revalidate();
	    finished=0;
	    state=1;
	    // state 1 waiting for command, state 2 doing skill;
	    while(finished==0)
	    {
		if(Mypetlist.get(index).getmp()==0 && Mypetlist.get(index).getap()==0)
		{
		    System.out.printf("");
		    finished=1;
		}
		else
		{
		    System.out.printf("");
		}
	    }
	    Mypetlist.get(index).setmp(Mypetlist.get(index).getmaxmp());
	    Mypetlist.get(index).setap(Mypetlist.get(index).getmaxap());
	    place.buttonmanager[focuspos.getx()*8+focuspos.gety()].setBorder(UIManager.getBorder("Button.border"));
	    index++;
	    index%=Mypetlist.size();
	}
	//end=false;
    }
    public POOCoordinate getPosition(POOPet p)
    {
	return null;
    }
    public void setfinished(int a)
    {
	this.finished=a;
    }
    public void appendlog(String str)
    {
	log.append(str);
    }
    public void receivedclick(Mycoordinate a)
    {
	if(this.state==1)
	{
	    int i,j;
	    if(place.buttonmanager[a.getx()*8+a.gety()].getstate()==0)
	    {
		int dis=this.Manhattan(a, this.focuspos);
		if(dis<=this.Mypetlist.get(index).getmp())
		{
		    this.appendlog("move to "+"( "+a.getx()+" , "+a.gety()+" )"+"\n");
		    place.removeicon(focuspos.getx(), focuspos.gety());
		    place.placeicon(a.getx(), a.gety(), Mypetlist.get(index).getportrait(),this.index+1);
		    place.buttonmanager[focuspos.getx()*8+focuspos.gety()].setBorder(UIManager.getBorder("Button.border"));
		    this.focuspos=a;
		    place.buttonmanager[focuspos.getx()*8+focuspos.gety()].setBorder(new LineBorder(Color.GREEN,4));
		    Mypetlist.get(index).setcoordinate(focuspos);
		    Mypetlist.get(index).setmp(this.Mypetlist.get(index).getmp()-dis);
		    if(fullportrait==Mypetlist.get(index).fullportrait)
		    {
			infopanel.setText(Mypetlist.get(index).showdescrption());
		    }
		}
		else
		{
		    this.appendlog("Illegal move, the point is too far away\n");
		}
	    }
	    else
	    {
		//this.appendlog("Illegal move, a pet is at there\n");
		this.appendlog("Illegal move, someone is at that spot\n");
	    }
	}
	else if(this.state==2)
	{
	    int i,j,dis=this.Manhattan(this.cursorpos, this.focuspos),damage;
	    if(dis<=maxrange && dis>=minrange)
	    {
		if(special==1)
		{
			if(place.buttonmanager[cursorpos.getx()*8+cursorpos.gety()].getstate()>0)
			{
			    appendlog("You can't teleport to the spot");
			    return;
			}
			else
			{
			    place.removeicon(focuspos.getx(), focuspos.gety());
			    place.placeicon(a.getx(), a.gety(), Mypetlist.get(index).getportrait(),this.index+1);
			    place.buttonmanager[focuspos.getx()*8+focuspos.gety()].setBorder(UIManager.getBorder("Button.border"));
			    this.focuspos=a;
			    place.buttonmanager[focuspos.getx()*8+focuspos.gety()].setBorder(new LineBorder(Color.GREEN,4));
			    Mypetlist.get(index).setcoordinate(focuspos);
			}
		}
		this.appendlog("use "+this.skillname+" at "+"( "+a.getx()+" , "+a.gety()+" )"+"\n");
		int multi=this.randonumber.nextInt(this.maxdamage-this.mindamage)+this.mindamage;
		int patk=this.Mypetlist.get(index).getpatk(),matk=this.Mypetlist.get(index).getmatk();
		if(type==1) // patk
		{
		    damage=patk*multi/100;
		}
		else if(type==2) //matk
		{
		    damage=matk*multi/100;
		}
		else if (type==3) // heal
		{
		    damage=matk*multi/100*-1;
		}
		else
		{
		    damage=0;
		}
		if(pattern==1)
		{
		    if(place.buttonmanager[cursorpos.getx()*8+cursorpos.gety()].getstate()>0)
		    {
			if(this.special!=4)
			{
			    if(type==1)
			    {
				this.appendlog("dealing "+damage+" physical damage to \n"+Mypetlist.get(this.place.buttonmanager[cursorpos.getx()*8+cursorpos.gety()].getstate()-1).getname()+"\n");
			    	Mypetlist.get(this.place.buttonmanager[cursorpos.getx()*8+cursorpos.gety()].getstate()-1).sethp(Mypetlist.get(this.place.buttonmanager[cursorpos.getx()*8+cursorpos.gety()].getstate()-1).gethp()-damage);
			   
			    }
			    else if(type==2)
			    {
				this.appendlog("dealing "+damage+" magical damage to \n"+Mypetlist.get(this.place.buttonmanager[cursorpos.getx()*8+cursorpos.gety()].getstate()-1).getname()+"\n");
				Mypetlist.get(this.place.buttonmanager[cursorpos.getx()*8+cursorpos.gety()].getstate()-1).sethp(Mypetlist.get(this.place.buttonmanager[cursorpos.getx()*8+cursorpos.gety()].getstate()-1).gethp()-damage);
			    }
			    else if(type==3)
			    {
				this.appendlog("recover "+damage*-1+" hp to \n"+Mypetlist.get(this.place.buttonmanager[cursorpos.getx()*8+cursorpos.gety()].getstate()-1).getname()+"\n");
				Mypetlist.get(this.place.buttonmanager[cursorpos.getx()*8+cursorpos.gety()].getstate()-1).sethp(Mypetlist.get(this.place.buttonmanager[cursorpos.getx()*8+cursorpos.gety()].getstate()-1).gethp()-damage);
				if(Mypetlist.get(this.place.buttonmanager[cursorpos.getx()*8+cursorpos.gety()].getstate()-1).gethp()>Mypetlist.get(this.place.buttonmanager[cursorpos.getx()*8+cursorpos.gety()].getstate()-1).getmaxhp())
				{
				    Mypetlist.get(this.place.buttonmanager[cursorpos.getx()*8+cursorpos.gety()].getstate()-1).sethp(Mypetlist.get(this.place.buttonmanager[cursorpos.getx()*8+cursorpos.gety()].getstate()-1).getmaxhp());
				}
			    }
			    if(Mypetlist.get(this.place.buttonmanager[cursorpos.getx()*8+cursorpos.gety()].getstate()-1).gethp()<=0)
			    {
				this.death(cursorpos.getx(), cursorpos.gety(), Mypetlist.get(this.place.buttonmanager[cursorpos.getx()*8+cursorpos.gety()].getstate()-1));
			    }
			}
			else
			{
			    this.appendlog("Set a curse on\n"+Mypetlist.get(this.place.buttonmanager[cursorpos.getx()*8+cursorpos.gety()].getstate()-1).getname()+"\n");
			    Mypetlist.get(this.place.buttonmanager[cursorpos.getx()*8+cursorpos.gety()].getstate()-1).setap(Mypetlist.get(this.place.buttonmanager[cursorpos.getx()*8+cursorpos.gety()].getstate()-1).getap()/mindamage);
			    Mypetlist.get(this.place.buttonmanager[cursorpos.getx()*8+cursorpos.gety()].getstate()-1).setmp(Mypetlist.get(this.place.buttonmanager[cursorpos.getx()*8+cursorpos.gety()].getstate()-1).getmp()/mindamage);
			}
		    }
		    else
		    {
			damage=0;
			if(type==1)
			{
			    this.appendlog("dealing 0 physical damage to the air!!!!\n");
			}
			else if(type==2)
			{
			    this.appendlog("dealing 0 magical damage to the air!!!!!\n");
			}
			else if(type==3)
			{
			    this.appendlog("recover 0 hp for the air!!!!\n");
			}
		    }
		}
		else if(pattern==2)
		{
		    int[] xshift={0,0,-1,1,0};
		    int[] yshift={0,-1,0,0,1};
		    int x,y,total=0;
		    for(i=0;i<5;i++)
		    {
			x=cursorpos.getx()+xshift[i];
			y=cursorpos.gety()+yshift[i];
			if(x<8 && x>=0 && y<8 && y>=0)
			{
			    if(place.buttonmanager[x*8+y].getstate()>0)
			    {
				if(type==1 && place.buttonmanager[x*8+y].getstate()-1 !=index)
				{
				    total=1;
				    this.appendlog("dealing "+damage+" physical damage to \n"+Mypetlist.get(this.place.buttonmanager[x*8+y].getstate()-1).getname()+"\n");
				    Mypetlist.get(this.place.buttonmanager[x*8+y].getstate()-1).sethp(Mypetlist.get(this.place.buttonmanager[x*8+y].getstate()-1).gethp()-damage);
				}
				else if(type==2 && place.buttonmanager[x*8+y].getstate()-1 !=index)
				{
				    total=1;
				    this.appendlog("dealing "+damage+" magical damage to \n"+Mypetlist.get(this.place.buttonmanager[x*8+y].getstate()-1).getname()+"\n");
				    Mypetlist.get(this.place.buttonmanager[x*8+y].getstate()-1).sethp(Mypetlist.get(this.place.buttonmanager[x*8+y].getstate()-1).gethp()-damage);
				}
				else if(type==3)
				{
				    total=1;
				    this.appendlog("recover "+damage*-1+" hp to \n"+Mypetlist.get(this.place.buttonmanager[x*8+y].getstate()-1).getname()+"\n");
				    Mypetlist.get(this.place.buttonmanager[x*8+y].getstate()-1).sethp(Mypetlist.get(this.place.buttonmanager[x*8+y].getstate()-1).gethp()-damage);
				    if(Mypetlist.get(this.place.buttonmanager[x*8+y].getstate()-1).gethp()>Mypetlist.get(this.place.buttonmanager[x*8+y].getstate()-1).getmaxhp())
				    {
					Mypetlist.get(this.place.buttonmanager[x*8+y].getstate()-1).sethp(Mypetlist.get(this.place.buttonmanager[x*8+y].getstate()-1).getmaxhp());
				    }
				}
				if(Mypetlist.get(this.place.buttonmanager[x*8+y].getstate()-1).gethp()<=0)
				    {
					this.death(x, y, Mypetlist.get(this.place.buttonmanager[x*8+y].getstate()-1));
				    }
			    }
			}
		    }
		    if(total==0)
		    {
			damage=0;
			if(type==1)
			{
			    this.appendlog("dealing 0 physical damage to\n"+"WHOLE LOT OF air!!!!\n");
			}
			else if(type==2)
			{
			    this.appendlog("dealing 0 magical damage to\n"+"WHOLE LOT OF air!!!!!\n");
			}
			else if(type==3)
			{
			    this.appendlog("recover 0 hp for\n"+"WHOLE LOT OF air!!!!\n");
			}
		    }
		}
		else if(pattern==3)
		{
		    int[] xshift={0,-1,0,1,-1,1,-1,0,1};
		    int[] yshift={0,-1,-1,-1,0,0,1,1,1};
		    int x,y,total=0;
		    for(i=0;i<9;i++)
		    {
			x=cursorpos.getx()+xshift[i];
			y=cursorpos.gety()+yshift[i];
			if(x<8 && x>=0 && y<8 && y>=0)
			{
			    if(place.buttonmanager[x*8+y].getstate()>0)
			    {
				total=1;
				if(type==1)
				{
				    this.appendlog("dealing "+damage+" physical damage to \n"+Mypetlist.get(this.place.buttonmanager[x*8+y].getstate()-1).getname()+"\n");
				    Mypetlist.get(this.place.buttonmanager[x*8+y].getstate()-1).sethp(Mypetlist.get(this.place.buttonmanager[x*8+y].getstate()-1).gethp()-damage);
				}
				else if(type==2)
				{
				    this.appendlog("dealing "+damage+" magical damage to \n"+Mypetlist.get(this.place.buttonmanager[x*8+y].getstate()-1).getname()+"\n");
				    Mypetlist.get(this.place.buttonmanager[x*8+y].getstate()-1).sethp(Mypetlist.get(this.place.buttonmanager[x*8+y].getstate()-1).gethp()-damage);
				}
				else if(type==3)
				{
				    this.appendlog("recover "+damage*-1+" hp to \n"+Mypetlist.get(this.place.buttonmanager[x*8+y].getstate()-1).getname()+"\n");
				    Mypetlist.get(this.place.buttonmanager[x*8+y].getstate()-1).sethp(Mypetlist.get(this.place.buttonmanager[x*8+y].getstate()-1).gethp()-damage);
				    if(Mypetlist.get(this.place.buttonmanager[x*8+y].getstate()-1).gethp()>Mypetlist.get(this.place.buttonmanager[x*8+y].getstate()-1).getmaxhp())
				    {
					Mypetlist.get(this.place.buttonmanager[x*8+y].getstate()-1).sethp(Mypetlist.get(this.place.buttonmanager[x*8+y].getstate()-1).getmaxhp());
				    }
				}
				if(Mypetlist.get(this.place.buttonmanager[x*8+y].getstate()-1).gethp()<=0)
				    {
					this.death(x, y, Mypetlist.get(this.place.buttonmanager[x*8+y].getstate()-1));
				    }
			    }
			}
		    }
		    if(total==0)
		    {
			damage=0;
			if(type==1)
			{
			    this.appendlog("dealing 0 physical damage to\n"+"WHOLE LOT OF air!!!!\n");
			}
			else if(type==2)
			{
			    this.appendlog("dealing 0 magical damage to\n"+"WHOLE LOT OF air!!!!!\n");
			}
			else if(type==3)
			{
			    this.appendlog("recover 0 hp for\n"+"WHOLE LOT OF air!!!!\n");
			}
		    }
		}
		if(damage>0 && special==3)
		{
		    int leech=this.randonumber.nextInt(damage);
		    this.appendlog("Steal "+leech+"Life\n");
		    Mypetlist.get(index).sethp(Mypetlist.get(index).gethp()+leech);
		    if(Mypetlist.get(index).gethp()>Mypetlist.get(index).getmaxhp())
		    {
			Mypetlist.get(index).sethp(Mypetlist.get(index).getmaxhp());
		    }
		}
		Mypetlist.get(index).setap(Mypetlist.get(index).getap()-cost);
		this.place.removeblanket();
		this.state=1;
		if(fullportrait==Mypetlist.get(index).fullportrait)
		{
		    infopanel.setText(Mypetlist.get(index).showdescrption());
		}
	    }
	}
    }
    public void changetostate2(int mindamage,int maxdamage,int pattern,int minrange,int maxrange,int cost,int type,String skillname,int special)
    {
	    this.state=2;
	    this.mindamage=mindamage;
	    this.maxdamage=maxdamage;
	    this.pattern=pattern;
	    this.minrange=minrange;
	    this.maxrange=maxrange;
	    this.cost=cost;
	    this.type=type;
	    this.skillname=skillname;
	    this.special=special;
	    if(this.special==2)
	    {
		this.appendlog("use "+this.skillname+"\n");
		int multi;
		int patk=this.Mypetlist.get(index).getpatk(),matk=this.Mypetlist.get(index).getmatk(),damage;
		int i;
		for(i=0;i<Mypetlist.size();i++)
		{
		    multi=this.randonumber.nextInt(this.maxdamage-this.mindamage)+this.mindamage;
		    if(type==1) // patk
		    {
			damage=patk*multi/100;
		    }
		    else if(type==2) //matk
		    {
			damage=matk*multi/100;
		    }
		    else if (type==3) // heal
		    {
			damage=matk*multi/100*-1;
		    }
		    else
		    {
			damage=0;
		    }
		    if(Mypetlist.get(i).nation==Mypetlist.get(index).nation)
		    {
			this.appendlog("recover "+damage*-1+" hp to \n"+Mypetlist.get(i).getname()+"\n");
			Mypetlist.get(i).sethp(Mypetlist.get(i).gethp()-damage);
			    if(Mypetlist.get(i).gethp()>Mypetlist.get(i).getmaxhp())
			    {
				Mypetlist.get(i).sethp(Mypetlist.get(i).getmaxhp());
			    }
		    }
		}
		Mypetlist.get(index).setap(Mypetlist.get(index).getap()-cost);
		this.state=1;
		this.place.removeblanket();
		if(fullportrait==Mypetlist.get(index).fullportrait)
		{
		    infopanel.setText(Mypetlist.get(index).showdescrption());
		}
		
		return;
	    }
	    else if(special==4)
	    {
		if(Mypetlist.get(index).getmaxhp()/Mypetlist.get(index).gethp()<2);
		{
		    return;
		}
	    }
	    place.setBlanket(minrange,maxrange,this.focuspos);
    }	
    public void cursorchange(Mycoordinate a)
    {
	this.cursorpos=a;
	int i;
	if(place.buttonmanager[a.getx()*8+a.gety()].getstate()>0)
	{
	    for(i=0;i<Mypetlist.size();i++)
	    {
		if(Mypetlist.get(i).getcoordinate().myequals(a))
		{
		    break;
		}
	    }
	    this.fullportrait=Mypetlist.get(i).fullportrait;
	    this.lportrait.setIcon(fullportrait);
	    this.infopanel.setText(Mypetlist.get(i).showdescrption());
	    this.infopanel.repaint();
	}
	if(this.state==2)
	{
	    int distance=this.Manhattan(this.focuspos, this.cursorpos);
	    if(distance>=minrange && distance<=maxrange)
	    {
		place.setBlanket(minrange,maxrange,this.focuspos);
		place.buttonmanager[this.cursorpos.getx()*8+this.cursorpos.gety()].setBackground(new Color(158,225,237,200));
		if(pattern==3)
		{
		    int[] xshift={-1,0,1,-1,1,-1,0,1};
		    int[] yshift={-1,-1,-1,0,0,1,1,1};
		    int x,y;
		    for(i=0;i<8;i++)
		    {
			x=cursorpos.getx()+xshift[i];
			y=cursorpos.gety()+yshift[i];
			if(x<8 && x>=0 && y<8 && y>=0)
			{
			    place.buttonmanager[x*8+y].setBackground(new Color(158,225,237,200));
			    place.buttonmanager[x*8+y].setBorderPainted(true);
			}
		    }
		}
		else if(pattern==2)
		{
		    int[] xshift={0,-1,1,0};
		    int[] yshift={-1,0,0,1};
		    int x,y;
		    for(i=0;i<4;i++)
		    {
			x=cursorpos.getx()+xshift[i];
			y=cursorpos.gety()+yshift[i];
			if(x<8 && x>=0 && y<8 && y>=0)
			{
			    place.buttonmanager[x*8+y].setBackground(new Color(158,225,237,200));
			    place.buttonmanager[x*8+y].setBorderPainted(true);
			}
		    }
		}
	    }
	}
    }
    public void cancelclick()
    {
	if(state==2)
	{
	    place.removeblanket();
	    state=1;
	}
    }
    public int Manhattan(Mycoordinate source,Mycoordinate destin)
    {
	return Math.abs(source.getx()-destin.getx())+Math.abs(source.gety()-destin.gety());
    }
    public void death(int x, int y,Mypet p)
    {
	this.appendlog(p.getname()+" is dead\n");
	this.place.removeicon(x,y);
	p.setdead(1);
	nationtotal[p.getnation()]=nationtotal[p.getnation()]-p.getmaxhp();
	if(nationtotal[p.getnation()]<=0)
	{
	    if(p.getnation()==1)
	    {
		appendlog("the boss win!!\nBye Bye\nExit in 3 secs");
	    }
	    else
	    {
		appendlog("the player win!!\nCongratulations!!\nExit in 3 secs");
	    }
	    place.setinvalid();
	    int i;
	    for(i=0;i<skillbutton.size();i++)
	    {
		skillbutton.get(i).setEnabled(false);
	    }
	    end=false;
	    this.fight();
	}
    }
}


class GCFrame extends JPanel implements ActionListener {
    Image myImage = new ImageIcon("./images/a.jpg").getImage();

    Timer timer = new Timer(20, this);

    private float alpha = 1f;

    public GCFrame() {
       super();
       this.setOpaque(false);
       this.setBackground(new Color(0,0,0,200));
       //timer.start();
    }

    public void paint(Graphics g) {
        if (!isOpaque() && getBackground().getAlpha() < 255) {
            g.setColor(getBackground());
            g.fillRect(0, 0, getWidth(), getHeight());
        }
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2d.drawImage(myImage, 10, 10, null);
      super.paint(g);
    }
    public void actionPerformed(ActionEvent e) {
      alpha += -0.01f;
      if (alpha <= 0.5f) {
        alpha = 1f;
        timer.stop();
      }
      repaint();
    }

  }
  class Mymouselistener implements MouseListener{
    private innergamelabel controller=null;
    Mymouselistener (innergamelabel a)
    {
	this.controller=a;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
	// TODO Auto-generated method stub
	if(e.getButton()==MouseEvent.BUTTON1)
	{
	    this.controller.tellarenaclick();
	}
	else if(e.getButton()==MouseEvent.BUTTON3)
	{
	    this.controller.cancelarenaclick();
	}
    }

    @Override
    public void mouseEntered(MouseEvent e) {
	// TODO Auto-generated method stub
	this.controller.tellarenacursor();
    }

    @Override
    public void mouseExited(MouseEvent e) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void mousePressed(MouseEvent e) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void mouseReleased(MouseEvent e) {
	// TODO Auto-generated method stub
	
    }
      
  }
  class innergamelabel extends JButton{
      private Mycoordinate pos;
      private int state;
      private Myarena arena=null;
      private Mymouselistener manager=null;
      public innergamelabel(int px,int py)
      {
  	pos=new Mycoordinate(px,py);
  	this.state=0;
  	manager=new Mymouselistener(this);
  	this.addMouseListener(manager);
      }
      /*public void showpos()
      {
	  System.out.printf("(%d %d)",this.pos.getx(),this.pos.gety());
      }*/
      @Override
      protected void paintComponent(Graphics g) {
	  super.paintComponent(g);
	  if (!isOpaque() && getBackground().getAlpha() < 255) {
              g.setColor(getBackground());
              g.fillRect(0, 0, getWidth(), getHeight());
          }
      }
      public void setstate(int a)
      {
	  this.state=a;
      }
      public int getstate()
      {
	  return this.state;
      }
    public void setarena(Myarena parena)
    {
	this.arena=parena;
    }
    public int getx()
    {
	return this.pos.getx();
    }
    public int gety()
    {
	return this.pos.gety();
    }
    public void tellarenaclick()
    {
	this.arena.receivedclick(this.pos);
    }
    public void tellarenacursor()
    {
	this.arena.cursorchange(this.pos);
    }
    public void cancelarenaclick()
    {
	this.arena.cancelclick();
    }
    public Mycoordinate getpos()
    {
	return this.pos;
    }
  }
  class Board extends JLabel
  {
      public innergamelabel[] buttonmanager=new innergamelabel[64];
      public Board(int r,int g,int b,int alpha,int border)
      {
  	this.setLayout(new GridBagLayout());
  	GridBagConstraints c1 = new GridBagConstraints();
  	int i,j;
	c1.gridwidth = 1;
	c1.gridheight = 1;
	c1.fill = GridBagConstraints.BOTH;
	c1.anchor = GridBagConstraints.CENTER;
	c1.ipadx=0;
	c1.ipady=0;
	// Adding the game board
	this.setLayout(new GridBagLayout());
	for(i=0;i<8;i++)
	{
		for(j=0;j<8;j++)
		{
			c1.gridx=i;
			c1.gridy=j;
			buttonmanager[i*8+j]=new innergamelabel(i,j);
			//tmp.setContentAreaFilled(false);
			//tmp.setBorderPainted(false);
			buttonmanager[i*8+j].setPreferredSize(new Dimension(75,75));
			buttonmanager[i*8+j].setBackground(new Color(r,g,b,alpha)); // upper layer blanket 25,25,25,185 // absolute transparent alpha=0
			buttonmanager[i*8+j].setOpaque(false);
			buttonmanager[i*8+j].setstate(0);
			if(border==1)
			{
			    buttonmanager[i*8+j].setBorderPainted(true);
			}
			else
			{
			    buttonmanager[i*8+j].setBorderPainted(false);
			}
			this.add(buttonmanager[i*8+j],c1);
		}
	}
	this.setOpaque(false);
	this.setPreferredSize(new Dimension(600,600));
	this.setVisible(true);
      }
      public void placeicon(int x,int y,ImageIcon icon,int index)
      {
	  this.buttonmanager[x*8+y].setIcon(icon);
	  this.buttonmanager[x*8+y].setOpaque(true);
	  this.buttonmanager[x*8+y].paintImmediately(0, 0, 75, 75);
	  this.buttonmanager[x*8+y].setstate(index);
	  this.paintImmediately(0, 0, 600, 600);
      }
      public void removeicon(int x, int y)
      {
	  this.buttonmanager[x*8+y].setIcon(null);
	  this.buttonmanager[x*8+y].setOpaque(false);
	  this.buttonmanager[x*8+y].paintImmediately(0, 0, 75, 75);
	  this.buttonmanager[x*8+y].setstate(0);
	  this.paintImmediately(0, 0, 600, 600);
      }
      public void setarena(Myarena arena)
      {
	  int i;
	  for(i=0;i<64;i++)
	  {
	      this.buttonmanager[i].setarena(arena);
	  }
      }
      public void setstate(int a)
      {
	  int i;
	  for(i=0;i<64;i++)
	  {
	      this.buttonmanager[i].setstate(a);
	  }
      }
      public void setBlanket(int minrange, int maxrange,Mycoordinate pos)
      {
	  int i;
	  for(i=0;i<64;i++)
	  {
	      if(this.Manhattan(this.buttonmanager[i].getpos(), pos)>maxrange || this.Manhattan(this.buttonmanager[i].getpos(), pos)<minrange)
	      {
	      this.buttonmanager[i].setBackground(new Color(25, 25, 25, 185));
	      this.buttonmanager[i].setBorderPainted(false);
	      }
	      else
	      {
		  this.buttonmanager[i].setBackground(new Color(192, 192, 192, 200));
		  this.buttonmanager[i].setBorderPainted(true);
	      }
	  }
      }
      public void removeblanket()
      {
	  int i;
	  for(i=0;i<64;i++)
	  {
	      this.buttonmanager[i].setBackground(new Color(192, 192, 192, 200));
	      this.buttonmanager[i].setBorderPainted(true);
	  }
      }
      public int Manhattan(Mycoordinate source,Mycoordinate destin)
      {
  	return Math.abs(source.getx()-destin.getx())+Math.abs(source.gety()-destin.gety());
      }
      public void setinvalid()
      {
	  int i;
	  for(i=0;i<64;i++)
	  {
	      this.buttonmanager[i].setEnabled(false);
	  }
      }
  }
  
  class petOrder implements Comparator<Object>
  {
      public int compare(Object a, Object b)
      {
	  if(a instanceof Mypet)
	  {
	      if(b instanceof Mypet)
	      {
		  return ((Mypet) b).getagility()-((Mypet) a).getagility();
	      }
	      else
	      {
		  return 0;
	      }
	  }
	  else
	  {
	      return 0;
	  }
      }
  }