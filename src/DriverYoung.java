import java.util.Iterator;

/**
 * CSC 1600
 * Data Structures
 *
 * Project Description:
 *
 * Implement an unsorted array-based dictionary (table) based on the given DictionaryInterface. Allow the array to
 * expand as necessary during execution. Write a test program to test the unsorted array-base table class you wrote.
 *
 * @author Brandon Young
 */
// File Name: DriverYoung.java
public class DriverYoung {
    public static void main(String[] args) {

        // The Dictionary (table)
        UnsortedArrayDictionaryYoung<City, String> table = new UnsortedArrayDictionaryYoung<>();

        // Holds the City Objects to add to the Table
        City c;

        // Adds 4 Citys to the table and their description
        c = new City("Jamestown");
        table.add(c, "A great place to visit");
        c = new City("Gloversville");
        table.add(c, "My hometown");
        c = new City("Johnstown");
        table.add(c, "The city I work in");
        c = new City("Saratoga");
        table.add(c, "A fun place to go");

        // Creates an iterator for iterating over the search-keys
        Iterator<City> keyIter = table.getKeyIterator();
        // Loops over the table search-keys
        while (keyIter.hasNext()) {
            displayCity(keyIter.next());
        } // end while loop



    } // end main

    public static void displayCity(City c) {
        System.out.println( c.getCity() );
    }
} // end Driver Young
