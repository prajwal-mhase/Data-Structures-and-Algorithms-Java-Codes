import java.util.Scanner;
import java.util.Arrays;

class Prims
{
    static final int INF=9999;

    public static void PrimMST(int[][] G,int NOV)
    {
        boolean[] select=new boolean[NOV];
        Arrays.fill(select,false);
        
        int mincost=0;
        select[0]=true;
        int ECount=1;

        while(ECount<NOV)
        {
            int min=INF;
            int a=0,b=0;

            for(int i=0;i<NOV;i++)
            {
                if(select[i])
                {
                    for(int j=0;j<NOV;j++)
                    {
                        if(!select[j]&&G[i][j]<min)
                        {
                            min=G[i][j];
                            a=i;
                            b=j;
                        }
                    }
                }
            }

            if(min==INF)
            {
                System.out.println("The graph is not connected.");
                return;
            }

            System.out.println("Edge("+a+","+b+") cost="+min);
            mincost+=min;
            select[b]=true;
            ECount++;
        }
        System.out.println("Minimum cost of spanning tree : "+mincost);
    }

    public static void main(String args[])
    {
        Scanner s=new Scanner(System.in);
        int[][] G={{INF,8,2,INF,INF},
                    {8,INF,INF,5,6},
                    {2,INF,INF,INF,7},
                    {INF,5,INF,INF,4},
                    {INF,6,7,4,INF}};
        PrimMST(G,5);
    }
}
