package com.wipro.retail.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.wipro.retail.bean.RetailBean;
import com.wipro.retail.service.Administrator;


@SuppressWarnings("serial")
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {

    Administrator admin = new Administrator();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String operation = request.getParameter("operation");

        if (operation.equals("newRecord")) {

            String status = addRecord(request);
            if (status.equals("FAIL") || status.equals("INVALID INPUT")
                    || status.equals("INVALID CUSTOMER NAME")
                    || status.equals("INVALID PURCHASE DETAILS")
                    || status.equals("ALREADY EXISTS")) {

                response.sendRedirect("error.html");
            } else {
                response.sendRedirect("success.html");
            }
        }

        else if (operation.equals("viewRecord")) {

            RetailBean bean = viewRecord(request);

            if (bean == null) {
                request.setAttribute("message", "No matching records exists! Please try again!");
            } else {
                request.setAttribute("bean", bean);
            }

            RequestDispatcher rd = request.getRequestDispatcher("displayRetailTransaction.jsp");
            rd.forward(request, response);
        }

        else if (operation.equals("viewAllRecords")) {

            List<RetailBean> list = admin.viewAllRecords();

            if (list.isEmpty()) {
                request.setAttribute("message", "No records available!");
            } else {
                request.setAttribute("list", list);
            }

            RequestDispatcher rd = request.getRequestDispatcher("displayAllRetailTransactions.jsp");
            rd.forward(request, response);
        }
    }

    public String addRecord(HttpServletRequest request) {

        try {
            RetailBean bean = new RetailBean();

            bean.setCustomerName(request.getParameter("customerName"));
            bean.setProductName(request.getParameter("productName"));

            String dateStr = request.getParameter("purchaseDate");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(dateStr);

            bean.setPurchaseDate(date);
            bean.setQuantity(Integer.parseInt(request.getParameter("quantity")));
            bean.setPrice(Double.parseDouble(request.getParameter("price")));
            bean.setRemarks(request.getParameter("remarks"));

            return admin.addRecord(bean);

        } catch (Exception e) {
            e.printStackTrace();
            return "FAIL";
        }
    }

    public RetailBean viewRecord(HttpServletRequest request) {

        try {
            String name = request.getParameter("customerName");
            String dateStr = request.getParameter("purchaseDate");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(dateStr);

            return admin.viewRecord(name, date);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
