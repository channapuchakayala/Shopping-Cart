package servelets;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CartController;
import controller.ItemController;
import controller.LoginController;

/**
 * Servlet implementation class MainServlet
 */
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String action = request.getPathInfo().substring(1);
		String result = "";
	
	/*	
		switch(action) {

		case "login":
			result = new LoginController().login(request);
			break;

		case "getList":
			result = new ItemController().getItems(request);
			break;

		case "getItem":
			result = new ItemController().getItem(request);
			break;

		case "addToCart":
			result = new CartController().addItemToCart(request);
			break;

		case "showCart":
			result = new CartController().viewCart(request);
			break;

		case "removeFromCart":
			result = new CartController().removeFromCart(request);
			break;

		case "checkOut":
			result = new CartController().checkOut(request);
			break;
		}
		*/
		
		Properties prop = new Properties();
		InputStream input = getClass().getClassLoader().getResourceAsStream("Application.properties");
		prop.load(input);
		
		String propValue = prop.getProperty(action);
		String propStr[] = propValue.split(",");
		try {
			Class<?> controllerClass = Class.forName(propStr[0]);
			Method method =  controllerClass.getMethod(propStr[1], HttpServletRequest.class);
			result = (String) method.invoke(controllerClass.newInstance(), request);
					
		} catch (ClassNotFoundException |
				NoSuchMethodException | 
				SecurityException |
				IllegalAccessException | 
				IllegalArgumentException | 
				InvocationTargetException | 
				InstantiationException e) {
			e.printStackTrace();
		}
		
		
		String page = "/views/"+result+".jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
