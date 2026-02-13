<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ page import="java.util.*, com.wipro.retail.bean.RetailBean" %>

<%
List<RetailBean> list = (List<RetailBean>) request.getAttribute("list");
String message = (String) request.getAttribute("message");

if (list != null) {
%>

<h3>All Retail Transactions</h3>

<table border="1">
<tr>
<th>ID</th>
<th>Customer</th>
<th>Product</th>
<th>Date</th>
<th>Qty</th>
<th>Price</th>
<th>Remarks</th>
</tr>

<%
for (RetailBean bean : list) {
%>
<tr>
<td><%= bean.getRecordId() %></td>
<td><%= bean.getCustomerName() %></td>
<td><%= bean.getProductName() %></td>
<td><%= bean.getPurchaseDate() %></td>
<td><%= bean.getQuantity() %></td>
<td><%= bean.getPrice() %></td>
<td><%= bean.getRemarks() %></td>
</tr>
<%
}
%>

</table>

<%
} else {
%>

<h3><%= message %></h3>

<%
}
%>

</body>
</html>