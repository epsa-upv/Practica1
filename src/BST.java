
import java.util.Iterator;
import java.util.Stack;

public class BST<T extends Comparable<T>> implements Iterable<T> {
    public class Node {
        private T data;
        private Node left, right;

        Node(T data) {
            this.data = data;
            left = right = null;
        }

        // Métodos de acceso público
        public T getData() {
            return data;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
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
            if (value.compareTo(current.getData()) < 0) {
                current = current.getLeft();
            } else if (value.compareTo(current.getData()) > 0) {
                current = current.getRight();
            } else {
                return; // Valor duplicado, no se inserta
            }
        }

        if (value.compareTo(parent.getData()) < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
    }

    // Operación search (iterativa)
    public Node search(T value) {
        Node current = root;
        while (current != null) {
            if (value.compareTo(current.getData()) == 0) {
                return current;
            } else if (value.compareTo(current.getData()) < 0) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }
        return null;
    }

    // Operación delete (iterativa)
    public void delete(T value) {
        Node parent = null;
        Node current = root;

        // Buscar el nodo a eliminar
        while (current != null && !current.getData().equals(value)) {
            parent = current;
            if (value.compareTo(current.getData()) < 0) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }

        if (current == null) return; // No se encontró el valor

        // Caso 1: Nodo sin hijos o con un solo hijo
        if (current.getLeft() == null || current.getRight() == null) {
            Node newChild = current.getLeft() != null ? current.getLeft() : current.getRight();

            if (parent == null) {
                root = newChild;
            } else if (parent.getLeft() == current) {
                parent.left = newChild;
            } else {
                parent.right = newChild;
            }
        }
        // Caso 2: Nodo con dos hijos
        else {
            Node successorParent = current;
            Node successor = current.getRight();

            while (successor.getLeft() != null) {
                successorParent = successor;
                successor = successor.getLeft();
            }

            current.data = successor.getData();

            if (successorParent.getLeft() == successor) {
                successorParent.left = successor.getRight();
            } else {
                successorParent.right = successor.getRight();
            }
        }
    }

    // Operación max (iterativa)
    public Node max() {
        if (root == null) return null;

        Node current = root;
        while (current.getRight() != null) {
            current = current.getRight();
        }
        return current;
    }

    // Operación min (iterativa)
    public Node min() {
        if (root == null) return null;

        Node current = root;
        while (current.getLeft() != null) {
            current = current.getLeft();
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
                    current = current.getLeft();
                }

                Node node = stack.pop();
                current = node.getRight();
                return node.getData();
            }
        };
    }
}