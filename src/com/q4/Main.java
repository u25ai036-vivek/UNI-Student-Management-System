package com.q4;

import java.util.*;	

public class Main {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

		String x;
		System.out.println("Enter Str");
		Scanner sc=new Scanner(System.in);
		x=sc.nextLine();

		String Words[]=x.split(" ");
		for(String word:Words)
		{
			System.out.print(StringUpper(word)+" ");
		}

	}

	static String StringUpper(String x)
	{
		String temp=String.valueOf(x.charAt(0));
		temp=temp.toUpperCase();
		x=temp+x.substring(1);

		return x;
	}

}
