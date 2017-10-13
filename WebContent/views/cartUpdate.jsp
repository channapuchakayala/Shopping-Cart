<html>
<h3>Selected Item added to Cart</h3>
<body>


	<img src='../images/${itemAdded.imageName}.jpg' height="300" width="300">

	<br>
	<h3> Item Name:  ${itemAdded.itemName} </h3>
	<br>
	<h3> Item Code:  ${itemAdded.itemCode} </h3>
	<br>
	<h3> Total Price:  ${itemAdded.price * quantity} </h3>

	<br>
	<a href='showCart' target='DetailsFrame'> View Your Cart </a>
</body>
</html>