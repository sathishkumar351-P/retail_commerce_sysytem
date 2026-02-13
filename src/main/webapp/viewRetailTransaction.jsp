<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<body>

<h2>Search Transaction</h2>

<form action="MainServlet" method="post">

<input type="hidden" name="operation" value="viewRecord"/>

Customer Name:
<input type="text" name="customerName" required><br><br>

Purchase Date:
<input type="date" name="purchaseDate" required><br><br>

<input type="submit" value="Search">

</form>

</body>
</html>
