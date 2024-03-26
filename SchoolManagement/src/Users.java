package com.school;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Users {
	/*Add Student or Teacher With Role*/
	public static void AddUser(Connection conn,Scanner sc,String role) {
		System.out.print("Enter "+role+" First Name : ");
		String name = sc.next();
		System.out.print("Enter "+role+" Address : ");
		String add = sc.next();
		System.out.print("Enter "+role+" Mobile : ");
		String mob = sc.next();
		System.out.print("Enter "+role+" Course/Subject : ");
		String course = sc.next();
		try {
			String sql = "INSERT INTO users(u_name,u_add,u_mobile,u_role,u_course) VALUE(?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1,name);
			pst.setString(2,add);
			pst.setString(3,mob);
			pst.setString(4,role);
			pst.setString(5,course);
			int ar = pst.executeUpdate();
			if(ar>0)
				System.out.println(role+" Added Successfylly!");
			else
				System.out.println(role+" Add Failed!");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	/*Display Student or Teacher With Role*/
	public static void DisplayUser(String role,Connection conn)
	{
		try {
			String str = "SELECT * FROM users WHERE u_role=?";
			PreparedStatement ps = conn.prepareStatement(str);
			ps.setString(1, role);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println(role+" ID : "+rs.getInt(1));
				System.out.println(role+" Name : "+rs.getString(2));
				System.out.println(role+" Address : "+rs.getString(3));
				System.out.println(role+" Mobile : "+rs.getString(4));
				System.out.println(role+" Course/Subject : "+rs.getString(6));
				System.out.println("=============================\n");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	/*Enter Marks of a particular Student*/
	public static void AddMarks(int uid,Connection conn,Scanner sc) {
		try {
			System.out.print("Enter Hindi Marks : ");
			int hindi = sc.nextInt();
			System.out.print("Enter English Marks : ");
			int english = sc.nextInt();
			System.out.print("Enter Maths Marks : ");
			int math = sc.nextInt();
			System.out.print("Enter Science Marks : ");
			int science = sc.nextInt();
			System.out.print("Enter Computer Marks : ");
			int computer = sc.nextInt();
			String str = "INSERT INTO marks VALUE(?,?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(str);
			pst.setInt(1, uid);
			pst.setInt(2, hindi);
			pst.setInt(3, english);
			pst.setInt(4, science);
			pst.setInt(5, math);
			pst.setInt(6, computer);
			int ar = pst.executeUpdate();
			if(ar>0)
				System.out.println("\nResult Updated Successfully!");
			else
				System.out.println("Failed To Update Result!");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	/*Find Student By ID*/
	public static boolean FindStudent(int id,Connection conn) {
		try {
			String str = "SELECT * FROM users WHERE u_id=? AND u_role='Student'";
			PreparedStatement pst = conn.prepareStatement(str);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			int count=0;
			while(rs.next())
				count++;
			if(count==0)
				return false;
			else
				return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	/* Display Result of Student By ID*/
	public static void ViewMarks(int id,Connection conn) {
		try {
			String sql = "SELECT u_name,u_course,hindi,english,math,science,computer FROM users JOIN marks on u_id=id WHERE u_id=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				System.out.println("Name : "+rs.getString(1));
				System.out.println("Course : "+rs.getString(2));
				System.out.println("Hindi : "+rs.getInt(3));
				System.out.println("English : "+rs.getInt(4));
				System.out.println("Maths : "+rs.getInt(5));
				System.out.println("Science : "+rs.getInt(6));
				System.out.println("Computer : "+rs.getInt(7));
				int total = 0;
				for(int i=3;i<=7;i++)
					total=total+(int)(rs.getInt(i));
				System.out.println("Total : "+total);
				System.out.println("Percentage : "+(total/5));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}