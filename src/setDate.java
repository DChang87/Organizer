import java.awt.Color;
import java.awt.event.*;


class setDate {
	DrawClass drawPanel;
	private boolean[] keys;
	public setDate(DrawClass d){
		drawPanel=d;
	}
	public boolean collide(int x,int y, int width, int height,int mouseX,int mouseY){
		return x<=mouseX && mouseX<=x+width && mouseY<=y+height && y<=mouseY;
	}
	public int[] getDate(){
		int[] dates = new int[3];
		dates[0]=-1;
		dates[1]=10;
		dates[2]=-1;
		try{
			dates[0]=Integer.parseInt(drawPanel.org.daytext.getText());
			dates[1]=Integer.parseInt(drawPanel.org.monthtext.getText());
			dates[2]=Integer.parseInt(drawPanel.org.yeartext.getText());
		}
		catch(NumberFormatException ex){
		}
		int[] monthlengths = {31,28,31,30,31,30,31,31,30,31,30,31};
		if (dates[1]>12 || dates[2]>2020 || dates[2]<2000){
			dates[2]=-1;
			return dates;
		}
		if (dates[0]>monthlengths[dates[1]-1]){
			dates[0]=-1;
		}
		return dates;
	}
	public int[] tempdate = new int[3];
	public void checkSDCollision(int mouseX,int mouseY,boolean down){
		Color colour0 = new Color(5,103,128);
		Color colour1 = new Color(0,58,82);
		Color colour2 = new Color(14,14,33);
		if (!drawPanel.editDate){
			if (down && collide(480,45,135,40,mouseX,mouseY)){
				drawPanel.setDateColor=colour2;
				drawPanel.settingDate=drawPanel.PRESSED;
				drawPanel.org.daytext.setVisible(true);
				drawPanel.org.monthtext.setVisible(true);
				drawPanel.org.yeartext.setVisible(true);
				tempdate=getDate();
				boolean flag=true;
				for (int i=0;i<3;i++){
					if (tempdate[i]==-1){
						flag=false;
					}
				}
				if (flag){
					drawPanel.currentdate=tempdate;
				}
				drawPanel.editDate=true;
			}
			else if (collide(480,45,135,40,mouseX,mouseY)){
				drawPanel.settingDate = drawPanel.HOVER;
				drawPanel.setDateColor=colour1;
			}
			else{
				drawPanel.settingDate=drawPanel.NORMAL;
				drawPanel.setDateColor = colour0;
			}
		}
		else{
			if (down && collide(480,45,135,40,mouseX,mouseY)){
				drawPanel.setDateColor=colour0;
				drawPanel.settingDate=drawPanel.PRESSED;
				drawPanel.org.daytext.setVisible(false);
				drawPanel.org.monthtext.setVisible(false);
				drawPanel.org.yeartext.setVisible(false);
				drawPanel.editDate=false;
			}
			else if (collide(480,45,135,40,mouseX,mouseY)){
				drawPanel.settingDate = drawPanel.HOVER;
				drawPanel.setDateColor=colour1;
			}
			else{
				drawPanel.settingDate=drawPanel.NORMAL;
				drawPanel.setDateColor = colour2;
			}
		}
	}
	
	
}
