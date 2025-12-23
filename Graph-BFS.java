import java.util.*;
class GraphBFS
{
    int NOV;
    LinkedList<Integer>[] adj;
    GraphBFS(int v)
    {
        NOV=v;
        adj=new LinkedList[v];
        for(int i=0;i<v;i++)
        {
            adj[i]=new LinkedList<>();
        }
    }
    public void addEdge(int s,int d)
    {
        adj[s].add(d);
    }
    public void BFS(int a)
    {
        boolean[] vis=new boolean[NOV];
        LinkedList<Integer> q=new LinkedList<>();
        vis[a]=true;
        q.add(a);
        while(!q.isEmpty())
        {
            int c=q.remove();
            System.out.print(" "+c);
            for(int next:adj[c])
            {
                if(!vis[next])
                {
                    vis[next]=true;
                    q.add(next);
                }
            }
        }
    }
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        int NOV=sc.nextInt();
        System.out.print("Enter number of edges: ");
        int NOE=sc.nextInt();
        GraphBFS g=new GraphBFS(NOV);
        while(NOE!=0)
        {
            System.out.print("Enter source of edge: ");
            int s=sc.nextInt();
            System.out.print("Enter destination of edge: ");
            int d=sc.nextInt();
            g.addEdge(s,d);
            NOE--;
        }
        System.out.println("BFS starting from vertex 0:");
        g.BFS(0);
        sc.close();
    }
}
