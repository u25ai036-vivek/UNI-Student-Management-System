package com.q5;

import java.io.*;

public class Main {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		
		File f1=new File("C:\\Users\\ALG\\Documents\\java\\lab8\\src\\com\\q5\\abc.txt");
		FileReader Fr=null;
		FileWriter fw=null;
		try 
		{
			
			Fr=new FileReader(f1); //file reader
			String ans=""; // base string zero
			int ch;        // temp cahr
		
			while((ch=(int)Fr.read())!=-1)  // taking value that adding like sum=sum +i
			{
				ans+=String.valueOf((char)ch);
			}
		
			ans=ans.replace("his", "her");   //replacement
		
			fw=new FileWriter(f1); //file writer
			System.out.println(ans);         //for verification 
			fw.write(ans);
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(fw!=null) fw.flush();              //flush
				if(fw!=null) fw.close();				//close
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
