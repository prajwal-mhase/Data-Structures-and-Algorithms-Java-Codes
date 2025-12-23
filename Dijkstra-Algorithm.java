import java.util.*;
class Dijkstra
{
    static int NOV;
    public int MinDistance(int distance[],boolean vis[])
    {
        int min=Integer.MAX_VALUE,minIndex=-1;
        for(int k=0;k<distance.length;k++)
        {
            if(!vis[k]&&distance[k]<=min)
            {
                min=distance[k];
                minIndex=k;
            }
        }
        return minIndex;
    }
    void dijkstra(int[][] graph,int s)
    {
        int[] distance=new int[NOV];
        boolean[] vis=new boolean[NOV];
        for(int i=0;i<NOV;i++)
        {
            distance[i]=Integer.MAX_VALUE;
            vis[i]=false;
        }
        distance[s]=0;
        for(int count=0;count<NOV-1;count++)
        {
            int u=MinDistance(distance,vis);
            vis[u]=true;
            for(int i=0;i<NOV;i++)
            {
                if(!vis[i] && graph[u][i]!=0 && distance[u]!=Integer.MAX_VALUE &&
                    distance[u]+graph[u][i]<distance[i])
                {
                    distance[i]=distance[u]+graph[u][i];
                }
            }
        }
        printSolution(distance);
    }
    void printSolution(int[] distance) {
        System.out.println("Vertex \t\t Distance from Source");
        for(int i=0;i<NOV;i++)
        {
            System.out.println(i+" \t\t "+distance[i]);
        }
    }
    public static void main(String args[]) {
        Scanner s=new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        NOV=s.nextInt();
        int[][] graph=new int[NOV][NOV];
        System.out.println("Enter Adjacency matrix: ");
        for(int i=0;i<NOV;i++)
        {
            for(int j=0;j<NOV;j++)
            {
                graph[i][j]=s.nextInt();
            }
        }
        System.out.println("Source vertex: 0");
        Dijkstra dijkstra=new Dijkstra();
        dijkstra.dijkstra(graph,0);
    }
}
