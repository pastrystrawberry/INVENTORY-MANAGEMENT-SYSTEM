import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class UpdateProductDialog extends JDialog {

    public static void showDialog(JFrame parent, ProductDAO dao, InventoryGUI gui, int productId) {
        if (productId == -1) {
            JOptionPane.showMessageDialog(parent, "Please select a product to update.");
            return;
        }

        try {
            List<Product> products = dao.getAllProducts();
            Product productToUpdate = null;
            for (Product p : products) {
                if (p.getId() == productId) {
                    productToUpdate = p;
                    break;
                }
            }

            if (productToUpdate == null) {
                JOptionPane.showMessageDialog(parent, "Product not found.");
                return;
            }

            JDialog dialog = new JDialog(parent, "Update Product", true);
            dialog.setSize(300, 300);
            dialog.setLocationRelativeTo(parent);
            dialog.setLayout(new GridLayout(6, 2, 10, 10));

            JLabel nameLabel = new JLabel("Name:");
            JTextField nameField = new JTextField(productToUpdate.getName());

            JLabel quantityLabel = new JLabel("Quantity:");
            JTextField quantityField = new JTextField(String.valueOf(productToUpdate.getQuantity()));

            JLabel priceLabel = new JLabel("Price:");
            JTextField priceField = new JTextField(String.valueOf(productToUpdate.getPrice()));

            JLabel categoryLabel = new JLabel("Category:");
            JTextField categoryField = new JTextField(productToUpdate.getCategory());

            JButton updateBtn = new JButton("Update");
            JButton cancelBtn = new JButton("Cancel");

            dialog.add(nameLabel);
            dialog.add(nameField);
            dialog.add(quantityLabel);
            dialog.add(quantityField);
            dialog.add(priceLabel);
            dialog.add(priceField);
            dialog.add(categoryLabel);
            dialog.add(categoryField);
            dialog.add(updateBtn);
            dialog.add(cancelBtn);

            updateBtn.addActionListener(e -> {
                try {
                    String name = nameField.getText();
                    int qty = Integer.parseInt(quantityField.getText());
                    double price = Double.parseDouble(priceField.getText());
                    String category = categoryField.getText();

                    dao.updateProduct(new Product(productId, name, qty, price, category));
                    gui.refreshTable();
                    dialog.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dialog, "Error updating product.");
                    ex.printStackTrace();
                }
            });

            cancelBtn.addActionListener(e -> dialog.dispose());

            dialog.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(parent, "Error loading product.");
        }
    }
}
