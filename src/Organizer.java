import java.awt.BorderLayout;
import java.awt.TextField;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
public class Organizer extends JFrame implements ActionListener,MouseMotionListener,MouseListener{
	DrawClass drawPanel = new DrawClass(this);
	public TextField daytext = new TextField("day",4);
	public TextField monthtext = new TextField("month",4);
	public TextField yeartext = new TextField("year",4);
	public int mouseX,mouseY;
	public boolean down;
	Timer myTimer=new Timer(1,this);
	
	public Organizer(){
		super("Task Organizer");
		myTimer.start();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,600);
		setLayout(null);
		drawPanel = new DrawClass(this);
		drawPanel.setLocation(0,0);
		drawPanel.setSize(800,640);
		drawPanel.setLayout(null);
		add(drawPanel);
		addMouseMotionListener(this);
		addMouseListener(this);
		daytext.addActionListener(this);
		monthtext.addActionListener(this);
		yeartext.addActionListener(this);
		daytext.setBounds(630,50,40,20);
		monthtext.setBounds(680,50,40,20);
		yeartext.setBounds(730,50,40,20);
		daytext.setVisible(false);
		monthtext.setVisible(false);
		yeartext.setVisible(false);
		drawPanel.add(daytext);
		drawPanel.add(monthtext);
		drawPanel.add(yeartext);
		setResizable(false);
		setVisible(true);
		createTextFiles();
	}
	
	public static void main (String[] args){
		new Organizer();
	}
	public void createTextFiles(){
		Writer writer = null;
		int[] days = {31,28,31,30,31,30,31,31,30,31,30,31};
		for (int year=2000;year<=2020;year++){
			for(int month=1;month<=12;month++){
				for(int day=1;day<=days[month-1];day++){
					File parentDir = new File("alldates");
					parentDir.mkdir();
					String hash = year+"-"+month+"-"+day;
					String fileName = hash + ".txt";
					File file = new File(parentDir, fileName);
					try {
						file.createNewFile();
					} 
					catch (IOException e) {
					} 
				}
			}
		}
	}
	public void actionPerformed(ActionEvent evt){
		Object source = evt.getSource();
		if (source==myTimer){
			drawPanel.repaint();
			drawPanel.setD.checkSDCollision(mouseX,mouseY,down);
		}
		if (source==daytext){
		}
		if (source==monthtext){
		}
		if (source==yeartext){
		}
	}
	// ------------ MouseListener ------------------------------------------
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {
    	down=false;
    }    
    public void mouseClicked(MouseEvent e){
    	
    }  
    public void mousePressed(MouseEvent e){
    	down=true;
    }
    	
    // ---------- MouseMotionListener ------------------------------------------
    public void mouseDragged(MouseEvent e){}
    public void mouseMoved(MouseEvent e){
    	mouseX=e.getX();
    	mouseY = e.getY();
    }
}

