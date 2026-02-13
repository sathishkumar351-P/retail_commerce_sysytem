package com.wipro.retail.dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wipro.retail.bean.RetailBean;
import com.wipro.retail.util.DBUtil;

public class RetailDAO {

    // ================= CREATE RECORD =================
    public String createRecord(RetailBean bean) {
        String status = "FAIL";

        try {
            Connection con = DBUtil.getDBConnection();

            // ✅ Generate RECORDID (MANDATORY)
            String recordId = generateRecordID1(
                    bean.getCustomerName(),
                    bean.getPurchaseDate()
            );
            bean.setRecordId(recordId);

            String sql =
                "INSERT INTO RETAIL_TBL " +
                "(RECORDID, CUSTOMERNAME, PRODUCTNAME, PURCHASE_DATE, QUANTITY, PRICE, REMARKS) " +
                "VALUES (?,?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, bean.getRecordId());
            ps.setString(2, bean.getCustomerName());
            ps.setString(3, bean.getProductName());
            ps.setDate(4, new java.sql.Date(bean.getPurchaseDate().getTime()));
            ps.setInt(5, bean.getQuantity());
            ps.setDouble(6, bean.getPrice());
            ps.setString(7, bean.getRemarks());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                status = bean.getRecordId();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    // ================= CHECK IF RECORD EXISTS =================
    public boolean recordExists(String customerName, Date date) {
        boolean flag = false;

        try {
            Connection con = DBUtil.getDBConnection();
            String sql =
                "SELECT 1 FROM RETAIL_TBL WHERE CUSTOMERNAME=? AND PURCHASE_DATE=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, customerName);
            ps.setDate(2, new java.sql.Date(date.getTime()));

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                flag = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    // ================= GENERATE RECORD ID =================
    public String generateRecordID1(String customerName, Date date) {
        String id = "";

        try {
            Connection con = DBUtil.getDBConnection();
            Statement st = con.createStatement();
            ResultSet rs =
                st.executeQuery("SELECT RETAIL_SEQ.NEXTVAL FROM DUAL");

            int seq = 0;
            if (rs.next()) {
                seq = rs.getInt(1);
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String datePart = sdf.format(date);

            String namePart =
                customerName.length() >= 2
                    ? customerName.substring(0, 2).toUpperCase()
                    : customerName.toUpperCase();

            id = datePart + namePart + seq;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    // ================= FETCH SINGLE RECORD =================
    public RetailBean fetchRecord(String customerName, Date date) {
        RetailBean bean = null;

        try {
            Connection con = DBUtil.getDBConnection();
            String sql =
                "SELECT * FROM RETAIL_TBL WHERE CUSTOMERNAME=? AND PURCHASE_DATE=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, customerName);
            ps.setDate(2, new java.sql.Date(date.getTime()));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                bean = new RetailBean();
                bean.setRecordId(rs.getString(1));
                bean.setCustomerName(rs.getString(2));
                bean.setProductName(rs.getString(3));
                bean.setPurchaseDate(rs.getDate(4));
                bean.setQuantity(rs.getInt(5));
                bean.setPrice(rs.getDouble(6));
                bean.setRemarks(rs.getString(7));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    // ================= FETCH ALL RECORDS =================
    public List<RetailBean> fetchAllRecords() {
        List<RetailBean> list = new ArrayList<>();

        try {
            Connection con = DBUtil.getDBConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM RETAIL_TBL");

            while (rs.next()) {
                RetailBean bean = new RetailBean();
                bean.setRecordId(rs.getString(1));
                bean.setCustomerName(rs.getString(2));
                bean.setProductName(rs.getString(3));
                bean.setPurchaseDate(rs.getDate(4));
                bean.setQuantity(rs.getInt(5));
                bean.setPrice(rs.getDouble(6));
                bean.setRemarks(rs.getString(7));
                list.add(bean);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
