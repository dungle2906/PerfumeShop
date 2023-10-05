/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Models.GetProduct;
import Models.Product;
import Models.ProductImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ACER
 */
public class ProductDAO {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public ProductDAO() throws Exception {
        conn = DatabaseConnection.DatabaseConnection.getConnection();
    }

    public ResultSet GetAll() {
        try {
            ps = conn.prepareStatement("select * from Product");
            rs = ps.executeQuery();
            return rs;
        } catch (SQLException e) {
        }
        return null;
    }

    public ResultSet GetAllExact(int ID) {
        try {
            ps = conn.prepareStatement("select Product_ID,P_Name,P_Price,Brand.Brand_Name,P_Size,P_Country,P_Status from Product\n"
                    + "inner join Brand\n"
                    + "on Product.Brand_ID = Brand.Brand_ID\n"
                    + "where Product_ID = ?;");
            ps.setInt(1, ID);
            rs = ps.executeQuery();
            return rs;
        } catch (SQLException e) {
        }
        return null;
    }

    public Product getInfoForUpdating(int ID) {
        Product pro = null;
        try {
            ps = conn.prepareStatement("select * from Product\n"
                    + "where Product_ID = ?;");
            ps.setInt(1, ID);
            rs = ps.executeQuery();
            if (rs.next()) {
                pro = new Product(rs.getInt("Product_ID"), rs.getInt("Catagory_ID"), rs.getString("P_Name"), rs.getString("P_Status"), rs.getString("P_Price"),
                        rs.getInt("Brand_ID"), rs.getString("P_Size"), rs.getString("P_Country"));
            }
        } catch (Exception e) {
        }
        return pro;
    }

    public int UpdateProduct(Product pro) {
        String a, b, c, d, e, f;
        int ID = pro.getProduct_ID();
        b = pro.getP_price();
        int Cat = pro.getCategory_ID();
        int rb = pro.getBrand_ID();
        int price = Integer.parseInt(b);
        a = pro.getP_name();
        c = pro.getP_availability();
        d = pro.getP_size();
        e = pro.getP_Country();
        String sql = "Update Product set Product_ID = ?, Catagory_ID = ?, P_Name = ?, P_Status = ?, P_Price = ? ,Brand_ID = ?, P_Size = ?, P_Country = ? where Product_ID = ?";
        int result = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, pro.getProduct_ID());
            ps.setInt(2, pro.getCategory_ID());
            ps.setString(3, pro.getP_name());
            ps.setString(4, pro.getP_availability());
            ps.setInt(5, price);
            ps.setInt(6, pro.getBrand_ID());
            ps.setString(7, pro.getP_size());
            ps.setString(8, pro.getP_Country());
            ps.setInt(9, pro.getProduct_ID());
            result = ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public ResultSet GetAllBasedOnCatagory(int ID) {
        try {
            ps = conn.prepareStatement("select Product_ID,P_Name,P_Price,Brand.Brand_Name,P_Size,P_Country,P_Status from Product\n"
                    + "inner join Brand\n"
                    + "on Product.Brand_ID = Brand.Brand_ID\n"
                    + "where Catagory_ID = ?;");
            ps.setInt(1, ID);
            rs = ps.executeQuery();
            return rs;
        } catch (SQLException e) {
        }
        return null;
    }

    public int Delete(int ID) {
        int ketqua = 0;
        String sql = "delete from Product where Product_ID=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ID);
            String URL = GetImageURL(ID);
            DeleteImage(URL);
            DeleteImageDB(ID);
            ketqua = ps.executeUpdate();
        } catch (SQLException ex) {
        }
        return ketqua;
    }

    public int DeleteImageDB(int ID) {
        int ketqua = 0;
        String sql = "delete from Product_Image where Product_ID=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ID);
            ketqua = ps.executeUpdate();
        } catch (SQLException ex) {
        }
        return ketqua;
    }

    public int AddNew(Product pro) {
        String sql = "insert into Product values(?,?,?,?,?,?,?,?)";
        int result = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, pro.getProduct_ID());
            ps.setInt(2, pro.getBrand_ID());
            ps.setInt(3, pro.getCategory_ID());
            ps.setString(4, pro.getP_name());
            ps.setString(5, pro.getP_availability());
            ps.setInt(6, Integer.parseInt(pro.getP_price()));
            ps.setString(7, pro.getP_size());
            ps.setString(8, pro.getP_Country());
            result = ps.executeUpdate();
        } catch (SQLException ex) {

        }
        return result;
    }

    public int AddImage(ProductImage PImg) {
        String sql = "insert into Product_Image values(?,?,?)";
        int result = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, PImg.getImage_ID());
            ps.setInt(2, PImg.getProduct_ID());
            ps.setString(3, PImg.getImage_URL());
            result = ps.executeUpdate();
        } catch (SQLException ex) {

        }
        return result;
    }

    public int GetLastImageID() {
        String sql = " Select max(Image_ID) as highest from Product_Image";
        int digit = 0;
        String ID = null;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                ID = rs.getString("highest");
            }
            if (ID == null) {
                ID = "1";
            }
            digit = Integer.parseInt(ID);
            digit = digit + 1;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return digit;
    }

    public String GetImageURL(int ID) {
        String sql = "Select Image_URL from Product_Image where Product_ID = ?";
        String URL = "";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, ID);
            rs = ps.executeQuery();
            if (rs.next()) {
                URL = rs.getString("Image_URL");
            }
        } catch (SQLException ex) {

        }
        return URL;
    }

    public void DeleteImage(String imgName) {
        String HardPath = "C:\\Users\\User\\OneDrive\\FPTU Study Files\\FullProject_Test\\src\\main\\webapp";
        Path imagesPath = Paths.get(HardPath + File.separator + imgName);

        try {
            Files.delete(imagesPath);
            System.out.println("File "
                    + imagesPath.toAbsolutePath().toString()
                    + " successfully removed");
        } catch (IOException e) {
            System.err.println("Unable to delete "
                    + imagesPath.toAbsolutePath().toString()
                    + " due to...");
            e.printStackTrace();
        }
    }

    public ResultSet getProduct2() {
        try {
            ps = conn.prepareStatement("use PerfumeShopV3\n"
                    + "select Product.Product_ID, Product.P_Name as name,Product.P_Status as status,Product.P_Size as size,Product.P_Country as co, Product.P_Price as price ,Product_Image.Image_URL as URL from Product\n"
                    + "inner join Product_Image\n"
                    + "on Product_Image.Product_ID = Product.Product_ID");
            rs = ps.executeQuery();
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public GetProduct getProductByID(int id) {
        String query = "use PerfumeShopV3;\n"
                + "select Product.Product_ID as ID, P_Name, Brand_Name, Cat_Name,  P_Country, P_Status, P_Price, P_Size, Image_URL from Product\n"
                + "inner join Product_Image\n"
                + "on Product.Product_ID = Product_Image.Product_ID\n"
                + "inner join Brand\n"
                + "on Brand.Brand_ID = Product.Brand_ID\n"
                + "inner join Category\n"
                + "on Category.Category_ID = Product.Catagory_ID\n"
                + "where Product.Product_ID= ?;";
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new GetProduct(
                        rs.getInt(1),
                        rs.getString(2), // Get Product Name
                        rs.getString(3), // Get Brand Name
                        rs.getString(4), //Get Category Name
                        rs.getString(5), //Get Product Origin
                        rs.getString(6), //Get Product Status
                        rs.getInt(7), // Get Product Price
                        rs.getString(8),
                        rs.getString(9)// Get Product Size            
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
