package com.is.astar;

public class SearchNode {

	private StateInformation curState;
	private SearchNode parent;
	private double cost; // cost to get to this state
	private double hCost; // heuristic cost
	private double fCost; // f(n) cost

	/**
	 * Constructor for the root SearchNode
	 * 
	 * @param s
	 *            the state passed in
	 */
	public SearchNode(StateInformation s)
	{
		curState = s;
		parent = null;
		cost = 0;
		hCost = 0;
		fCost = 0;
	}

	/**
	 * Constructor for all other SearchNodes
	 * 
	 * @param prev
	 *            the parent node
	 * @param s
	 *            the state
	 * @param c
	 *            the g(n) cost to get to this node
	 * @param h
	 *            the h(n) cost to get to this node
	 */
	public SearchNode(SearchNode prev, StateInformation s, double c, double h)
	{
		parent = prev;
		curState = s;
		cost = c;
		hCost = h;
		fCost = cost + hCost;
	}

	/**
	 * @return the curState
	 */
	public StateInformation getCurState() {
		return curState;
	}

	/**
	 * @return the parent
	 */
	public SearchNode getParent() {
		return parent;
	}

	/**
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * @return the hCost
	 */
	public double getHCost() {
		return hCost;
	}

	/**
	 * @return the fCost
	 */
	public double getFCost() {
		return fCost;
	}
	
	
}
