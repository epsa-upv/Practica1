class TestTabla {
    public static void main(String[] args) {
        HashTable<String, Integer> tabla = new HashTable<>();
        tabla.put("Uno", 1);
        tabla.put("Dos", 2);
        tabla.put("Tres", 3);
        System.out.println(tabla);
        tabla.remove("Dos");
        System.out.println(tabla);

        System.out.println(tabla.get("Uno")); // ➝ 1
        System.out.println(tabla.contains("Dos")); // ➝ true
        System.out.println(tabla.size()); // ➝ 3

        System.out.println(tabla.contains("Dos")); // ➝ false
        System.out.println(tabla); // ➝ {Uno=1, Tres=3}
    }
}
