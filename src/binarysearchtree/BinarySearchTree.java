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
}

public class BinarySearchTree {

    
    static Node root = new Node();
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Please enter the initial sequence of values");
        ArrayList<Integer> items = new ArrayList<Integer>();
        
        while(scanner.hasNextInt()){
            int i = scanner.nextInt();
            items.add(i);
            if( items.size() >= 17 ) break;
        }
        
        for(int i=0; i< items.size(); i++){
           // System.out.println(items.get(i));
            insert(items.get(i),root);
        }
        
        printPostOrder(root);
    }
    
    private static void insert(Integer input, Node n) {
        if(n.data==null) n.data=input;
        else if(n.data >= input ){
            if(n.right == null) n.right = new Node();
            insert( input, n.right);
        }
        else if(n.data < input ){
            if(n.left == null) n.left = new Node();
            insert( input, n.left);
        }
    }

    private static void printPreOrder(Node n) {
        if(n.data != null) System.out.print(n.data + " ");
        if(n.left != null) printPreOrder(n.left);
        if(n.right != null) printPreOrder(n.right);
    }
    
    private static void printInOrder(Node n) {
        if(n.left != null) printPreOrder(n.left);
        if(n.data != null) System.out.print(n.data + " ");
        if(n.right != null) printPreOrder(n.right);
    }
    
    private static void printPostOrder(Node n) {
        if(n.left != null) printPreOrder(n.left);
        if(n.right != null) printPreOrder(n.right);
        if(n.data != null) System.out.print(n.data + " ");
    }
}
