package com.kerolos.userInterface;

import javax.swing.JFrame;

import com.kerolos.resources.Resources;
import com.kerolos.simulations.FirstSimulation;

public class GUI{

	public static void main(String[] args) {

		JFrame frame = new JFrame(Resources.APP_TITLE);
		FirstSimulation firstSimulation = new FirstSimulation();
		frame.add(firstSimulation);
		frame.setSize(Resources.FRAME_SIZE, Resources.FRAME_SIZE);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			firstSimulation.runSimulation();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
