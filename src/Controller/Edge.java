package Controller;

public class Edge<E extends Comparable<E>> implements Comparable<Edge<E>>{
	protected Vertex<E> refdest;
	protected int weight;
	
	public Edge(Vertex<E> refdest, int weight) {
		this.refdest = refdest;
		this.weight = weight;
	}
	public Edge(Vertex<E> refdest) {
		this(refdest,-1);
	}
	
	/*public boolean equals(Object o) {
		if (o instanceof Edge<?>) {
			Edge<E> e = (Edge<E>) o;
			return this.refdest.equals(e.refdest);
		}
		return false;
	}*/
	@Override
	public String toString() {
		if (this.weight > -1)
			return this.refdest.data.toString() + "[" + this.weight + "]";
		return this.refdest.data.toString();
	}
	
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	@Override
	public int compareTo(Edge o) {
		if(this.weight>o.getWeight()) {
			return 1;
		}
		else if(this.weight==o.getWeight()) {
			return 0;
		}
		return -1;
	}
}
