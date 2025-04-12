package superMarket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Dao {
	private static final String url = "jdbc:mysql://127.0.0.1:3306/supermarket";
	private static final String pwd = "root123";
	private static final String user = "root";
	private static Connection getConnection() {
		try {
			Connection conn = DriverManager.getConnection(url,user,pwd);
			return conn;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static ArrayList<Orders> ViewOrderById(int uid) {
		ArrayList<Orders> al = new ArrayList<>();
		Orders o = null;
		try {
			String sql = "SELECT orders.oid,users.uid,users.fname,product.pname,orders.qty,product.price,orders.qty*product.price AS Total,orders.qty*product.price*18/100 AS gst,orders.qty*product.price+orders.qty*product.price*18/100 AS GrandTotal FROM users JOIN orders ON users.uid=orders.uid JOIN product ON orders.pid=product.pid WHERE users.uid=?;";
			PreparedStatement pst = getConnection().prepareStatement(sql);
			pst.setInt(1, uid);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				o = new Orders();
				o.setOid(rs.getInt(1));
				o.setUid(rs.getInt(2));
				o.setFname(rs.getString(3));
				o.setPname(rs.getString(4));
				o.setQty(rs.getInt(5));
				o.setPrice(rs.getInt(6));
				o.setTotal(rs.getInt(7));
				o.setGst(rs.getInt(8));
				o.setGtotal(rs.getInt(9));
				al.add(o);
			}
			return al;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return al;
	}
	public static ArrayList<Orders> ViewOrders() {
		ArrayList<Orders> al = new ArrayList<>();
		Orders o = null;
		try {
			String sql = "SELECT orders.oid,users.uid,users.fname,product.pname,orders.qty,product.price,orders.qty*product.price AS Total,orders.qty*product.price*18/100 AS gst,orders.qty*product.price+orders.qty*product.price*18/100 AS GrandTotal FROM users JOIN orders ON users.uid=orders.uid JOIN product ON orders.pid=product.pid;";
			PreparedStatement pst = getConnection().prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				o = new Orders();
				o.setOid(rs.getInt(1));
				o.setUid(rs.getInt(2));
				o.setFname(rs.getString(3));
				o.setPname(rs.getString(4));
				o.setQty(rs.getInt(5));
				o.setPrice(rs.getInt(6));
				o.setTotal(rs.getInt(7));
				o.setGst(rs.getInt(8));
				o.setGtotal(rs.getInt(9));
				al.add(o);
			}
			return al;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return al;
	}
	public static boolean UpdateProduct(Products p) {
		try {
			String sql = "UPDATE product SET qty=? WHERE pid=?";
			PreparedStatement pst = getConnection().prepareStatement(sql);
			pst.setInt(1, p.getQty());
			pst.setInt(2, p.getPid());
			int ar = pst.executeUpdate();
			if(ar>0) {
				return true;
			}else {
				return false;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public static boolean OrderProduct(int uid,int pid,int qty) {
		try {
			String sql = "INSERT INTO orders(uid,pid,qty) VALUE(?,?,?)";
			PreparedStatement pst = getConnection().prepareStatement(sql);
			pst.setInt(1, uid);
			pst.setInt(2, pid);
			pst.setInt(3, qty);
			int ar = pst.executeUpdate();
			if(ar>0)
				return true;
			else
				return false;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public static Users GetUserById(int uid) {
		Users u = null;
		try {
			String sql = "SELECT * FROM users WHERE uid=?";
			PreparedStatement pst = getConnection().prepareStatement(sql);
			pst.setInt(1, uid);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				u = new Users();
				u.setUid(rs.getInt(1));
				u.setFname(rs.getString(2));
				u.setEmail(rs.getString(3));
				u.setPwd(rs.getString(4));
				u.setRoles(rs.getString(5));
				u.setStatus(rs.getInt(6));
			}
			return u;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return u;
	}
	public static boolean UpdateUserById(Users u) {
		try {
			String sql = "UPDATE users SET fname=?,email=?,pwd=?,roles=?,status=? WHERE uid=?";
			PreparedStatement pst = getConnection().prepareStatement(sql);
			pst.setString(1, u.getFname());
			pst.setString(2, u.getEmail());
			pst.setString(3, u.getPwd());
			pst.setString(4, u.getRoles());
			pst.setInt(5, u.getStatus());
			pst.setInt(6, u.getUid());
			int ar = pst.executeUpdate();
			if(ar>0)
				return true;
			else
				return false;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public static boolean DeleteProduct(int pid) {
		try {
			String sql = "DELETE FROM product WHERE pid=?";
			PreparedStatement pst = getConnection().prepareStatement(sql);
			pst.setInt(1, pid);
			int ar = pst.executeUpdate();
			if(ar>0)
				return true;
			else
				return false;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public static Products getProductById(int pid) {
		Products p = null;
		try {
			String sql = "SELECT * FROM product WHERE pid=?";
			PreparedStatement pst = getConnection().prepareStatement(sql);
			pst.setInt(1, pid);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				p = new Products();
				p.setPid(rs.getInt(1));
				p.setPname(rs.getString(2));
				p.setQty(rs.getInt(3));
				p.setPrice(rs.getInt(4));
			}
			return p;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return p;
	}
	public static ArrayList<Users> ViewUsers(String roles){
		ArrayList<Users> al = new ArrayList<>();
		Users u = null;
		try {
			String sql = "SELECT * FROM users WHERE roles=?";
			PreparedStatement pst = getConnection().prepareStatement(sql);
			pst.setString(1, roles);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				u = new Users();
				u.setUid(rs.getInt(1));
				u.setFname(rs.getString(2));
				u.setEmail(rs.getString(3));
				u.setPwd(rs.getString(4));
				u.setRoles(rs.getString(5));
				u.setStatus(rs.getInt(6));
				al.add(u);
			}
			return al;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return al;
	}
	public static boolean AddUser(Users u) {
		try {
			String sql = "INSERT INTO users(fname,email,pwd,roles) VALUE(?,?,?,?);";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1,u.getFname());
			ps.setString(2,u.getEmail());
			ps.setString(3,u.getPwd());
			ps.setString(4,u.getRoles());
			int ar = ps.executeUpdate();
			if(ar>0)
				return true;
			else
				return false;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public static Users CheckUser(String email,String pwd,String roles) {
		try {
			Users u = null;
			String sql = "SELECT * FROM users WHERE email=? AND pwd=? AND roles=?";
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, pwd);
			ps.setString(3, roles);
			int flag = 0;
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				flag = 1;
				u = new Users();
				u.setUid(rs.getInt(1));
				u.setFname(rs.getString(2));
				u.setEmail(rs.getString(3));
				u.setPwd(rs.getString(4));
				u.setRoles(rs.getString(5));
				u.setStatus(rs.getInt(6));
			}
			if(flag==1)
				return u;
			else
				return null;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static boolean AddProduct(Products p) {
		try {
			String str = "INSERT INTO product(pname,qty,price) VALUE(?,?,?)";
			PreparedStatement pst = getConnection().prepareStatement(str);
			pst.setString(1,p.getPname());
			pst.setInt(2,p.getQty());
			pst.setInt(3,p.getPrice());
			int ar = pst.executeUpdate();
			if(ar>0)
				return true;
			else
				return false;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public static ArrayList<Products> ViewProducts() {
		ArrayList<Products> al = new ArrayList<>();
		Products p = null;
		try {
			String sql = "SELECT * FROM product";
			PreparedStatement pst = getConnection().prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				p = new Products();
				p.setPid(rs.getInt(1));
				p.setPname(rs.getString(2));
				p.setQty(rs.getInt(3));
				p.setPrice(rs.getInt(4));
				al.add(p);
			}
			return al;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return al;
	}
}