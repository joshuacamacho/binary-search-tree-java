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

class Node{
    Integer data;
    Node left;
    Node right;
    Node parent;
}

public class BinarySearchTree {
    static Node root = new Node();
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Please enter the initial sequence of values");
        
        int count = 0;
        while(scanner.hasNextInt()){
            insert(new Integer(scanner.nextInt()),root);
            count++;
            if(count>=17) break;
        }
        
        allOrderPrint();
        boolean CONTINUE = true;
        while(CONTINUE){
            CONTINUE = commandLoop(scanner);
            System.out.print("In-order: ");
            printInOrder(root);
        }
    }
    
    private static void insert(Integer input, Node n) {
        if(n.data==null) n.data=input;
        else if(n.data <= input ){
            if(n.right == null) n.right = new Node();
            n.right.parent = n;
            insert( input, n.right);
        }
        else if(n.data > input ){
            if(n.left == null) n.left = new Node();
            n.left.parent = n;
            insert( input, n.left);
        }
    }

    private static void printPreOrder(Node n) {
        if(n.data != null) System.out.print(n.data + " ");
        if(n.left != null) printPreOrder(n.left);
        if(n.right != null) printPreOrder(n.right);
    }
    
    private static void printInOrder(Node n) {
        if(n.left != null) printInOrder(n.left);
        if(n.data != null) System.out.print(n.data + " ");
        if(n.right != null) printInOrder(n.right);
    }
    
    private static void printPostOrder(Node n) {
        if(n.left != null) printPostOrder(n.left);
        if(n.right != null) printPostOrder(n.right);
        if(n.data != null) System.out.print(n.data + " ");
    }

    private static boolean commandLoop(Scanner scan) {
       System.out.print("\nCommand? ");
       switch (scan.next()){
           case "I": 
               if(scan.hasNextInt()){
                   Integer input = scan.nextInt();
                   //if the node for this number already exists, begone!
                   if(findNode(input,root) != null){
                       System.out.println(
                               input +" already exists, ignore"
                       );
                       break;
                   }
                   insert(input,root);
               }
               break;
           case "D":
               if(scan.hasNextInt()){
                   delete(new Integer(scan.nextInt()));
               }
               break;
           case "P":
               break;
           case "S":
               break;
           case "E":
               return false;
           case "H":
               break;
       }
       return true;
    }

    private static void allOrderPrint() {
        System.out.print("Pre-order: ");
        printPreOrder(root);
        System.out.print('\n');
        
        System.out.print("In-order: ");
        printInOrder(root);
        System.out.print('\n');
        
        System.out.print("Post-order: ");
        printPostOrder(root);
        System.out.print('\n');
    }

    private static void delete(Integer input) { 
       Node toDelete = findNode(input, root);
       System.out.println(toDelete.data);
       if(toDelete.left !=null && toDelete.right!=null){
           //2 children
           
           
           
       }else if(toDelete.left != null){
           //to Delete has a left child
           if(toDelete.parent.left == toDelete){
               //toDelete is a left node
               toDelete.parent.left = toDelete.left;
           }else if(toDelete.parent.right == toDelete){
               //toDelete is a right node
               toDelete.parent.right = toDelete.left;
           }
           
       }else if(toDelete.right != null){
           //to Delete has a right child
           if(toDelete.parent.left == toDelete){
               //toDelete is a left node
               toDelete.parent.left = toDelete.right;
           }else if(toDelete.parent.right == toDelete){
               //toDelete is a right node
               toDelete.parent.right = toDelete.right;
           }
       }else{
           
           
           //no children
          if(toDelete.parent.left == toDelete){
               //toDelete is a left node
               toDelete.parent.left = null;
           }else if(toDelete.parent.right == toDelete){
               //toDelete is a right node
               toDelete.parent.right = null;
          } 
       }
    }

    private static Node findNode(Integer input, Node n) {
        if(n != null && n.data != null){
            if(n.data.intValue() == input.intValue()) {
                return n;
            }else{
                Node foundNode = findNode(input, n.left);
                if(foundNode == null){
                    foundNode = findNode(input, n.right);
                }
                return foundNode;
            }  
        }else{
            return null;
        }
    }
    
}
