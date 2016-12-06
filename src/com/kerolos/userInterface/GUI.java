package com.kerolos.userInterface;

import java.awt.Color;

import javax.swing.JFrame;

import com.kerolos.models.EvacuationCircle;
import com.kerolos.models.Robot;
import com.kerolos.resources.Resources;
import com.kerolos.simulations.FirstSimulation;
import com.kerolos.simulations.Simulation;

public class GUI{

	public static void main(String[] args) {

		JFrame frame = new JFrame(Resources.APP_TITLE);
		frame.setSize(Resources.FRAME_SIZE, Resources.FRAME_SIZE);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Simulation simulation1 = new Simulation();
		frame.add(simulation1);
		simulation1.runSimulation();
		while(!simulation1.getFirstSimulationDone()){
			System.out.println("reached");

		}
		
		//frame.remove(simulation1);
		System.out.println("reached");

		Robot firstRobot = new Robot(500,500,Color.red);
		Robot secondRobot = new Robot(600,450,Color.blue);
		Simulation simulation2 = new Simulation(firstRobot, secondRobot, true);
		frame.add(simulation2);
		simulation2.runSimulation();
	}
}
