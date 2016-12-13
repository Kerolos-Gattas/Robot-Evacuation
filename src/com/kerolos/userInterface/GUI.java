package com.kerolos.userInterface;

import javax.swing.JFrame;
import com.kerolos.resources.Resources;
import com.kerolos.simulations.Simulation;

public class GUI{

	public static void main(String[] args) {

		JFrame frame = new JFrame(Resources.APP_TITLE);
		frame.setSize(Resources.FRAME_SIZE, Resources.FRAME_SIZE);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Test Cases
		
		Simulation simulation = new Simulation(true, 1, true, 1, true, 1);  //Testing all three scenarios once
		//Simulation simulation = new Simulation(true, 5, true, 5, true, 5);  //Testing all three scenarios 5 times each
		//Simulation simulation = new Simulation(true, 30, false, 0, false, 0);  //Testing the first scenario 30 times
		//Simulation simulation = new Simulation(false, 0, true, 30, false, 0);  //Testing the second scenario 30 times
		//Simulation simulation = new Simulation(false, 0, false, 0, true, 30);    //Testing the third scenario 30 times

		frame.add(simulation);
		simulation.runSimulation();		
	}
}
