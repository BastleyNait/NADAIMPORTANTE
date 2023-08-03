package Controller;

public class Vertex<E extends Comparable<E>> implements Comparable<Vertex<E>> {

    protected E data;
    protected ListLinked<Edge<E>> listAdj;

    public Vertex(E data) {
        this.data = data;
        this.listAdj = new ListLinked<Edge<E>>();
    }

    public boolean equals(Object o) {
        if (o instanceof Vertex<?>) {
            Vertex<E> v = (Vertex<E>) o;
            return this.data.equals(v.data);
        }
        return false;
    }

    @Override
    public String toString() {
        return this.data + " -->\t " + this.listAdj.toString() + "\n";
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public int compareTo(Vertex<E> x) {
        return this.data.compareTo(x.data);
    }

    public ListLinked<Edge<E>> getListAdj() {
        return listAdj;
    }
}
