import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class InventoryGUI extends JFrame {

    private ProductDAO dao = new ProductDAO();
    private JTable table;
    private DefaultTableModel tableModel;

    public InventoryGUI() {
        setTitle("Inventory Management");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Table setup
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Quantity", "Price", "Category"}, 0);
        table = new JTable(tableModel);
        refreshTable();

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        JButton addBtn = new JButton("Add");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");
        JButton searchBtn = new JButton("Search");

        buttonPanel.add(addBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(searchBtn);

        add(buttonPanel, BorderLayout.SOUTH);

        // Button actions
        addBtn.addActionListener(e -> AddProductDialog.showDialog(this, dao, this));
        updateBtn.addActionListener(e -> UpdateProductDialog.showDialog(this, dao, this, getSelectedProductId()));
        deleteBtn.addActionListener(e -> deleteProduct());
        searchBtn.addActionListener(e -> searchProduct());

        setVisible(true);
    }

    public void refreshTable() {
        try {
            tableModel.setRowCount(0);
            List<Product> products = dao.getAllProducts();
            for (Product p : products) {
                tableModel.addRow(new Object[]{p.getId(), p.getName(), p.getQuantity(), p.getPrice(), p.getCategory()});
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading products.");
        }
    }

    private int getSelectedProductId() {
        int row = table.getSelectedRow();
        if (row == -1) return -1;
        return (int) table.getValueAt(row, 0);
    }

    private void deleteProduct() {
        int id = getSelectedProductId();
        if (id == -1) {
            JOptionPane.showMessageDialog(this, "Please select a product to delete.");
            return;
        }
        try {
            dao.deleteProduct(id);
            refreshTable();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error deleting product.");
        }
    }

    private void searchProduct() {
        String keyword = JOptionPane.showInputDialog(this, "Enter keyword to search:");
        if (keyword == null || keyword.isEmpty()) return;
        try {
            List<Product> results = dao.searchProducts(keyword);
            tableModel.setRowCount(0);
            for (Product p : results) {
                tableModel.addRow(new Object[]{p.getId(), p.getName(), p.getQuantity(), p.getPrice(), p.getCategory()});
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error searching products.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InventoryGUI::new);
    }
}
