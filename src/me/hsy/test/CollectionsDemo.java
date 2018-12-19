package me.hsy.test;
 
import java.util.List;
import java.util.ArrayList;
 
public class CollectionsDemo {
 
    public static void main(String[] args) {
 
        // Create a List.
        System.out.println("Creating the List...");
        List<String> list = new ArrayList<String>();
        list.add("String one");
        list.add("String two");
        list.add("String three");
 
        // Print out contents.
        printElements(list);
        
        // Set a new element at index 0.
        System.out.println("Setting an element...");
        list.set(0, "A new String");
        printElements(list);
        
        // Search for the newly added String.
        System.out.println("Searching for content...");
        System.out.print("Contains \"A new String\"? ");
        System.out.println(list.contains("A new String"));
        System.out.println("");
        
        // Create a sublist.
        System.out.println("Creating a sublist...");
        list = list.subList(1,3);
        printElements(list);
        
        // Clear all elements.
        System.out.println("Clearing all elements...");
        list.clear();
        printElements(list);
    }
    
    private static void printElements(List<String> list) {
        System.out.println("Size: "+list.size());
        for (Object o : list) {
            System.out.println(o.toString());
        }
        System.out.println("");
    }
}