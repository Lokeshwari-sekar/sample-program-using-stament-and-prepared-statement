package com.differentstatements;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public  class StmtAndPrepstmt
{
	static String driver="com.mysql.cj.jdbc.Driver";
	static String url="jdbc:mysql://localhost:3306/edustudent";
	static String un="root";
	static String pa="root";
	static Scanner sc=new Scanner(System.in);
	static Connection con=null;
	static Statement st=null;
	static ResultSet rs=null;
	static PreparedStatement pst=null;
	public static void main(String[] args)
	{
		while(true) {
		System.out.println("enter the choice which type of statement you want to execute");
		System.out.println("1.normal statement execution for insertion operation");
		System.out.println("2.prepared statement execution for insertion operation");
		int ch=sc.nextInt();
		switch(ch)
		{
		case 1:try { 
		Class.forName(driver);
		con=DriverManager.getConnection(url, un, pa);
		st=con.createStatement();
		System.out.println("enter the name");
		String na=sc.next();
		String sql1="select * from  mobinfo where name='"+na+"'";
		rs=st.executeQuery(sql1);
		if(!rs.next())
		{
			System.out.println("enter the ram");
			int ra=sc.nextInt();
			System.out.println("enter the price");
			int pri=sc.nextInt();
			String ins="insert into mobinfo values('"+na+"',"+ra+","+pri+")";
			
			int i=st.executeUpdate(ins);
			if(i>0)
			{
				System.out.println("result is inserted");
			}
	   }
		else
		{
			System.out.println("id is exists");
		}
     }
	catch (Exception e) {
		
		e.printStackTrace();
		
	}
		break;
		
		case 2:
			try
			{
				Class.forName(driver);
				con=DriverManager.getConnection(url, un, pa);
				System.out.println("enter the name");
				String name=sc.next();
				String sql="select * from mobinfo where name=?";
				pst=con.prepareStatement(sql);
				pst.setString(1, name);
				rs=pst.executeQuery();
				if(!rs.next())
				{
					System.out.println("enter the ram");
					int ram=sc.nextInt();
					System.out.println("ente the price");
					int price=sc.nextInt();
					String ins="insert into mobinfo values(?,?,?)";
					pst=con.prepareStatement(ins);
					pst.setString(1, name);
					pst.setInt(2, ram);
					pst.setInt(3, price);
					int i=pst.executeUpdate();
					if(i>0)
					{
						System.out.println("inserted succesfully");
					}
				}
				else
				{
					System.out.println("id is exists");
				}
				
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
	}
		System.out.println("do you want to continue yes/no");
		String in=sc.next();
		if(in.equalsIgnoreCase("no"))
		{
			break;
		}
	}
}}
