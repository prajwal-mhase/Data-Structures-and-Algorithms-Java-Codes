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

class BST
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

   public void whereToInsert(Node current, Node newNode)
   {
      if (newNode.data < current.data) 
      {
         if (current.left == null) 
         {
            current.left = newNode;
         }
         else 
         {
            whereToInsert(current.left, newNode);
         }
      }
      else
      {
         if (current.right == null) 
         {
            current.right = newNode;
         } 
         else 
         {
            whereToInsert(current.right, newNode);
         }
      }
   }

   public void preOrderTraversal(Node node)
   {
      if (node != null) 
      {
         System.out.print(node.data + " ");
         preOrderTraversal(node.left);
         preOrderTraversal(node.right);
      }
   }

   public void inOrderTraversal(Node node)
   {
      if (node != null) 
      {
         inOrderTraversal(node.left);
         System.out.print(node.data + " ");
         inOrderTraversal(node.right);
      }
   }

   public void postOrderTraversal(Node node)
   {
      if (node != null) 
      {
         postOrderTraversal(node.left);
         postOrderTraversal(node.right);
         System.out.print(node.data + " ");
      }
   }

   int flag;
   public void Search(Node current, int key)
   {
      flag = 0;
      if (current == null)
      {
         return;
      }

      if (current.data == key)
      {
         flag = 1;
         return;
      }
      else if (key < current.data)
      {
         Search(current.left, key);
      }
      else if (key > current.data)
      {
         Search(current.right, key);
      }
   }

   public int minValue()
   {
      if (root == null)
      {
         System.out.println("Tree is empty");
         return -1;
      }
      Node current = root;
      while (current.left != null)
      {
         current = current.left;
      }
      return current.data;
   }

   public int maxValue()
   {
      if (root == null)
      {
         System.out.println("Tree is empty");
         return -1;
      }
      Node current = root;
      while (current.right != null)
      {
         current = current.right;
      }
      return current.data;
   }

   public Node Delete(Node root, int del)
   {
      if (root == null)
      {
         return root;
      }

      if (del < root.data)
      {
         root.left = Delete(root.left, del);
      }
      else if (del > root.data)
      {
         root.right = Delete(root.right, del);
      }
      else
      {
         if (root.left == null)
         {
            return root.right;
         }
         else if (root.right == null)
         {
            return root.left;
         }
         root.data = minValue(root.right);
         root.right = Delete(root.right, root.data);
      }
      return root;
   }

   public int minValue(Node node)
   {
      int minValue = node.data;
      while (node.left != null)
      {
         minValue = node.left.data;
         node = node.left;
      }
      return minValue;
   }
}

public class BinarySearchTree
{
   public static void main(String[] args) 
   {
      Scanner s = new Scanner(System.in);
      BST b = new BST();
      int choice;
      do 
      {
         System.out.println("Menu : \n1. Insert \n2. Display \n3. Search Element \n4. Minimum Value \n5. Maximum Value \n6. Delete Element \n0. Exit");
         choice = s.nextInt();
         switch (choice) 
         {
            case 1:
               System.out.print("Enter element: ");
               int data = s.nextInt();
               b.insert(data);
               System.out.println("Element entered successfully !");
               break;
            case 2:
               System.out.print("Preordered Traversal : ");
               b.preOrderTraversal(b.root);
               System.out.println();
               System.out.print("Inordered Traversal : ");
               b.inOrderTraversal(b.root);
               System.out.println();
               System.out.print("Postordered Traversal : ");
               b.postOrderTraversal(b.root);
               System.out.println();
               break;
            case 3:
               System.out.print("Enter the element you want to search: ");
               int key = s.nextInt();
               b.Search(b.root, key);
               if (b.flag == 1)
               {
                  System.out.println("Element Found!");
               }
               else
               {
                  System.out.println("Element Not Found!");
               }
               break;
            case 4:
               int min = b.minValue();
               if (min != -1)
               {
                  System.out.println("Minimum Value : " + min);
               }
               break;
            case 5:
               int max = b.maxValue();
               if (max != -1)
               {
                  System.out.println("Maximum Value : " + max);
               }
               break;
            case 6:
               System.out.print("Enter the element you want to delete: ");
               int del = s.nextInt();
               b.Delete(b.root, del);
               System.out.println("Element deleted successfully!");
               break;
            case 0:
               System.out.println("Program Ended !");
               break;
            default:
               System.out.println("Invalid choice!");
         }
      } while (choice != 0);
      s.close();
   }
}
