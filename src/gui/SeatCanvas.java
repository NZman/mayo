package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JComponent;

import base.Area;

public class SeatCanvas extends JComponent{
	Area area;
	
	public SeatCanvas(Area area) {
		super();
		//this.setPreferredSize(new Dimension(300,300));
		this.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		setArea(area);
	}
	
	public void setArea(Area area) {
		this.area = area;
		this.setPreferredSize(new Dimension(area.getWidth(), area.getHeight()));
		repaint(0,0,area.getWidth(),area.getHeight());
	}
	
	public Area getArea() {
		return area;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(area != null) {
			g.setColor(Color.PINK);
			g.drawRect(0, 0, area.getWidth(), area.getHeight());
		}
	} 
}