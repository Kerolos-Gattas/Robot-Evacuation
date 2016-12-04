package com.kerolos.simulations;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import com.kerolos.models.EvacuationCircle;
import com.kerolos.models.Robot;
import com.kerolos.resources.Resources;

public class FirstSimulation  extends JPanel{

	private static final long serialVersionUID = 4176177314569070820L;
	
	private EvacuationCircle circle;
	private Robot firstRobot;
	private Robot secondRobot;
	private int moveSpeed;
	private double thetaFactor;
	private int circleCenter;
	
	public FirstSimulation(){
		circle = new EvacuationCircle();
		firstRobot = new Robot();
		secondRobot = new Robot();
		moveSpeed = 1;
		thetaFactor = 0.0100;
		circleCenter = circle.getCircleCenter() - (Resources.ROBOT_DIAMETER/2);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		circle.paint(g2d);
		
		firstRobot.setColor(Color.blue);
		firstRobot.paint(g2d);
		
		secondRobot.setColor(Color.red);
		secondRobot.paint(g2d);
	}
	
	public void runSimulation() throws InterruptedException{
		Thread.sleep(750);

		for(int i = 0; i < circle.getRadius(); i++){
			firstRobot.moveY(moveSpeed);
			secondRobot.moveY(-moveSpeed);
			this.repaint();

			Thread.sleep(10);
		}

		while(!firstRobot.getFoundExit() && !secondRobot.getFoundExit()){
			
			double firstRobotTheta = (Math.atan2(firstRobot.getY() - circleCenter, firstRobot.getX() - circleCenter));
			double secondRobotTheta = (Math.atan2(secondRobot.getY() - circleCenter, secondRobot.getX() - circleCenter));
			
			firstRobot.setX((int) (circleCenter + (circle.getRadius() * Math.cos(firstRobotTheta + thetaFactor))));
			firstRobot.setY((int) (circleCenter + (circle.getRadius() * Math.sin(firstRobotTheta + thetaFactor))));
			secondRobot.setX((int) (circleCenter + (circle.getRadius() * Math.cos(secondRobotTheta + thetaFactor))));
			secondRobot.setY((int) (circleCenter + (circle.getRadius() * Math.sin(secondRobotTheta + thetaFactor))));
			
			int firstRobotXdis = (int) firstRobot.getCenterX() - circle.getExitCenterX();
			int firstRobotYdis = (int) firstRobot.getCenterY() - circle.getExitCenterY();
			int secondRobotXdis = (int) secondRobot.getCenterX() - circle.getExitCenterX();
			int secondRobotYdis = (int) secondRobot.getCenterY() - circle.getExitCenterY();
			
			int firstRobotExitDistance = (int) Math.sqrt((firstRobotXdis)*(firstRobotXdis) + (firstRobotYdis)*(firstRobotYdis));
			int secondRobotExitDistance = (int) Math.sqrt((secondRobotXdis)*(secondRobotXdis) + (secondRobotYdis)*(secondRobotYdis));

			if(firstRobotExitDistance < 2)
				firstRobot.setFoundExit(true);
			
			if(secondRobotExitDistance < 2)
				secondRobot.setFoundExit(true);
			
			this.repaint();
			Thread.sleep(10);
		}
		
		/*float step = (float) 0.0001;

		if(firstRobot.getFoundExit()){
			int vx = firstRobot.getX() - secondRobot.getX();
			int vy = firstRobot.getY() - secondRobot.getY();
			//moveRobotToExit(secondRobot, firstRobot, vx, vy);
			for (float t = (float) 0.0; t < 1.0; t+= step) {
				secondRobot.moveX((vx*t));
				secondRobot.moveY((vy*t));
				this.repaint();
				Thread.sleep(20);
			}
		}
		else if(secondRobot.getFoundExit()){
			int vx = secondRobot.getX() - firstRobot.getX();
			int vy = secondRobot.getY() - firstRobot.getY();
			//moveRobotToExit(firstRobot, secondRobot, vx, vy);
			for (float t = (float) 0.0; t < 1.0; t+= step) {
				firstRobot.moveX((vx*t));
				firstRobot.moveY((vy*t));
				this.repaint();
				Thread.sleep(20);
			}
		}
		
		if(firstRobot.getFoundExit()){
			float distX = firstRobot.getX() - secondRobot.getX();
			float distY = firstRobot.getY() - secondRobot.getY();
			
			if(Math.abs(distX) > Math.abs(distY)){
				float vx = 1;
				float vy = distY/distX;
				System.out.println(vy);
				System.out.println(distX);
				System.out.println(distY);
				for(int i = 0; i < Math.abs(distX); i++){
					secondRobot.moveX(vx);
					secondRobot.moveY(vy);
					this.repaint();
					Thread.sleep(10);
				}
			}
			else{
				float vy = 1;
				float vx = distX/distY;
				System.out.println(vx);
				System.out.println(distX);
				System.out.println(distY);
				for(int i = 0; i < Math.abs(distY); i++){
					secondRobot.moveX(vx);
					secondRobot.moveY(vy);
					this.repaint();
					Thread.sleep(10);
				}
			}

		}
		else{
			float distX = secondRobot.getX() - firstRobot.getX();
			float distY = secondRobot.getY() - firstRobot.getY();
			
			if(Math.abs(distX) > Math.abs(distY)){
				float vx = 1;
				float vy = distY/distX;
				System.out.println(vy);
				System.out.println(distX);
				System.out.println(distY);
				for(int i = 0; i < Math.abs(distX); i++){
					firstRobot.moveX(vx);
					firstRobot.moveY(vy);
					this.repaint();
					Thread.sleep(10);
				}
			}
			else{
				float vy = 1;
				float vx = distX/distY;
				System.out.println(vx);
				System.out.println(distX);
				System.out.println(distY);
				for(int i = 0; i < Math.abs(distY); i++){
					firstRobot.moveX(vx);
					firstRobot.moveY(vy);
					this.repaint();
					Thread.sleep(10);
				}
			}
		}*/
		if(firstRobot.getFoundExit()){
			double angle = Math.atan2(firstRobot.getY()-secondRobot.getY(), firstRobot.getX()-secondRobot.getX());
			double xVel = 2* Math.cos(angle);
			double yVel = 2* Math.sin(angle);
			while(true){
				secondRobot.moveX(xVel);
				secondRobot.moveY(yVel);
				this.repaint();
				Thread.sleep(10);
				double distance = Math.sqrt((firstRobot.getX() - secondRobot.getX())*(firstRobot.getX() - secondRobot.getX()) + (firstRobot.getY() - secondRobot.getY())*(firstRobot.getY() - secondRobot.getY()));

				if(distance < 5)
					break;
			}
		}
		else{
			double angle = Math.atan2(secondRobot.getY()-firstRobot.getY(), secondRobot.getX()-firstRobot.getX());
			double xVel = 2* Math.cos(angle);
			double yVel = 2* Math.sin(angle);
			while(true){
				firstRobot.moveX(xVel);
				firstRobot.moveY(yVel);
				this.repaint();
				Thread.sleep(10);
				double distance = Math.sqrt((firstRobot.getX() - secondRobot.getX())*(firstRobot.getX() - secondRobot.getX()) + (firstRobot.getY() - secondRobot.getY())*(firstRobot.getY() - secondRobot.getY()));

				if(distance < 5)
					break;
			}
		}

	}
	
	private void moveRobotToExit(Robot firstRobot, Robot secondRobot, int vx, int vy) throws InterruptedException{
		int t = 0;
		while(firstRobot.getX() != secondRobot.getX() || firstRobot.getY() != secondRobot.getY()){
			firstRobot.moveX(firstRobot.getX() + (vx*t));
			firstRobot.moveY(firstRobot.getY() + (vy*t));
			t += 0.1;
			this.repaint();
			Thread.sleep(20);
		}
	}
}
