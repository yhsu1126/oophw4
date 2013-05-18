import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import ntu.csie.oop13spring.POOArena;
import ntu.csie.oop13spring.POOCoordinate;
import ntu.csie.oop13spring.POOPet;


public class Myarena extends POOArena{
    private JFrame frame;
    private Board place;
    private JLabel lportrait,infopanel,skillpanel;
    private JTextArea log;
    private JScrollPane gameprogress;
    private ImageIcon fullportrait;
    private String subscription;
    private int state,finished,index;
    private Mycoordinate focuspos,cursorpos;
    private ArrayList<Mypet> Mypetlist= new ArrayList<Mypet>();
    private boolean end;
    GCFrame scene;
    String gamelog="Welcome to POOArena!!\n";
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
	skillpanel = new JLabel("Skill Panel");
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
    }
    public boolean fight()
    {
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
	    place.placeicon(Mypetlist.get(i).getcoordinate().getx(), Mypetlist.get(i).getcoordinate().gety(), Mypetlist.get(i).getportrait());
	}
	index=0;
	while(true)
	{
	    focuspos=Mypetlist.get(index).getcoordinate();
	    this.appendlog(Mypetlist.get(index).getname()+" turn\n");
	    finished=0;
	    state=1;
	    // state 1 waiting for command, state 2 doing skill;
	    while(finished==0)
	    {
		if(Mypetlist.get(index).getmp()==0)
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
	    if(place.buttonmanager[a.getx()*8+a.gety()].getstate()!=1)
	    {
		int dis=this.Manhattan(a, this.focuspos);
		if(dis<=this.Mypetlist.get(index).getmp())
		{
		    this.appendlog("move to "+"( "+a.getx()+" , "+a.gety()+" )"+"\n");
		    place.removeicon(focuspos.getx(), focuspos.gety());
		    place.placeicon(a.getx(), a.gety(), Mypetlist.get(index).getportrait());
		    this.focuspos=a;
		    Mypetlist.get(index).setcoordinate(focuspos);
		    Mypetlist.get(index).setmp(this.Mypetlist.get(index).getmp()-dis);
		    if(this.Mypetlist.get(index).getmp()==0)
		    {
			finished=1;
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
		this.appendlog("Illegalmove :( "+a.getx()+" , "+a.gety()+" )"+place.buttonmanager[a.getx()*8+a.gety()].getstate()+"\n");
	    }
	}
    }
    public void cursorchange(Mycoordinate a)
    {
	this.cursorpos=a;
	int i;
	if(place.buttonmanager[a.getx()*8+a.gety()].getstate()==1)
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
    }
    /*public Mycoordinate getPosition(POOPet p)
    {
	
    }*/
    public int Manhattan(Mycoordinate source,Mycoordinate destin)
    {
	return Math.abs(source.getx()-destin.getx())+Math.abs(source.gety()-destin.gety());
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
       timer.start();
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
    private int state;
    private innergamelabel controller=null;
    Mymouselistener (innergamelabel a)
    {
	this.controller=a;
    }
    public void setstate(int a)
    {
	this.state=a;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
	// TODO Auto-generated method stub
	if(this.state==1)
	{
	    this.controller.tellarenaclick();
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
      public void setlistenerstate(int a)
      {
	  this.manager.setstate(a);
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
	this.setlistener(1);
      }
      public void placeicon(int x,int y,ImageIcon icon)
      {
	  this.buttonmanager[x*8+y].setIcon(icon);
	  this.buttonmanager[x*8+y].setOpaque(true);
	  this.buttonmanager[x*8+y].paintImmediately(0, 0, 75, 75);
	  this.buttonmanager[x*8+y].setstate(1);
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
      public void setlistener(int a)
      {
	  int i;
	  for(i=0;i<64;i++)
	  {
	      this.buttonmanager[i].setlistenerstate(a);
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