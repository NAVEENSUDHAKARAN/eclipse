package com.chainsys.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.chainsys.util.ConnectUtil;

public class UserEndManagement {

	public static void userEndManagement() throws ClassNotFoundException, SQLException
	{
		Scanner UserEnd = new Scanner(System.in);
		ValidationChecker check = new ValidationChecker();
		ConnectUtil connect = new ConnectUtil();
		Connection connection = ConnectUtil.getConnection();
		DbMethods db = new DbMethods();
		ArrayList list = new ArrayList();
		
		int totalAmount = 0;
		
		System.out.println("Enter Your Name : ");
		String customerName = UserEnd.next();
		while(!check.str(customerName))
		{
			System.err.println("UserName Should Only Contain Alphabets\nEnter Your Name Again : ");
			customerName = UserEnd.next();
		}
		
		System.out.println("Enter Your PhoneNumber : ");
		String phNumber = UserEnd.next();
		while(!check.phoneNumber(phNumber))
		{
			System.err.println("Phone Number Should have Ten Numbers\nEnter Number Again : ");
			phNumber = UserEnd.next();
		}
		
		System.out.println("1 ---> Enter 1 to Purchase\n2 ---> Enter 2 to Quit");
		int choose = UserEnd.nextInt();
		
		while (choose != 1 && choose != 2) {
			System.err.println("Enter The Valid Number : ");
			choose = UserEnd.nextInt();
		}
		
		boolean stop = true;
		while(stop)
		{
			if(choose == 1)
			{
				System.out.println("\nChoose The Department");
				System.out.println("0 ---> Quit\n1 ---> Mobile\n2 ---> Fruits\n3 ---> Jewellery");
				int opt = UserEnd.nextInt();
				while (opt != 1 && opt != 2 && opt != 3 && opt != 0) {
					System.err.println("Enter The Valid Number : ");
					opt = UserEnd.nextInt();
				}

				if(opt == 0)					
				{
					System.out.println("****************************");
					System.out.println("Customer Name : " + customerName);
					System.out.println("Phone Number : " + phNumber);
					System.out.println("*****Products Purchased*****");
					System.out.println(list.toString());
					System.out.println("Bill Amount is : " + totalAmount);
					System.out.println("****************************");

					break;
				}
				if (opt == 1) {
					System.out.println("\nChoose The Mobile To View Stock");

					DbMethods.readStock(101, 200);
					System.out.println("Choose the Product ID to purchase : ");
					int purchase = UserEnd.nextInt();
					while(purchase <=100 || purchase >=201)
					{
						System.err.println("Invalid ID\nChoose the Product ID to purchase : ");
						purchase = UserEnd.nextInt();
					}
					System.out.println("Choose Number of Products to Purchase : ");
					int purchaseCount = UserEnd.nextInt();
					
					list.add(DbMethods.displayStock(purchase));
					System.out.println(list);
	
					int purchasedStock =  db.retrieveStockUsingId(purchase)- purchaseCount;
					db.updateStock(purchasedStock, purchase);
					
					totalAmount += (DbMethods.amountCalculate(purchase) * purchaseCount);
					
				} else if (opt == 2) {
					System.out.println("\nChoose The Fruits To View Stock");

					DbMethods.readStock(201, 300);
					System.out.println("Choose the Product ID to purchase : ");
					int purchase = UserEnd.nextInt();
					while(purchase <=200 || purchase >=301)
					{
						System.err.println("Invalid ID\nChoose the Product ID to purchase : ");
						purchase = UserEnd.nextInt();
					}
					System.out.println("Choose Number of Products to Purchase : ");
					int purchaseCount = UserEnd.nextInt();
					list.add(DbMethods.displayStock(purchase));
					System.out.println(list);

					int purchasedStock =  db.retrieveStockUsingId(purchase)- purchaseCount;
					db.updateStock(purchasedStock, purchase);
					
					totalAmount += (DbMethods.amountCalculate(purchase) * purchaseCount);
					
				} else if (opt == 3) {
					System.out.println("\nChoose The Jewel To View Stock");

					DbMethods.readStock(301, 400);
					System.out.println("Choose the Product ID to purchase : ");
					int purchase = UserEnd.nextInt();
					while(purchase <=300 ||purchase >=401)
					{
						System.err.println("Invalid ID\nChoose the Product ID to purchase : ");
						purchase = UserEnd.nextInt();
					}
					System.out.println("Choose Number of Products to Purchase : ");
					int purchaseCount = UserEnd.nextInt();
					
					list.add(DbMethods.displayStock(purchase));
					System.out.println(list);
					
					int purchasedStock =  db.retrieveStockUsingId(purchase)- purchaseCount;
					db.updateStock(purchasedStock, purchase);
					
					totalAmount += (DbMethods.amountCalculate(purchase) * purchaseCount);
					
				}
			}
			else if(choose == 2)
			{
				stop = false;
			}
		}
	}
}
