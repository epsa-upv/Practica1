import java.util.Iterator;
import java.util.Stack;

public class BST<T extends Comparable<T>> implements Iterable<T> {
    private class Node {
        T data;
        Node left, right;

        Node(T data) {
            this.data = data;
            left = right = null;
        }
    }

    private Node root;

    public BST() {
        root = null;
    }

    // Operación add (iterativa)
    public void add(T value) {
        Node newNode = new Node(value);
        if (root == null) {
            root = newNode;
            return;
        }

        Node current = root;
        Node parent = null;

        while (current != null) {
            parent = current;
            if (value.compareTo(current.data) < 0) {
                current = current.left;
            } else if (value.compareTo(current.data) > 0) {
                current = current.right;
            } else {
                return; // Valor duplicado, no se inserta
            }
        }

        if (value.compareTo(parent.data) < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
    }

    // Operación search (iterativa)
    public Node search(T value) {
        Node current = root;
        while (current != null) {
            if (value.compareTo(current.data) == 0) {
                return current;
            } else if (value.compareTo(current.data) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return null;
    }

    // Operación delete (iterativa)
    public void delete(T value) {
        Node parent = null;
        Node current = root;

        // Buscar el nodo a eliminar
        while (current != null && !current.data.equals(value)) {
            parent = current;
            if (value.compareTo(current.data) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        if (current == null) return; // No se encontró el valor

        // Caso 1: Nodo sin hijos o con un solo hijo
        if (current.left == null || current.right == null) {
            Node newChild = current.left != null ? current.left : current.right;

            if (parent == null) {
                root = newChild;
            } else if (parent.left == current) {
                parent.left = newChild;
            } else {
                parent.right = newChild;
            }
        }
        // Caso 2: Nodo con dos hijos
        else {
            Node successorParent = current;
            Node successor = current.right;

            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }

            current.data = successor.data;

            if (successorParent.left == successor) {
                successorParent.left = successor.right;
            } else {
                successorParent.right = successor.right;
            }
        }
    }

    // Operación max (iterativa)
    public Node max() {
        if (root == null) return null;

        Node current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current;
    }

    // Operación min (iterativa)
    public Node min() {
        if (root == null) return null;

        Node current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    // Implementación del iterador inorden (iterativo)
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Stack<Node> stack = new Stack<>();
            private Node current = root;

            @Override
            public boolean hasNext() {
                return !stack.isEmpty() || current != null;
            }

            @Override
            public T next() {
                while (current != null) {
                    stack.push(current);
                    current = current.left;
                }

                Node node = stack.pop();
                current = node.right;
                return node.data;
            }
        };
    }
}
