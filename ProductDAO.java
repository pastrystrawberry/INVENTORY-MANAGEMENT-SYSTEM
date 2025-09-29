import java.sql.*;
import java.util.*;

public class ProductDAO {

    public void addProduct(Product p) throws Exception {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "INSERT INTO products(name, quantity, price, category) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, p.getName());
            ps.setInt(2, p.getQuantity());
            ps.setDouble(3, p.getPrice());
            ps.setString(4, p.getCategory());
            ps.executeUpdate();
        }
    }

    public List<Product> getAllProducts() throws Exception {
        List<Product> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM products");
            while (rs.next()) {
                list.add(new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("quantity"),
                    rs.getDouble("price"),
                    rs.getString("category")
                ));
            }
        }
        return list;
    }

    public void updateProduct(Product p) throws Exception {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "UPDATE products SET name=?, quantity=?, price=?, category=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, p.getName());
            ps.setInt(2, p.getQuantity());
            ps.setDouble(3, p.getPrice());
            ps.setString(4, p.getCategory());
            ps.setInt(5, p.getId());
            ps.executeUpdate();
        }
    }

    public void deleteProduct(int id) throws Exception {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "DELETE FROM products WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public List<Product> searchProducts(String keyword) throws Exception {
        List<Product> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT * FROM products WHERE name LIKE ? OR category LIKE ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("quantity"),
                    rs.getDouble("price"),
                    rs.getString("category")
                ));
            }
        }
        return list;
    }
}
