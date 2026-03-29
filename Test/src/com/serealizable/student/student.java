package com.serealizable.student;

import java.io.*;


public class student implements Serializable
{
	private static final long serialVersionUID =1L;
	public String name;
    public int age;
    public String Id;
    public  String password;



//    public student(String name, int age, String Id, String password) {
//        this.name     = name;
//        this.age      = age;
//        this.Id       = Id;
//        this.password = password;
//    }

    //get mothdp
    public String getName()    { return name; }
    public int    getAge()     { return age; }
    public String getId()      { return Id; }
    public String getPassword(){ return password; }

    //ste methso
    public void setName(String name)       { this.name     = name; }
    public void setAge(int age)            { this.age      = age; }
    public void setId(String Id)           { this.Id       = Id; }
    public void setPassword(String password){ this.password = password; }


    public void serializeFile()
    {
        String branch=Id.substring(3,4);
        File f1 = new File("C:\\Users\\ALG\\Documents\\oopsAssingnment\\dataBase\\"+branch+"\\students\\" + Id);
        f1.getParentFile().mkdirs();
		try{
		    f1.createNewFile();
		    FileOutputStream fos=new FileOutputStream(f1);
		    ObjectOutputStream oos=new ObjectOutputStream(fos);
		    oos.writeObject(this);
		    oos.close();
		    fos.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ;
    }

    public static student dserializeFile(String id)
    {
    	student stemp=null;
        File f1 = new File("C:\\Users\\ALG\\Documents\\oopsAssingnment\\dataBase\\ai\\students\\"+id);
        try{	
		    FileInputStream fis=new FileInputStream(f1);
		    ObjectInputStream ois=new ObjectInputStream(fis);
		    stemp=(student)ois.readObject();
            ois.close();
            fis.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
        return stemp;
    }
    
}
