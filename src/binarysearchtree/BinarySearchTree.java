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
        Scanner scanner = new Scanner(System.in).useDelimiter("[,\\s+]");;
        
        System.out.println("Please enter the initial sequence of values");
        String  lines = scanner.nextLine();    
        String[] strs = lines.trim().split("\\s+");
        for (int i = 0; i < strs.length; i++) {
            insert(new Integer(strs[i]),root);
        }

        allOrderPrint();
        boolean CONTINUE = true;
        while(CONTINUE){
            CONTINUE = commandLoop(scanner);
            if(root != null && CONTINUE) {
                System.out.print("In-order: ");
                printInOrder(root);
            }
            else if(CONTINUE) System.out.println("Tree is empty ! (:");
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
           case "i":
               if(scan.hasNextInt()){
                   Integer input = scan.nextInt();
                   //if the node for this number already exists, begone!
                   if(findNode(input,root) != null){
                       System.out.println(
                               input +" already exists, ignore"
                       );
                       break;
                   }
                   if(root==null) root = new Node();
                   insert(input,root);
               }
               break;
           case "D":
           case "d":
               if(scan.hasNextInt()){
                   Integer input = scan.nextInt();
                   if(findNode(input,root)== null){
                       System.out.println(input + " is not in the tree");
                       break;
                   }
                   delete(input);
               }
               break;
           case "P":
           case "p":
               if(scan.hasNextInt()){
                   Integer input = scan.nextInt();
                   if(findNode(input,root)==null){
                       System.out.println(input + " is not in the tree");
                       break;
                   }
                   Node p = findPredecessor(findNode(input,root));
                   if (p != null) System.out.println("Predecessor is " +  p.data);
                   else System.out.println("There is no predecessor! (:");
               }
               break;
           case "S":
           case "s":
               if(scan.hasNextInt()){
                   Integer input = scan.nextInt();
                   if(findNode(input,root)==null){
                       System.out.println(input + " is not in the tree");
                       break;
                   }
                   Node s = findSuccessor(findNode(input,root));
                   if(s != null) System.out.println("Successor is " + s.data);
                   else System.out.println("There is no successor! (:");
                }
               break;
           case "E":
           case "e":
               return false;
           case "H":
           case "h":
               System.out.print(
                       "I  Insert a value\n"+
                       "D  Delete a value\n"+
                       "P  Find a predecessor\n"+
                       "S  Find a successor\n"+
                       "E  Exit the program\n"+
                       "H  Display this message\n");
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
       
       if(toDelete.left !=null && toDelete.right!=null){
           //2 children
           Node lowestRight = findLowestChild(toDelete.right);
          
           if(lowestRight.right==null){
               
               
               toDelete.data = lowestRight.data;
               
               if(lowestRight.parent.right == lowestRight){
                   
                   lowestRight.parent.right=null;
               }else{
                   
                   lowestRight.parent.left=null;
               }
           }else{
               //lowest in right column has right child
               
               toDelete.data = lowestRight.data;
               if(lowestRight.parent.right == lowestRight){
                   lowestRight.right.parent = lowestRight.parent;
                   lowestRight.parent.right = lowestRight.right;
               }else{
                   lowestRight.right.parent = lowestRight.parent;
                   lowestRight.parent.left = lowestRight.right;
               }
           }
           
       }else if(toDelete.left != null){
           //to Delete has a left child
           if(toDelete.parent == null){
               root = toDelete.left;
               root.parent=null;
           }else{

                toDelete.left.parent = toDelete.parent;
                if(toDelete.parent.left == toDelete){
                    //toDelete is a left node
                    toDelete.parent.left = toDelete.left;
                }else if(toDelete.parent.right == toDelete){
                    //toDelete is a right node
                    toDelete.parent.right = toDelete.left;
                }
            }
           
       }else if(toDelete.right != null){
           //to Delete has a right child
            if(toDelete.parent == null){
               root = toDelete.right;
               root.parent=null;
           }else{
                
                toDelete.right.parent = toDelete.parent;
                if(toDelete.parent.left == toDelete){
                    //toDelete is a left node
                    toDelete.parent.left = toDelete.right;
                }else if(toDelete.parent.right == toDelete){
                    //toDelete is a right node
                    toDelete.parent.right = toDelete.right;
                }
            }
       }else{
           //if we're trying to delete the root
           if(root==toDelete){
               System.out.println("deleted root!");
               root =null;
           }else{
               
                if(toDelete.parent.left == toDelete){
                     //toDelete is a left node
                     toDelete.parent.left = null;
                 }else if(toDelete.parent.right == toDelete){
                     //toDelete is a right node
                     toDelete.parent.right = null;
                } 
           }
           //no children
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

    private static Node findLowestChild(Node n) {
        if(n.left != null) return findLowestChild(n.left);
        return n;
    }

 
    private static Node findSuccessor(Node n)
    {
        if (n == null)
            return null;
        
        if (n.right != null)
            return findLowestChild(n.right);
        
        Node y = n.parent;
        Node x = n;
        while (y != null && x == y.right)
        {
            x = y;
            y = y.parent;
        }
        
        return y;
    }
    
    
    private static Node findPredecessor(Node n)
        {
            if (n == null) return null;
            if (n.left != null) return findHighestChild(n.left);

            Node parent = n.parent;

            Node y = parent;
            Node x = n;
            while (y != null && x == y.left)
            {
                x = y;
                y = y.parent;
            }

            return y;
        }

    private static Node findHighestChild(Node n) {
        if(n.right != null) return findHighestChild(n.right);
        return n;
    }
    
    
}
