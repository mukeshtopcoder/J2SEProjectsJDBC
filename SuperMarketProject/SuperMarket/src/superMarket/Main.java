package superMarket;
import java.util.Scanner;
public class Main {
	private static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		while(true) {
			System.out.println("---------------------------\n");
			System.out.println("1. Admin Login");
			System.out.println("2. Customer Login");
			System.out.println("3. Customer Signup");
			System.out.println("4. Exit");
			System.out.print("Enter Your Choice : ");
			int choice = sc.nextInt();
			switch(choice) {
			case 1: 
				Users.Login("Admin");
				break;
			case 2: 
				Users.Login("Customer");
				break;
			case 3: 
				Users.AddUser("Customer");
				break;
			case 4: 
				System.out.println("\nThank You! For Using Our Software!");
				System.exit(0);
				break;
			default:
				System.out.println("\nPlease Re-Enter Your Choice!");
			}
		}
	}
}