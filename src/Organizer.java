import java.awt.BorderLayout;
import java.awt.TextField;
import java.awt.event.*;

import javax.swing.*;
public class Organizer extends JFrame implements ActionListener,MouseMotionListener,MouseListener{
	DrawClass drawPanel = new DrawClass(this);
	public TextField daytext = new TextField("Enter day",7);
	public TextField monthtext = new TextField("Enter month",7);
	public TextField yeartext = new TextField("Enter year",7);
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
		daytext.setBounds(600,40,60,20);
		monthtext.setBounds(665,40,60,20);
		yeartext.setBounds(730,40,60,20);
		daytext.setVisible(false);
		monthtext.setVisible(false);
		yeartext.setVisible(false);
		drawPanel.add(daytext);
		drawPanel.add(monthtext);
		drawPanel.add(yeartext);
		setResizable(false);
		setVisible(true);
		
	}
	
	public static void main (String[] args){
		new Organizer();
	}
	public void actionPerformed(ActionEvent evt){
		Object source = evt.getSource();
		//int day = Integer.parseInt(daytext.getText());
		//int month =Integer.parseInt(monthtext.getText());
		//int year = Integer.parseInt(yeartext.getText());
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

