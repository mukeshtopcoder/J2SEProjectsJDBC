import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Patient {
	public void AddPatient(Scanner sc, Connection conn) {
		System.out.print("\nEnter First Name : ");
		String fname = sc.next();
		System.out.print("Enter Last Name : ");
		String lname = sc.next();
		System.out.print("Enter Mobile Number : ");
		String mobile = sc.next();
		System.out.print("Enter Address : ");
		String address = sc.next();
		System.out.print("Enter Disease : ");
		String disease = sc.next();
		try {
			String sql = "INSERT INTO patient(fname,lname,mobile,address,disease) VALUE(?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, fname);
			pst.setString(2, lname);
			pst.setString(3, mobile);
			pst.setString(4, address);
			pst.setString(5, disease);
			int ar = pst.executeUpdate();
			if(ar>0)
				System.out.println("Patient Successfully Added!");
			else
				System.out.println("Patient Added Failed!");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void ViewPatients(Connection conn) {
		try {
			String str = "SELECT * FROM patient";
			PreparedStatement pst = conn.prepareStatement(str);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				System.out.println("Patient ID : "+rs.getInt(1));	
				System.out.println("Patient First Name : "+rs.getString(2));
				System.out.println("Patient Last Name : "+rs.getString(3));
				System.out.println("Patient Mobile No : "+rs.getString(4));
				System.out.println("Patient Address : "+rs.getString(5));
				System.out.println("Patient Disease : "+rs.getString(6));
				System.out.println("===============================");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public boolean GetPatientById(int pid,Connection conn) {
		try {
			String sql = "SELECT * FROM patient Where pid=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, pid);
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