import java.util.Scanner;

class KruskalAlgorithmUserInput
{
 static int INF=9999;
 static int NOV;
 static int NOE;
 static int[] parent;

 static int find(int i)
 {
  while(parent[i]!=i)
  {
   i=parent[i];
  }
  return i;
 }

 static void union(int i,int j)
 {
  int a=find(i);
  int b=find(j);
  parent[a]=b;
 }

 public static void KruskalMST(int[][] cost)
 {
  int mincost=0;
  for(int i=0;i<NOV;i++)
  {
   parent[i]=i;
  }
  int ECount=0;
  while(ECount<NOV-1)
  {
   int min=INF,a=-1,b=-1;
   for(int i=0;i<NOV;i++)
   {
    for(int j=0;j<NOV;j++)
    {
     if(find(i)!=find(j) && cost[i][j]<min)
     {
      min=cost[i][j];
      a=i;
      b=j;
     }
    }
   }
   if(a != -1 && b != -1) {
    union(a,b);
    System.out.println("Edge("+a+","+b+") cost="+min);
    mincost+=min;
    ECount++;
   }
  }
  System.out.println("Minimum cost of spanning tree : "+mincost);
 }

 public static void main(String args[])
 {
  Scanner s=new Scanner(System.in);
  System.out.print("Enter no. of vertices : ");
  NOV=s.nextInt();
  System.out.print("Enter no. of edges : ");
  NOE=s.nextInt();
  parent=new int[NOV];
  int[][] cost=new int[NOV][NOV];
  for(int i=0;i<NOV;i++)
  {
   for(int j=0;j<NOV;j++)
   {
    System.out.print("Enter cost of "+i+" -- "+j+" : ");
    cost[i][j]=s.nextInt();
   }
  }

  KruskalMST(cost);
 }
}

