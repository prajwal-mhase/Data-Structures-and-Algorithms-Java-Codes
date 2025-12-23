import java.util.*;

class HeapSort
{
    public void MaxHeap(int a[])
    {
        int n=a.length;
        for(int i=(n/2)-1;i>=0;i--)
            MaxHeapify(a,n,i);
    
        for(int j=n-1;j>=0;j--)
        {
            int temp=a[0];
            a[0]=a[j];
            a[j]=temp;
            MaxHeapify(a,j,0);
        }
        System.out.print("Sorted Elements: ");
        for(int m=0;m<a.length;m++)
        {
            System.out.print(a[m]+" ");
        }
    }

    public void MaxHeapify(int a[],int n,int i)
    {
        int lar=i;
        int l=(2*i)+1;
        int r=(2*i)+2;

        if(l<n && a[l]>a[lar])
            lar=l;
        if(r<n && a[r]>a[lar])
            lar=r;

        if(lar!=i)
        {
            int temp=a[i];
            a[i]=a[lar];
            a[lar]=temp;

            MaxHeapify(a,n,lar);
        }
    }

    public void MinHeap(int a[])
    {
        int n=a.length;
        for(int i=(n/2)-1;i>=0;i--)
            MinHeapify(a,n,i);
    
        for(int j=n-1;j>=0;j--)
        {
            int temp=a[0];
            a[0]=a[j];
            a[j]=temp;
            MinHeapify(a,j,0);
        }
        System.out.print("Sorted Elements: ");
        for(int o=0;o<a.length;o++)
        {
            System.out.print(a[o]+" ");
        }
    }

    public void MinHeapify(int a[],int n,int i)
    {
        int small=i;
        int l=(2*i)+1;
        int r=(2*i)+2;

        if(l<n && a[l]<a[small])
            small=l;
        if(r<n && a[r]<a[small])
            small=r;

        if(small!=i)
        {
            int temp=a[i];
            a[i]=a[small];
            a[small]=temp;

            MinHeapify(a,n,small);
        }
    }
    public static void main(String args[])
    {
        
        Scanner s=new Scanner(System.in);
        System.out.print("Enter no. of elements: ");
        int p=s.nextInt();
        int a[]=new int[p];
        System.out.print("Enter Elements: ");
        for(int k=0;k<a.length;k++)
        {
            a[k]=s.nextInt();
        }
        
        HeapSort hs=new HeapSort();
        
        System.out.print("Sort(1. Ascending/2. Decending): ");
        int ch=s.nextInt();
        switch(ch)
        {
            case 1://Max Heap
                hs.MaxHeap(a);
                break;
            case 2://Min Heap
                hs.MinHeap(a);
                break;
            default:System.out.println("Invalid choice !");
        }
    }
}
