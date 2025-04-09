public class TestLista {
    public static void main(String[] args) {

        ListImpl<String> lista1 = new ListImpl<>();
        List<Integer> lista = new ListImpl<>();

        try {
            lista1.insertar(0, "A"); // Lista: [A]
            lista1.insertar(1, "B"); // Lista: [A, B]
            lista1.insertar(2, "C"); // Lista: [A, B, C]

            System.out.println(lista1.obtener(1)); // ➝ B
            System.out.println(lista1.buscar("C")); // ➝ 2

            lista1.eliminar(1); // Borra "B"
            System.out.println(lista1.obtener(1)); // ➝ C

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
