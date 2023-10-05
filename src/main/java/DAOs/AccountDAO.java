/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import DatabaseConnection.DatabaseConnection;
import EncodeMD5.MD5;
import Models.Account;
import Models.AccountLogin;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ACER
 */
public class AccountDAO {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    /**
     *
     * @throws Exception
     */
    public AccountDAO() throws Exception {
        conn = DatabaseConnection.getConnection();
    }

    /**
     * Get All form Account where Account_ID = ?
     *
     * @param ID
     * @return
     */
    public ResultSet GetAll(int ID) {
        try {
            ps = conn.prepareStatement("select * from Account where Account_ID=?");
            ps.setInt(1, ID);
            rs = ps.executeQuery();
            return rs;
        } catch (SQLException e) {
        }
        return null;
    }

    /**
     * Get Account where that Account is Admin
     *
     * @return
     */
    public ResultSet GetAdmin() {
        String sql = "select Account_ID, Fullname, Email from Account where IsAdmin = 1;";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            return rs;
        } catch (SQLException e) {
        }
        return null;
    }

    /**
     * Get Account where that Account is not Admin
     *
     * @return
     */
    public ResultSet GetNotAdmin() {
        String sql = "select Account_ID, Fullname, Email from Account where IsAdmin = 0;";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            return rs;
        } catch (SQLException e) {
        }
        return null;
    }

    /**
     * Get Partial Info from All Account
     *
     * @return
     */
    public ResultSet GetInfo() {
        String sql = "select Account_ID, Fullname, Email from Account";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            return rs;
        } catch (SQLException e) {
        }
        return null;
    }

    /**
     *
     * @return
     */
    public int GetCountAccount() {
        int count = 0;
        String sql = "select count(Account_ID) as [counter] from Account\n"
                + "where IsAdmin = 0;";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt("counter");
            }
        } catch (SQLException e) {
        }
        return count;
    }

    /**
     *
     * @param ID
     * @return
     */
    public int DeleteAccount(int ID) {
        int ketqua = 0;
        String sql = "delete from Account where Account_ID=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ID);
            ketqua = ps.executeUpdate();
        } catch (SQLException ex) {
        }
        return ketqua;
    }

    /**
     * Get ID of Account from it fullname
     *
     * @param Fullname
     * @return
     */
    public int GetIDFromFullname(String Fullname) {
        int ID = 0;
        String sql = "select Account_ID from Account\n"
                + "where Fullname = ?;";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, Fullname);
            rs = ps.executeQuery();
            if (rs.next()) {
                ID = rs.getInt("Account_ID");
            }
        } catch (SQLException e) {
        }
        return ID;
    }

    /**
     * Make account an admin
     *
     * @param ID
     * @return
     */
    public int AddAdmin(int ID) {
        String sql = "Update Account set IsAdmin=1 where Account_ID = ?";
        int result = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ID);
            result = ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /**
     * Remove account from admin
     *
     * @param ID
     * @return
     */
    public int RemoveAdmin(int ID) {
        String sql = "Update Account set IsAdmin=0 where Account_ID = ?";
        int result = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ID);
            result = ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /**
     * Login check if the username and password is available and if it's
     * available in the database
     *
     * @param acc
     * @return
     * @throws SQLException
     */
    public boolean Login(Account acc) throws SQLException {
        String sql = "SELECT * FROM Account WHERE Username=? AND Password=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, acc.getUsername());
            ps.setString(2, MD5.encode(acc.getPassword()));
            rs = ps.executeQuery();
        } catch (Exception ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs.next();
    }

    /**
     *
     * @param Account_ID
     * @param Fullname
     * @param Username
     * @param Password
     * @param Mobile_Number
     * @param Email
     * @param Address
     * @param IsAdmin
     */
    /**
     *
     * @param acc
     * @return
     */
    /**
     *
     * @param username
     * @return
     */
    public String GetFullName(String username) {
        String fullname = null;

        try {
            ps = conn.prepareStatement("select Fullname from Account where Username=?");
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                fullname = rs.getString("Fullname");
            }
        } catch (SQLException ex) {

        }
        return fullname;

    }

    /**
     *
     * @param username
     * @return
     */
    public boolean IsAdmin(String username) {
        String sql = "SELECT IsAdmin FROM Account WHERE Username = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int isAdmin = rs.getInt("IsAdmin");
                return (isAdmin == 1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     *
     * @return
     */
    public String GetMaxAccountID() {
        String maxID = "";

        try {
            ps = conn.prepareStatement("SELECT MAX(Account_ID) AS MaxID FROM Account");
            rs = ps.executeQuery();
            if (rs.next()) {
                maxID = rs.getString("MaxID");
            }
        } catch (SQLException ex) {

        }
        return maxID;
    }

    /**
     *
     * @param Account_ID
     * @param Fullname
     * @param Username
     * @param Password
     * @param Mobile_Number
     * @param Email
     * @param Address
     * @param IsAdmin
     * @param Gender
     * @param Birthday
     */
    public void signup(
            String Account_ID,
            String Fullname,
            String Username,
            String Password,
            String Mobile_Number,
            String Email,
            String Address,
            String IsAdmin,
            String Gender,
            String Birthday) {
        try {
            ps = conn.prepareStatement("INSERT INTO Account VALUES (?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, Account_ID);
            ps.setString(2, Fullname);
            ps.setString(3, Username);
            ps.setString(4, EncodeMD5.MD5.encode(Password));
            ps.setString(5, Mobile_Number);
            ps.setString(6, Email);
            ps.setString(7, Address);
            ps.setString(8, IsAdmin);
            ps.setString(9, Gender);
            ps.setString(10, Birthday);
            ps.executeUpdate();
        } catch (Exception e) {

            System.out.println(e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
                throw new RuntimeException(e);

            }
        }
    }

    /**
     *
     * @param Email
     * @return
     */
    public AccountLogin checkEmail(String Email) {//kiet

        String query = "select * from Account\n"
                + "where Email = ?\n";
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, Email);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new AccountLogin(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10)
                );
            }
        } catch (Exception e) {
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    /**
     *
     * @param newPassword
     * @param email
     * @return
     */
    public AccountLogin forgetpass(String newPassword, String email) {//kiet

        String query = "UPDATE Account\n"
                + "SET Password = ? \n"
                + "where Email  = ?";
        try {
            ps = conn.prepareStatement(query); // nem cau lenh query sang sql
//            rs = ps.executeQuery();// chay cau lenh query nhan ket qua tra ve
            ps.setString(1, newPassword);
            ps.setString(2, email);
            ps.executeUpdate();
        } catch (Exception e) {

        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
                throw new RuntimeException(e);
            }
        }
        return null;

    }

    public ResultSet GetInfoAccount(int ID) {
        String sql = "select Mobile_Number, [Address] from Account\n"
                + "where Account_ID = ?;";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, ID);
            rs = ps.executeQuery();
            return rs;
        } catch (SQLException ex) {

        }
        return null;
    }

    public int UpdateAccount(String Fullname, String Username, String Mobile_Number, String Email, String Address, String Gender, Date date, int Account_ID) {
        int kq = 0;
        String sql = "UPDATE Account\n"
                + "SET  Fullname = ?, Username = ?, Mobile_Number = ?,\n"
                + "Email = ?, Address = ?, Gender = ?, Birthday = ? WHERE Account_ID = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, Fullname);
            ps.setString(2, Username);
            ps.setString(3, Mobile_Number);
            ps.setString(4, Email);
            ps.setString(5, Address);
            ps.setString(6, Gender);
            ps.setDate(7, date);
            ps.setInt(8, Account_ID);
            kq = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }

}
