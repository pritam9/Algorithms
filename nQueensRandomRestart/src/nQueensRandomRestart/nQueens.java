package nQueensRandomRestart;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;

public class nQueens {

	static int nQueens;								//To make the value of number of queens as static

	//method to design the board by placing the given number of queens in the random places
	public static Queen[] designBoard(int nQueens){
		Queen[] start = new Queen[nQueens];
		Random gen = new Random();
		for(int i=0; i<nQueens; i++){
			int j=i;
			if(j>nQueens){
				j=j%nQueens;
			}
			start[i] = new Queen(gen.nextInt(nQueens),j,nQueens);
		}
		return start;
	}

	//Method to design a chessboard 
	public static int[][] chessboard()
	{   int[][] board;
	board = new int [nQueens][nQueens]; 
	for(int j = 0; j < nQueens; j++){
		for(int i = 0; i < nQueens; i++){
			board[i][j] = 0;
		}
	}
	return board;
	}

	public static void main(String[] args)
	{

		int numberOfRuns = 2000;
		int hcCount=0;
		int rrCount=0;
		int[][] board;
		int hillClimbNodes=0, randomRestartNodes=0;
		//Inputting value from the user
		Scanner sc=new Scanner(new InputStreamReader(System.in));
		System.out.println("Enter the number of queens:");
		nQueens=sc.nextInt();

		Random gen = new Random();
		//Generate random chess board for n queens 
		Queen[] initChessBoard = designBoard(nQueens);
		//System.out.println("Array is - "+initChessBoard);
		// board=chessboard();
		//HillClimbAlgo hillClimb = new HillClimbAlgo(initChessBoard);
		/*if(hillSolved.getHeuristic()==0){
		 hcCount++;
		}
if(randomSolved.getHeuristic()==0){
	rrCount++;
}*/

		//Node hillSolved = hillClimb.hillClimbing();
		//}
		RandomRestart randomRestart = new RandomRestart(initChessBoard);
		Node randomSolved = randomRestart.randomRestart();
		System.out.println("Restart Solved \n"+randomSolved.toString()); 
		System.out.println("Random restart : Number of state changes  :"+HillClimbAlgo.number_of_states);
		System.out.println("Random restart : Number of Random Restarts:"+RandomRestart.getNumber_of_restarts());
		MinConflictAlgo minConflict = new MinConflictAlgo(nQueens);
		minConflict.print(System.out);
		minConflict.solve();
		minConflict.print(System.out);
		//minConflict.solve_conflict();
	}




}
