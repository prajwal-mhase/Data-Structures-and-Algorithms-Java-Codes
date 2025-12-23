import java.util.Scanner;
import java.util.Arrays;

class PrimsUI
{
    static int NOV;
    static final int INF=9999;
    static boolean[] select;

    public static void PrimMST(int[][] G)
    {
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
            System.out.println("Edge("+a+"--"+b+") cost="+min);
            mincost+=min;
            select[b]=true;
            ECount++;
        }
        System.out.println("Minimum cost of spanning tree : "+mincost);
    }

    public static void main(String args[])
    {
        Scanner s=new Scanner(System.in);
        System.out.print("Enter the number of vertices: ");
        NOV=s.nextInt();
        select=new boolean[NOV];
        int[][] G=new int[NOV][NOV];

        for(int i=0;i<NOV;i++)
        {
            for(int j=0;j<NOV;j++)
            {
                System.out.print("Enter cost of "+i+" -- "+j+" : ");
                G[i][j]=s.nextInt();
            }
        }

        PrimMST(G);
    }
}
