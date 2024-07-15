import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class User {
    private String username;
    private String password;
    private String email;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
class Product {
    private String name;
    private double price;
    private int stock;
    private String description;

    public Product(String name, double price, int stock, String description) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
class Cart {
    private Map<Product, Integer> items;

    public Cart() {
        this.items = new HashMap<>();
    }

    public void addProduct(Product product, int quantity) {
        items.put(product, items.getOrDefault(product, 0) + quantity);
    }

    public void removeProduct(Product product) {
        items.remove(product);
    }

    public double getTotalPrice() {
        return items.entrySet().stream().mapToDouble(e -> e.getKey().getPrice() * e.getValue()).sum();
    }

    public Map<Product, Integer> getItems() {
        return items;
    }
}
class Order {
    private User user;
    private Map<Product, Integer> items;
    private double totalPrice;
    private Date orderDate;
    private String status;

    public Order(User user, Map<Product, Integer> items, double totalPrice) {
        this.user = user;
        this.items = items;
        this.totalPrice = totalPrice;
        this.orderDate = new Date();
        this.status = "Pending";
    }

    public User getUser() {
        return user;
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
public class ECommerceApp {
    private static List<User> users = new ArrayList<>();
    private static List<Product> products = new ArrayList<>();
    private static List<Order> orders = new ArrayList<>();
    private static User currentUser = null;
    private static Cart cart = new Cart();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            if (currentUser == null) {
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        register(scanner);
                        break;
                    case 2:
                        login(scanner);
                        break;
                    case 3:
                        running = false;
                        break;
                }
            } else {
                System.out.println("1. View Products");
                System.out.println("2. Add Product");
                System.out.println("3. View Cart");
                System.out.println("4. Checkout");
                System.out.println("5. View Orders");
                System.out.println("6. Logout");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        viewProducts();
                        break;
                    case 2:
                        addProduct(scanner);
                        break;
                    case 3:
                        viewCart();
                        break;
                    case 4:
                        checkout();
                        break;
                    case 5:
                        viewOrders();
                        break;
                    case 6:
                        currentUser = null;
                        break;
                }
            }
        }

        scanner.close();
    }
private static void register(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();
        System.out.print("Enter email: ");
        String email = scanner.next();

        users.add(new User(username, password, email));
        System.out.println("Registration successful!");
    }

    private static void login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                currentUser = user;
                System.out.println("Login successful!");
                return;
            }
        }

        System.out.println("Invalid username or password.");
    }
 private static void viewProducts() {
        System.out.println("Available Products:");
        for (Product product : products) {
            System.out.println("Name: " + product.getName() + ", Price: " + product.getPrice() + ", Stock: " + product.getStock() + ", Description: " + product.getDescription());
        }
    }

    private static void addProduct(Scanner scanner) {
        System.out.print("Enter product name: ");
        String name = scanner.next();
        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter product stock: ");
        int stock = scanner.nextInt();
        System.out.print("Enter product description: ");
        String description = scanner.next();

        products.add(new Product(name, price, stock, description));
        System.out.println("Product added successfully!");
    }
