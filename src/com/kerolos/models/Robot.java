package com.kerolos.models;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.kerolos.resources.Resources;

public class Robot {

	private final int diameter = Resources.ROBOT_DIAMETER;
	private int x;
	private int y;
	private Color color;
	private boolean foundExit;
	
	public Robot(){
		x = (Resources.FRAME_SIZE/2) - (Resources.ROBOT_DIAMETER/2);
		y = (Resources.FRAME_SIZE/2) - (Resources.ROBOT_DIAMETER/2);	
		color = Color.BLACK;
		foundExit = false;
	}
	
	public void paint(Graphics2D g) {
		g.setColor(color);
		g.fillOval(x, y, diameter, diameter);
	}
	
	public void setColor(Color c){
		color = c;
	}
	
	public int getY(){
		return y;
	}
	
	public int getX(){
		return x;
	}
	
	public void moveX(int value){
		x += value;
	}
	
	
	public void moveY(int value){
		y += value;
	}
	
	public boolean getFoundExit(){
		return foundExit;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
}
