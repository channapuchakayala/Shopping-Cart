

<html>
	<head>
	<meta http-equiv="refresh" content="text/html; charset="ISO-8859-1">
		<%@ taglib prefix="c"  uri ="http://java.sun.com/jsp/jstl/core" %>
	</head>

	<body>

<table border ="0">
<c:forEach items="${itemList}" var="item">
	<tr>
		<td> <a href='getItem?itemCode=${item.itemCode}' target='DetailsFrame'>
		<img src='../images/${item.imageName}.jpg' height = "100" width="100">
		</a>
		</td>
		<td> <a href='getItem?itemCode=${item.itemCode}' target='DetailsFrame'>
		${item.itemName}
		</a>
		</td>
	</tr>
</c:forEach>
</table>

<br>
<br>
<a href='showCart' target='DetailsFrame'> View Your Cart </a>
</body>
</html>