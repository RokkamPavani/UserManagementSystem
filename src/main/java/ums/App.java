package ums;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class App 
{
	static Scanner s=new Scanner(System.in);
	public static void main(String[] args)
	{
		System.out.println("Select Options:");
		System.out.println("1) Login"+"\t"+"2) Create Account"+"\t"+"3) Update Data");
		System.out.println("4) Delete Data"+"\t"+"5) View all users"+"\t"+"6) Exit");
		
		int key=s.nextInt();
		switch(key)
		{
		case 1:
			login();
			break;
		case 2:
			createAccount();
			break;
		case 3:
			update();
			break;
		case 4:
			delete();
			break;
		case 5:
			viewTable();
			break;
		case 6:
			System.exit(0);
			break;
		default:
			System.out.println("Invalid input");
			break;
		}
	}
	private static void viewTable() {
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("url","username","pw");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from user");
			System.out.printf("%-5s | %-15s | %-25s | %-15s | %-10s%n", "ID", "Name", "Email", "Phone", "Password");
		    System.out.println("--------------------------------------------------------------------------------");
		    while (rs.next()) 
		    {
		       System.out.printf("%-5d | %-15s | %-25s | %-15d | %-10s%n",rs.getInt(1), rs.getString(2), rs.getString(3), rs.getLong(4), rs.getString(5));
		    }
			rs.close();			
			st.close();
			con.close();
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	private static void delete() {
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("url","username","pw");
			PreparedStatement st=con.prepareStatement("delete from user where id=?");
			System.out.println("Enter id");
			st.setInt(1, s.nextInt());
			System.out.println(st.executeUpdate()+" Row updated...");
			viewTable();
			st.close();
			con.close();
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}
	private static void update() {
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("url","username","pw");
			PreparedStatement st=con.prepareStatement("update user set name=? where  id= ?");
			System.out.println("Enter new name");
			st.setString(1, s.next());
			System.out.println("Enter id");
			st.setInt(2, s.nextInt());
			System.out.println(st.executeUpdate()+" Row updated...");	
			viewTable();
			st.close();
			con.close();
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}
	private static void createAccount() {
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("url","username","pw");
			PreparedStatement st=con.prepareStatement("insert into user values(?,?,?,?,?)");
			System.out.println("Enter id");
			st.setInt(1, s.nextInt());
			System.out.println("Enter name");
			st.setString(2, s.next());
			System.out.println("Enter email");
			st.setString(3, s.next());
			System.out.println("Enter phnno");
			st.setLong(4, s.nextLong());
			System.out.println("Enter password");
			st.setString(5, s.next());
			System.out.println(st.executeUpdate()+" Row updated...");	
			viewTable();
			st.close();
			con.close();
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}
	private static void login() {
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("url","username","pw");
			PreparedStatement st=con.prepareStatement("select * from user where email =? and pw =?");	
			System.out.println("Enter email");
			st.setString(1, s.next());
			System.out.println("Enter password");
			st.setString(2, s.next());
//			createAccount();
			st.close();
			con.close();
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}
}
