import java.io.Serializable;
import java.util.Iterator;
import java.util.function.Consumer;

/**
 * CSC 1600
 * Data Structures
 *
 * The UnsortedArrayDictionaryYoung.java is an unsorted array-based dictionary (table) based on the
 * DictionaryInterface Interface.
 *
 * @author Brandon Young
 */
// File Name: UnsortedArrayDictionaryYoung.java
public class UnsortedArrayDictionaryYoung<K, V> implements DictionaryInterface<K, V> {


    // The backbone of the Table
    private KeyValue<K, V> [] table;
    // Holds the current size of the table
    private int size;
    // Holds the starting size of the table
    private final int START_SIZE = 10;
    // Holds the value of the current maximum size. Should be START_SIZE unless table is resized
    private int maxSize = 10;



    /**
     * The UnsortedArrayDictionaryYoung method is the default constructor.
     * It creates an empty Dictionary.
     */
    public UnsortedArrayDictionaryYoung() {
        table = new KeyValue[START_SIZE];
        size = 0;
    } // default constructor

    /**
     * Returns the index of the given key
     * Precondition: none
     * PostCondition: Returns -1 if the key is not in the table and the index if it is
     * @return (int): The index of the current key or -1 if the key is not in the table
     */
    private int getIndex(K key) {
        int index = -1;
        // loop through the array to see check for key. Stops if key is found or end of array
        for (int i = 0; i < size && !key.equals(table[i]); i++) {
            // If key is found set index = to the index of key
            if ( key.equals(table[i]) ) {
                index = i;
            } // end if
        } // end for loop
        return index;
    } // end getIndex

    /**
     * The resize method doubles the current size of the table
     * Precondition: None
     * Post Consition: Doubles the size of the current table and doubles the size of maxSize
     */
    private void resize() {
        // creates a new table double the size of the original
        KeyValue <K, V> [] newTable = new KeyValue[maxSize * 2];
        // copies the contents of the table to the newTable
        for (int index = 0; index < maxSize; index++) {
            newTable[index] = table[index];
        } // end for loop
        // set reference of table to newTable
        table = newTable;
        // updates maxSize to reflect new size
        maxSize *= 2;
    } // end resizeArray


    @Override
    public V add(K key, V value) {
        V result = null;
        int index;
        // If the key is already in the table
        if (contains(key)) {
            // get keys index
            index = getIndex(key);
            // if the key is in table get current value before changing
            result = table[index].getValue();
            // update the current value
            table[index].setValue(value);
        } else { // If the key is not in the table
            // resize the table if it is at max capacity
            if (size == maxSize) {
                resize();
            } // end if
            // Add new keyValue to table and increase current size
            table[size] = new KeyValue(key, value);
            size++;
        } // end if
        return result;
    } // end add

    @Override
    public V remove(K key) {
        V result = null;
        if ( contains(key) ) {
            int index = getIndex(key);
            // add the value for the given key to result
            result = table[index].getValue();
            KeyValue<K, V>[] tempTable = new KeyValue[size];
            // loop through table to add items to tempTable
            for (int i = 0; i < tempTable.length; i++) {
                // if the index is less than key then add it to tempTable
                if( i < index ) {
                    // add items to tempTable as long as its not index
                    tempTable[i] = table[i];
                } else if (i > index) { // if index is greater than key index
                    // add items to tempTable[i-1] to correct for removed item
                    tempTable[i-1] = table[i];
                } // end if else
            } // end for loop
            // change table to the reference of tempTable which now has removed key
            table = tempTable;
        } // end if
        return result;
    } // end remove

    @Override
    public V getValue(K key) {
        int index = getIndex(key);
        return table[index].getValue();
    } // end getValue

    @Override
    public boolean contains(K key) {
        return getIndex(key) != -1;
    } // end contains

    @Override
    public Iterator<K> getKeyIterator() {
        Iterator<K> keyIterator = new Iterator<K>() {

            // Holds the current index of the iterator
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size && table[currentIndex] != null;
            } // end hasNext

            @Override
            public K next() {
                //return the current key in the table and then increment currentIndex
                return table[currentIndex++].getKey();
            } // end next

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            } // end remove

            @Override
            public void forEachRemaining(Consumer<? super K> action) {
                // default implementation of forEachRemaining
                while(hasNext()) {
                    action.accept(next());
                } // end while
            } // end forEachRemaining
        };
        return keyIterator;
    } // end getKeyIterator

    @Override
    public Iterator<V> getValueIterator() {
        Iterator<V> valueIterator = new Iterator<V>() {

            // Holds the current index of the iterator
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size && table[currentIndex] != null;
            } // end hasNext

            @Override
            public V next() {
                //return the current key in the table and then increment currentIndex
                return table[currentIndex++].getValue();
            } // end next

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            } // end remove

            @Override
            public void forEachRemaining(Consumer<? super V> action) {
                // default implementation of forEachRemaining
                while(hasNext()) {
                    action.accept(next());
                } // end while
            } // end forEachRemaining
        };
        return valueIterator;
    } // end getValueIterator

    @Override
    public boolean isEmpty() {
        return size == 0;
    } // end isEmpty

    @Override
    public int getSize() {
        return size;
    } // end getSize

    @Override
    public void clear() {
        new UnsortedArrayDictionaryYoung();
    } // end clear

    /**
     * The inner class keyValue is used to link the values to their corresponding keys
     *
     */
    private class KeyValue<S, T> implements Serializable {
        // Holds the fields search-key
        S key;
        // Holds the fields value
        T value;

        /**
         * The keyValue method is the default constructor and creates a new keyValue pair
         * Precondition: None
         * Post Condition: creates a new field with a search-key and value
         * @param searchKey S: The search-key of the new field
         * @param theValue T: The value of the new field
         */
        private KeyValue (S searchKey, T theValue) {
            key = searchKey;
            value = theValue;
        } // end default constructor

        /**
         * The getKey method returns the key-value of a field
         * Precondition: None
         * Post Condition: returns the search-key value key for the field
         * @return (S): The search-key of the field
         */
        private S getKey() {
            return key;
        } // end getKeyValue

        /**
         * The getValue method returns the value of the field
         * Precondition: None
         * Post Condition: returns the value for the field
         * @return (T): The value associated with the search-key
         */
        private T getValue() {
            return value;
        } // end getValue

        /**
         * The setValue method sets the value in the field
         * Precondition: None
         * Post Condition: Sets the value of the field
         * @param newValue T: the newValue to set
         */
        private void setValue (T newValue) {
            value = newValue;
        } // end setValue

    } // end Node

} // end UnsortedArrayDictionaryYoung.java
