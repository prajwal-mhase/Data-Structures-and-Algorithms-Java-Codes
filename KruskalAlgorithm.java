import java.util.Scanner;
class Kruskal
{
 static int INF=9999;
 static int NOV=4;
 static int NOE;
 static int[] parent=new int[NOV];

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
  int ECount=1;
  while(ECount<NOV)
  {
   int min=INF,a=0,b=0;
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
   union(a,b);
   System.out.println("Edge("+a+","+b+") cost="+min);
   mincost+=min;
   ECount++;
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
  int[][] cost={{INF,3,1,2},
                {3,INF,INF,4},
                {2,INF,INF,5},
                {1,4,5,INF}
               };
  KruskalMST(cost);
 }
}
