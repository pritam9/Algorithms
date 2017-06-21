package nQueensRandomRestart;

public class RandomRestart {
	HillClimbAlgo hillClimb;
	int nodes;
	Node start;
	private static int number_of_restarts=0;
	
	public static int getNumber_of_restarts() {
		return number_of_restarts;
	}
	public void setStartNode(Node n){
		start = n;
	}

	public Node getStartNode(){
		return start;
	}
	
	public RandomRestart(Queen[] initBoard){
		hillClimb = new HillClimbAlgo(initBoard);
		nodes = 0;
	}
	
	//Random restart hill climbing algorithm
	public Node randomRestart(){
		Node currentNode = hillClimb.getFirstNode();
		
		setStartNode(currentNode);
		int heuristic = currentNode.getHeuristic();
				
		while(heuristic!=0){
			Node nextNode = hillClimb.hillClimbing();
			System.out.println("Next node after Restart is \n"+nextNode.toString());
			nodes+=hillClimb.getNodes();
			heuristic = nextNode.getHeuristic();
			//Restart if hueristic is greater than 0
			if(heuristic!=0){
				number_of_restarts++;
				hillClimb = new HillClimbAlgo();
				currentNode = hillClimb.getFirstNode();
				System.out.println("Node after Random Restart is \n"+currentNode.toString());
			}else
				currentNode = nextNode;
			
		}
		return currentNode;
	}
	
}