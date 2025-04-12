package superMarket;
import java.util.ArrayList;
import java.util.Scanner;
public class Users {
	private int uid;
	private String fname;
	private String email;
	private String pwd;
	private String roles;
	private int status;
	private static Scanner sc = new Scanner(System.in);
	private static Scanner scanner = new Scanner(System.in);
	public static void ActiveUser() {
		Users user = null;
		ViewUsers("Customer");
		System.out.print("Enter User ID : ");
		int uid = sc.nextInt();
		user = Dao.GetUserById(uid);
		if(user!=null) {
			System.out.println("Name : "+user.getFname()+"\nEmail : "+user.getEmail());
			System.out.println("1. Active");
			System.out.println("2. Unactive");
			System.out.print("Enter Choice : ");
			int flag=0;
			int choice = sc.nextInt();
			if(choice==1) {
				flag=1;
				user.setStatus(1);
			}else {
				if(choice==2) {
					flag=1;
					user.setStatus(0);
				}else {
					System.out.println("Wrong Choice!\n");
				}
			}
			if(flag==1) {
				Dao.UpdateUserById(user);
				System.out.println("User Update Successfully!\n");
			}
		}else {
			System.out.println("User Not Found!");
		}
	}
	public static void ViewUsers(String roles) {
		ArrayList<Users> al = Dao.ViewUsers(roles);
		System.out.println("Here Is All Customer Details");
		System.out.println("  UID         Name            Email         Status  ");
		System.out.println("---------------------------------------------");
		for(Users u : al) {
			System.out.printf("%5d%15s%20s",u.getUid(),u.getFname(),u.getEmail());
			if(u.getStatus()==1)
				System.out.print("   Active\n");
			else
				System.out.print("   Unactive\n");
		}
		System.out.println("---------------------------------------------");
		System.out.println("Press Enter Key To Continue...");
		scanner.nextLine();
	}
	public static void Login(String roles) {
		System.out.print("Enter Email ID : ");
		String email = sc.next();
		System.out.print("Enter Password : ");
		String pwd = sc.next();
		Users u = Dao.CheckUser(email,pwd,roles);
		if(u!=null) {
			if(u.getRoles().equals("Admin")) {
				AdminDashboard();
			}else {
				if(u.getStatus()==1) {
				CustomerDashboard(u);
				}else {
					System.out.println("You Are Not A Active Customer\nPlease Contact Admin!\n");
				}
			}
		}else {
			System.out.println("User is Not Available");
		}
	} 
	public static void AdminDashboard() {
		int flag=1;
		while(flag==1) {
			System.out.println("\n\n\n***** ADMIN *****\n");
			System.out.println("1. Add Product");
			System.out.println("2. View All Products");
			System.out.println("3. View All Customers");
			System.out.println("4. Delete Product");
			System.out.println("5. View All Orders");
			System.out.println("6. Active/Unactive User");
			System.out.println("7. Exit");
			System.out.print("Enter Your Choice : ");
			int choice = sc.nextInt();
			switch(choice) {
			case 1: 
				Products.AddProduct(sc);
				break;
			case 2:
				Products.ViewProducts();
				break;
			case 3: 
				ViewUsers("Customer");
				break;
			case 4:
				Products.DeleteProduct(sc);
				break;
			case 5:
				Orders.ViewOrders();
				break;
			case 6:
				ActiveUser();
				break;
			case 7:
				System.out.println("\nBye-Bye ADMIN!\n");
				flag=0;
				break;
			default:
				System.out.println("Please Try Again!\n");
			}
		}
	}
	public static void CustomerDashboard(Users c) {
		int flag=1;
		while(flag==1) {
		System.out.println("\n\n***** Welcome, "+c.getFname()+" *****");
		System.out.println("1. View All Products");
		System.out.println("2. View All Orders");
		System.out.println("3. Cancel An Order");
		System.out.println("4. Exit");
		System.out.print("Enter Your Choice : ");
		int choice = sc.nextInt();
		switch(choice) {
		case 1:
			Products.BuyProduct(c);
			break;
		case 2: 
			Orders.ViewOrderById(c.getUid());
			break;
		case 3:
			break;
		case 4:
			System.out.println("Bye, "+c.getFname());
			flag=0;
			break;
		default:
			System.out.println("\nWrong Choice!\n");
		}
		}
	}
	public static void AddUser(String roles) {
		System.out.print("Enter Full Name : ");
		String fname = sc.nextLine();
		System.out.print("Enter Email : ");
		String email = sc.next();
		System.out.print("Enter Password : ");
		String pwd = sc.next();
		Users u = new Users(fname,email,pwd,roles);
		if(Dao.AddUser(u)) {
			System.out.println("Customer Added Success!");
			System.out.println("Welcome! "+u.getFname());
		}else {
			System.out.println("Failed To Add Customer!");
		}
	}
	public Users(String fname, String email, String pwd, String roles) {
		super();
		this.fname = fname;
		this.email = email;
		this.pwd = pwd;
		this.roles = roles;
	}
	public Users() {
		super();
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}