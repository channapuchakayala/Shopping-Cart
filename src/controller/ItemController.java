package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import model.Item;
import service.ItemDao;

public class ItemController {
	
	public String getItems(HttpServletRequest request) {
		
		ItemDao dao = new ItemDao();
		try {
			ArrayList<Item> list = dao.getItems();
			request.setAttribute("itemList", list);
			return "showList";
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			request.setAttribute("fetalMessage", e.getMessage());
			return "somethingWrong";
		}
		
	}
	
public String getItem(HttpServletRequest request) {
		
		ItemDao dao = new ItemDao();
		try {
			String itemCodeStr = request.getParameter("itemCode");
			System.out.println("itemCodeStr  == "+itemCodeStr);
			Item item = dao.getItem(Integer.parseInt(itemCodeStr));
			
			request.setAttribute("item", item);
			return "itemDetails";
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			request.setAttribute("fetalMessage", e.getMessage());
			return "somethingWrong";
		}
		
	}

}
