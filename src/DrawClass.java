import java.awt.*;
import java.awt.event.*;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.applet.*;
import java.io.*;

import javax.swing.*;

import java.util.*;
class DrawClass extends JPanel implements KeyListener{
	public Organizer org;
	private boolean[] keys;
	private Image background;
	private Image tbackground;
	private Font Giddyup;
	public final int NORMAL=0, HOVER=1,PRESSED=2;
	public int settingDate=NORMAL;
	public boolean editDate=false;
	public Color setDateColor = new Color(5,103,128);
	setDate setD;
	public DrawClass(Organizer o){
		keys = new boolean[65535];
		setD= new setDate(this);
		background = new ImageIcon("background.jpg").getImage();
		tbackground = new ImageIcon("taskbackground.png").getImage();
		addKeyListener(this);
		
		setSize(800,600);
		org = o;
		newFont();
	}
	public void addNotify(){
		super.addNotify();
        requestFocus();
	}
	public void newFont(){
		InputStream is;
		try{
			is = new FileInputStream("Giddyup.ttf");
			Giddyup = Font.createFont(Font.TRUETYPE_FONT, is);
			Giddyup=Giddyup.deriveFont(50f);
		}
		catch(IOException ex){
			System.out.println("File not found");
		}
		catch(FontFormatException ex){
			System.out.println("Font cannot be created");
		}
	}
	// --------- Keystuff---------------
    public void keyTyped(KeyEvent e){}
    public void keyPressed(KeyEvent e){
    	keys[e.getKeyCode()]=true;
    }
    public void keyReleased(KeyEvent e){
    	keys[e.getKeyCode()]=false;
    }
	public void paintComponent(Graphics g){
		g.drawImage(background,0,0,this);
		g.drawImage(tbackground,20,25,this);
		g.setFont(Giddyup);
		g.setColor(setDateColor);
		g.drawString("Set Date",460,60);
	}
    
}
