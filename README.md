# AutoPark-Inventory

#### Overview
The **AutoPark Inventory Management System** is a comprehensive JavaFX-based application for managing the operations of an auto park, enabling efficient tracking and handling of vehicle inventory and sales. The system supports multiple vehicle types, tires, and related sales functionality, including cart management, sales statistics, and inventory updates. The program consists of 13 files, each serving a specific purpose in the system's architecture.

---

### Features
1. **Vehicle and Item Inventory Management**:
   - Displays and manages a variety of items, including Sedans, SUVs, Trucks, Minivans, and Tires.
   - Handles inventory levels, including automatic updates when items are sold or returned.

2. **Cart Management**:
   - Add items to a cart for checkout.
   - Remove items from the cart, updating inventory levels dynamically.

3. **Sales Operations**:
   - Complete sales, updating revenue and tracking the number of items sold.
   - Generate and display sales summaries.

4. **Statistics and Insights**:
   - Displays sales count, total revenue, and average revenue per sale.
   - Identifies and showcases the most popular items based on sales data.

5. **Admin Controls**:
   - Reset inventory stock levels to initial quantities.
   - Manage buttons dynamically based on item availability and cart state.

---

### System Architecture

#### **1. Classes for Vehicles**
- **`Vehicle`** (Abstract Class): Base class for all vehicles, capturing shared attributes like make, model, year, price, and quantity.
- **`PersonalVehicle`** (Abstract Class): Derived from `Vehicle`, representing personal vehicles like Sedans and SUVs.
  - **`Sedan`**: Represents sedan vehicles.
  - **`SUV`**: Represents SUVs with optional all-wheel drive.
- **`CommercialVehicle`** (Abstract Class): Derived from `Vehicle`, representing commercial vehicles like Trucks and Minivans.
  - **`Truck`**: Represents trucks, including heavy-duty variants.
  - **`Minivan`**: Represents minivans with optional covered configurations.

#### **2. Item Management**
- **`Item`**: Abstract base class for all items, including tires and vehicles. Handles pricing, inventory, and sales quantities.
- **`Tire`**: Represents a specific type of tire with attributes like wheel diameter and section width.

#### **3. Application Components**
- **`AutoPark`**: Core model class managing the inventory, sales data, and statistics.
- **`AutoParkApp`**: Main application class initializing the JavaFX user interface.
- **`AutoParkController`**: Controller handling user interactions and coordinating between the view and model.
- **`AutoParkView`**: JavaFX pane defining the user interface components and layout.

---

### User Interface
1. **Inventory List**:
   - Displays all available items with details such as make, model, year, price, and stock levels.

2. **Current Cart**:
   - Displays selected items for purchase.

3. **Park Summary**:
   - Shows total sales, revenue, and average revenue per sale.
   - Highlights the three most popular items based on sales.

4. **Interactive Buttons**:
   - **Add to Cart**: Enabled when an item is selected from the inventory.
   - **Remove Item**: Enabled when an item is selected from the cart.
   - **Complete Sale**: Enabled when the cart contains items.
   - **Reset Stock**: Resets inventory to initial levels.

---

### How It Works
1. **Start the Application**:
   - The main interface displays inventory, cart, and park summary.

2. **Add Items to Cart**:
   - Select an item from the inventory and click "Add to Cart."

3. **Remove Items from Cart**:
   - Select an item from the cart and click "Remove Item."

4. **Complete Sale**:
   - Click "Complete Sale" to finalize the transaction. The system updates sales and revenue statistics.

5. **Reset Stock**:
   - Click "Reset Stock" to restore inventory levels to their initial values.

---

### Technical Requirements
- **Java**: JDK 11 or higher.
- **JavaFX**: JavaFX 11 or higher for GUI functionality.
- **IDE**: IntelliJ IDEA or any Java-compatible IDE for running the project.

---

### File List
1. `AutoPark.java`: Core model for managing inventory and sales.
2. `AutoParkApp.java`: Main application entry point using JavaFX.
3. `AutoParkController.java`: Handles user interactions and logic.
4. `AutoParkView.java`: Defines the user interface layout.
5. `Item.java`: Abstract base class for inventory items.
6. `Tire.java`: Represents tire details.
7. `Vehicle.java`: Abstract base class for all vehicles.
8. `Sedan.java`: Represents sedan vehicles.
9. `SUV.java`: Represents SUVs.
10. `Truck.java`: Represents trucks.
11. `Minivan.java`: Represents minivans.
12. `PersonalVehicle.java`: Abstract class for personal vehicles.
13. `CommercialVehicle.java`: Abstract class for commercial vehicles.

---

### Future Enhancements
1. **Reporting**:
   - Add features to generate detailed sales and inventory reports.

2. **User Roles**:
   - Include admin and customer roles for specific permissions.

3. **Advanced Filters**:
   - Enable filters for searching inventory based on attributes like make, model, and price.

---

### Getting Started
1. Clone the repository.
2. Open the project in your Java IDE.
3. Ensure JavaFX is configured in your IDE.
4. Run the `AutoParkApp` class to launch the application.

--- 
