import java.util.Comparator;
import java.util.PriorityQueue;

class Node
{
 char data;
 int freq;
 Node left, right;
}

class MyComparator implements Comparator<Node>
{
 public int compare(Node x, Node y)
 {
  return x.freq - y.freq;
 }
}

class Huffman
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
  int n = 7;
  char[] charArray = {'a', 'b', 'c', 'd', 'e', 'f', 'g'};
  int[] charfreq = {5, 10, 1, 25, 16, 61, 23};

  PriorityQueue<Node> q = new PriorityQueue<>(n, new MyComparator());

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
 }
}
