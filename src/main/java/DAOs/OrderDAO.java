/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Models.Order;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class OrderDAO {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public OrderDAO() throws Exception {
        conn = DatabaseConnection.DatabaseConnection.getConnection();
    }

    public ResultSet GetAll() {
        try {
            ps = conn.prepareStatement("select * from [Order]");
            rs = ps.executeQuery();
            return rs;
        } catch (SQLException e) {
        }
        return null;
    }

    public int GetIncome() {
        int count = 0;
        String sql = "select sum(Total_Price) as Income from [Order]\n"
                + "where Order_Status_ID = 1;";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt("Income");
            }
        } catch (SQLException e) {
        }
        return count;
    }

    public int GetNewOrder() {
        int count = 0;
        String sql = "select count(Order_ID) as NewOrder from [Order]\n"
                + "where Order_Status_ID = 3 or Order_Status_ID =2;";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt("NewOrder");
            }
        } catch (SQLException e) {
        }
        return count;
    }

    public ResultSet GetOrderInfo(int Status) {
        String sql = "select Order_ID,Total_Price,Phone_Number,Delivery_Instruction,Delivery_Address,Delivery_Time, \n"
                + "Account.Fullname as fn, Account.Email as email, Payment_Method.Payment_Method as Pay_Met, Order_Status.Order_Status as [Status] from [Order]\n"
                + "inner join Account on Account.Account_ID = [Order].Account_ID\n"
                + "inner join Payment_Method on Payment_Method.Payment_Method_ID = [Order].Payment_Method_ID\n"
                + "inner join Order_Status on Order_Status.Order_Status_ID = [Order].Order_Status_ID where [Order].Order_Status_ID = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, Status);
            rs = ps.executeQuery();
            return rs;
        } catch (SQLException e) {
        }
        return null;
    }

    public ResultSet GetSpecificOrderInfo(int ID) {
        String sql = "select Order_ID,Total_Price,Phone_Number,Delivery_Instruction,Delivery_Address,Delivery_Time, \n"
                + "Account.Fullname as fn, Account.Email as email, Payment_Method.Payment_Method as Pay_Met, Order_Status.Order_Status as [Status] from [Order]\n"
                + "inner join Account on Account.Account_ID = [Order].Account_ID\n"
                + "inner join Payment_Method on Payment_Method.Payment_Method_ID = [Order].Payment_Method_ID\n"
                + "inner join Order_Status on Order_Status.Order_Status_ID = [Order].Order_Status_ID where [Order].Order_ID = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, ID);
            rs = ps.executeQuery();
            return rs;
        } catch (SQLException e) {
        }
        return null;
    }

    public int GetLastOrderID() {
        int maxID = 0;
        String sql = "SELECT max(Order_ID) as MaxID \n"
                + "FROM [Order];";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                maxID = rs.getInt("MaxID");
            }
            maxID += 1;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return maxID;
    }

    public ResultSet GetPaymentMethod() {
        String sql = "select * from Payment_Method";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            return rs;

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public int InsertOrder(Order ord) {
        String sql = "insert into [Order] (Order_ID,Account_ID,Total_Price,Phone_Number,Delivery_Instruction,Delivery_Address, Delivery_Time,Order_Status_ID,Payment_Method_ID)\n"
                + "values (?,?,?,?,?,?,?,?,?)";
        int result = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ord.getOrder_ID());
            ps.setInt(2, ord.getAccount_ID());
            ps.setInt(3, ord.getTotal_Price());
            ps.setString(4, ord.getPhone_number());
            ps.setString(5, ord.getDelivery_Instruction());
            ps.setString(6, ord.getDelivery_Address());
            ps.setDate(7, ord.getDelivery_Time());
            ps.setInt(8, ord.getOrder_Status_ID());
            ps.setInt(9, ord.getPayment_Method_ID());
            result = ps.executeUpdate();
        } catch (SQLException ex) {

        }
        return result;
    }

    public int CompleteOrder(int O_ID) {
        String sql = "Update [Order]\n"
                + "set Order_Status_ID = 1\n"
                + "where Order_ID = ?";        
        int result = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, O_ID);
            result = ps.executeUpdate();
        } catch (Exception ex) {
            
        }
        return result;
    }

}
