package Controller;

class Nodo<T extends Comparable<T>> {
    private T data;
    private Nodo<T> left;
    private int height = 1;
    private Nodo<T> right;

    public Nodo(T data) {
        this(data, null, null);
    }

    public Nodo(T data, Nodo<T> left, Nodo<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    Nodo<T> getLeft() {
        return left;
    }

    void setLeft(Nodo<T> left) {
        this.left = left;
    }

    Nodo<T> getRight() {
        return right;
    }

    void setRight(Nodo<T> right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}