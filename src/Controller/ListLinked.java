package Controller;

public class ListLinked<E extends Comparable<E>> {

    Node<E> head;
    private int count;

    public ListLinked() {
        this.head = null;
        this.count = 0;
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public int lenght() {
        return this.count;
    }

    public boolean search(E x) {
        Node<E> aux = this.head;
        for (; aux != null; aux = aux.getNext()) {
            if (aux.getData().equals(x)) {
                return true;
            }
        }
        return false;
    }

    public Node<E> getNodeAtIndex(int index) {
        if (index < 0 || index >= this.count) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }

        Node<E> aux = this.head;
        int currentIndex = 0;

        while (currentIndex < index && aux != null) {
            aux = aux.getNext();
            currentIndex++;
        }

        return aux;
    }

    public E searchData(E x) {
        Node<E> aux = this.head;
        for (; aux != null; aux = aux.getNext()) {
            if (aux.getData().compareTo(x) == 0) {
                return aux.getData();
            }
        }
        return null;
    }

    public void insertFirst(E x) {
        this.head = new Node<E>(x, this.head);
        this.count++;
    }

    public void insertLast(E x) {
        if (this.isEmpty()) {
            this.insertFirst(x);
        } else {
            Node<E> aux = this.head;
            while (aux != null && aux.getNext() != null) {
                aux = aux.getNext();
            }
            if (aux != null) {
                aux.setNext(new Node<E>(x));
                this.count++;
            }
        }
    }

    public void insertarOrdenado(E x) {
        if (this.isEmpty()) {
            this.insertFirst(x);
        } else {
            Node<E> current = this.head;
            Node<E> y = current;
            while (current != null) {
                Comparable comparador = (Comparable) x;
                int rta = comparador.compareTo(current.getData());
                if (rta < 0) {
                    break;
                }
                y = current;
                current = current.getNext();
            }
            if (current == y) {
                this.insertFirst(x);
            } else {
                y.setNext(new Node<E>(x, current));
                this.count++;
            }
        }
    }

    public void remove(E x) {
        if (!isEmpty()) {
            if (this.head.getData().equals(x)) {
                this.head = this.head.getNext();
                this.count--;
            } else {
                Node<E> aux = this.head;
                Node<E> prev = null;
                while (aux != null && !aux.getData().equals(x)) {
                    prev = aux;
                    aux = aux.getNext();
                }
                if (aux != null) {
                    prev.setNext(aux.getNext());
                    this.count--;
                }
            }
        }
    }

    public Node<E> getHead() {
        return this.head;
    }

    public String toString() {
        String str = "";
        for (Node<E> aux = this.head; aux != null; aux = aux.getNext()) {
            str = str + aux.toString() + ", ";
        }
        return str;
    }
}
