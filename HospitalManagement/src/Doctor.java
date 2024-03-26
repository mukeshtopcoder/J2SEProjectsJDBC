import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Doctor {
	public void AddDoctor(Scanner sc, Connection conn) {
		System.out.print("\nEnter First Name : ");
		String fname = sc.next();
		System.out.print("Enter Last Name : ");
		String lname = sc.next();
		System.out.print("Enter Mobile Number : ");
		String mobile = sc.next();
		System.out.print("Enter Speciality : ");
		String speciality = sc.next();
		try {
			String sql = "INSERT INTO doctor(fname,lname,mobile,speciality) VALUE(?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, fname);
			pst.setString(2, lname);
			pst.setString(3, mobile);
			pst.setString(4, speciality);
			int ar = pst.executeUpdate();
			if(ar>0)
				System.out.println("Doctor Successfully Added!");
			else
				System.out.println("Doctor Added Failed!");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void ViewDoctors(Connection conn) {
		try {
			String str = "SELECT * FROM doctor";
			PreparedStatement pst = conn.prepareStatement(str);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				System.out.println("Doctor ID : "+rs.getInt(1));	
				System.out.println("Doctor First Name : "+rs.getString(2));
				System.out.println("Doctor Last Name : "+rs.getString(3));
				System.out.println("Doctor Mobile No : "+rs.getString(4));
				System.out.println("Doctor Speciality : "+rs.getString(5));
				System.out.println("===============================");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public boolean GetDoctorById(int did,Connection conn) {
		try {
			String sql = "SELECT * FROM doctor WHERE did=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, did);
			ResultSet rs = pst.executeQuery();
			int count=0;
			while(rs.next())
				count++;
			if(count>0)
				return true;
			else
				return false;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}