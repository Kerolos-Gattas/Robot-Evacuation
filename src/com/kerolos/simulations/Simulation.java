package com.kerolos.simulations;

import javax.swing.JPanel;

import com.kerolos.models.EvacuationCircle;
import com.kerolos.models.Robot;

public class Simulation extends JPanel implements Runnable{

	private static final long serialVersionUID = 6242576973536189470L;
	
	private EvacuationCircle circle;
	private Robot robot;
	private int moveSpeed;
	private double thetaFactor;
	private int circleCenter;
	private boolean foundExit;
	private boolean otherRobotFoundExit;
	
	public Simulation(){
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
