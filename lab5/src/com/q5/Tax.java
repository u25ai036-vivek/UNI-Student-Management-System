package com.q5;

import java.util.Scanner;;

class Account
{
	float grossPay;
	float taxRate=0.15f;
	float numOfHours;
	float moneyPerHours=12f;

	Account(float value)
	{
		numOfHours=value;
	}

	float calculateTax()
	{
		return grossPay*taxRate;
	}

	float  calculateNetPay()
	{
		return moneyPerHours*numOfHours;
	}

}

public class Tax {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		System.out.println("Enter working hours");
		Scanner sc=new Scanner(System.in);
		float x=sc.nextFloat();
		Account emp=new Account(x);
		emp.grossPay=emp.calculateNetPay();
		System.out.println("Gross pay and tax is "+emp.grossPay+" and "+emp.calculateTax());

	}

}
