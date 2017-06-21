import java.util.LinkedList;
import java.util.List;
/**vertex class has information about 
 * vertex name,distance,previous vertex,
 * and availability of a vertex,
 * position(position is vertex in priority queue, used in implementation of decrease key  priority)
 * color of the vertex (used in BFS to discover reachable vertices)
 *Adjacency list of edges stores the adjacent edges information of a vertex.
 */
public class Vertex {
	public String name;
	public List<Edge> adj;
	public double dist; 
	public Vertex prev;
	public boolean availability;
	int pos;
	public String color;

	public Vertex(String nm, boolean availability) {
		name = nm;
		this.availability = availability;
		adj = new LinkedList();
		reset();
	}

	public Vertex() {

	}

	public boolean getAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public double getDist() {
		return dist;
	}

	public void setDist(double dist) {
		this.dist = dist;
	}

	public Vertex getPrev() {
		return prev;
	}

	public void setPrev(Vertex prev) {
		this.prev = prev;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public void reset() {
		dist = Dijakstras.INFINITY;
		prev = null;
		color = "White";
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}