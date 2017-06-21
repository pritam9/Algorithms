package nQueensRandomRestart;

import java.util.ArrayList;
import java.util.Random;

public class HillClimbAlgo {
	final static int N=nQueens.nQueens;
	Queen[] initLevel;
	Node first; 
	int nodes;
	public static int number_of_states=0;
	
	public HillClimbAlgo(){
		first = new Node(N); 
		initLevel = new Queen[N]; 
		startState();
		nodes=0;
	}
	
	public HillClimbAlgo(Queen[] q){
		
		//Initiate node and fetch the row and columns of the starting Queen, set the state and calculate whether it is being attacked
		first = new Node(N);
		initLevel = new Queen[N];
		for(int i=0; i<q.length; i++){
			initLevel[i] = new Queen(q[i].getRow(), q[i].getColumn(),N);
			//System.out.println("Hueristic is - "+initLevel[i].toString());
		}
		first.setState(initLevel);
		first.computeHeuristic();
		nodes=0;
	}
	
//Getters for starting node and total number of generated nodes
	public Node getFirstNode(){
		return first;
	}
	
	public int getNodes(){
		return nodes;
	}
	
	public void startState(){
		Random gen = new Random();
		for(int i=0; i<N; i++){
			initLevel[i] = new Queen(gen.nextInt(N), i,N);
		}
		first.setState(initLevel);
		first.computeHeuristic();
	}
	
	//HillClimbing Algorithm returning the node position
	public Node hillClimbing(){
		Node currentNode = first;
		System.out.println("Intitial state is - \n"+currentNode.toString());
		
		while(true){
			ArrayList<Node> nextNode = currentNode.generateNeighbours(currentNode);
			nodes+=nextNode.size();
			Node newNode = null;
			for(int i=0; i<nextNode.size(); i++){
				//If next node has least weight than current node then the new node is the next node
				if(nextNode.get(i).compareTo(currentNode) < 0){
					number_of_states++;
					newNode = nextNode.get(i);
				}
			}
			
			if(newNode==null)
				return currentNode;
			//System.out.println("Current Node is - \n"+newNode.toString());
			currentNode = newNode;
		}
	}
	
}