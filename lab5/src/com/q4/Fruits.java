package com.q4;

import java.util.Scanner;

class Fruit
{
	String name;
	int numbrof;
	float price;
	Fruit(String s,int n,float f)
	{
		name=s;
		numbrof=n;
		price=f;
	}
	void DisplayFruit()
	{
		System.out.println("name "+name);
		System.out.println("Quntity "+numbrof);
		System.out.println("proce "+price);
	}
}

public class Fruits {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		String name;
		int numbrof;
		float price;
		System.out.println("Enter name , quntity and price");
		name=sc.nextLine();
		numbrof=sc.nextInt();
		price=sc.nextFloat();

		Fruit f1=new Fruit(name, numbrof, price);
		f1.DisplayFruit();
		sc.nextLine();

		System.out.println("Enter name , quntity and price");
		name=sc.nextLine();
		numbrof=sc.nextInt();
		price=sc.nextFloat();

		Fruit f2=new Fruit(name, numbrof, price);
		f2.DisplayFruit();
	}

}
