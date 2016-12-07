package com.kerolos.simulations;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.kerolos.models.EvacuationCircle;
import com.kerolos.models.Robot;
import com.kerolos.resources.Resources;

public class Simulation extends JPanel{

	private EvacuationCircle circle;
	private Robot firstRobot;
	private Robot secondRobot;
	private int moveSpeed;
	private double thetaFactor;
	private int circleCenter;
	private boolean firstSimulationDone;
	private boolean secondSimulationDone;
	
	public Simulation(){
		circle = new EvacuationCircle();
		this.firstRobot = new Robot(Color.red);
		this.secondRobot = new Robot(Color.blue);
		this.thetaFactor = 0.0100;
		moveSpeed = 1;
		circleCenter = circle.getCircleCenter() - (Resources.ROBOT_DIAMETER/2);
		firstSimulationDone = false;
		secondSimulationDone = false;
	}

	public Simulation(Robot first, Robot second, boolean firstSimulationDone){
		circle = new EvacuationCircle();
		this.firstRobot = new Robot(first);
		this.secondRobot = new Robot(second);
		this.thetaFactor = 0.0100;
		moveSpeed = 1;
		circleCenter = circle.getCircleCenter() - (Resources.ROBOT_DIAMETER/2);
		this.firstSimulationDone = firstSimulationDone;
		secondSimulationDone = false;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		firstRobot.drawRobot(g2d);
		secondRobot.drawRobot(g2d);
		circle.paint(g2d);
	}
	
	public void runSimulation(){
		try {
			Thread.sleep(400);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		double angle = Math.atan2(firstRobot.getCenterY()-circle.getCircleCenter(), firstRobot.getCenterX()-circle.getCircleCenter());
		double xVel1 = 1* Math.cos(angle);
		double yVel1 = 1* Math.sin(angle);
		
		double angle1 = Math.atan2(secondRobot.getCenterY()-circle.getCircleCenter(), secondRobot.getCenterX()-circle.getCircleCenter());
		double xVel12 = 1* Math.cos(angle1);
		double yVel12 = 1* Math.sin(angle1);
		
		double xPoint = (circleCenter + (circle.getRadius() * Math.cos(angle)));
		double yPoint = (circleCenter + (circle.getRadius() * Math.sin(angle)));
		
		double xPoint1 = (circleCenter + (circle.getRadius() * Math.cos(angle1)));
		double yPoint1 = (circleCenter + (circle.getRadius() * Math.sin(angle1)));
		
		double distance1 = Math.sqrt((firstRobot.getX() - circle.getCircleCenter())*(firstRobot.getX() - circle.getCircleCenter()) + (firstRobot.getY() - circle.getCircleCenter())*(firstRobot.getY() - circle.getCircleCenter()));
		double distance2 = Math.sqrt((secondRobot.getX() - circle.getCircleCenter())*(secondRobot.getX() - circle.getCircleCenter()) + (secondRobot.getY() - circle.getCircleCenter())*(secondRobot.getY() - circle.getCircleCenter()));

		int delay = 15;
		
		Timer timer = new Timer(delay, null); 
	    timer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
    			firstRobot.move(firstRobot.getX() + xVel1,firstRobot.getY() +  yVel1);
    			double distance1 = Math.sqrt((firstRobot.getX() - xPoint)*(firstRobot.getX() - xPoint) + (firstRobot.getY() - yPoint)*(firstRobot.getY() - yPoint));
    			if(distance1 < 1){
    				findExit(firstRobot, secondRobot, thetaFactor);
    				timer.stop();
    			}
            	if(secondRobot.getFoundExit()){
            		moveToExit(firstRobot, secondRobot);
            		timer.stop();
            	}
                repaint();
            }
        });
        timer.start();
        
		Timer timer1 = new Timer(delay, null); 
		timer1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
    			secondRobot.move(secondRobot.getX() + xVel12, secondRobot.getY() + yVel12);
    			double distance1 = Math.sqrt((secondRobot.getX() - xPoint1)*(secondRobot.getX() - xPoint1) + (secondRobot.getY() - yPoint1)*(secondRobot.getY() - yPoint1));
    			if(distance1 < 1){
    				findExit(secondRobot, firstRobot, -thetaFactor);
    				timer1.stop();
    			}
            	if(firstRobot.getFoundExit()){
            		moveToExit(secondRobot, firstRobot);
            		timer1.stop();
            	}
                repaint();
            }
        });
        timer1.start();		       
	}
	
	public void findExit(Robot firstRobot1, Robot secondRobot1, double theta){
		
		int delay = 15;
		Robot firstRobot = firstRobot1;
		Robot secondRobot = secondRobot1;
		Timer timer = new Timer(delay, null); 
		timer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
        		double firstRobotTheta = (Math.atan2(firstRobot.getY() - circleCenter, firstRobot.getX() - circleCenter));
        		firstRobot.move((int) (circleCenter + (circle.getRadius() * Math.cos(firstRobotTheta + theta))), (int) (circleCenter + (circle.getRadius() * Math.sin(firstRobotTheta + theta))));
        		
        		int firstRobotXdis = (int) firstRobot.getCenterX() - circle.getExitCenterX();
        		int firstRobotYdis = (int) firstRobot.getCenterY() - circle.getExitCenterY();
        		
        		int firstRobotExitDistance = (int) Math.sqrt((firstRobotXdis)*(firstRobotXdis) + (firstRobotYdis)*(firstRobotYdis));

        		if(firstRobotExitDistance < 2){
        			firstRobot.setFoundExit(true); 
        			timer.stop();
        		}
        		
            	if(secondRobot.getFoundExit()){
            		moveToExit(firstRobot, secondRobot);
            		timer.stop();
            	}
            	
                repaint();
            }
        });
        timer.start();
	}
	
	public void moveToExit(Robot firstRobot1, Robot secondRobot1){
		int delay = 15;
		Robot firstRobot = firstRobot1;
		Robot secondRobot = secondRobot1;
		
		Timer timer = new Timer(delay, null); 
		timer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
    			double angel = Math.atan2(secondRobot.getY()-firstRobot.getY(), secondRobot.getX()-firstRobot.getX());
    			double xVel = 2* Math.cos(angel);
    			double yVel = 2* Math.sin(angel);
    			firstRobot.move(firstRobot.getX() + xVel, firstRobot.getY() + yVel);
    			
				double distance = Math.sqrt((firstRobot.getX() - secondRobot.getX())*(firstRobot.getX() - secondRobot.getX()) + (firstRobot.getY() - secondRobot.getY())*(firstRobot.getY() - secondRobot.getY()));
                repaint();

				if(distance < 5){
					if(!firstSimulationDone){
						firstSimulationDone = true;
						Robot firstRobot = new Robot(100);
						Robot secondRobot = new Robot();
						resetRobots(firstRobot, secondRobot);
						circle.resetExit();
						runSimulation();
					}
					else{
						secondSimulationDone = true;
						Random r = new Random();
						//double oneHunderedAndEightyDegreePointX = (Resources.FRAME_SIZE/2) - (Resources.CIRCLE_DIAMETER/2) + 250;
						//double ninetyDegreePointY = (Resources.FRAME_SIZE/2) - (Resources.CIRCLE_DIAMETER/2) + 250;

						Robot firstRobot = new Robot(r);
						Robot secondRobot = new Robot(r);
						
						resetRobots(firstRobot, secondRobot);
						circle.resetExit();
						runSimulation();
					}
					timer.stop();
				}
            }
        });
        timer.start();	
	}
	
	public void resetRobots(Robot first, Robot second){
		firstRobot.setX(first.getX());
		firstRobot.setY(first.getY());
		secondRobot.setX(second.getX());
		secondRobot.setY(second.getY());
		
		firstRobot.setFoundExit(false);
		secondRobot.setFoundExit(false);
		
		firstRobot.setReachedCircle(false);
		secondRobot.setReachedCircle(false);

		repaint();
	}
	
	public boolean getFirstSimulationDone(){
		return firstSimulationDone;
	}
}
