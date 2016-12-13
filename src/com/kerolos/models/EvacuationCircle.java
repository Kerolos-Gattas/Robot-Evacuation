package com.kerolos.models;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import com.kerolos.resources.Resources;

public class EvacuationCircle {

	private final int diameter = Resources.CIRCLE_DIAMETER;
	private final int exitDiameter = Resources.EXIT_DIAMETER;
	
	//x and y start coordinates for the evacuation circle
	private final int x;
	private final int y;
	
	//x and y start coordinates for the exit circle
	private int exitX;
	private int exitY;
	
	public EvacuationCircle(){
		x = (Resources.FRAME_SIZE/2) - (diameter/2);
		y = (Resources.FRAME_SIZE/2) - (diameter/2);	
		
		//determine a random x and y coordinates for the exit on the evacuation circle using a random angle
		double rangeMin = 0;
		double rangeMax = 2 * Math.PI;
		Random r = new Random();
		double randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
		exitX = (int) ((Resources.FRAME_SIZE/2) + ((diameter/2) * Math.cos(randomValue)) - (exitDiameter/2));
		exitY = (int) ((Resources.FRAME_SIZE/2) + ((diameter/2) * Math.sin(randomValue)) - (exitDiameter/2));
	}
	
	//Draw evacuation circle and the exit
	public void paint(Graphics2D g) {
		g.setColor(Color.black);
		g.drawOval(x, y, diameter, diameter);
		g.setColor(Color.green);
		g.fillOval(exitX, exitY, exitDiameter, exitDiameter);
	}
	
	//Determine a new exit location on the evacuation circle
	public void resetExit(){
		//determine a random x and y coordinates for the exit on the evacuation circle using a random angle
		double rangeMin = 0;
		double rangeMax = 2 * Math.PI;
		Random r = new Random();
		double randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
		exitX = (int) ((Resources.FRAME_SIZE/2) + ((diameter/2) * Math.cos(randomValue)) - (exitDiameter/2));
		exitY = (int) ((Resources.FRAME_SIZE/2) + ((diameter/2) * Math.sin(randomValue)) - (exitDiameter/2));
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
		return diameter/2;
	}
	
	public int getExitCenterX(){
		return (exitX + (exitDiameter/2));
	}
	
	public int getExitCenterY(){
		return (exitY + (exitDiameter/2)); 
	}

	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
}	
