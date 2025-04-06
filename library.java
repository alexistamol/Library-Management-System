import java.util.*;

public class library {
    static Scanner scanner = new Scanner(System.in);
    static Map<String, Boolean> books = new HashMap<>();
    static Map<String, String> borrowedBooks = new HashMap<>();
    static Map<String, String> returnBook = new HashMap<>();
    static Map<String, String> accounts = new HashMap<>(); 

    public static void main(String[] args) {
        books.put("The Great Gatsby", true);
        books.put("To Kill a Mockingbird", true);
        books.put("1984", true);
        books.put("Moby Dick", true);
        books.put("Pride and Prejudice", true);

        while (true) {
            System.out.println("\n=== Library System ===");
            System.out.println("1. Register (Create Account)");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) createacc();
            else if (choice == 2) login();
            else if (choice == 3) break;
            else System.out.println("Invalid choice. Try again.");
        }
    }

    // ----------------------------
    // Register (Create Account)
    // ----------------------------
    static void createacc() {
        System.out.print("\nEnter new username: ");
        String username = scanner.nextLine();

        if (accounts.containsKey(username)) {
            System.out.println("Username already exists.");
            return;
        }

        System.out.print("Enter new password: ");
        String password = scanner.nextLine();

        accounts.put(username, password);
        System.out.println("Account created successfully!");
    }

    // ----------------------------
    // Login
    // ----------------------------
    static void login() {
        System.out.print("\nEnter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        
        if (username.equals("admin") && password.equals("admin123")) {
            System.out.println("Logged in as Admin.");
            adminMenu();
            return;
        }

        
        if (accounts.containsKey(username) && accounts.get(username).equals(password)) {
            System.out.println("Logged in as User: " + username);
            userMenu();
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    // ----------------------------
    // Admin Menu
    // ----------------------------
    static void adminMenu() {
        while (true) {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. Add Book");
            System.out.println("2. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) addBook();
            else if (choice == 2) break;
            else System.out.println("Invalid choice.");
        }
    }

    // ----------------------------
    // User Menu
    // ----------------------------
    static void userMenu() {
        while (true) {
            System.out.println("\n=== User Menu ===");
            System.out.println("1. View Books");
            System.out.println("2. Search Book");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) viewBooks();
            else if (choice == 2) searchBook();
            else if (choice == 3) borrowBook();
            else if (choice == 4) returnBook();
            else if (choice == 5) break;
            else System.out.println("Invalid choice. Try again.");
        }
    }

    // ----------------------------
    // Features
    // ----------------------------

    static void viewBooks() {
        System.out.println("\nList of Books:");
        
        books.keySet().forEach(title -> {
            System.out.println("- " + title);
        });
    }
    
                                                                                    
    

    static void searchBook() {
        System.out.print("\nEnter book title to search: ");
        String title = scanner.nextLine();
        if (books.containsKey(title)) {
            System.out.println(title + " - " + (books.get(title) ? "Book Available" : "Borrowed"));
        } else System.out.println("Book not found.");
    }

    static void borrowBook() {
        System.out.print("\nEnter book title to borrow: ");
        String title = scanner.nextLine();
    
        if (books.containsKey(title) && books.get(title)) {
            System.out.print("Enter borrowing date (YYYY-MM-DD): ");
            String date = scanner.nextLine();

            books.put(title, false);
            borrowedBooks.put(title, date);

            System.out.println("You borrowed: " + title + " on " + date);
        } else {
            System.out.println("Book unavailable or not found.");
        }
    }


    static void returnBook() {
        System.out.print("Enter book title to return: ");
        String title = scanner.nextLine();
    
        if (books.containsKey(title) && !books.get(title)) {
            System.out.print("Enter return date (YYYY-MM-DD): ");
            String rdate = scanner.nextLine();

            books.put(title, false);
            returnBook.put(title, rdate);

            
            if (borrowedBooks.containsKey(title)) {
                System.out.println("You returned: " + title + " on " + rdate);
               

                System.out.println("Borrow Date: " + borrowedBooks.get(title));
                borrowedBooks.remove(title);
            } else {
                System.out.println("You returned: " + title);
                System.out.println("Borrow Date information not found.");
            }
    
        } else {
            System.out.println("Book is available.");
        }
    }

    static void addBook() {
        System.out.print("\nEnter new book title: ");
        String title = scanner.nextLine();
        
        if (books.containsKey(title)) {
            System.out.println("Book already exists in the library.");
        } else {
            books.put(title, true);
            System.out.println("Book \"" + title + "\" has been added to the library.");
        }
    }
}