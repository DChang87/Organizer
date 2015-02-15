import java.awt.*;
import java.awt.event.*;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.applet.*;
import java.io.*;

import javax.swing.*;

import java.text.*;
import java.util.*;
class DrawClass extends JPanel implements KeyListener{
	public Organizer org;
	private boolean[] keys;
	private Image background;
	private Image tbackground;
	private Image rbackground;
	public Image newtaskbutton,cleardaybutton,newtaskbutton2,cleardaybutton2;
	private Font Giddyup,Giddyup50,Giddyup25;
	public final int NORMAL=0, HOVER=1,PRESSED=2;
	public int settingDate=NORMAL;
	public boolean editDate=false;
	public Color setDateColor = new Color(5,103,128);
	public int[] currentdate=new int[3];
	public DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	public Date today = new Date();
	public Image newtaskDraw=newtaskbutton;
	public Image clearDayDraw = cleardaybutton;
	
	/*
	 * DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
Date date = new Date();
System.out.println(dateFormat.format(date)); //2014/08/06 15:59:48
	 */
	setDate setD;
	TasksClass tc;
	public DrawClass(Organizer o){
		keys = new boolean[65535];
		setD= new setDate(this);
		tc = new TasksClass(this);
		background = new ImageIcon("background.jpg").getImage();
		newtaskbutton = new ImageIcon("newtaskbutton.png").getImage();
		cleardaybutton = new ImageIcon("cleardaybutton.png").getImage();
		tbackground = new ImageIcon("taskbackground.png").getImage();
		rbackground = new ImageIcon("rightbackground.png").getImage();
		newtaskbutton2 = new ImageIcon("newtaskbutton2.png").getImage();
		cleardaybutton2 = new ImageIcon("cleardaybutton2.png").getImage();
		addKeyListener(this);
		currentdate[0]=Integer.parseInt(dateFormat.format(today).substring(8,10));
		currentdate[1]=Integer.parseInt(dateFormat.format(today).substring(5,7));
		currentdate[2]=Integer.parseInt(dateFormat.format(today).substring(0,4));
		System.out.println(currentdate[2]+" "+currentdate[1]+" "+currentdate[0]);
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
			Giddyup50=Giddyup.deriveFont(50f);
			Giddyup25=Giddyup.deriveFont(25f);
			
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
		g.drawImage(rbackground,480,80,this);
		
		g.setFont(Giddyup50);
		g.setColor(setDateColor);
		g.drawString("Set Date",480,70);
		g.setColor(new Color(0,154,196));
		g.drawString("all tasks",565,120);
		g.setFont(Giddyup25);
		g.setColor(new Color(63,63,161));
		
		for (int i=0;i<tc.allTasks.size();i++){
			g.drawString(tc.allTasks.get(i),500,150+30*i);
		}
		g.drawImage(newtaskDraw,480,490,this);
		g.drawImage(clearDayDraw,630,490,this);
		g.drawString("Current Date: "+currentdate[2]+"/"+currentdate[1]+"/"+currentdate[0],575,20);
	}
    
}
