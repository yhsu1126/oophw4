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
import javax.swing.Timer;

import ntu.csie.oop13spring.POOArena;
import ntu.csie.oop13spring.POOCoordinate;
import ntu.csie.oop13spring.POOPet;


public class Myarena extends POOArena{
    private JFrame Gamebox; 
    Myarena()
    {
	super();
	Gamebox.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
    }
    public boolean fight()
    {
	return true;
    }
    public void show()
    {
	int i,j;
	JFrame.setDefaultLookAndFeelDecorated(true);
	JFrame frame = new JFrame("Fade out");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	com.sun.awt.AWTUtilities.setWindowOpaque(frame, false); //6u14
	frame.setLayout(new BorderLayout());
	frame.setContentPane(new GCFrame());
	frame.setLayout(new GridBagLayout());
	GridBagConstraints c1 = new GridBagConstraints();
	c1.gridwidth = 1;
	c1.gridheight = 1;
	c1.fill = GridBagConstraints.BOTH;
      	c1.anchor = GridBagConstraints.CENTER;
      	c1.ipadx=0;
      	c1.ipady=0;
      	c1.gridx=0;
      	c1.gridy=0;
      	Board place=new Board();
      	frame.add(place.innerboard,c1);
      	frame.setSize(1258, 710);
	frame.setResizable(false);
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);
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
    Image myImage = new ImageIcon("a.jpg").getImage();

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
        alpha = 0.5f;
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
  	    ImageIcon background=new ImageIcon("game.jpg");
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