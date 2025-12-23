import java.util.Scanner;

class Node
{
 int data;
 Node left, right;

 public Node(int d)
 {
  data = d;
  left = right = null;
 }
}

class BT
{
 Node root;

 public void insert(int data)
 {
  Node newNode = new Node(data);
  if (root == null) 
  {
   root = newNode;
  } 
  else 
  {
   whereToInsert(root, newNode);
  }
 }

 private void whereToInsert(Node current, Node newNode)
 {
  Scanner s = new Scanner(System.in);
  int choice;
  while (true) 
  {
   System.out.println("Where to insert: 1. Left / 2. Right");
   choice = s.nextInt();
   if (choice == 1) 
   {
    if (current.left == null) 
    {
     current.left = newNode;
     break;
    } 
    else 
    {
     current = current.left;
    }
   } 
   else if (choice == 2) 
   {
    if (current.right == null) 
    {
     current.right = newNode;
     break;
    } 
    else 
    {
     current = current.right;
    }
   } 
   else 
   {
    System.out.println("Invalid choice! Please choose 1 or 2.");
   }
  }
 }

 public void PreOrderTraversal(Node node)
 {
  if (node != null) 
  {
   System.out.print(node.data + " ");
   PreOrderTraversal(node.left);
   PreOrderTraversal(node.right);
  }
 }

 public void InOrderTraversal(Node node)
 {
  if (node != null) 
  {
   InOrderTraversal(node.left);
   System.out.print(node.data + " ");
   InOrderTraversal(node.right);
  }
 }

 public void PostOrderTraversal(Node node)
 {
  if (node != null) 
  {
   PostOrderTraversal(node.left);
   PostOrderTraversal(node.right);
   System.out.print(node.data + " ");
  }
 }

 public void countNodes() 
 {
  System.out.println("No. of nodes = " + count(root));
 }

 private int count(Node root) 
 {
  if (root == null) 
  {
   return 0;
  } 
  else 
  {
   return 1 + count(root.left) + count(root.right);
  }
 }

 public void displayLeaves() 
 {
  System.out.println("Leaves are:");
  displayLeaves(root);
 }

 private void displayLeaves(Node current) 
 {
  if (current == null) 
  {
   return;
  }
  if (current.left == null && current.right == null) 
  {
   System.out.print(current.data + " ");
  }
  if (current.left != null) 
  {
   displayLeaves(current.left);
  }
  if (current.right != null) 
  {
   displayLeaves(current.right);
  }
 }

 public void countLeaves() 
 {
  System.out.println("No of leaves: " + countLeaves(root));
 }

 private int countLeaves(Node current) 
 {
  if (current == null) 
  {
   return 0;
  }
  if (current.left == null && current.right == null) 
  {
   return 1;
  } 
  else 
  {
   return countLeaves(current.left) + countLeaves(current.right);
  }
 }

 public void displayHeight() 
 {
  System.out.println("Height: " + height(root));
 }

 private int height(Node current) 
 {
  if (current == null) 
  {
   return 0;
  }
  return Math.max(height(current.left), height(current.right)) + 1;
 }
}

public class BinaryTree
{
 public static void main(String[] args) 
 {
  Scanner s = new Scanner(System.in);
  BT b = new BT();
  int choice;
  do 
  {
   System.out.println("Menu :\n1. Insert a node\n2. Display nodes\n3. Count nodes\n4. Display leaves\n5. Count leaves\n6. Display height\n0. Exit");
   choice = s.nextInt();
   switch (choice) 
   {   
    case 0:
     System.out.println("Program Ended.");
     break;
    case 1:
     System.out.print("Enter element: ");
     int data = s.nextInt();
     b.insert(data);
     System.out.println("Element inserted successfully !");
     break;
    case 2:
     System.out.println("Pre Order Traversal:");
     b.PreOrderTraversal(b.root);
     System.out.println("\nIn Order Traversal:");
     b.InOrderTraversal(b.root);
     System.out.println("\nPost Order Traversal:");
     b.PostOrderTraversal(b.root);
     System.out.println();
     break;
    case 3:
     b.countNodes();
     break;
    case 4:
     b.displayLeaves();
     break;
    case 5:
     b.countLeaves();
     break;
    case 6:
     b.displayHeight();
     break;
    default:
     System.out.println("Invalid choice!");
   }
  } while (choice != 0);
 }
}
