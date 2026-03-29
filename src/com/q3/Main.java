package com.q3;

import java.util.*;

public class Main {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		String Name[]=new String[10];
		Scanner sc=new Scanner(System.in);
		for(int i=0;i<Name.length;i++)
		{
			System.out.println("Enter the name  "+(i+1));
			Name[i]=sc.nextLine();
		}

		for(int i=0;i<Name.length;i++)
		{
			Name[i]=Remove3(Name[i]);
			System.out.println(Name[i]);
		}
		
		for(int i=0;i<Name.length-1;i++)
		{
			for(int j=i+1;j<Name.length;j++)
			{
				if(Name[i].compareTo(Name[j])>0)
				{
					String temp=Name[i];
					Name[i]=Name[j];
					Name[j]=temp;
				}
			}
		}

		System.out.println("Sorted names are:");
		for(int i=0;i<Name.length;i++)
		{
			System.out.println(Name[i]);
		}
	}

	static String Remove3(String Name)
	{
		if(Name.length()>3)
		{
			return Name.substring(3,Name.length());
		}
		else
		{
			return "-1";
		}
	}

}
