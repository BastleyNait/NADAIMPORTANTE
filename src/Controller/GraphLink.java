package Controller;

public class GraphLink<E extends Comparable<E>> {

    protected ListLinked<Vertex<E>> listVertex;

    public GraphLink() {
        this.listVertex = new ListLinked<Vertex<E>>();
    }

    public String insertVertex(E data) {
        Vertex<E> v = new Vertex<E>(data);
        if (this.listVertex.search(v)) {
            return "El codigo de " + data + " ya fue insertado";
        } else {
            this.listVertex.insertarOrdenado(v);
            return "Agregado con exito";
        }
    }

    public void insertEdge(E dataOri, E dataDes) {
        Vertex<E> vOri = this.listVertex.searchData(new Vertex<E>(dataOri));
        Vertex<E> vDes = this.listVertex.searchData(new Vertex<E>(dataDes));

        if (vOri == null || vDes == null) {
            System.out.println(dataOri + " o " + dataDes + "no existen");
        } else {
            Edge<E> e = new Edge<E>(vDes);
            if (vOri.listAdj.search(e)) {
                System.out.println("Arista (" + dataOri + "," + dataDes + ") ya fue insertada");
            } else {
                vOri.listAdj.insertFirst(e);
                vDes.listAdj.insertFirst(new Edge<E>(vOri));
            }
        }
    }

    public void removeEdge(E dataOri, E dataDes) {
        Vertex<E> vOri = this.listVertex.searchData(new Vertex<E>(dataOri));
        Vertex<E> vDes = this.listVertex.searchData(new Vertex<E>(dataDes));

        if (vOri == null || vDes == null) {
            System.out.println(dataOri + " o " + dataDes + " no existen ....");
        } else {
            Edge<E> e = new Edge<E>(vDes);
            if (vOri.listAdj.search(e)) {
                vOri.listAdj.remove(e);
                vDes.listAdj.remove(new Edge<E>(vOri));
            } else {
                System.out.println("La arista (" + dataOri + "," + dataDes + ") no existe en el grafo");
            }
        }
    }

    public String removeVertex(E x) {
        Vertex<E> vertex = this.listVertex.searchData(new Vertex<E>(x));
        if (vertex == null) {
            return x + " no existe como vertice en el grafo";
        } else {
            Node<Vertex<E>> vAux = this.listVertex.getHead();
            Edge<E> e = new Edge<E>(vertex);
            while (vAux != null) {
                vAux.getData().listAdj.remove(e);
                vAux = vAux.getNext();
            }
            this.listVertex.remove(vertex);
            return "Eliminado con exito";
        }
    }

    public boolean searchEdge(E dataOri, E dataDes) {
        Vertex<E> vOri = this.listVertex.searchData(new Vertex<E>(dataOri));
        Vertex<E> vDes = this.listVertex.searchData(new Vertex<E>(dataDes));
        if (vOri == null || vDes == null) {
            return false;
        } else {
            Edge<E> e = new Edge<E>(vDes);
            if (vOri.listAdj.search(e)) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean searchVertex(E data) {
        Vertex<E> vertex = this.listVertex.searchData(new Vertex<E>(data));
        return vertex != null;
    }

    public Vertex<E> buscarElemento(E data) {
        Vertex<E> vertex = this.listVertex.searchData(new Vertex<E>(data));
        return vertex;
    }

    public ListLinked<Vertex<E>> getListVertex() {
        return listVertex;
    }

    public void insertEdge(E dataOri,  int weight, E dataDes) {
        Vertex<E> vOri = this.listVertex.searchData(new Vertex<E>(dataOri));
        Vertex<E> vDes = this.listVertex.searchData(new Vertex<E>(dataDes));

        if (vOri == null || vDes == null) {
            System.out.println(dataOri + " o " + dataDes + " no existen....");
        } else {
            Edge<E> e = new Edge<E>(vDes, weight);
            if (vOri.listAdj.search(e) || vDes.listAdj.search(e)) {
                System.out.println("Arista (" + dataOri + "," + dataDes + ") ya fue insertada...");
            } else {
                vOri.listAdj.insertFirst(e);
                vDes.listAdj.insertFirst(new Edge<E>(vOri, weight));
            }
        }
    }

    public Vertex<E> getVertexAtIndex(int index) {
        if (index < 0 || index >= this.listVertex.lenght()) {
            return null;
        }

        Node<Vertex<E>> aux = this.listVertex.getHead();
        int currentIndex = 0;

        while (currentIndex < index && aux != null) {
            aux = aux.getNext();
            currentIndex++;
        }

        if (aux != null) {
            return aux.getData();
        } else {
            return null;
        }
    }

    public Almacen getVertexByCode(int code) {
        Node<Vertex<E>> current = listVertex.getHead();

        while (current != null) {
            Vertex<Almacen> vertex = (Vertex<Almacen>) current.getData();

            if (vertex.getData().getCodigoAlmacen() == code) {
                return vertex.getData();
            }
            current = current.getNext();
        }

        return null;
    }

    public Almacen getVertexByName(String nombre) {
        Node<Vertex<E>> current = listVertex.getHead();
        while (current != null) {
            Vertex<Almacen> vertex = (Vertex<Almacen>) current.getData();
            if (vertex.getData().getNombreAlmacen().equals(nombre)) {
                return vertex.getData();
            }
            current = current.getNext();
        }
        return null;
    }

    @Override
    public String toString() {
        return this.listVertex.toString();
    }
}