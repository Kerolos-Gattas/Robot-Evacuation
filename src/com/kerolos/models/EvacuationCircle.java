package com.kerolos.models;

import java.awt.Graphics2D;

import com.kerolos.resources.Resources;

public class EvacuationCircle {

	private final int diameter = Resources.CIRCLE_DIAMETER;
	private final int x;
	private final int y;
	private final int centerCircleX;
	private final int centerCircleY;
	private double exitX;
	private double exitY;
	
	public EvacuationCircle(){
		x = (Resources.FRAME_SIZE/2) - (diameter/2);
		y = (Resources.FRAME_SIZE/2) - (diameter/2);
		centerCircleX = (x + (diameter/2));
		centerCircleY = (y + (diameter/2));
	}
	
	public void paint(Graphics2D g) {
		g.drawOval(x, y, diameter, diameter);
	}
}	
