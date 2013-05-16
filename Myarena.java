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
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import ntu.csie.oop13spring.POOArena;
import ntu.csie.oop13spring.POOCoordinate;
import ntu.csie.oop13spring.POOPet;

import java.util.*;


public class Myarena extends POOArena{
    private JFrame frame;
    private Board place;
    private JLabel portrait,log,infopanel,skillpanel;
    private JScrollPane gameprogress;
    private ImageIcon fullportrait;
    private String subscription;
    private int state;
    private Mycoordinate focuspos;
    private ArrayList<POOPet> Mypetlist= new ArrayList<POOPet>();
    GCFrame scene;
    Myarena()
    {
	super();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	place=new Board();
	int i,j;
	frame = new JFrame("Fade out");
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
	portrait=new JLabel();
	portrait.setPreferredSize(new Dimension(215,300));
	portrait.setOpaque(false);
	portrait.setBackground(new Color(192,192,192,225));
	frame.add(portrait,c1);
	// intro... state
	c1.gridwidth = 3;
	c1.gridheight = 4;
	c1.anchor = GridBagConstraints.WEST;
	c1.ipadx=10;
	c1.ipady=0;
	c1.gridx=0;
	c1.gridy=4;
	infopanel=new JLabel();
	infopanel.setVerticalAlignment(SwingConstants.TOP);
	infopanel.setPreferredSize(new Dimension(215,300));
	infopanel.setOpaque(true);
	infopanel.setBackground(new Color(192,192,192,225));
	frame.add(infopanel,c1);
	//add the board
	c1.gridwidth = 8;
	c1.gridheight = 8;
	c1.anchor = GridBagConstraints.CENTER;
	c1.gridx=4;
	c1.gridy=0;
	c1.ipadx=10;
	c1.ipady=10;
	Board place=new Board();
	/*JLabel upper=Board.CreateBoard(25, 25, 25, 185,0);
	JLabel lower=Board.CreateBoard(192, 192, 192, 185,1);*/
	//layeredPane.add(lower, 0);
	frame.add(place.innerboard,c1);
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
	gameprogress=new JScrollPane();
	log=new JLabel();
	log.setText("<html>Welcome to the POOArena!!</html>");
	log.setVisible(true);
	log.setOpaque(false);
	log.setBackground(new Color(192,192,192,225));
	gameprogress.setPreferredSize(new Dimension(215,300));
	gameprogress.setOpaque(true);
	gameprogress.setBackground(new Color(192,192,192,225));
	frame.add(gameprogress,c1);
	gameprogress.add(log);
	frame.setSize(1258, 710);
	frame.setResizable(false);
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);
    }
    public boolean fight()
    {
	return true;
    }
    
    @Override
    public void addPet(POOPet p)
    {
	Mypetlist.add(p);
    }
    public void show()
    {
	    //place.stylechange();
	      /*JFrame.setDefaultLookAndFeelDecorated(true);
	      JFrame frame = new JFrame();
	      //com.sun.awt.AWTUtilities.setWindowOpacity(frame, .5f);
	      com.sun.awt.AWTUtilities.setWindowOpaque(frame, false); //6u14
	      //1.7.0 frame.setBackground(new Color(0,0,0,0));
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      JPanel p = new JPanel();
	      p.add(new JButton("JButton"));
	      p.setBackground(new Color(.5f,.8f,.5f,.5f));
	      frame.getContentPane().add(p);
	      frame.setSize(320, 240);
	      frame.setLocationRelativeTo(null);
	      frame.setVisible(true);*/
    }
    public POOCoordinate getPosition(POOPet p)
    {
	return new Mycoordinate(0,0);
    }
    /*public Mycoordinate getPosition(POOPet p)
    {
	
    }*/
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
      if (alpha <= 0.8f) {
        alpha = 1f;
        timer.stop();
      }
      repaint();
    }

  }

  class innergamelabel extends JButton{
      private Mycoordinate pos;
      public innergamelabel(int px,int py)
      {
  	pos=new Mycoordinate(px,py);
      }
      @Override
      protected void paintComponent(Graphics g) {
          if (!isOpaque() && getBackground().getAlpha() < 255) {
              g.setColor(getBackground());
              g.fillRect(0, 0, getWidth(), getHeight());
          }
          super.paintComponent(g);
      }
  }

  class Board
  {
      private int r,g,b,alpha;
      public JLabel innerboard;
      private innergamelabel[] buttonmanager=new innergamelabel[64];
      public Board()
      {
  	innerboard= CreateBoard(192,192,192,200,1);
      }
      private JLabel CreateBoard(int r,int g,int b,int alpha,int border)
      {
  	    int i,j;
  	    ImageIcon background=new ImageIcon("./images/game.jpg");
  	    background = new ImageIcon(background.getImage().getScaledInstance(600, 600, BufferedImage.SCALE_SMOOTH));
  	    JLabel board = new JLabel();
  	    //board.setIcon(background);     
  	    board.setLayout(new GridBagLayout());
  	    GridBagConstraints c1 = new GridBagConstraints();
  	    c1.gridwidth = 1;
  	    c1.gridheight = 1;
  	    c1.fill = GridBagConstraints.BOTH;
  	    c1.anchor = GridBagConstraints.CENTER;
  	    c1.ipadx=0;
  	    c1.ipady=0;
  	    // Adding the game board
  	    board.setLayout(new GridBagLayout());
  	    for(i=0;i<8;i++)
  	    {
  			for(j=0;j<8;j++)
  			{
  				c1.gridx=i;
  				c1.gridy=j;
  				innergamelabel tmp=new innergamelabel(i,j);
  				tmp.setOpaque(false);
  				//tmp.setContentAreaFilled(false);
  				//tmp.setBorderPainted(false);
  				tmp.setPreferredSize(new Dimension(75,75));
  				tmp.setBackground(new Color(r,g,b,alpha)); // upper layer banket 25,25,25,185 // absolute transparent alpha=0
  				tmp.setOpaque(false);
  				buttonmanager[i*8+j]=tmp;
  				if(border==1)
  				{
  				    tmp.setBorderPainted(true);
  				}
  				else
  				{
  				    tmp.setBorderPainted(false);
  				}
  				board.add(tmp,c1);
  			}
  	    }
  	    board.setOpaque(false);
  	    board.setPreferredSize(new Dimension(600,600));
  	    board.setVisible(true);
  	    return board;
      }
  }