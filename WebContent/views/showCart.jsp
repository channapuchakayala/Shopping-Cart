<html>
	<head>
	<meta http-equiv="refresh" content="text/html; charset="ISO-8859-1">
		<%@ taglib prefix="c"  uri ="http://java.sun.com/jsp/jstl/core" %>
	</head>

<body>
    <h4> ${cartMessage}<h4>
	<h3>Items in your cart</h3>
	<font size='4'>
	<table border ="1">
	  <thead>
	  	<tr>
	  		<th>Item</th>
	  		<th>Quantity</th>
	  		<th>Price</th>
	  		<th>Amount</th>
	  		<th></th>
		</tr>
	  </thead>
	  
	  <c:set var='totalAmount' value='0' />
	  
	  <c:forEach items="${cartItemList}" var="cartItem">
	  	<c:set var='amount' value='${cartItem.item.price * cartItem.quantity}' />
	  	<tr>
	  		<td><img src='../images/${cartItem.item.imageName}.jpg' height = "100" width="100"><br>${cartItem.item.itemName}</td>
	  		<td align='right'>${cartItem.quantity}</td>
	  		<td align='right'>${cartItem.item.price}</td>
	  		<td align='right'>${amount}</td>
	  		<td><a href='removeFromCart?itemCode=${cartItem.item.itemCode}'>Remove</a></td>
	  	</tr>
	  	<c:set var='totalAmount' value='${totalAmount + amount}' />
	  </c:forEach>
	  <tr>
	   <td colspan ='3'>Total Cart Amount</td>
	   <td align='right'>${totalAmount}</td>
	   <td></td>
	  </tr>
	 
	 </table>
	 <br><br>
	 <a href='checkOut' target='_parent'>Check Out</a>

</body>
<html>