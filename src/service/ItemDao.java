package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Item;

public class ItemDao {
	
	public ArrayList<Item> getItems() throws ClassNotFoundException, SQLException {
		
		ArrayList<Item> itemList = new ArrayList<Item>();
		
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn = DriverManager.getConnection("jdbc:sqlserver://10.131.81.99;user=sa;password=P@ssword01;database=shopping_cart");
			//System.out.println(" after connection");

			Statement sta = conn.createStatement();
			String Sql = "select * from item";

			ResultSet rs = sta.executeQuery(Sql);
			while (rs.next()) {
				Item item = new Item();
				item.setItemCode(rs.getInt("itemcode"));
				item.setItemName(rs.getString("itemname"));
				item.setPrice(rs.getInt("price"));
				item.setImageName(rs.getString("imagename"));	
				itemList.add(item);
			}


			return itemList;
		
	}
	
	
public Item getItem(int itemcode) throws ClassNotFoundException, SQLException {
		
	Item item = null;
		
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn = DriverManager.getConnection("jdbc:sqlserver://10.131.81.99;user=sa;password=P@ssword01;database=shopping_cart");
			//System.out.println(" after connection");

			String sql = "select * from item where itemcode = ? ";
			PreparedStatement sta = conn.prepareStatement(sql);
			sta.setInt(1, itemcode);;

			ResultSet rs = sta.executeQuery();
			if(rs.next()) {
				item = new Item();
				item.setItemCode(rs.getInt("itemcode"));
				item.setItemName(rs.getString("itemname"));
				item.setPrice(rs.getInt("price"));
				item.setImageName(rs.getString("imagename"));
			}


			return item;
		
	}

}
