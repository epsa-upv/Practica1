import java.util.Iterator;
import java.util.function.Consumer;


public class ListImpl <E> implements List<E> {
    private Node<E> primero;
    private int talla;

    public ListImpl(){
        primero = null;
        talla = 0;
    }

    //CLASES INTERNAS DE IMPLEMENTAR LISTAS
    private static class Node<E>{
        //El Nodo siempre va a tener dos atributos asociados, el dato que puede ser de cualquer tipo y el apuntador para indicar el siguiente elemento.
        E dato;
        public Node<E> siguiente;

        //ConstructorA: Crea un nodo con un dato que no tiene siguiente.
        public Node(E dato){
            this.dato = dato;
            this.siguiente = null;
        }
        //Constructor B: Crea un nodo con un dato d enlazado a un nodo preexistente, es decir, el nuevo queda como el primero.
        public Node(E dato, Node<E> siguiente){
            this.dato = dato;
            this.siguiente = siguiente;
        }
    }

    private class CIterator implements Iterator<E> {
        private Node<E> actual;

        // Constructor que inicializa el iterator al primer nodo
        public CIterator() {
            this.actual = primero;
        }

        @Override
        public boolean hasNext() {
            return actual != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            E dato = actual.dato;
            actual = actual.siguiente;
            return dato;
        }
    }


    //IMPLEMENTAR LA INTERFAZ LISTA
    @Override
    public void insertar(int pos, E data) throws WrongIndexException {
        if (pos==0) primero = new Node<E>(data,primero);
        else {
            Node<E> aux = primero;
            for (int k = 0; k<pos-1; k++)
                aux = aux.siguiente;
            aux.siguiente = new Node<E>(data,aux.siguiente);
        }
        talla++;
    }

    @Override
    public void eliminar(int pos) throws WrongIndexException {
        E x;
        if (pos==0) {
            x = primero.dato;
            primero = primero.siguiente;
        }
        else {
            Node<E> aux = primero;
            for (int k = 0; k<pos-1; k++)
                aux = aux.siguiente;
            x = aux.siguiente.dato;
            aux.siguiente = aux.siguiente.siguiente;
        }
        talla--;
    }

    @Override
    public E obtener(int pos) throws WrongIndexException {
        Node<E> aux = primero;
        for (int k = 0; k<pos; k++)
            aux = aux.siguiente;
        return aux.dato;
    }

    @Override
    public int buscar(E data) {
        Node<E> aux = primero;
        int k = 0;
        while (aux!=null && aux.dato!= data) {
            k++;
            aux = aux.siguiente;
        }
        if (aux!=null) return k;
        else return -1;
    }

    @Override
    public Iterator<E> iterator() {
        return new CIterator();
    }

    @Override
    public int talla() {
        return talla;
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        List.super.forEach(action);
    }

}