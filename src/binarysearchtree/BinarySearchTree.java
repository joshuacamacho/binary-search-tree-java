/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarysearchtree;
import java.util.*;  
/**
 *
 * @author Josh
 */
public class BinarySearchTree {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Please enter the initial sequence of values");
        ArrayList<Integer> items = new ArrayList<Integer>();
        
        while(scanner.hasNextInt() && items.size() < 17){
            int i = scanner.nextInt();
            items.add(i);
        }
        
        for(int i=0; i< items.size(); i++){
            System.out.println(items.get(i));
        }
    }
    
}
