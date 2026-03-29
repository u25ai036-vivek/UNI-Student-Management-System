package com.q6;

import java.util.Scanner;

class student 
{
	String name;

	String average(double mark1, double mark2, double mark3) 
	{
		double avg = (mark1 + mark2 + mark3) / 3;
		if (avg > 50) 
		{
			return "Passed";
		} 
		else 
		{
			return "Failed";
		}
	}

	String setName(String name) 
	{
		this.name = name;
		return name;
	}

}

public class Result {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		student s1 = new student();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the name of the student:");
		String name = sc.nextLine();
		s1.setName(name);
		System.out.println("Enter the marks of three subjects:");
		double mark1 = sc.nextDouble();
		double mark2 = sc.nextDouble();
		double mark3 = sc.nextDouble();
		String result = s1.average(mark1, mark2, mark3);
		System.out.println("The student " + s1.name + " has " + result);
	}

}
