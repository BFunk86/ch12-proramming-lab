/**
 * CSC 1600
 * Data Structures
 *
 * This is a simple City class used to help demonstrate the UnsortedArrayDictionary class.
 *
 * @author Brandon Young
 */
// File Name: City.java
public class City {
    // Name of City
    private String name;

    /**
     * This is the constructor for the City Object
     * @param name String: The name of the City
     */
    public City(String name) {
        this.name = name;
    } // end constructor

    /**
     * Returns the name of the City
     * @return (String): The name of the city
     */
    public String getCity() {
        return name;
    } // end getCity
} // end City
