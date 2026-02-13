<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ page import="com.wipro.retail.bean.RetailBean" %>

<%
RetailBean bean = (RetailBean) request.getAttribute("bean");
String message = (String) request.getAttribute("message");

if (bean != null) {
%>

<h3>Transaction Details</h3>

Record ID: <%= bean.getRecordId() %><br>
Customer Name: <%= bean.getCustomerName() %><br>
Product Name: <%= bean.getProductName() %><br>
Purchase Date: <%= bean.getPurchaseDate() %><br>
Quantity: <%= bean.getQuantity() %><br>
Price: <%= bean.getPrice() %><br>
Remarks: <%= bean.getRemarks() %><br>

<%
} else {
%>

<h3><%= message %></h3>

<%
}
%>

</body>
</html>