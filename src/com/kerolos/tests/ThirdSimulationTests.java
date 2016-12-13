package com.kerolos.tests;

import javax.swing.JFrame;

import org.junit.Test;

import com.kerolos.resources.Resources;
import com.kerolos.simulations.Simulation;

public class ThirdSimulationTests {

	@Test
	public void thirdSimulationtest() {
		JFrame frame = new JFrame(Resources.APP_TITLE);
		frame.setSize(Resources.FRAME_SIZE, Resources.FRAME_SIZE);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Simulation simulation = new Simulation(false, 0, false, 0, true, 20);
		frame.add(simulation);
		simulation.runSimulation();
	}

}
