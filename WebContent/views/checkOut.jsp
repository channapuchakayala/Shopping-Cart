<html>
	<head>
	<meta http-equiv="refresh" content="text/html; charset="ISO-8859-1">
		<%@ taglib prefix="c"  uri ="http://java.sun.com/jsp/jstl/core" %>
	</head>

<body>
   
	<h4> Thank you for shopping, below items will deliver shortly<h4>
	<font size='4'>
	<table border ="1">
	  <thead>
	  	<tr>
	  		<th>Item</th>
	  		<th>Quantity</th>
		</tr>
	  </thead>
	 
	  <c:forEach items="${cartItemList}" var="cartItem">
	  	<tr>
	  		<td><img src='../images/${cartItem.item.imageName}.jpg' height = "100" width="100"><br>${cartItem.item.itemName}</td>
	  		<td align='right'>${cartItem.quantity}</td>
	  	</tr>
	  </c:forEach>
	 </table>
	 <br><br>	
</body>
<html>