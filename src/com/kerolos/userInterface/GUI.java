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
		
		Simulation simulation = new Simulation();
		frame.add(simulation);
		simulation.runSimulation();
	}
}
