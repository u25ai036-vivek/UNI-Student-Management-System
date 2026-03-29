package com.serealizable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.*;

import com.serealizable.student.student;



public class Main
{

	public static void main(String[] args) 
	{
		student s1=new student();
		s1.name="sajal";
		s1.age=22;
		s1.Id="123ai";
		s1.password="sajal@123";

		s1.serializeFile();
		student stemp= student.dserializeFile("123ai");

		System.out.println("Name: " + stemp.getName()
							+ "\nAge: " + stemp.getAge()
							+ "\nId: " + stemp.getId()
							+ "\nPassword: " + stemp.getPassword()
							);
		
		

	}

}
