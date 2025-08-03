package com.ecommerce.main;

import com.ecommerce.model.*;
import com.ecommerce.repository.FileOrderRepository;
import com.ecommerce.repository.FileUserRepository;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.CartService;
import com.ecommerce.service.OrderService;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.UserService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static final UserService userService;
    private static final ProductService productService;
    private static final CartService cartService;
    private static final OrderService orderService;
    private static User loggedInUser = null;

    static {
        UserRepository userRepository = new FileUserRepository();
        OrderRepository orderRepository = new FileOrderRepository();
        productService = new ProductService();
        userService = new UserService(userRepository);
        cartService = new CartService();
        orderService = new OrderService(orderRepository);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (loggedInUser == null) {
                showAuthMenu(scanner);
            } else {
                showMainMenu(scanner);
            }
        }
    }

    private static void showAuthMenu(Scanner scanner) {
        System.out.println("\n--- Welcome to the E-Commerce Console App ---");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");

        try {
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: handleRegister(scanner); break;
                case 2: handleLogin(scanner); break;
                case 3: System.out.println("Exiting application. Goodbye!"); System.exit(0); break;
                default: System.out.println("Invalid choice.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine();
        }
    }

    private static void handleRegister(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        User user = userService.registerUser(username, password);
        if (user != null) {
            System.out.println("Registration successful!");
        } else {
            System.out.println("Username already exists.");
        }
    }

    private static void handleLogin(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        loggedInUser = userService.loginUser(username, password);
        if (loggedInUser != null) {
            System.out.println("Login successful! Welcome, " + loggedInUser.getUsername());
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void showMainMenu(Scanner scanner) {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. View Products");
        System.out.println("2. View Cart");
        System.out.println("3. Place Order");
        System.out.println("4. Logout");
        System.out.print("Enter your choice: ");

        try {
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: displayProductsAndAddToCart(scanner); break;
                case 2: displayCart(); break;
                case 3: handlePlaceOrder(); break;
                case 4: loggedInUser = null; System.out.println("You have been logged out."); break;
                default: System.out.println("Invalid choice.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine();
        }
    }

    private static void displayProductsAndAddToCart(Scanner scanner) {
        List<Product> products = productService.getProducts();
        System.out.println("\n--- Available Products ---");
        for (Product product : products) {
            System.out.println(product);
        }

        System.out.print("\nEnter the ID of the product to add to cart (or 0 to go back): ");
        try {
            long productId = scanner.nextLong();
            scanner.nextLine();
            if (productId == 0) return;

            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();
            scanner.nextLine();
            if (quantity <= 0) {
                System.out.println("Quantity must be positive.");
                return;
            }

            Optional<Product> productOpt = productService.findById(productId);
            if (productOpt.isPresent()) {
                cartService.addProductToCart(loggedInUser.getUserId(), productOpt.get(), quantity);
                System.out.println("'" + productOpt.get().getName() + "' has been added to your cart.");
            } else {
                System.out.println("Product with ID " + productId + " not found.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid numbers.");
            scanner.nextLine();
        }
    }

    private static void displayCart() {
        ShoppingCart cart = cartService.getCart(loggedInUser.getUserId());
        System.out.println("\n--- Your Shopping Cart ---");
        if (cart.getItems().isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            double total = 0;
            for (CartItem item : cart.getItems()) {
                Product product = item.getProduct();
                double itemTotal = product.getPrice() * item.getQuantity();
                System.out.printf("- %s | Qty: %d | Subtotal: $%.2f%n",
                        product.getName(), item.getQuantity(), itemTotal);
                total += itemTotal;
            }
            System.out.printf("------------------------\nTotal: $%.2f%n", total);
        }
    }

    private static void handlePlaceOrder() {
        ShoppingCart cart = cartService.getCart(loggedInUser.getUserId());
        Order newOrder = orderService.createOrder(cart);
        if (newOrder != null) {
            System.out.println("\n--- Order Placed Successfully! ---");
            System.out.printf("Order ID: %d%n", newOrder.getOrderId());
            System.out.printf("Total Price: $%.2f%n", newOrder.getTotalPrice());
            cartService.clearCart(loggedInUser.getUserId());
        } else {
            System.out.println("Your cart is empty. Cannot place an order.");
        }
    }
}