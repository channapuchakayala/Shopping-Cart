package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.CartItem;
import model.Item;
import service.ItemDao;

public class CartController {
	
	public String addItemToCart(HttpServletRequest request) {
		
		if(!checkSession(request))
			return "Login";
		
		
		int itemCode = Integer.parseInt(request.getParameter("itemCode"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		System.out.println("itemCode = "+itemCode);
		System.out.println("itemQuantity = "+quantity);
		
		HttpSession session = request.getSession(false);
		
		HashMap<Integer,Integer> cartMap = (HashMap<Integer, Integer>) session.getAttribute("cart");
		Integer oldItemQtantity = cartMap.get(itemCode);
		if(oldItemQtantity == null ){
			oldItemQtantity = 0;
		} 
		
		cartMap.put(itemCode, oldItemQtantity + quantity);
		
		try {
			Item item = new ItemDao().getItem(itemCode);
			request.setAttribute("itemAdded", item);
			request.setAttribute("quantity", quantity);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return "cartUpdate";
	}
	
	
	public String removeFromCart(HttpServletRequest request) {
		if(!checkSession(request))
			return "Login";
		
		HttpSession session = request.getSession(false);
		HashMap<Integer,Integer> cartMap = (HashMap<Integer, Integer>) session.getAttribute("cart");
		
		int itemCode = Integer.parseInt(request.getParameter("itemCode"));
		cartMap.remove(itemCode);
		
		
		Item item = null;
		try {
			item = new ItemDao().getItem(itemCode);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("cartMessage", "Item "+item.getItemName()+" removed from the cart");
		//ArrayList<CartItem> cartItemList = getCartItemList(cartMap);
		//request.setAttribute("cartItemList", cartItemList);
		//return "showCart";
		
		return viewCart(request);
	}
	
	
	public String viewCart(HttpServletRequest request) {
		if(!checkSession(request))
			return "Login";
		
		HttpSession session = request.getSession(false);
		HashMap<Integer,Integer> cartMap = (HashMap<Integer, Integer>) session.getAttribute("cart");
		ArrayList<CartItem> cartItemList = getCartItemList(cartMap);
		request.setAttribute("cartItemList", cartItemList);
		
		return "showCart";
	}
	
	
	public String checkOut(HttpServletRequest request) {
		if(!checkSession(request))
			return "Login";
		
		HttpSession session = request.getSession(false);
		HashMap<Integer,Integer> cartMap = (HashMap<Integer, Integer>) session.getAttribute("cart");
		ArrayList<CartItem> cartItemList = getCartItemList(cartMap);
		request.setAttribute("cartItemList", cartItemList);
		
		session.removeAttribute("cart");
		session.invalidate();
		
		return "checkOut";
	}
	
	private ArrayList<CartItem> getCartItemList(HashMap<Integer,Integer> cartMap) {
		
		ArrayList<CartItem>  cartItemList = new ArrayList<CartItem>();
		
		Set<Integer> itemCodeSet = cartMap.keySet();
		
		try {
			for(Integer itemCode : itemCodeSet) {
				Item item = new ItemDao().getItem(itemCode);
				CartItem cartItem = new CartItem();
				cartItem.setItem(item);
				cartItem.setQuantity(cartMap.get(itemCode));
				
				cartItemList.add(cartItem);
			}
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return cartItemList;
	}
	
	private boolean checkSession(HttpServletRequest request) {
		
		HttpSession session = request.getSession(false);
		if(session == null) {
			request.setAttribute("errorMessage", "Please login first");
			return false;
			
		}
		
		// incase of session invalidate after check out
		HashMap<Integer,Integer> cartMap = (HashMap<Integer, Integer>) session.getAttribute("cart");
		if(cartMap == null)  {
			request.setAttribute("errorMessage", "Your session expired, Please login again");
			return false;
		}
		
		return true;
	}

}
