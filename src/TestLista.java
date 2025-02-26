public class TestLista {
    public static void main(String[] args) {
        List<Integer> lista = new ListImpl<>();

        try {
            // Intentamos insertar elementos en la lista
            lista.insertar(0, 10);
            lista.insertar(1, 20);
            lista.insertar(1, 15);

            // Imprimir la lista usando el iterator
            System.out.print("Lista después de inserciones: ");
            for (Integer item : lista) {
                System.out.print(item + " ");
            }
            System.out.println();

        } catch (List.WrongIndexException e) {
            System.out.println("Error al insertar: " + e.getMessage());
        }
    }
}
