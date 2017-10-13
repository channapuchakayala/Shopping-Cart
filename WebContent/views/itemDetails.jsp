<html>
<h3>Selected Item Details</h3>
<body>


	<img src='../images/${item.imageName}.jpg' height="300" width="300">

	<br>
	<h3> Item Name:  ${item.itemName} </h3>
	<br>
	<h3> Item Code:  ${item.itemCode} </h3>
	<br>
	<h3> Item Price:  ${item.price} </h3>
	<br>
	
	<form action="addToCart" >
	<input type="hidden" name="itemCode" value='${item.itemCode}'>
	Quantity :
	<input type="text" size='3' name="quantity" value="1"> 
	<input type="submit" value="Add to cart">
	
	</form>

</body>
</html>