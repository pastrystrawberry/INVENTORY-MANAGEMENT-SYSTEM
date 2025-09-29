import javax.swing.*;
import java.awt.*;

public class AddProductDialog extends JDialog {

    public static void showDialog(JFrame parent, ProductDAO dao, InventoryGUI gui) {
        JDialog dialog = new JDialog(parent, "Add Product", true);
        dialog.setSize(300, 300);
        dialog.setLocationRelativeTo(parent);
        dialog.setLayout(new GridLayout(6, 2, 10, 10));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();

        JLabel quantityLabel = new JLabel("Quantity:");
        JTextField quantityField = new JTextField();

        JLabel priceLabel = new JLabel("Price:");
        JTextField priceField = new JTextField();

        JLabel categoryLabel = new JLabel("Category:");
        JTextField categoryField = new JTextField();

        JButton addBtn = new JButton("Add");
        JButton cancelBtn = new JButton("Cancel");

        dialog.add(nameLabel);
        dialog.add(nameField);
        dialog.add(quantityLabel);
        dialog.add(quantityField);
        dialog.add(priceLabel);
        dialog.add(priceField);
        dialog.add(categoryLabel);
        dialog.add(categoryField);
        dialog.add(addBtn);
        dialog.add(cancelBtn);

        addBtn.addActionListener(e -> {
            try {
                String name = nameField.getText();
                int qty = Integer.parseInt(quantityField.getText());
                double price = Double.parseDouble(priceField.getText());
                String category = categoryField.getText();

                dao.addProduct(new Product(name, qty, price, category));
                gui.refreshTable();
                dialog.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Error adding product.");
                ex.printStackTrace();
            }
        });

        cancelBtn.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }
}
