/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import DatabaseConnection.DatabaseConnection;
import Models.Cart;
import Models.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ACER
 */
public class CartDAO {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public CartDAO() throws Exception {
        conn = DatabaseConnection.getConnection();
    }

    public int AddNewCart(Cart cart) {
        String sql = "insert into Cart values(?,?,?,?,?)";
        int result = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cart.getProductID());
            ps.setInt(2, cart.getAccountID());
            ps.setString(3, cart.getP_Name());
            ps.setInt(4, cart.getP_Amount());
            ps.setInt(5, cart.getP_Price());
            result = ps.executeUpdate();
        } catch (SQLException ex) {

        }
        return result;
    }

    public int GetCartProductAmountByProductIDAndAccountID(int P_ID, int A_ID) {
        int Amount = 0;
        String sql = "select P_Amount from Cart\n"
                + "where Product_ID = ? and Account_Id = ?; ";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, P_ID);
            ps.setInt(2, A_ID);
            rs = ps.executeQuery();
            if (rs.next()) {
                Amount = rs.getInt("P_Amount");
            }
        } catch (Exception e) {
        }
        return Amount;
    }

    public int UpdateCartAmountAndPrice(int P_Amount, int P_Price, int P_ID, int A_ID) {
        int result = 0;
        String sql = "update Cart\n"
                + "SET P_Amount=?, P_Price=?\n"
                + "where Product_ID = ? and Account_Id = ?; ";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, P_Amount);
            ps.setInt(2, P_Price);
            ps.setInt(3, P_ID);
            ps.setInt(4, A_ID);
            result = ps.executeUpdate();
        } catch (Exception e) {
        }
        return result;
    }

    public ResultSet ShowCartByID(int A_ID) {
        String sql = "select * from Cart\n"
                + "where Account_ID = ?;";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, A_ID);
            rs = ps.executeQuery();
            return rs;
        } catch (SQLException e) {
        }
        return null;
    }

    public int GetTotalPriceBasedOnAccountID(int A_ID) {
        int TotalPrice = 0;
        String sql = "select sum(P_Price) as TotalPrice from Cart\n"
                + "where Account_ID = ?;";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, A_ID);
            rs = ps.executeQuery();
            if (rs.next()) {
                TotalPrice = rs.getInt("TotalPrice");
            }
        } catch (SQLException e) {
        }
        return TotalPrice;
    }

    public int DeleteProductFromCart(int P_ID, int A_ID) {
        int ketqua = 0;
        String sql = "DELETE FROM Cart \n"
                + "WHERE Product_ID = ? and Account_ID = ?;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, P_ID);
            ps.setInt(2, A_ID);
            ketqua = ps.executeUpdate();
        } catch (SQLException ex) {
        }
        return ketqua;
    }

    public int GetNumberOfCartBasedOnID(int A_ID) {
        int Counter = 0;
        String sql = "select count(Account_ID) as Counter from Cart\n"
                + "where Account_ID = ?;";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, A_ID);
            rs = ps.executeQuery();
            if (rs.next()) {
                Counter = rs.getInt("Counter");
            }
        } catch (SQLException e) {
        }
        return Counter;
    }

    public int DeleteFromCartWithAccountID(int A_ID) {
        int ketqua = 0;
        String sql = "Delete from Cart where Account_ID = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, A_ID);
            rs = ps.executeQuery();
            ketqua = ps.executeUpdate();
        } catch (SQLException e) {
        }
        return ketqua;
    }

}
