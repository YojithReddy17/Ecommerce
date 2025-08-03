Core Java E-Commerce Console Application
This project is a command-line e-commerce application built entirely with Core Java. It demonstrates key object-oriented programming (OOP) principles, clean architecture by separating concerns (model, repository, service), and data persistence through file serialization. It serves as the foundation for a future web application.

✨ Features
User Authentication: Users can register for a new account and log in.

Data Persistence: User accounts and placed orders are saved to local files (users.ser, orders.ser), so data persists between application sessions.

Product Catalog: View a predefined list of available products.

Shopping Cart:

Add products to a personal, in-memory shopping cart.

View the contents of the cart with quantities, prices, and a calculated total.

Checkout Process: Convert the shopping cart into a permanent Order object, which is then saved to a file.

🛠️ Technologies & Concepts
Language: Java 11 (or higher)

Core Concepts:

Object-Oriented Programming (Encapsulation, Inheritance, Polymorphism)

Java Collections Framework (List, Map, ArrayList, HashMap)

Java Streams API for data processing

Java I/O for file handling (ObjectInputStream & ObjectOutputStream for serialization)

Clean Architecture (Model-Repository-Service pattern)

🚀 Getting Started
To run this application on your local machine, you'll need to have a Java Development Kit (JDK) installed.

1. Clone the Repository
Bash

git clone <your-repository-url>
cd <repository-folder-name>
2. Compile the Code
Navigate to the source directory and compile all the Java files.

Bash

cd src
javac com/ecommerce/main/Main.java
The javac command will automatically find and compile all the necessary dependent classes (User.java, ProductService.java, etc.).

3. Run the Application
From the src directory, run the main class.

Bash

java com.ecommerce.main.Main
You should now see the "Welcome" menu in your console!

📁 Project Structure
The project is organized into packages to separate different layers of the application logic.

src
└── com
    └── ecommerce
        ├── main
        │   └── Main.java         # Entry point and console UI
        ├── model
        │   ├── User.java         # User data model
        │   ├── Product.java      # Product data model
        │   ├── CartItem.java     # Represents a product and quantity in the cart
        │   ├── ShoppingCart.java # Holds a user's cart items
        │   └── Order.java        # Represents a placed order
        ├── repository
        │   ├── UserRepository.java      # Interface for user data access
        │   ├── FileUserRepository.java  # Saves user data to a file
        │   ├── OrderRepository.java     # Interface for order data access
        │   └── FileOrderRepository.java # Saves order data to a file
        └── service
            ├── UserService.java    # Handles user registration/login logic
            ├── ProductService.java # Manages product data
            ├── CartService.java    # Manages shopping cart operations
            └── OrderService.java   # Manages order creation logic
