package com.kerolos.simulations;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.kerolos.models.EvacuationCircle;
import com.kerolos.models.Robot;
import com.kerolos.resources.Resources;

public class Simulation extends JPanel{

	private static final long serialVersionUID = 6000139980679959727L;

	private EvacuationCircle circle;
	private Robot firstRobot;
	private Robot secondRobot;
	private double moveSpeed;
	private double thetaFactor;
	private int circleCenterMinusRobotRadius; //Subtracting the circle radius here so that we can consider the robot from its center

	//simulation counters and booleans (determines which simulation to run and how many times)
	private boolean runFirstSimulation;
	private int firstSimulationCounter;

	private boolean runSecondSimulation;
	private int secondSimulationCounter;

	private boolean runThirdSimulation;
	private int thirdSimulationCounter;

	//runtime variables
	private long startTime;
	private long endTime;
	private ArrayList<Long> totalTime;

	public Simulation(boolean runFirstSimulation, int firstSimulationCounter, boolean runSecondSimulation, int secondSimulationCounter,
			boolean runThirdSimulation, int thirdSimulationCounter){
		circle = new EvacuationCircle();
		this.thetaFactor = 0.0100;
		this.runFirstSimulation = runFirstSimulation;
		this.firstSimulationCounter = firstSimulationCounter;
		this.runSecondSimulation = runSecondSimulation;
		this.secondSimulationCounter = secondSimulationCounter;
		this.runThirdSimulation = runThirdSimulation;
		this.thirdSimulationCounter = thirdSimulationCounter;
		moveSpeed = 1.5;
		circleCenterMinusRobotRadius = circle.getCircleCenter() - (Resources.ROBOT_DIAMETER/2);
		totalTime = new ArrayList<Long>();
		//Determine which simulation to run first
		if(runFirstSimulation && firstSimulationCounter > 0){
			firstRobot = new Robot(Color.red);
			secondRobot = new Robot(Color.blue);			
			this.firstSimulationCounter--;
		}
		else if(runSecondSimulation && secondSimulationCounter > 0){
			firstRobot = new Robot(100);
			firstRobot.setColor(Color.red);
			secondRobot = new Robot();
			secondRobot.setColor(Color.blue);
			this.secondSimulationCounter--;
		}
		else if(runThirdSimulation && thirdSimulationCounter > 0){
			Random r = new Random();
			firstRobot = new Robot(r);
			firstRobot.setColor(Color.red);
			secondRobot = new Robot(r);	
			secondRobot.setColor(Color.blue);
			this.thirdSimulationCounter--;
		}

	}

	//Draw evacuation circle, robots and the exit
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

	//This method starts the simulation, the two robots Move to the nearest point on the circle.
	public void runSimulation(){

		//get Starting time for the simulation
		startTime = System.currentTimeMillis();
		
		//getting the angle between the evacuation circle center and the robot to determine the nearest point to the robot from the circle
		double angle = Math.atan2(firstRobot.getCenterY()-circle.getCircleCenter(), firstRobot.getCenterX()-circle.getCircleCenter());
		double xVel1 = moveSpeed* Math.cos(angle);
		double yVel1 = moveSpeed* Math.sin(angle);

		//getting the angle between the evacuation circle center and the robot to determine the nearest point to the robot from the circle
		double angle1 = Math.atan2(secondRobot.getCenterY()-circle.getCircleCenter(), secondRobot.getCenterX()-circle.getCircleCenter());
		double xVel2 = moveSpeed* Math.cos(angle1);
		double yVel2 = moveSpeed* Math.sin(angle1);

		//Determining the x and y coordinates of the nearest point on the circle to the robot
		double xPoint = (circleCenterMinusRobotRadius + (circle.getRadius() * Math.cos(angle)));
		double yPoint = (circleCenterMinusRobotRadius + (circle.getRadius() * Math.sin(angle)));
		double xPoint1 = (circleCenterMinusRobotRadius + (circle.getRadius() * Math.cos(angle1)));
		double yPoint1 = (circleCenterMinusRobotRadius + (circle.getRadius() * Math.sin(angle1)));

		int delay = 15; //A delay for the timers

		Timer timer = new Timer(delay, null); 
		timer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//move robot towards the circle
				firstRobot.move(firstRobot.getX() + xVel1,firstRobot.getY() +  yVel1);

				//Calculate the distance between the robot and the circle
				double distance1 = Math.sqrt((firstRobot.getX() - xPoint)*(firstRobot.getX() - xPoint) + (firstRobot.getY() - yPoint)*(firstRobot.getY() - yPoint));

				//check if robot has reached the circle
				if(distance1 < 1){
					findExit(firstRobot, secondRobot, thetaFactor); //try to find the exit
					timer.stop();
				}

				//check if the other robot has found the exit
				if(secondRobot.getFoundExit()){
					moveToExit(firstRobot, secondRobot); //move to the exit
					timer.stop();
				}
				repaint();
			}
		});
		timer.start();

		Timer timer1 = new Timer(delay, null); 
		timer1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//move robot towards the circle
				secondRobot.move(secondRobot.getX() + xVel2, secondRobot.getY() + yVel2);

				//Calculate the distance between the robot and the circle
				double distance1 = Math.sqrt((secondRobot.getX() - xPoint1)*(secondRobot.getX() - xPoint1) + (secondRobot.getY() - yPoint1)*(secondRobot.getY() - yPoint1));

				//check if robot has reached the circle
				if(distance1 < 1){
					findExit(secondRobot, firstRobot, -thetaFactor); //try to find the exit
					timer1.stop();
				}
				if(firstRobot.getFoundExit()){
					moveToExit(secondRobot, firstRobot); //move to the exit
					timer1.stop();
				}
				repaint();
			}
		});
		timer1.start();		       
	}

	//The two robots move around the circle poetries to find the exit
	public void findExit(Robot firstRobot, Robot secondRobot, double theta){

		int delay = 15;
		Timer timer = new Timer(delay, null); //Have to do it this way so that I can stop the timer from 
		//inside the listener.

		timer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//get the current angle between the robot and the circle center
				double firstRobotTheta = (Math.atan2(firstRobot.getY() - circleCenterMinusRobotRadius, firstRobot.getX() - circleCenterMinusRobotRadius));

				//set X and Y coordinates to the next point on the circle
				firstRobot.move((int) (circleCenterMinusRobotRadius + (circle.getRadius() * Math.cos(firstRobotTheta + theta))), (int) (circleCenterMinusRobotRadius + (circle.getRadius() * Math.sin(firstRobotTheta + theta))));

				//adjusting the points to the center of the robot to detect collision with the exit
				int firstRobotXdis = (int) firstRobot.getCenterX() - circle.getExitCenterX();
				int firstRobotYdis = (int) firstRobot.getCenterY() - circle.getExitCenterY();

				//get the distance between the exit and the robot
				int firstRobotExitDistance = (int) Math.sqrt((firstRobotXdis)*(firstRobotXdis) + (firstRobotYdis)*(firstRobotYdis));

				//if robot found the exit stop moving
				if(firstRobotExitDistance < 2){
					firstRobot.setFoundExit(true); 
					timer.stop();
				}

				//if other robot found the exit then move towards it
				if(secondRobot.getFoundExit()){
					moveToExit(firstRobot, secondRobot);
					timer.stop();
				}

				repaint();
			}
		});
		timer.start();
	}

	//This method makes the robot move towards the exit/the robot that found the exit.
	public void moveToExit(Robot firstRobot, Robot secondRobot){

		int delay = 15;

		Timer timer = new Timer(delay, null); 
		timer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//calculate the angle between the two robots
				double angel = Math.atan2(secondRobot.getY()-firstRobot.getY(), secondRobot.getX()-firstRobot.getX());
				double xVel = moveSpeed* Math.cos(angel);
				double yVel = moveSpeed* Math.sin(angel);

				//move the robot towards the other robot
				firstRobot.move(firstRobot.getX() + xVel, firstRobot.getY() + yVel);

				//calculate the distance between the two robots
				double distance = Math.sqrt((firstRobot.getX() - secondRobot.getX())*(firstRobot.getX() - secondRobot.getX()) + (firstRobot.getY() - secondRobot.getY())*(firstRobot.getY() - secondRobot.getY()));
				repaint();
				
				//Stop moving if the robot reaches the exit
				if(distance < 5){
					endTime = System.currentTimeMillis();
					totalTime.add(endTime - startTime);
					
					//calculate average if all simulations are done
					if(firstSimulationCounter < 1 && secondSimulationCounter < 1 && thirdSimulationCounter < 1){
						long total = 0;
						System.out.println("Running Times:");
						for(int i = 0; i < totalTime.size(); i++){
							total += totalTime.get(i);
							System.out.print(totalTime.get(i) + ", ");
						}
						long average = total/totalTime.size();
						System.out.println("\nThe average running time is: " + average);
					}
					
					//check which simulation to run next
					if(runFirstSimulation && firstSimulationCounter > 0){
						Robot firstRobot = new Robot(Color.red);
						Robot secondRobot = new Robot(Color.blue);
						
						resetRobots(firstRobot, secondRobot);
						circle.resetExit();
						
						firstSimulationCounter--;
						runSimulation();
					}
					else if(runSecondSimulation && secondSimulationCounter > 0){
						Robot firstRobot = new Robot(100);
						Robot secondRobot = new Robot();
						
						resetRobots(firstRobot, secondRobot);
						circle.resetExit();
						
						secondSimulationCounter--;
						runSimulation();					
					}
					else if(runThirdSimulation && thirdSimulationCounter > 0){
						Random r = new Random();
						Robot firstRobot = new Robot(r);
						Robot secondRobot = new Robot(r);
						
						resetRobots(firstRobot, secondRobot);
						circle.resetExit();
						
						thirdSimulationCounter--;
						runSimulation();
					}
					
					timer.stop();
				}
			}
		});
		timer.start();	
	}

	//reset the locations of the two robots and restart the simulation
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
}
