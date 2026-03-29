package com.q1;

import java.util.Scanner;

class QueueOverFlow extends Exception
{
	QueueOverFlow(String s)
	{
		super(s);
	}
}

class QueueUnderFlow extends Exception
{
	QueueUnderFlow(String s)
	{
		super(s);
	}
}

interface Queue
{
	void insert(int value);
	void delete();
	void top();
	void display();
}

class QueueImpl implements Queue
{
	
	int arr[]=new int[10] ;
	int front=-1;
	int size=0;
	
	public void insert(int value) 
	{
		if(front==size)
		{
			try
			{
				throw new QueueOverFlow("Queue is full u cant push");
			}
			catch(QueueOverFlow ex)
			{
				ex.printStackTrace();
			}
		}
		else
		{
			arr[size]=value;
			size++;
			size%=10;
		}
		if(front==-1) front=0;
	}
	public void delete()
	{
		if(front==-1)
		{
			try
			{
				throw new QueueUnderFlow("Your Queue is empty u cant do pop opration");
			}
			catch(QueueUnderFlow ex)
			{
				ex.printStackTrace();
			}
		}
		else
		{
			front++;
			front%=10;
			if(front==size) front=-1 ;
		}
		
	}
	public void display()
	{
		System.out.println();
		System.out.println("queue is");
		if(front==-1)
		{
			System.out.println("Empty");
		}
		else if(front<size)
		{
			for(int i=front;i<=size-1;i++)
			{
				System.out.println(arr[i]+" ");
			}
			System.out.println();
		}
		else
		{
			for(int i=front;i<=9;i++)
			{
				System.out.println(arr[i]+" ");
			}
			for(int i=0;i<size;i++)
			{
				System.out.println(arr[i]+" ");
			}
		}
	}
	public void top()
	{
		if(size==0)
		{
			System.out.println("Top Element is "+arr[9]);
		}
		else
		{
			System.out.println("Top Element is "+arr[size-1]);
		}
	}
}

public class busTicket {

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
        Queue q = new QueueImpl();

        while(true)
        {
            System.out.println("\n--- Queue Menu ---");
            System.out.println("1. Insert (Push)");
            System.out.println("2. Delete (Pop)");
            System.out.println("3. Top");
            System.out.println("4. Display");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch(choice)
            {
                case 1:
                    System.out.print("Enter value to insert: ");
                    int val = sc.nextInt();
                    q.insert(val);
                    break;

                case 2:
                    q.delete();
                    break;

                case 3:
                    q.top();
                    break;

                case 4:
                    q.display();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                System.out.println("Invalid choice");
            }
        }

	}

}
