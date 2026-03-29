package com.serealizable.student;

class Strin extends Object
{
	char[] str;

	Strin(char ch[])
	{
		str=new char[ch.length];
		for(int i=0;i<ch.length;i++)
		{

			str[i]=ch[i];
		}
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		for(int i=0;i<str.length;i++)
		{
			System.out.printf("%c",str[i]);
		}
		return "";
	}
	

	


}

public class StringDemo 
{

	public static void main(String[] args) 
	{
		char[] ch= {'h','e','l','l','o'};
		Strin x=new Strin(ch);
		
		System.out.println(x);
		

	}

}
