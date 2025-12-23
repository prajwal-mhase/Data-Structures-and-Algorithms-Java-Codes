import java.util.Scanner;

class LP
{
    int currSize=0;
    int maxSize;
    String[] keys;
    String[] values;

    public LP(int size)
    {
        this.maxSize=size;
        keys=new String[maxSize];
        values=new String[maxSize];
    }

    public int hash(String k)
    {
        return Integer.parseInt(k)%maxSize;
    }

    public boolean insert(String k,String v)
    {
        int temp=hash(k);
        int i=temp;
        do
        {
            if(keys[i]==null)
            {
                keys[i]=k;
                values[i]=v;
                currSize++;
                return true;
            }
            if(keys[i].equals(k))
            {
                values[i]=v;
                return false;
            }
            i=(i+1)%maxSize;
        } while(i!=temp);
        System.out.println("Hash table is full.");
        return false;
    }

    public void display()
    {
        boolean isEmpty = true;
        for(int i=0; i<maxSize; i++)
        {
            if(keys[i]!=null)
            {
                System.out.println("Key: "+keys[i]+", Value: "+values[i]);
                isEmpty = false;
            }
        }
        if (isEmpty)
        {
            System.out.println("Hash table is empty.");
        }
    }

    public boolean delete(String k)
    {
        int temp=hash(k);
        int i=temp;
        while(keys[i]!=null) 
        {
            if(keys[i].equals(k))
            {
                keys[i]=null;
                values[i]=null;
                currSize--;
                rehash(i);
                return true;
            }
            i=(i+1)%maxSize;
            if(i==temp) break;
        }
        System.out.println("Key not found for deletion.");
        return false;
    }

    public void rehash(int index)
    {
        int i=(index+1)%maxSize;
        while(keys[i]!=null)
        {
            String k=keys[i];
            String v=values[i];
            keys[i]=null;
            values[i]=null;
            currSize--;
            insert(k,v);
            i=(i+1)%maxSize;
        }
    }

    public void Get(String k)
    {
        int temp=hash(k);
        int i=temp;
        while(keys[i]!=null)
        {
            if(keys[i].equals(k))
            {
                System.out.println("Value : "+values[i]);
                return;
            }
            i=(i+1)%maxSize;
            if(i==temp) break;
        }
        System.out.println("Key not found.");
    }

    public void Clear()
    {
        for(int i=0; i<maxSize; i++)
        {
            keys[i]=values[i]=null;
        }
        currSize=0;
    }
}

public class HashTableLP
{
    public static void main(String[] args)
    {
        Scanner s=new Scanner(System.in);
        System.out.print("Enter table size: ");
        int size=s.nextInt();
        while (size <= 0)
        {
            System.out.print("Please enter a positive integer: ");
            size = s.nextInt();
        }
        LP l=new LP(size);
        int choice=0;

        do
        {
            System.out.print("Menu:\n1. Insert\n2. Display\n3. Delete\n4. Get\n5. Clear\n6. Size\n7. Exit\nEnter your choice: ");
            int ch=s.nextInt();
            switch(ch)
            {
                case 1:
                    System.out.print("Enter key and value: ");
                    boolean updated = l.insert(s.next(), s.next());
                    if (updated)
                    {
                        System.out.println("Data inserted successfully.");
                    }
                    else
                    {
                        System.out.println("Data updated successfully.");
                    }
                    break;
                case 2:
                    l.display();
                    break;
                case 3:
                    System.out.print("Enter the key of the value you want to delete: ");
                    if (l.delete(s.next()))
                    {
                        System.out.println("Data deleted successfully.");
                    }
                    break;
                case 4:
                    System.out.print("Enter the key: ");
                    l.Get(s.next());
                    break;
                case 5:
                    l.Clear();
                    System.out.println("Hash table cleared.");
                    break;
                case 6:
                    System.out.println("Current size : "+l.currSize);
                    break;
                case 7:
                    choice=1;
                    System.out.println("Program ended !");
                    break;
                default:
                    System.out.println("Invalid choice !");
            }
        } while(choice!=1);
    }
}
