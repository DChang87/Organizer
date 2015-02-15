import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

import javax.swing.*;
public class Organizer extends JFrame implements ActionListener,MouseMotionListener,MouseListener{
	DrawClass drawPanel;
	public TextField daytext = new TextField("day",4);
	public TextField monthtext = new TextField("month",4);
	public TextField yeartext = new TextField("year",4);
	public int mouseX,mouseY;
	public TextArea notesArea = new TextArea("Enter additional information");
	public boolean down;
	Timer myTimer=new Timer(1,this);
	public ArrayList<TextField> tasklist = new ArrayList<TextField>();
	public ArrayList<TextField> detailtextfields = new ArrayList<TextField>();
	public Organizer(){
		super("Task Organizer");
		createTextFiles();
		myTimer.start();
		drawPanel = new DrawClass(this);
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
		create_detailtextfields();
	}
	public static void main (String[] args){
		new Organizer();
	}
	public void addTextField(){
		tasklist.add(new TextField());
		tasklist.get(drawPanel.tc.currentTask).setBounds(490,140+drawPanel.tc.currentTask*40,180,40);
		tasklist.get(drawPanel.tc.currentTask).setVisible(true);
		drawPanel.add(tasklist.get(drawPanel.tc.currentTask));
	}
	public void create_detailtextfields(){
		int i=0;
		TextField tempTF = new TextField();
		detailtextfields.add(new TextField());
		if (drawPanel.tc.allTasks.size()==0){
			detailtextfields.get(i).setText("New Task");
		}
		else{
			detailtextfields.get(i).setText(drawPanel.tc.allTasks.get(drawPanel.tc.currentTask));
		}
		detailtextfields.get(i).setBounds(200,40,220,35);
		i++;
		detailtextfields.add(new TextField());
		if (drawPanel.tc.allTasks.size()==0){
			detailtextfields.get(i).setText("0");
		}
		else{
			detailtextfields.get(i).setText(""+drawPanel.tc.rank.get(drawPanel.tc.currentTask));
		}
		detailtextfields.get(i).setBounds(130,80,50,35);
		i++;
		detailtextfields.add(new TextField());
		if (drawPanel.tc.allTasks.size()==0){
			detailtextfields.get(i).setText("0");
		}
		else{
			detailtextfields.get(i).setText(""+drawPanel.tc.completion.get(drawPanel.tc.currentTask));
		}
		detailtextfields.get(i).setBounds(345,80,100,35);
		i++;
		detailtextfields.add(new TextField());
		if (drawPanel.tc.allTasks.size()==0){
			detailtextfields.get(i).setText(drawPanel.currentdate[0]+"");
		}
		else{
			detailtextfields.get(i).setText(""+drawPanel.tc.dueDates.get(drawPanel.tc.currentTask).get(0));
		}
		detailtextfields.get(i).setBounds(170,120,40,35);
		i++;
		detailtextfields.add(new TextField());
		if (drawPanel.tc.allTasks.size()==0){
			detailtextfields.get(i).setText(drawPanel.currentdate[1]+"");
		}
		else{
			detailtextfields.get(i).setText(""+drawPanel.tc.dueDates.get(drawPanel.tc.currentTask).get(1));
		}		
		detailtextfields.get(i).setBounds(220,120,40,35);
		i++;
		detailtextfields.add(new TextField());
		if (drawPanel.tc.allTasks.size()==0){
			detailtextfields.get(i).setText(drawPanel.currentdate[2]+"");
		}
		else{
			detailtextfields.get(i).setText(""+drawPanel.tc.dueDates.get(drawPanel.tc.currentTask).get(2));
		}
		
		detailtextfields.get(i).setBounds(270,120,40,35);
		i++;
		notesArea.setVisible(false);
		notesArea.setBounds(140,160,300,200);
		drawPanel.add(notesArea);
		for (int k=0;k<detailtextfields.size();k++){
			detailtextfields.get(k).setVisible(false);
			drawPanel.add(detailtextfields.get(k));
		}
		
	}
	public void createTextFiles(){
		Writer writer = null;
		int[] days = {31,28,31,30,31,30,31,31,30,31,30,31};
		for (int year=2000;year<=2020;year++){
			System.out.println("year"+year);
			for(int month=1;month<=12;month++){
				for(int day=1;day<=days[month-1];day++){
					
					String hash = year+"-"+month+"-"+day;
					String fileName = hash + ".txt";
					
					try {
						FileWriter outfile = new FileWriter(new File(fileName));
						outfile.write("0");
						outfile.close();
						
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
			drawPanel.setD.checkSDCollision(this);
			drawPanel.tc.checkNTCollision(this);
			drawPanel.tc.checkCDCollision(mouseX, mouseY, down);
			drawPanel.tc.checkEditDonedetails(this);
			for (int i=0;i<drawPanel.tc.numTasks;i++){
				drawPanel.tc.checkEDCollide(this,i);
				drawPanel.tc.checkTextCollision(this, i);
			}
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

