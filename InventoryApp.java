import java.util.List;
import java.util.Scanner;

public class InventoryApp {
    private static Scanner sc = new Scanner(System.in);
    private static ProductDAO dao = new ProductDAO();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Inventory Management ===");
            System.out.println("1. Add Product");
            System.out.println("2. View All Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Search Products");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addProduct();
                case 2 -> viewProducts();
                case 3 -> updateProduct();
                case 4 -> deleteProduct();
                case 5 -> searchProducts();
                case 6 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void addProduct() {
        try {
            System.out.print("Enter name: ");
            String name = sc.nextLine();
            System.out.print("Enter quantity: ");
            int qty = sc.nextInt();
            System.out.print("Enter price: ");
            double price = sc.nextDouble();
            sc.nextLine();
            System.out.print("Enter category: ");
            String category = sc.nextLine();

            Product p = new Product(name, qty, price, category);
            dao.addProduct(p);
            System.out.println("Product added.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void viewProducts() {
        try {
            List<Product> products = dao.getAllProducts();
            System.out.println("\n--- Product List ---");
            for (Product p : products) {
                System.out.println(
                    p.getId() + " | " + p.getName() + " | " +
                    p.getQuantity() + " | " + p.getPrice() + " | " + p.getCategory()
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void updateProduct() {
        try {
            System.out.print("Enter product ID to update: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter new name: ");
            String name = sc.nextLine();
            System.out.print("Enter new quantity: ");
            int qty = sc.nextInt();
            System.out.print("Enter new price: ");
            double price = sc.nextDouble();
            sc.nextLine();
            System.out.print("Enter new category: ");
            String category = sc.nextLine();

            Product p = new Product(id, name, qty, price, category);
            dao.updateProduct(p);
            System.out.println("Product updated.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void deleteProduct() {
        try {
            System.out.print("Enter product ID to delete: ");
            int id = sc.nextInt();
            dao.deleteProduct(id);
            System.out.println("Product deleted.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void searchProducts() {
        try {
            System.out.print("Enter search keyword: ");
            String keyword = sc.nextLine();

            List<Product> products = dao.searchProducts(keyword);
            System.out.println("\n--- Search Results ---");
            for (Product p : products) {
                System.out.println(
                    p.getId() + " | " + p.getName() + " | " +
                    p.getQuantity() + " | " + p.getPrice() + " | " + p.getCategory()
                );
            }
            if (products.isEmpty()) {
                System.out.println("No products found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
