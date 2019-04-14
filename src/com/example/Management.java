package com.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.Statement;

public class Management {
	
	static Connection con= null;
	int count=0;

	
	public static void main(String[] args){
		try {

			//System.out.println(con + "--------------");
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mgnt?useSSL=false", "root",
					"Shreya1994");
			new Management().displayMenu();     
		} catch (Exception e2) {
			e2.printStackTrace();

		}
	}		

	public  void displayMenu() {

		Scanner scanner= new Scanner(System.in);
		System.out.println("\n Inventory Management");
		System.out.println("=======================");
		System.out.println("1-Create");
		System.out.println("2-UpdateBuy");
		System.out.println("3-UpdateSell");
		System.out.println("4-Delete");
		System.out.println("5-Report");
		System.out.println("6-Exit");
		System.out.println("\n Please Select an option");
		int input = scanner.nextInt();

		try{
			switch(input) {
			case 1:
				insertion();
				displayMenu();
				break;
			case 2:
				updateBuy();
				displayMenu();
				break;
			case 3:
				updateSell();
				displayMenu();
				break;
			case 4:
				delete();
				displayMenu();
				break;
			case 5:
				report();
				displayMenu();
				break;
			case 6:
				exit();
				break;
			default:
				System.out.println("Invalid Selection");
				break;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void exit() {
		// TODO Auto-generated method stub
		
	}

	public void insertion() {
		
		try {
			
			Scanner scanner= new Scanner(System.in);
			System.out.println("Enter intname");
			String intname = scanner.nextLine();
			System.out.println("Enter costprice");
			String costprice = scanner.nextLine();
			System.out.println("Enter sellprice");
			String sellprice = scanner.nextLine();
			String exp = "insert into list(intname,costprice,sellprice) values(?,?,?)";
			PreparedStatement ps = con.prepareStatement(exp);
			ps.setString(1, intname);
			ps.setString(2, costprice);
			ps.setString(3, sellprice);
			ps.executeUpdate();
			System.out.println("Record inserted");
		
	}catch (SQLException e2) {
		e2.printStackTrace();
		
	  }
	
	}	
		//if (i > 0) {
			//System.out.println("Record Inserted");
			//	return true;
			//}
			//	else 
			//	return false;
	//	}
		//catch (Exception e2) {
			//return false;
		//}
	//}

	public boolean updateBuy() {
	    count++;
	    try {
	    	Scanner scanner= new Scanner(System.in);
			System.out.println("Enter intname");
			String intname = scanner.nextLine();
		String exp= "UPDATE list SET quantity= ? WHERE intname=?";
		PreparedStatement ps = con.prepareStatement(exp);
		
		ps.setString(2, intname);
		ps.setInt(1, count );
		ps.executeUpdate();
		System.out.println("Updated");
	    }catch (SQLException e2) {
			e2.printStackTrace();
			return true;
		}
		return false;
	
	}
	public boolean updateSell() {
	    count--;
	    try {
	    	Scanner scanner= new Scanner(System.in);
			System.out.println("Enter intname");
			String intname = scanner.nextLine();
		String exp= "UPDATE list SET quantity= ? WHERE intname=?";
		PreparedStatement ps = con.prepareStatement(exp);
		
		ps.setString(2, intname);
		ps.setInt(1, count );
		ps.executeUpdate();
		System.out.println("Updated");
	    }catch (SQLException e2) {
			e2.printStackTrace();
			return true;
		}
		return false;
	
	}
	
	public boolean delete() {
		try {
			Scanner scanner= new Scanner(System.in);
			System.out.println("Enter intname");
			String intname = scanner.nextLine();
			String exp = "DELETE FROM list WHERE intname= ?";
			PreparedStatement ps = con.prepareStatement(exp);
			ps.setString(1, intname);
			ps.executeUpdate();
			System.out.println("Record deleted");
			

		} catch (SQLException e2) {
			e2.printStackTrace();
			return true;
		}
		return true;
	}
	public String report() {
		System.out.println("INVENTORY REPORT");
		System.out.println("==============================");
		System.out.printf( "intname"+"\t"+ "costprice"+"\t"+ "sellprice"+"\t"+ "quantityavailable"+"\t");
		System.out.println("\n==============================");
		Statement stmt = null;
		String exp = "select intname, costprice, sellprice, quantity from list";
		try {
			 stmt= (Statement) con.createStatement();
			ResultSet rs = stmt.executeQuery(exp);

			
			while (rs.next()) {
				String intname= rs.getString("intname")+"\t";
				String costprice=rs.getString("costprice")+"\t" ;
				String sellprice= rs.getString("sellprice")+"\t";
				String quantity=rs.getString("quantity")+"\t";
				System.out.println(intname+""+costprice+""+sellprice+""+quantity);
			}
			


		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
		return exp;
	}
}

