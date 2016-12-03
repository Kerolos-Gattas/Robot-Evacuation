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
	private int radius;
	private int moveSpeed;
	
	public FirstSimulation(){
		circle = new EvacuationCircle();
		firstRobot = new Robot();
		secondRobot = new Robot();
		
		radius = Resources.CIRCLE_DIAMETER/2;
		moveSpeed = 1;
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

		for(int i = 0; i < radius; i++){
			firstRobot.moveY(moveSpeed);
			secondRobot.moveY(-moveSpeed);
			this.repaint();

			Thread.sleep(20);
		}
		System.out.println((Math.atan2( firstRobot.getX() - (Resources.FRAME_SIZE/2) , (Resources.FRAME_SIZE/2) - firstRobot.getY() ) +(Resources.FRAME_SIZE/2)) %(Resources.FRAME_SIZE/2));
		while(!firstRobot.getFoundExit() && !secondRobot.getFoundExit()){
			//firstRobot.setX(x);
			//firstRobot.setY(y);
			//secondRobot.setX(x);
			//secondRobot.setY(y);

		}
	}
}
