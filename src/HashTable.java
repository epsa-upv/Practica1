import java.util.Iterator;
import java.util.LinkedList;

public class HashTable<K, V> implements Dictionary<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;
    private LinkedList<TableEntry<K, V>>[] table;
    private int size;

    private static class TableEntry<K, V> {
        K key;
        V value;

        TableEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private class Citerator implements Iterator<K> {
        private int index = 0;
        private Iterator<TableEntry<K, V>> bucketIterator;

        Citerator() {
            moveToNextBucket();
        }

        private void moveToNextBucket() {
            while (index < table.length && (table[index] == null || table[index].isEmpty())) {
                index++;
            }
            if (index < table.length) {
                bucketIterator = table[index].iterator();
            }
        }

        public boolean hasNext() {
            return bucketIterator != null && bucketIterator.hasNext();
        }

        public K next() {
            K key = bucketIterator.next().key;
            if (!bucketIterator.hasNext()) {
                index++;
                moveToNextBucket();
            }
            return key;
        }
    }

    public HashTable(int capacity) {
        table = new LinkedList[capacity];
        size = 0;
    }

    public HashTable() {
        this(DEFAULT_CAPACITY);
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7FFFFFFF) % table.length;
    }

    @Override
    public V put(K key, V value) {
        int index = hash(key);
        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }
        for (TableEntry<K, V> entry : table[index]) {
            if (entry.key.equals(key)) {
                V oldValue = entry.value;
                entry.value = value;
                return oldValue;
            }
        }
        table[index].add(new TableEntry<>(key, value));
        size++;
        if (size / (double) table.length > LOAD_FACTOR) {
            resize();
        }
        return null;
    }

    @Override
    public V get(K key) {
        int index = hash(key);
        if (table[index] != null) {
            for (TableEntry<K, V> entry : table[index]) {
                if (entry.key.equals(key)) {
                    return entry.value;
                }
            }
        }
        return null;
    }

    @Override
    public V remove(K key) {
        int index = hash(key);
        if (table[index] != null) {
            Iterator<TableEntry<K, V>> it = table[index].iterator();
            while (it.hasNext()) {
                TableEntry<K, V> entry = it.next();
                if (entry.key.equals(key)) {
                    it.remove();
                    size--;
                    return entry.value;
                }
            }
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        return get(key) != null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        table = new LinkedList[table.length];
        size = 0;
    }

    private void resize() {
        LinkedList<TableEntry<K, V>>[] oldTable = table;
        table = new LinkedList[oldTable.length * 2];
        size = 0;
        for (LinkedList<TableEntry<K, V>> bucket : oldTable) {
            if (bucket != null) {
                for (TableEntry<K, V> entry : bucket) {
                    put(entry.key, entry.value);
                }
            }
        }
    }

    @Override
    public Iterator<K> iterator() {
        return new Citerator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (LinkedList<TableEntry<K, V>> bucket : table) {
            if (bucket != null) {
                for (TableEntry<K, V> entry : bucket) {
                    sb.append(entry.key).append("=").append(entry.value).append(", ");
                }
            }
        }
        if (sb.length() > 1) sb.setLength(sb.length() - 2);
        sb.append("}");
        return sb.toString();
    }
}
