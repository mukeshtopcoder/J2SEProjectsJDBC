import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class AdminPanel {
	private static final String url = "jdbc:mysql://127.0.0.1:3306/hospital";
	private static final String user = "root";
	private static final String pass = "root123";
	private static Connection conn;
	private static Scanner sc = new Scanner(System.in);
	static Patient patient = new Patient();
	static Doctor doctor = new Doctor();
	public static void main(String[] args) {
		try {
			conn = DriverManager.getConnection(url,user,pass);
			while(true) {
				System.out.println("\n\n*** HOSPITAL MANAGEMENT SYSTEM ***");
				System.out.println("1. Add Patient");
				System.out.println("2. View Patients");
				System.out.println("3. Add Doctor");
				System.out.println("4. View Doctors");
				System.out.println("5. Book An Appointment");
				System.out.println("6. View Appointments");
				System.out.println("7. EXIT");
				System.out.print("Enter Your Choice : ");
				int choice = sc.nextInt();
				switch(choice) {
				case 1: patient.AddPatient(sc, conn); break;
				case 2: patient.ViewPatients(conn); break;
				case 3: doctor.AddDoctor(sc, conn); break;
				case 4: doctor.ViewDoctors(conn); break;
				case 5: if(Appointment.BookAppointment(conn,sc,patient,doctor))
							System.out.println("Appointment Booked!");
						else
							System.out.println("Appointment Booking Failed!");
						break;
				case 6: Appointment.ViewAppointments(conn); break;
				case 7: 
					System.out.println("Bye-Bye");
					System.exit(0);
				default: System.out.println("\nWrong Entered!\n\n");
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}