package com.q3;

import java.util.Scanner;

class Product 
{
	String id;
	String name;
	String catogoryId;
	float unitPrice;


}
class ElectricalProducts extends Product
{
	float rangeStart;
	float rangeEnd;
	float Wattage;

	void setWattage(float value)
	{
		Wattage=value;
	}
	void setPrice(float value)
	{
		unitPrice=value;
	}
	void setRange(float v1,float v2)
	{
		rangeStart=v1;
		rangeEnd=v2;
	}
	ElectricalProducts(String v1,String v2,float v3)
	{
		id=v1;
		name=v2;
		unitPrice=v3;
	}
	void Display()
	{
		System.out.println("id "+id);
		System.out.println("name "+name);
		System.out.println("unit price "+unitPrice);
		System.out.println("range "+rangeStart+" to "+rangeEnd);
		System.out.println("wattage "+Wattage);
	}
}

public class ProductDetails {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc= new Scanner(System.in);
		ElectricalProducts voltMeter=new ElectricalProducts("001","Volt Meter",400);
		voltMeter.Display();
		System.out.println("Enter new range of voltage and new Wattage");
		float a,b,c;
		a=sc.nextFloat();
		b=sc.nextFloat();
		c=sc.nextFloat();
		voltMeter.setRange(a,b);
		voltMeter.setWattage(c);
		voltMeter.Display();
		sc.close();
		
	}

}
