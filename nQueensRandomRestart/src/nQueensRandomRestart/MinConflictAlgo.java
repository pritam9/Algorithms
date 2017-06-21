package nQueensRandomRestart;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;

public class MinConflictAlgo {
	 int[] rows;
	 Queen[] queens;
	 Random random = new Random();

     /**
      * Creates a new n x n board and randomly fills it with one
      * queen in each column.
      */
     public MinConflictAlgo(int n ) {
		// TODO Auto-generated constructor stub
    	 rows = new int[n];
    	 queens=new Queen[n];
         arrange();
         arrangeBoard();
         
	}
     void arrange() {
         for (int i = 0, n = rows.length; i < n; i++) {
             rows[i] = i;
         }
         for (int i = 0, n = rows.length; i < n; i++) {
             int j = random.nextInt(n);
             int rowToSwap = rows[i];
             rows[i] = rows[j];
             rows[j] = rowToSwap;
         }
        /* for (int i = 0, n = rows.length; i < n; i++) {
             System.out.println(rows[i]);
         }*/
         
     }
     
     //Scramble board such that each column is having only one queen
     void arrangeBoard(){
    	 ArrayList<Integer> usedColumns = new ArrayList<Integer>(); 
    	 for(int i=0; i<queens.length; i++){
 			queens[i] = new Queen(random.nextInt(queens.length),i,queens.length);
 		}
     }
     
     void solve() {
    	 //PrintStream ps = new PrintStream(System.console())
    	 
         int moves = 0;
         int states = 0;
         int number_of_restarts=0;

         // This would be a lot faster if we used arrays of ints instead.
         ArrayList<Integer> candidates = new ArrayList<Integer>();

         while (true) {

             // Find nastiest queen
             int maxConflicts = 0;
             candidates.clear();
             for (int c = 0; c < rows.length; c++) {
                 int conflicts = conflicts(rows[c], c);
                 if (conflicts == maxConflicts) {
                     candidates.add(c);
                 } else if (conflicts > maxConflicts) {
                     maxConflicts = conflicts;
                     candidates.clear();
                     candidates.add(c);
                 }
             }

             if (maxConflicts == 0) {
                 // Checked *every* queen and found no conflicts
            	 states+=moves;
            	 System.out.println("Min-COnflict : Number of state changes "+states+"\nMin-Conflict : Number of Random Restarts - "+number_of_restarts);
                 return;
             }

             // Pick a random queen from those that had the most conflicts
             int worstQueenColumn =
                     candidates.get(random.nextInt(candidates.size()));

             // Move her to the place with the least conflicts.
             int minConflicts = rows.length;
             candidates.clear();
             for (int r = 0; r < rows.length; r++) {
                 int conflicts = conflicts(r, worstQueenColumn);
                 if (conflicts == minConflicts) {
                     candidates.add(r);
                 } else if (conflicts < minConflicts) {
                     minConflicts = conflicts;
                     candidates.clear();
                     candidates.add(r);
                 }
             }

             if (!candidates.isEmpty()) {
                 rows[worstQueenColumn] =
                     candidates.get(random.nextInt(candidates.size()));
             }

             moves++;
             if (moves == rows.length * 2) {
            	 states+=moves;
                 // TO avoid conflicts, I am restarting at random moves.
            	 number_of_restarts++;
                 arrange();
                 moves = 0;
             }
         }
     }
     
     
     //MInConflict with Queen Object array
     void solve_conflict() {
         int moves = 0;
         int number_of_restarts=0;

         // This would be a lot faster if we used arrays of ints instead.
         ArrayList<Queen> candidates = new ArrayList<Queen>();

         while (true) {

             // Find nastiest queen
             int maxConflicts = 0;
             candidates.clear();
             for (int col = 0; col < queens.length; col++) {
                 int conflicts = queen_conflicts(queens[col]);
                 if (conflicts == maxConflicts) {
                     candidates.add(queens[col]);
                 } else if (conflicts > maxConflicts) {
                     maxConflicts = conflicts;
                     candidates.clear();
                     candidates.add(queens[col]);
                 }
             }

             if (maxConflicts == 0) {
                 // Checked *every* queen and found no conflicts
            	 System.out.println("Min-Conflict number of restarts are - "+number_of_restarts);
                 return;
             }

             // Pick a random queen from those that had the most conflicts
             int worstQueenColumn =
                     candidates.get(random.nextInt(candidates.size())).getColumn();

             // Move her to the place with the least conflicts.
             int minConflicts = queens.length;
             candidates.clear();
             for (int r = 0; r < queens.length; r++) {
                 int conflicts = queen_conflicts(queens[r]);
                 if (conflicts == minConflicts) {
                     candidates.add(queens[r]);
                 } else if (conflicts < minConflicts) {
                     minConflicts = conflicts;
                     candidates.clear();
                     candidates.add(queens[r]);
                 }
             }

             if (!candidates.isEmpty()) {
                 queens[worstQueenColumn] =
                     candidates.get(random.nextInt(candidates.size()));
             }

             moves++;
             if (moves == queens.length * 2) {
                 // Trying too long... start over.
            	 number_of_restarts++;
                 arrangeBoard();
                 moves = 0;
             }
         }
     }

     int conflicts(int row, int col) {
         int count = 0;
         for (int c = 0; c < rows.length; c++) {
             if (c == col) continue;
             int r = rows[c];
             if (r == row || Math.abs(r-row) == Math.abs(c-col)) count++;
         }
         return count;
     }
     
     
     //Get conflicts for queen
     int queen_conflicts(Queen queen) {
         int count = 0;
         for (int c = 0; c < queens.length; c++) {
             if (c == queen.getColumn()) continue;
             int r = queens[c].getRow();
             if (r == queen.getRow() || Math.abs(r-queen.getRow()) == Math.abs(c-queen.getColumn())) count++;
         }
         return count;
     }
     void print(PrintStream stream) {
         for (int r = 0; r < rows.length; r++) {
             for (int c = 0; c < rows.length; c++) {
                 stream.print(rows[c] == r ? "Q " : "X ");
             }
             stream.println();
         }
     }
 

}
