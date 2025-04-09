public class TestABB {
    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();

        // Prueba de inserción
        bst.add(50);
        bst.add(30);
        bst.add(20);
        bst.add(40);
        bst.add(70);
        bst.add(60);
        bst.add(80);

        // Prueba de búsqueda
        System.out.println("Buscar 40: " + (bst.search(40) != null ? "Encontrado" : "No encontrado"));
        System.out.println("Buscar 100: " + (bst.search(100) != null ? "Encontrado" : "No encontrado"));

        // Prueba de mínimo y máximo
        System.out.println("Mínimo: " + bst.min().data);
        System.out.println("Máximo: " + bst.max().data);

        // Prueba de iterador inorden
        System.out.println("Recorrido inorden:");
        for (Integer num : bst) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Prueba de eliminación
        bst.delete(20);
        bst.delete(30);
        bst.delete(50);

        System.out.println("Recorrido después de eliminar:");
        for (Integer num : bst) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
