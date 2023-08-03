package Controller;

import java.util.ArrayList;

public class AVL<T extends Comparable<T>> {

    private Nodo<T> root;

    public AVL() {
        this.root = null;
    }

    public Nodo<T> getRoot() {
        return root;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void insert(T x) {
        root = insert(x, root);
    }

    private Nodo<T> insert(T x, Nodo<T> current) {
        if (current == null) {
            return new Nodo<>(x);
        }

        int comparison = x.compareTo(current.getData());
        if (comparison <= 0) {
            current.setLeft(insert(x, current.getLeft()));
        } else {
            current.setRight(insert(x, current.getRight()));
        }

        updateHeight(current);
        return applyRotation(current);
    }

    public void remove(T x) {
        System.out.println(x + "  " + root);
        root = remove(x, root);
        
    }

    private Nodo<T> remove(T x, Nodo<T> current) {
        if (current == null) {
            throw new ElementNotFoundException("No se encontró el elemento");
        }

        int comparison = x.compareTo(current.getData());
        if (comparison < 0) {
            current.setLeft(remove(x, current.getLeft()));
        } else if (comparison > 0) {
            current.setRight(remove(x, current.getRight()));
        } else {
            if (current.getLeft() == null && current.getRight() == null) {
                return null; // This is the case when current is the only node in the tree
            } else if (current.getLeft() == null) {
                return current.getRight();
            } else if (current.getRight() == null) {
                return current.getLeft();
            }
            T min = getMin(current.getRight());
            current.setData(min);
            current.setRight(remove(min, current.getRight()));
        }

        updateHeight(current);
        return applyRotation(current);
    }

    public T search(T x) {
        Nodo<T> result = search(x, root);
        if (result == null) {
            throw new ElementNotFoundException("No se encontró el elemento");
        }
        return result.getData();
    }

    private Nodo<T> search(T x, Nodo<T> current) {
        if (current == null || current.getData().compareTo(x) == 0) {
            return current;
        }

        int comparison = x.compareTo(current.getData());

        if (comparison < 0) {
            return search(x, current.getLeft());
        } else {
            return search(x, current.getRight());
        }
    }

    private T getMin(Nodo<T> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node.getData();
    }

    public T getMin() {
        if (isEmpty()) {
            throw new ElementNotFoundException("El árbol está vacío");
        }
        Nodo<T> current = root;
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current.getData();
    }

    public T getMax() {
        if (isEmpty()) {
            throw new ElementNotFoundException("El árbol está vacío");
        }
        Nodo<T> current = root;
        while (current.getRight() != null) {
            current = current.getRight();
        }
        return current.getData();
    }

    public T parent(T x) {
        Nodo<T> parentNode = findParent(x, root);
        if (parentNode == null) {
            throw new ElementNotFoundException("El nodo no tiene padre");
        }
        return parentNode.getData();
    }

    private Nodo<T> findParent(T x, Nodo<T> current) {
        if (current == null || current.getData().equals(x)) {
            return null;
        }

        boolean leftIsParent = current.getLeft() != null && current.getLeft().getData().equals(x);
        boolean rightIsParent = current.getRight() != null && current.getRight().getData().equals(x);

        if (leftIsParent || rightIsParent) {
            return current;
        }

        int comparison = x.compareTo(current.getData());
        if (comparison < 0) {
            return findParent(x, current.getLeft());
        } else {
            return findParent(x, current.getRight());
        }
    }

    public ArrayList<T> inOrder() {
        ArrayList<T> dataNodes = new ArrayList<>();

        if (isEmpty()) {
            return dataNodes;
        }

        inOrder(root, dataNodes);
        return dataNodes;
    }

    private void inOrder(Nodo<T> current, ArrayList<T> dataNodes) {
        if (current.getLeft() != null) {
            inOrder(current.getLeft(), dataNodes);
        }
        dataNodes.add(current.getData());
        if (current.getRight() != null) {
            inOrder(current.getRight(), dataNodes);
        }
    }

    public ArrayList<T> inOrderAVL() {
        ArrayList<T> dataNodes = new ArrayList<>();

        if (isEmpty()) {
            return dataNodes;
        }

        inOrderAVL(root, dataNodes);
        return dataNodes;
    }

    private void inOrderAVL(Nodo<T> current, ArrayList<T> dataNodes) {
        if (current.getLeft() != null) {
            inOrderAVL(current.getLeft(), dataNodes);
        }
        dataNodes.add(current.getData());
        if (current.getRight() != null) {
            inOrderAVL(current.getRight(), dataNodes);
        }
    }

    private void updateHeight(Nodo<T> current) {
        int maxHeight = Math.max(
                height(current.getLeft()),
                height(current.getRight())
        );
        current.setHeight(maxHeight + 1);
    }

    private int height(Nodo<T> node) {
        return (node != null) ? node.getHeight() : 0;
    }

    private Nodo<T> applyRotation(Nodo<T> current) {
        int balance = balance(current);

        if (balance > 1) {
            if (balance(current.getLeft()) < 0) {
                current.setLeft(rotateLeft(current.getLeft()));
            }
            return rotateRight(current);
        }

        if (balance < -1) {
            if (balance(current.getRight()) > 0) {
                current.setRight(rotateRight(current.getRight()));
            }
            return rotateLeft(current);
        }

        return current;
    }

    private Nodo<T> rotateRight(Nodo<T> node) {
        Nodo<T> leftNode = node.getLeft();
        Nodo<T> centralNode = leftNode.getRight();
        leftNode.setRight(node);
        node.setLeft(centralNode);
        updateHeight(node);
        updateHeight(leftNode);
        return leftNode;
    }

    private Nodo<T> rotateLeft(Nodo<T> node) {
        Nodo<T> rightNode = node.getRight();
        Nodo<T> centralNode = rightNode.getLeft();
        rightNode.setLeft(node);
        node.setRight(centralNode);
        updateHeight(node);
        updateHeight(rightNode);
        return rightNode;
    }

    private int balance(Nodo<T> node) {
        return (node != null)
                ? height(node.getLeft()) - height(node.getRight())
                : 0;
    }
}

class ElementNotFoundException extends RuntimeException {

    public ElementNotFoundException(String message) {
        super(message);
    }
}
