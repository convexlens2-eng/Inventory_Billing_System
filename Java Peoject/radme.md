# 📦 Inventory Billing System

A lightweight Java-based inventory management system designed for small businesses to efficiently track stock and manage customer billing.

---

## 🚀 Features

- Add, update, and search products  
- Real-time stock management  
- Billing system with cart functionality  
- Low stock alerts (threshold < 5)  
- Prevents negative stock transactions  
- Dynamic user feedback messages  

---

## 🏗️ Project Structure

SmartStock/
│
├── Product.java        # Product model (id, name, price, stock)  
├── Inventory.java      # Manages all products  
├── CartItem.java      # Represents items in cart  
├── Bill.java          # Handles billing logic  
├── Main.java          # Entry point (menu-driven system)  

---

## 🧠 Concepts Used

- Object-Oriented Programming (OOP)  
- Encapsulation  
- Java Collections Framework  
  - HashMap → Fast product lookup (O(1))  
  - ArrayList → Cart management  
- Input handling using Scanner  

---

## ⚡ Key Functionalities

### Add Product
- Add new items with ID, name, price, and stock  

### Update Product
- Modify existing product details  

### Billing System
- Add items to cart  
- Generate total bill  
- Automatically updates stock  

### Stock Validation
- Prevents selling more than available stock  

---

## 🛠️ Challenges Solved

- Fixed Scanner input bug (nextInt() + nextLine())  
- Implemented validation to avoid negative stock  
- Improved user experience with dynamic messages  

---

## 🔮 Future Improvements

- File handling for persistent storage  
- GUI using JavaFX  
- Database integration (MySQL)  
- Analytics dashboard  

---

## 📚 Learning Outcomes

- Practical use of data structures  
- Writing modular and scalable code  
- Handling edge cases and user errors  
- Real-world problem solving  

---

## ⭐ Acknowledgment

This project was developed as part of a BYOP Capstone to solve real-world inventory problems faced by small businesses.