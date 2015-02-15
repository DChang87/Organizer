import java.awt.BorderLayout;
import java.awt.TextField;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.*;
class TasksClass {
	DrawClass drawPanel;
	public ArrayList<String> allTasks = new ArrayList<String>();
	public int numTasks;
	public ArrayList<ArrayList<Integer>> dueDates = new ArrayList<ArrayList<Integer>>();
	public ArrayList<Integer> completion = new ArrayList<Integer>();
	public ArrayList<Integer> rank=new ArrayList<Integer>();
	public ArrayList<String> notes = new ArrayList<String>();
	public int currentTask=-1;
	public final int EDIT=0,DONE=1;
	public ArrayList<Integer>editingdetails = new ArrayList<Integer>();
	public ArrayList<Integer>hoveringText = new ArrayList<Integer>();
	public ArrayList<Integer>edit_done = new ArrayList<Integer>();
	public ArrayList<Integer> hovering=new ArrayList<Integer>();
	public TasksClass(DrawClass d){
		drawPanel=d;
	}
	public void readFile(){
		String name=drawPanel.currentdate[2]+"-"+drawPanel.currentdate[1]+"-"+drawPanel.currentdate[0]+".txt";
	}
	public void checkNTCollision(Organizer o){
		if (o.down&&drawPanel.setD.collide(480, 515, 150, 50, o.mouseX, o.mouseY)){
			newTask();
			o.down=false;
		}
		else if (drawPanel.setD.collide(480, 515, 150, 50, o.mouseX,o.mouseY)){
			drawPanel.newtaskDraw=drawPanel.newtaskbutton2;
		}
		else{
			drawPanel.newtaskDraw=drawPanel.newtaskbutton;
		}
	}
	public void checkCDCollision(int mouseX,int mouseY,boolean down){
		if (down&&drawPanel.setD.collide(630, 515, 150, 50, mouseX, mouseY)){
			clearDay();
		}
		else if (drawPanel.setD.collide(630, 515, 150, 50, mouseX, mouseY)){
			drawPanel.clearDayDraw=drawPanel.cleardaybutton2;
		}
		else{
			drawPanel.clearDayDraw=drawPanel.cleardaybutton;
		}
	}
	public void checkTextCollision(Organizer o,int i){
		if (o.down && drawPanel.setD.collide(500, 160+40*i, 225, 40, o.mouseX, o.mouseY)){
			currentTask=i;
		}
		else if (drawPanel.setD.collide(500, 160+40*i, 225,40, o.mouseX, o.mouseY)){
			hoveringText.set(i,1);
		}
		else{
			hoveringText.set(i,0);
		}
	}
	public void newTask(){
		currentTask=numTasks;
		numTasks++;
		drawPanel.org.addTextField();
		for (int i=0;i<drawPanel.org.tasklist.size();i++){
			if (allTasks.size()<=i){
				allTasks.add("");
			}
			allTasks.set(i,drawPanel.org.tasklist.get(i).getText());
			if (i!=currentTask){
				drawPanel.org.tasklist.get(i).setVisible(false);
				edit_done.set(i,EDIT);
			}
		}
		edit_done.add(DONE);
		rank.add(0);
		notes.add("");
		editingdetails.add(0);
		completion.add(0);
		hovering.add(0);
		hoveringText.add(0);
		System.out.println("newtask");
		dueDates.add(new ArrayList<Integer>());
		dueDates.get(currentTask).add(drawPanel.currentdate[0]);
		dueDates.get(currentTask).add(drawPanel.currentdate[1]);
		dueDates.get(currentTask).add(drawPanel.currentdate[2]);
		
	}
	public void checkEDCollide(Organizer o,int i){
		if (o.down && drawPanel.setD.collide(730, 160+i*40, 50, 28, o.mouseX, o.mouseY)){
			if (edit_done.get(i)==EDIT){
				/*for (int k=0;k<drawPanel.org.tasklist.size();k++){
					drawPanel.org.tasklist.get(i).setVisible(false);
					edit_done.set(i,EDIT);
					allTasks.set(i,drawPanel.org.tasklist.get(i).getText());
					System.out.println("invisible");
				}*/
				edit_done.set(i,DONE);
				drawPanel.org.tasklist.get(i).setVisible(true);
				System.out.println("edit");
			}
			else{
				edit_done.set(i,EDIT);
				allTasks.set(i, drawPanel.org.tasklist.get(i).getText());
				drawPanel.org.tasklist.get(i).setVisible(false);
			}
			o.down=false;
		}
		else if (drawPanel.setD.collide(730, 160+i*40, 50, 28, o.mouseX, o.mouseY)){
			hovering.set(i,2);
		}
		else{
			hovering.set(i,0);
		}
	}
	public void clearDay(){
		for (int i=0;i<drawPanel.org.tasklist.size();i++){
			drawPanel.remove(drawPanel.org.tasklist.get(i));
			drawPanel.revalidate();
			drawPanel.repaint();
		}
		drawPanel.org.tasklist.clear();
		edit_done.clear();
		numTasks=0;
		allTasks.clear();
		currentTask=-1;
		editingdetails.clear();
		hoveringText.clear();
		rank.clear();
		dueDates.clear();
		notes.clear();
		completion.clear();
	}
}

import javax.swing.*;
public class Organizer extends JFrame implements ActionListener,MouseMotionListener,MouseListener{
	DrawClass drawPanel = new DrawClass(this);
	public TextField daytext = new TextField("day",4);
	public TextField monthtext = new TextField("month",4);
	public TextField yeartext = new TextField("year",4);
	public int mouseX,mouseY;
	public boolean down;
	Timer myTimer=new Timer(1,this);
	public ArrayList<TextField> tasklist = new ArrayList<TextField>();
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
	public void addTextField(){
		tasklist.add(new TextField());
		tasklist.get(drawPanel.tc.currentTask).setBounds(490,140+drawPanel.tc.currentTask*40,180,30);
		tasklist.get(drawPanel.tc.currentTask).setVisible(true);
		drawPanel.add(tasklist.get(drawPanel.tc.currentTask));
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
			drawPanel.setD.checkSDCollision(this);
			drawPanel.tc.checkNTCollision(this);
			drawPanel.tc.checkCDCollision(mouseX, mouseY, down);
			for (int i=0;i<drawPanel.tc.numTasks;i++){
				drawPanel.tc.checkEDCollide(this,i);
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

