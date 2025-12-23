import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

class Node
{
   char data;
   int freq;
   Node left, right;

   public Node()
   {}
}

class MyComparator implements Comparator<Node>
{
   public int compare(Node x, Node y)
   {
      return x.freq - y.freq;
   }
}

class HuffmanUI
{
   public static void printCode(Node root, String s)
   {
      if (root.left == null && root.right == null && Character.isLetter(root.data))
      {
         System.out.println(root.data + ":" + s);
         return;
      }
      if (root.left != null)
      {
         printCode(root.left, s + "0");
      }
      if (root.right != null)
      {
         printCode(root.right, s + "1");
      }
   }

   public static void main(String[] args)
   {
      Scanner s = new Scanner(System.in);

      System.out.print("Enter number of elements : ");
      int n = s.nextInt();
      s.nextLine();

      char[] charArray = new char[n];
      int[] charfreq = new int[n];

      System.out.println("Enter elements and their frequency : ");
      for (int j = 0; j < n; j++)
      {
         System.out.print("Enter element " + (j + 1) + " : ");
         charArray[j] = s.next().charAt(0);
         s.nextLine();

         System.out.print("Enter frequency of " + charArray[j] + " : ");
         charfreq[j] = s.nextInt();
         s.nextLine();
      }

      PriorityQueue<Node> q = new PriorityQueue<>(n, new MyComparator());

      System.out.println("Huffman Code : ");
      for (int i = 0; i < n; i++)
      {
         Node hn = new Node();
         hn.data = charArray[i];
         hn.freq = charfreq[i];
         hn.left = null;
         hn.right = null;
         q.add(hn);
      }

      while (q.size() > 1)
      {
         Node x = q.poll();
         Node y = q.poll();

         Node f = new Node();
         f.freq = x.freq + y.freq;
         f.data = '-';
         f.left = x;
         f.right = y;

         q.add(f);
      }

      Node root = q.poll();

      printCode(root, "");

      s.close();
   }
}
