package com.q1;

import java.util.Scanner;
import java.util.*;


public class Main {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		String x;
		System.out.println("Enter Str");
		Scanner sc=new Scanner(System.in);
		x=sc.nextLine();
		int small=-1;
		int big=-1;
		if(x.length()>3)
		{
			String slits[]=x.split(" ");
			int i=0;
			while(i<slits.length)
			{
				if(slits[i].equalsIgnoreCase("the"))
				{
					if(small==-1) small=i;
					big=i;
				}
				i++;
			}
			for(int j=small+1;j<big;j++)
			{
				System.out.print(slits[j]+" ");
			}
		}
	}

}
