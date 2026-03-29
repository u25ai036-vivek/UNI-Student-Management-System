package com.q2;

import java.util.Scanner;

class UserDifinedException extends Exception
{
	UserDifinedException(String str)
	{
		super(str);
	}
}
class NullStringException extends Exception
{
	NullStringException(String str)
	{
		super(str);
	}
}

public class HexaDecimal {

	public static void main(String[] args) throws UserDifinedException , NullStringException
	{
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		String number;
		System.out.println("Enter the Number");
		number=sc.nextLine();
		sc.close();
		if(number.length()==0)
		{
			throw new NullStringException("Empty String");
		}
		
		else
		{
			
		
		int flag=0;
		for(int i=0;i<number.length();i++)
		{
			char c=number.charAt(i);
			if(c!='A'&&c!='B'&&c!='C'&&c!='D'&&c!='E'&&c!='F'&&c!='1'&&c!='2'&&c!='3'&&c!='4'&&c!='5'&&c!='6'&&c!='7'&&c!='8'&&c!='9')
			{
				flag=1;
				break;
			}
		}
		if(flag==0)
		{
			System.out.println("Entered Number is Hexadecimal");
		}
		else
		{
			throw new UserDifinedException("Entered Number is not Hexadecimal");
		}
		}
	}

}
