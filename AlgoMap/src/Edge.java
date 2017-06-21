/**Edge class has information about Source vertex,
 * Destination Vertex, Cost of each edge(transmission time)
 * availability of each edge
 *
 */
public class Edge {
	public Vertex source, dest; 
	public double cost; 
	boolean availability;

	public Edge(Vertex v, Vertex d, double c, boolean availability) {
		source = v;
		dest = d;
		cost = c;
		this.availability = availability;
	}

	public Edge() {
		// TODO Auto-generated constructor stub
	}

	public String getSource() {
		return source.name;
	}

	public String getDest() {
		return dest.name;
	}

	public double getCost() {
		return cost;
	}

	public boolean getAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public void setCost(double cost) {
		// TODO Auto-generated method stub
		this.cost = cost;
	}
}
