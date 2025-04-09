public class TestAVL {
    public static void main(String[] args) {
        AVL<Integer> avl = new AVL<>();

        // Prueba de inserción
        avl.add(10);
        avl.add(20);
        avl.add(30);
        avl.add(40);
        avl.add(50);
        avl.add(25);

        // Prueba de búsqueda
        System.out.println("Buscar 30: " + (avl.search(30) != null ? "Encontrado" : "No encontrado"));
        System.out.println("Buscar 100: " + (avl.search(100) != null ? "Encontrado" : "No encontrado"));

        // Prueba de mínimo y máximo
        System.out.println("Mínimo: " + avl.min().data);
        System.out.println("Máximo: " + avl.max().data);

        // Prueba de iterador postorden
        System.out.println("Recorrido postorden:");
        for (Integer num : avl) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Prueba de eliminación
        avl.delete(20);
        avl.delete(30);

        System.out.println("Recorrido después de eliminar:");
        for (Integer num : avl) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}