import java.util.*;
class TasksClass {
	DrawClass drawPanel;
	public ArrayList<String> allTasks = new ArrayList<String>();
	public int numTasks;
	public int[] dueDates = new int[3];
	public int completion;
	public int rank;
	public String notes;
	public TasksClass(DrawClass d){
		drawPanel=d;
	}
	public void readFile(){
		String name=drawPanel.currentdate[2]+"-"+drawPanel.currentdate[1]+"-"+drawPanel.currentdate[0]+".txt";
	}
	public void checkNTCollision(int mouseX,int mouseY,boolean down){
		if (down&&drawPanel.setD.collide(480, 515, 150, 50, mouseX, mouseY)){
			newTask();
		}
		else if (drawPanel.setD.collide(480, 515, 150, 50, mouseX, mouseY)){
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
	public void newTask(){
		
	}
	public void clearDay(){
		
	}
}
