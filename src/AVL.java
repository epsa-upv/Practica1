import java.util.Iterator;
import java.util.Stack;

public class AVL<T extends Comparable<T>> implements Iterable<T> {
    private class Node {
        T data;
        Node left, right;
        int height;

        Node(T data) {
            this.data = data;
            this.height = 1;
        }
    }

    private Node root;

    public AVL() {
        root = null;
    }

    // Operación add (iterativa)
    public void add(T value) {
        if (root == null) {
            root = new Node(value);
            return;
        }

        Stack<Node> path = new Stack<>();
        Node current = root;

        // Insertar como en BST normal
        while (true) {
            path.push(current);
            if (value.compareTo(current.data) < 0) {
                if (current.left == null) {
                    current.left = new Node(value);
                    break;
                }
                current = current.left;
            } else if (value.compareTo(current.data) > 0) {
                if (current.right == null) {
                    current.right = new Node(value);
                    break;
                }
                current = current.right;
            } else {
                return; // Valor duplicado
            }
        }

        // Actualizar alturas y balancear
        while (!path.isEmpty()) {
            Node node = path.pop();
            updateHeight(node);
            balance(node, path.isEmpty() ? null : path.peek());
        }
    }

    // Operación delete (iterativa)
    public void delete(T value) {
        Stack<Node> path = new Stack<>();
        Node current = root;
        Node parent = null;

        // Buscar el nodo a eliminar
        while (current != null && !current.data.equals(value)) {
            parent = current;
            path.push(parent);
            if (value.compareTo(current.data) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        if (current == null) return; // No se encontró

        // Caso 1: Nodo sin hijos o con un hijo
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
            path.push(current);
            Node successorParent = current;
            Node successor = current.right;

            while (successor.left != null) {
                successorParent = successor;
                path.push(successorParent);
                successor = successor.left;
            }

            current.data = successor.data;

            if (successorParent == current) {
                successorParent.right = successor.right;
            } else {
                successorParent.left = successor.right;
            }
        }

        // Actualizar alturas y balancear
        while (!path.isEmpty()) {
            Node node = path.pop();
            updateHeight(node);
            balance(node, path.isEmpty() ? null : path.peek());
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

    // Métodos auxiliares para AVL
    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    private void updateHeight(Node node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    private int balanceFactor(Node node) {
        return height(node.left) - height(node.right);
    }

    private void balance(Node node, Node parent) {
        int balance = balanceFactor(node);

        // Rotación izquierda-izquierda
        if (balance > 1 && balanceFactor(node.left) >= 0) {
            if (parent == null) {
                root = rightRotate(node);
            } else if (parent.left == node) {
                parent.left = rightRotate(node);
            } else {
                parent.right = rightRotate(node);
            }
            return;
        }

        // Rotación izquierda-derecha
        if (balance > 1 && balanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            if (parent == null) {
                root = rightRotate(node);
            } else if (parent.left == node) {
                parent.left = rightRotate(node);
            } else {
                parent.right = rightRotate(node);
            }
            return;
        }

        // Rotación derecha-derecha
        if (balance < -1 && balanceFactor(node.right) <= 0) {
            if (parent == null) {
                root = leftRotate(node);
            } else if (parent.left == node) {
                parent.left = leftRotate(node);
            } else {
                parent.right = leftRotate(node);
            }
            return;
        }

        // Rotación derecha-izquierda
        if (balance < -1 && balanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            if (parent == null) {
                root = leftRotate(node);
            } else if (parent.left == node) {
                parent.left = leftRotate(node);
            } else {
                parent.right = leftRotate(node);
            }
        }
    }

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    // Implementación del iterador postorden (iterativo)
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Stack<Node> stack1 = new Stack<>();
            private Stack<Node> stack2 = new Stack<>();

            {
                if (root != null) {
                    stack1.push(root);
                    while (!stack1.isEmpty()) {
                        Node node = stack1.pop();
                        stack2.push(node);
                        if (node.left != null) stack1.push(node.left);
                        if (node.right != null) stack1.push(node.right);
                    }
                }
            }

            @Override
            public boolean hasNext() {
                return !stack2.isEmpty();
            }

            @Override
            public T next() {
                return stack2.pop().data;
            }
        };
    }
}