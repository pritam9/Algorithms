package com.is.astar;

import java.util.ArrayList;



public interface StateInformation {
	
	// determine if current state is goal
		boolean isGoal();

		// generate successors to the current state
		ArrayList<StateInformation> genSuccessors();

		// determine cost from initial state to THIS state
		double findCost();

		// print the current state
		public void printState();

		// compare the actual state data
		public boolean equals(StateInformation s);

}
