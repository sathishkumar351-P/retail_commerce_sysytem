package com.wipro.retail.service;

import java.util.Date;
import java.util.List;

import com.wipro.retail.bean.RetailBean;
import com.wipro.retail.dao.RetailDAO;
import com.wipro.retail.util.InvalidInputException;

public class Administrator {

    private RetailDAO dao = new RetailDAO();


    public String addRecord(RetailBean bean) {

        try {
           
            if (bean == null ||
                bean.getCustomerName() == null ||
                bean.getPurchaseDate() == null) {
                throw new InvalidInputException();
            }

            if (bean.getCustomerName().length() < 2) {
                return "INVALID CUSTOMER NAME";
            }

            if (bean.getPrice() < 0 || bean.getQuantity() < 1) {
                return "INVALID PURCHASE DETAILS";
            }

            if (dao.recordExists(bean.getCustomerName(), bean.getPurchaseDate())) {
                return "ALREADY EXISTS";
            }

            
            return dao.createRecord(bean);

        } catch (InvalidInputException e) {
            return "INVALID INPUT";
        }
    }

    
    public RetailBean viewRecord(String customerName, Date date) {
        return dao.fetchRecord(customerName, date);
    }

 
    public List<RetailBean> viewAllRecords() {
        return dao.fetchAllRecords();
    }
}

