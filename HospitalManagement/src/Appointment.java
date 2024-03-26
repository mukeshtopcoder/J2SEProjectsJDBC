import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Appointment {
	public static boolean BookAppointment(Connection conn,Scanner sc,Patient patient,Doctor doctor) {
		System.out.println("Enter Patient ID : ");
		int pid = sc.nextInt();
		if(patient.GetPatientById(pid, conn)) {
			System.out.println("Enter Doctor ID : ");
			int did = sc.nextInt();
			if(doctor.GetDoctorById(did, conn)) {
				System.out.println("Enter Date (DD-MM-YYYY) : ");
				String date = sc.next();
				try {
				String sql = "INSERT INTO appointment(pid,did,dates) VALUE(?,?,?)";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setInt(1, pid);
				pst.setInt(2, did);
				pst.setString(3, date);
				int ar = pst.executeUpdate();
				if(ar>0)
					return true;
				else
					return false;
				}catch(Exception e) {
					e.printStackTrace();
				}
				return true;
			}else
				return false;
		}else
			return false;
	}
	public static void ViewAppointments(Connection conn) {
		try {
			String sql = "SELECT a.id,p.fname,d.fname,p.disease,a.dates FROM appointment a\r\n"
					+ "JOIN patient p\r\n"
					+ "ON a.pid=p.pid\r\n"
					+ "JOIN doctor d\r\n"
					+ "ON a.did=d.did;";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				System.out.println("Appointment ID : "+rs.getInt(1));
				System.out.println("Patient Name : "+rs.getString(2));
				System.out.println("Doctor Name : "+rs.getString(3));
				System.out.println("Disease Name : "+rs.getString(4));
				System.out.println("Appointment Date : "+rs.getString(5));
				System.out.println("=============================");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}