import java.util.Iterator;

public interface List<E> extends Iterable<E> {
    void insertar(int pos, E data) throws WrongIndexException;
    void eliminar(int pos) throws WrongIndexException;
    E obtener(int pos) throws WrongIndexException;
    int buscar(E data);
    Iterator<E> iterator();
    int talla();

    //¿Qué son clases internas?
    class WrongIndexException extends Exception {
    }
}
