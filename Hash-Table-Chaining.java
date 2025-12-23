import java.util.Scanner;
import java.util.LinkedList;

class CWOR
{
    LinkedList<Integer>[] table;
    int size;

    CWOR(int size)
    {
        this.size=size;
        table=new LinkedList[size];
        for(int i=0;i<size;i++)
        {
            table[i]=new LinkedList<>();
        }
    }
    
    public void insert(int key)
    {
        int idx=hashfun(key);
        table[idx].add(key);
    }

    private int hashfun(int key)
    {
        return key%size;
    }

    public void display()
    {
        System.out.println("Hash Table:");
        for(int j=0;j<size;j++)
        {
            System.out.print("Index "+j+": ");
            for(int key:table[j])
            {
                System.out.print(key+" -> ");
            }
            System.out.println("null");
        }
    }

    public void search(int key)
    {
        int idx=hashfun(key);
        if(table[idx].contains(key))
        {
            System.out.println("Element " + key + " found at index: " + idx);
        }
        else
        {
            System.out.println("Element " + key + " not found!");
        }
    }
}

class HashTableCWOR
{
    public static void main(String[] args)
    {
        Scanner sr=new Scanner(System.in);
        System.out.print("Enter table size: ");
        int size=sr.nextInt();
        if(size <= 0)
        {
            System.out.println("Invalid table size. Exiting.");
            return;
        }
        CWOR c=new CWOR(size);
        while(true)
        {
            System.out.println("Menu: \n1. Insert \n2. Display \n3. Search \n4. Exit");
            System.out.print("Enter Choice: ");
            int ch=sr.nextInt();
            switch(ch)
            {
                case 1:
                    System.out.print("How many elements do you want to insert: ");
                    int n=sr.nextInt();
                    System.out.print("Enter elements: ");
                    for(int i=0;i<n;i++)
                    {
                        c.insert(sr.nextInt());
                    }
                    break;
                case 2:
                    c.display();
                    break;
                case 3:
                    System.out.print("Enter the element you want to search: ");
                    c.search(sr.nextInt());
                    break;
                case 4:
                    System.out.println("Program Ended!");
                    sr.close();
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
