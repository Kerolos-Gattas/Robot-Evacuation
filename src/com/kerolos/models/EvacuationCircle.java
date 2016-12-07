package com.kerolos.models;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import com.kerolos.resources.Resources;

public class EvacuationCircle {

	private final int diameter = Resources.CIRCLE_DIAMETER;
	private final int x;
	private final int y;
	private int exitX;
	private int exitY;
	
	public EvacuationCircle(){
		x = (Resources.FRAME_SIZE/2) - (diameter/2);
		y = (Resources.FRAME_SIZE/2) - (diameter/2);		
		double rangeMin = 0;
		double rangeMax = 2 * Math.PI;
		Random r = new Random();
		double randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
		exitX = (int) ((Resources.FRAME_SIZE/2) + ((Resources.CIRCLE_DIAMETER/2) * Math.cos(randomValue)) - (Resources.EXIT_DIAMETER/2));
		exitY = (int) ((Resources.FRAME_SIZE/2) + ((Resources.CIRCLE_DIAMETER/2) * Math.sin(randomValue)) - (Resources.EXIT_DIAMETER/2));
	}
	
	public void paint(Graphics2D g) {
		g.setColor(Color.black);
		g.drawOval(x, y, diameter, diameter);
		g.setColor(Color.green);
		g.fillOval(exitX, exitY, Resources.EXIT_DIAMETER, Resources.EXIT_DIAMETER);
	}
	
	public void resetExit(){
		double rangeMin = 0;
		double rangeMax = 2 * Math.PI;
		Random r = new Random();
		double randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
		exitX = (int) ((Resources.FRAME_SIZE/2) + ((Resources.CIRCLE_DIAMETER/2) * Math.cos(randomValue)) - (Resources.EXIT_DIAMETER/2));
		exitY = (int) ((Resources.FRAME_SIZE/2) + ((Resources.CIRCLE_DIAMETER/2) * Math.sin(randomValue)) - (Resources.EXIT_DIAMETER/2));
	}
	
	public int getExitX(){
		return exitX;
	}
	
	public int getExitY(){
		return exitY;
	}
	
	public int getCircleCenter(){
		return Resources.FRAME_SIZE/2;
	}
	
	public int getRadius(){
		return Resources.CIRCLE_DIAMETER/2;
	}
	
	public int getExitCenterX(){
		return (exitX + (Resources.EXIT_DIAMETER/2));
	}
	
	public int getExitCenterY(){
		return (exitY + (Resources.EXIT_DIAMETER/2)); 
	}

	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
}	
