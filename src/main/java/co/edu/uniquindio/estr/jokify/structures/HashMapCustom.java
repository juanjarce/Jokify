package co.edu.uniquindio.estr.jokify.structures;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Entry<K, V> implements Serializable {

    //Serial ID for Serializable
    private static final long serialVersionUID = 1L;

    K key;
    V value;

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

public class HashMapCustom<K, V> implements Serializable{

    //Serial ID for Serializable
    private static final long serialVersionUID = 1L;

    private List<Entry<K, V>> entries;
    private int size;

    // Constructor
    public HashMapCustom() {
        entries = new ArrayList<>();
        size = 0;
    }

    // Method to add an entry to the HashMap
    public void put(K key, V value) {
        // Check if the key already exists
        for (Entry<K, V> entry : entries) {
            if (entry.key.equals(key)) {
                // If key exists, update the value
                entry.value = value;
                return;
            }
        }
        // If key doesn't exist, add a new entry
        entries.add(new Entry<>(key, value));
        size++;
    }

    // Method to get the value associated with a key
    public V get(K key) {
        // Iterate through entries to find the key
        for (Entry<K, V> entry : entries) {
            if (entry.key.equals(key)) {
                // Return the value if key is found
                return entry.value;
            }
        }
        // Return null if key is not found
        return null;
    }

    // Method to remove an entry from the HashMap
    public void remove(K key) {
        // Iterate through entries to find the key
        for (Entry<K, V> entry : entries) {
            if (entry.key.equals(key)) {
                // Remove the entry if key is found
                entries.remove(entry);
                size--;
                return;
            }
        }
    }

    // Method to get the size of the HashMap
    public int size() {
        return size;
    }

    // Method to check if the HashMap is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Method to check if the HashMap contains a specific key
    public boolean containsKey(K key) {
        // Iterate through entries to find the key
        for (Entry<K, V> entry : entries) {
            if (entry.key.equals(key)) {
                // Return true if key is found
                return true;
            }
        }
        // Return false if key is not found
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (Entry<K, V> entry : entries) {
            sb.append(entry.key)
                    .append("=")
                    .append(entry.value)
                    .append(", ");
        }
        if (!entries.isEmpty()) {
            sb.setLength(sb.length() - 2); // Remove the last ", "
        }
        sb.append("}");
        return sb.toString();
    }
}
