package com.is.astar;

import java.util.Scanner;


public class AStar {

	public static void main(String[] args) {

		// Get User input for initial State
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter initial state of 8-Puzzele:");
		int[] startingStateBoard = getPuzzleBoard(scanner);
		
		// Get User Input for goal state
		System.out.println("\n\nGreat!!\nNow Enter Goal state of 8-Puzzele:");
		int[] goalStateBoard = getPuzzleBoard(scanner);

		System.out.println("\n Select Hueristic approach:\n1.Number out of place\n2.Manhatten Distacne");
		int h_id = scanner.nextInt();
		
		//Call Search method
		AStarImpl.aStarSearch(startingStateBoard, true, h_id,goalStateBoard);
		scanner.close();


	}


	// Store 9 values on board in one dimensional array; for empty block enter 0
	private static int[] getPuzzleBoard(Scanner scan) {
		// TODO Auto-generated method stub
		int[] board = new int[9];
		int index = 0;
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				System.out.println("Enter board value at - ["+i+"]["+j+"] = ");
				board[index] = scan.nextInt();
				index++;
			}
		}
		;
		return board;
	}

}
