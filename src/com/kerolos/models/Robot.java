package com.kerolos.models;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import com.kerolos.resources.Resources;

public class Robot {

	private final int diameter = Resources.ROBOT_DIAMETER;
	private double x;
	private double y;
	private Color color;
	private boolean foundExit;
	private boolean otherRobotFoundExit;
	private int circleCenter;
	private boolean reachedCircle;

	public Robot(){
		x = (Resources.FRAME_SIZE/2) - (Resources.ROBOT_DIAMETER/2);
		y = (Resources.FRAME_SIZE/2) - (Resources.ROBOT_DIAMETER/2);	
		color = Color.black;
		foundExit = false;
		reachedCircle = false;
	}
	
	public Robot(double incrementY){
		x = (Resources.FRAME_SIZE/2) - (Resources.ROBOT_DIAMETER/2);
		y = (Resources.FRAME_SIZE/2) - (Resources.ROBOT_DIAMETER/2) + incrementY;	
		color = Color.black;
		foundExit = false;
		reachedCircle = false;
	}
	
	public Robot(Random r){

		double rangeMin = Resources.FRAME_SIZE/2 - Resources.CIRCLE_DIAMETER/2;
		double rangeMax = Resources.FRAME_SIZE/2 + Resources.CIRCLE_DIAMETER/2;
		
		double distance = 1000;
		double randomValue1 = 0;
		double randomValue2 = 0;
		while(distance > (Resources.CIRCLE_DIAMETER/2)){
			randomValue1 = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
			randomValue2 = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
			distance = Math.sqrt((randomValue1 - (Resources.FRAME_SIZE/2))*(randomValue1 - (Resources.FRAME_SIZE/2)) + (randomValue2 - (Resources.FRAME_SIZE/2))*(randomValue2 - (Resources.FRAME_SIZE/2)));
		}

		x = randomValue1;
		y = randomValue2;	
		
		color = Color.black;
		foundExit = false;
		reachedCircle = false;
	}
	
	public Robot(Color c){
		x = (Resources.FRAME_SIZE/2) - (Resources.ROBOT_DIAMETER/2);
		y = (Resources.FRAME_SIZE/2) - (Resources.ROBOT_DIAMETER/2);	
		color = c;
		foundExit = false;
		reachedCircle = false;
	}
	
	public Robot(double x, double y, Color c){
		this.x = x;
		this.y = y;
		this.color = c;
		foundExit = false;
		reachedCircle = false;
	}
	
	public Robot(Robot robot){
		this.x = robot.x;
		this.y = robot.y;
		this.color = robot.color;
		foundExit = false;
		reachedCircle = false;
	}
	
	public void drawRobot(Graphics2D g) {
		g.setColor(color);
		g.fillOval((int) x, (int) y, diameter, diameter);
	}
	
	public void move(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public void setColor(Color c){
		color = c;
	}
	
	public double getY(){
		return y;
	}
	
	public double getX(){
		return x;
	}
	
	public void moveX(double value){
		x += value;
	}
	
	public void moveY(double value){
		y += value;
	}
	
	public double getCenterX(){
		return (x + (Resources.ROBOT_DIAMETER/2));
	}
	
	public double getCenterY(){
		return (y + (Resources.ROBOT_DIAMETER/2)); 
	}
	
	public boolean getFoundExit(){
		return foundExit;
	}
	
	public void setX(double x){
		this.x = x;
	}
	
	public void setY(double y){
		this.y = y;
	}
	
	public void setFoundExit(boolean value){
		foundExit = value;
	}
	
	public boolean getReachedCircle(){
		return reachedCircle;
	}
	
	public void setReachedCircle(boolean value){
		reachedCircle = value;
	}
}
