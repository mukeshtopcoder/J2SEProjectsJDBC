package com.school;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class AdminPanel {
	/*Details for Database Connection*/
	private static Connection conn;
	private static Scanner sc = new Scanner(System.in);
	private static final String url = "jdbc:mysql://127.0.0.1:3306/school";
	private static final String user = "root";
	private static final String pass = "root123";
	public static void main(String[] args) {
		/*DashBoard For ADMIN*/
		try {
			conn = DriverManager.getConnection(url,user,pass);
			while(true) {
				System.out.print("\nPress 1 to Add Student\nPress 2 to Add Teacher\nPress 3 to View All Student\nPress 4 to View All Teachers\nPress 5 to Add Result\nPress 6 To Display Result\nPress 7 to Exit!\n\nEnter Your Choice : ");
				int choice = sc.nextInt();
				switch(choice) {
				case 1: Users.AddUser(conn, sc, "Student"); break;
				case 2: Users.AddUser(conn, sc, "Teacher"); break;
				case 3: Users.DisplayUser("Student", conn);; break;
				case 4: Users.DisplayUser("Teacher", conn); break;
				case 5: System.out.print("\nEnter Student ID : ");
						int id = sc.nextInt();
						if(Users.FindStudent(id, conn))
							Users.AddMarks(id,conn,sc);
						else
							System.out.println("\nStudent Not Found!");
						break;
				case 6:System.out.print("\nEnter Student ID : ");
						id = sc.nextInt();
						if(Users.FindStudent(id, conn))
							Users.ViewMarks(id,conn);
						else
							System.out.println("\nStudent Not Found!");
						break;
				case 7: 
					System.out.println("\nBye-Bye");
					System.exit(0);
				default:
					System.out.println("\nWrong Entered!\nTry Again!");
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}