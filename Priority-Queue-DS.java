import java.util.*;

class PriorityQueue
{
    int a[]=new int[100];
    int size=0;

    public void insert(int x)
    {
        if(size>=a.length)
        {
            System.out.println("Priority Queue is full!");
            return;
        }
        a[size++]=x;
        ShiftUp(size-1);
    }

    public void ShiftUp(int n)
    {
        while (n>0 && a[parent(n)]<a[n])
        {
            swap(parent(n),n);
            n=parent(n);
        }
    }

    public int parent(int n)
    {
        return(n-1)/2;
    }

    void swap(int i,int j)
    {
        int temp=a[i];
        a[i]=a[j];
        a[j]=temp;
    }

    public void display()
    {
        if (size<=0)
        {
            System.out.println("Priority Queue is empty!");
            return;
        }
        System.out.print("Priority Queue: ");
        for (int i=0;i<size;i++)
        {
            System.out.print(a[i]+" ");
        }
        System.out.println();
    }

    public void remove(int y)
    {
        int idx=-1;
        for (int i=0;i<size;i++)
        {
            if (a[i]==y)
            {
                idx=i;
                break;
            }
        }
        if (idx==-1)
        {
            System.out.println("Element not found!");
            return;
        }
        a[idx]=a[size-1];
        size--;
        ShiftUp(idx);
        ShiftDown(idx);
    }

    public void ShiftDown(int n)
    {
        while (2*n+1<size)
        {
            int j=2*n+1;
            if(j+1<size && a[j]<a[j+1])
                j++;
            if (a[n]>=a[j])
                break;
            swap(n,j);
            n=j;
        }
    }

    public void ChangePriority(int oldV, int newV)
    {
        int idx=-1;
        for (int i=0;i<size;i++)
        {
            if(a[i]==oldV)
            {
                idx=i;
                break;
            }
        }
        if (idx==-1)
        {
            System.out.println("Element not found!");
            return;
        }
        a[idx]=newV;
        if(newV>oldV)
            ShiftUp(idx);
        else
            ShiftDown(idx);
    }

    public static void main(String[] args)
    {
        Scanner sr=new Scanner(System.in);
        PriorityQueue p=new PriorityQueue();
        while(true)
        {
            System.out.print("Menu: \n1. Insert \n2. Display \n3. Remove \n4. Change Priority \n5. Max value \n0. Exit \nEnter choice: ");
            switch(sr.nextInt())
            {
                case 1:
                    System.out.print("Enter the element you want to insert: ");
                    p.insert(sr.nextInt());
                    System.out.println("Element inserted successfully.");
                    break;
                case 2:
                    p.display();
                    break;
                case 3:
                    if (p.size<=0)
                    {
                        System.out.println("Priority Queue is empty!");
                        break;
                    }
                    System.out.print("Enter the element you want to delete: ");
                    p.remove(sr.nextInt());
                    System.out.println("Element removed successfully.");
                    break;
                case 4:
                    System.out.println("Enter old value: ");
                    int oldV=sr.nextInt();
                    System.out.println("Enter new value: ");
                    int newV=sr.nextInt();
                    p.ChangePriority(oldV,newV);
                    System.out.println("Priority changed successfully.");
                    break;
                case 5:
                    System.out.println("Max element: "+p.a[0]);
                    break;
                case 0:
                    System.out.println("Program Ended !");
                    sr.close();
                    return;
                default:
                    System.out.println("Invalid choice !");
                    break;
            }
        }
    }
}
