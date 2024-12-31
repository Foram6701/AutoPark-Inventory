import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.Arrays;
import java.util.List;

public class AutoParkController {
    private ListView<String> parkInventoryList;
    private ListView<String> currentCartList;
    private ListView<String> mostPopularItemsList;
    private TextField salesTextField;
    private TextField revenueTextField;
    private TextField dollarPerSaleTextField;
    private TextField dollarAmountInCart;
    private AutoPark model;
    private Button addToCartButton;
    private Button removeItemButton;
    private Button completeSaleButton;
    private Button resetStockButton;
    private AutoParkView view;

    public AutoParkController(
            ListView<String> parkInventoryList,
            ListView<String> currentCartList,
            ListView<String> mostPopularItemsList,
            TextField salesTextField,
            TextField revenueTextField,
            TextField dollarPerSaleTextField,
            TextField dollarAmountInCart,
            AutoPark model,
            Button addToCartButton,
            Button removeItemButton,
            Button completeSaleButton,
            Button resetStockButton,
            AutoParkView view) {
        this.parkInventoryList = parkInventoryList;
        this.currentCartList = currentCartList;
        this.mostPopularItemsList = mostPopularItemsList;
        this.salesTextField = salesTextField;
        this.revenueTextField = revenueTextField;
        this.dollarPerSaleTextField = dollarPerSaleTextField;
        this.model = model;
        this.addToCartButton = addToCartButton;
        this.removeItemButton = removeItemButton;
        this.completeSaleButton = completeSaleButton;
        this.resetStockButton = resetStockButton;
        this.view = view;
        this.dollarAmountInCart = dollarAmountInCart;
        initializeEventHandlers();
        initializeSelectionListeners();
    }

    private void initializeEventHandlers() {
        addToCartButton.setOnAction(event -> handleAddToCart());
        removeItemButton.setOnAction(event -> handleRemoveFromCart());
        completeSaleButton.setOnAction(event -> handleCompleteSale());
        resetStockButton.setOnAction(event -> handleResetStock());
    }

    private void initializeSelectionListeners() {
        parkInventoryList.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            updateAddToCartButtonState();
        });
        currentCartList.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            updateRemoveFromCartButtonState();
        });
        currentCartList.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            updateRemoveFromCartButtonState();
        });
        currentCartList.getItems().addListener((javafx.collections.ListChangeListener<String>) change -> {
            updateCompleteSaleButtonState(); // Update the button state when the cart changes
        });
    }


    private void handleAddToCart() {

        String selectedItem = parkInventoryList.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            // Find the actual item object by name
            Item item = model.findItemByName(selectedItem.split(" - ")[0]); // Extract name part only

            if (item != null) {
                double revenue = item.sellUnits(1); // Attempt to sell 1 unit

                if (revenue > 0) {
                    // Add the item to the cart
                    currentCartList.getItems().add(item.toString());

                    if (item.getInvQuantity() == 0) {
                        // Remove the item from parkInventoryList when stock is zero
                        int index = parkInventoryList.getItems().indexOf(selectedItem);
                        if (index != -1) {
                            parkInventoryList.getItems().remove(index); // Remove using the index
                        }
                    } else {
                        // Update the item in the list to reflect new stock level
                        int index = parkInventoryList.getItems().indexOf(selectedItem);
                        if (index != -1) {
                            parkInventoryList.getItems().set(index, item.toString()); // Update with new quantity

                        }
                    }
                }
                updateDollarAmountInCart();
                updateAddToCartButtonState(); // Ensure button state is updated
            }
        }
    }

    private void handleRemoveFromCart() {
        System.out.println("here in remove");
        String selectedItem = currentCartList.getSelectionModel().getSelectedItem(); // Use currentCartList selection

        if (selectedItem != null) {
            // Find the actual item object by name (splitting by " - " if needed)
            Item item = model.findItemByName(selectedItem.split(" - ")[0]);

            if (item != null) {
                item.sellUnits(-1); // Increase the stock by removing 1 unit from the cart

                // Remove the item from the cart
                currentCartList.getItems().remove(selectedItem);
                // Update or add the item in the inventory list
                int index = currentCartList.getItems().indexOf(selectedItem.toString());
                if (index != -1) {
                    currentCartList.getItems().set(index, item.toString()); // Update existing item in inventory
                } else {
                    parkInventoryList.getItems().add(item.toString()); // Add back to inventory if not already present
                }
            }
            // Ensure the button state is updated
            updateRemoveFromCartButtonState();
        }
    }

    private void handleCompleteSale() {
        double totalCost = 0.0;
        for (String itemString : currentCartList.getItems()) {
            Item item = model.findItemByName(itemString);
            if (item != null) {
                totalCost += item.getPrice(); // Assuming each item is 1 unit
            }
        }
        if (view != null) {
            view.updateSalesAndRevenue(totalCost);
        } else {
            System.out.println("Error: View is not initialized.");
        }
        currentCartList.getItems().clear();
        updateCompleteSaleButtonState(); // Ensure button state is updated
        updateMostPopularItemsList();
    }

    private void handleResetStock() {
        for (Item item : model.getItems()) {
            if (item != null) {
                item.resetStock();
            }
        }
        parkInventoryList.getItems().clear();
        currentCartList.getItems().clear();
        salesTextField.clear();
        revenueTextField.clear();
        dollarPerSaleTextField.clear();
        for (Item item : model.getItems()) {
            if (item != null && item.getInvQuantity() > 0) {
                parkInventoryList.getItems().add(item.toString());
            }
        }
    }

    private void updateAddToCartButtonState() {
        addToCartButton.setDisable(parkInventoryList.getSelectionModel().getSelectedItem() == null);
    }

    private void updateRemoveFromCartButtonState() {
        removeItemButton.setDisable(currentCartList.getSelectionModel().getSelectedItem() == null);
    }

    private void updateCompleteSaleButtonState() {
        completeSaleButton.setDisable(currentCartList.getItems().isEmpty());
}
    private void updateDollarAmountInCart() {
        double totalAmount = 0.0;

        // Calculate total amount of items in the cart
        for (String itemString : currentCartList.getItems()) {
            Item item = model.findItemByName(itemString.split(" - ")[0]);
            if (item != null) {
                totalAmount += item.getPrice(); // Assuming each item is 1 unit
            }
        }

        // Update the dollarAmountInCart TextField
        dollarAmountInCart.setText(String.format("%.2f", totalAmount));
    }
    private void updateMostPopularItemsList() {
        // Retrieve all items from the model
        Item[] itemsArray = model.getItems();
        List<Item> items = Arrays.asList(itemsArray);
        items.sort((item1, item2) -> Integer.compare(item2.getSoldQuantity(), item1.getSoldQuantity()));


        // Sort items by sold quantity in descending order
        items.sort((item1, item2) -> Integer.compare(item2.getSoldQuantity(), item1.getSoldQuantity()));

        // Clear existing items in the Most Popular Items list
        mostPopularItemsList.getItems().clear();

        // Add top 3 items to the Most Popular Items list
        for (int i = 0; i < Math.min(3, items.size()); i++) {
            Item item = items.get(i);
            if (item.getSoldQuantity() > 0) {
                mostPopularItemsList.getItems().add(item.toString() + " (Sold: " + item.getSoldQuantity() + ")");
            }
        }
    }
    }

