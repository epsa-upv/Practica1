class TestTabla {
    public static void main(String[] args) {
        HashTable<String, Integer> tabla = new HashTable<>();
        tabla.put("Uno", 1);
        tabla.put("Dos", 2);
        tabla.put("Tres", 3);
        System.out.println(tabla);
        tabla.remove("Dos");
        System.out.println(tabla);
    }
}
