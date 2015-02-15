import java.util.*;
class TasksClass {
	DrawClass drawPanel;
	public ArrayList<String> allTasks = new ArrayList<String>();
	public int numTasks;
	public ArrayList<ArrayList<Integer>> dueDates = new ArrayList<ArrayList<Integer>>();
	public ArrayList<Integer> completion = new ArrayList<Integer>();
	public ArrayList<Integer> rank=new ArrayList<Integer>();
	public ArrayList<String> notes = new ArrayList<String>();
	public int currentTask=0;
	public final int EDIT=0,DONE=1;
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
	public void newTask(){
		currentTask=numTasks;
		numTasks++;
		drawPanel.org.addTextField();
		for (int i=0;i<drawPanel.org.tasklist.size();i++){
			if (allTasks.size()<=i){
				allTasks.add("");
			}
			allTasks.set(i,drawPanel.org.tasklist.get(i).getText());
		}
		edit_done.add(DONE);
		rank.add(0);
		notes.add("");
		completion.add(0);
		hovering.add(0);
		System.out.println("newtask");
		dueDates.add(new ArrayList<Integer>());
		dueDates.get(currentTask).add(drawPanel.currentdate[0]);
		dueDates.get(currentTask).add(drawPanel.currentdate[1]);
		dueDates.get(currentTask).add(drawPanel.currentdate[2]);
		
	}
	public void checkEDCollide(Organizer o,int i){
		if (o.down && drawPanel.setD.collide(730, 160+i*40, 50, 28, o.mouseX, o.mouseY)){
			if (edit_done.get(i)==EDIT){
				edit_done.set(i,DONE);
				drawPanel.org.tasklist.get(i).setVisible(true);
			}
			else{
				edit_done.set(i,EDIT);
				allTasks.set(i, drawPanel.org.tasklist.get(i).getText());
				drawPanel.org.tasklist.get(i).setVisible(false);
			}
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
		rank.clear();
		dueDates.clear();
		notes.clear();
		completion.clear();
	}
}
