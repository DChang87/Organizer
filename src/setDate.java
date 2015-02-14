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
