import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.control.ListView;

public class AutoParkView extends Pane {
    private ListView<String> parkInventoryList;
    private ListView<String> currentCartList;
    private ListView<String> mostPopularItemsList;
    private Button addToCartButton;
    private Button removeItemButton;
    private Button completeSaleButton;
    private Button resetStockButton;
    private TextField salesTextField;
    private TextField revenueTextField;
    private TextField DollarPerSaleTextField;
    private TextField dollarAmountInCart;
    private int salesCount = 0;
    private double totalRevenue = 0.0;
    private double avgRevenuePerSale = 0.0;

    public AutoParkView() {
        // Add all the labels
        Label label1 = new Label("Park Inventory:");
        label1.relocate(20, 10);
        label1.setPrefSize(150, 30);
        Label label2 = new Label("Current Cart:");
        label2.relocate(310, 10);
        label2.setPrefSize(200, 30);
        Label label3 = new Label("Park Summary:");
        label3.relocate(600, 10);
        label3.setPrefSize(150, 30);
        Label label4 = new Label("# Sales:");
        label4.relocate(600, 35);
        label4.setPrefSize(100, 30);
        Label label5 = new Label("Revenue:");
        label5.relocate(600, 80);
        label5.setPrefSize(100, 30);
        Label label6 = new Label("$/Sale:");
        label6.relocate(600, 120);
        label6.setPrefSize(100, 30);
        Label label7 = new Label("Most Popular Items:");
        label7.relocate(600, 160);
        label7.setPrefSize(150, 30);

        // Add the TextFields
        salesTextField = new TextField();
        salesTextField.relocate(660, 40);
        salesTextField.setPrefSize(100, 30);
        salesTextField.setEditable(false);
        revenueTextField = new TextField();
        revenueTextField.relocate(660, 85);
        revenueTextField.setPrefSize(100, 30);
        revenueTextField.setEditable(false);
        DollarPerSaleTextField = new TextField();
        DollarPerSaleTextField.relocate(660, 125);
        DollarPerSaleTextField.setPrefSize(100, 30);
        DollarPerSaleTextField.setEditable(false);
        dollarAmountInCart = new TextField();
        dollarAmountInCart.relocate(380,10);
        dollarAmountInCart.setPrefSize(80,10);
        dollarAmountInCart.setEditable(false);

        // Add the buttons
        addToCartButton = new Button("Add to Cart");
        addToCartButton.relocate(20, 360);
        addToCartButton.setPrefSize(120, 30);
        addToCartButton.setDisable(true);
        addToCartButton.setDisable(true);
        removeItemButton = new Button("Remove Item");
        removeItemButton.relocate(320, 360);
        removeItemButton.setPrefSize(120, 30);
        removeItemButton.setDisable(true);
        completeSaleButton = new Button("Complete Sale");
        completeSaleButton.relocate(450, 360);
        completeSaleButton.setPrefSize(120, 30);
        completeSaleButton.setDisable(true);
        resetStockButton = new Button("Reset Stock");
        resetStockButton.relocate(640, 360);
        resetStockButton.setPrefSize(120, 30);

        // Add the three listview components
        parkInventoryList = new ListView<>();
        parkInventoryList.relocate(20, 45);
        parkInventoryList.setPrefSize(280, 300);
        currentCartList = new ListView<>();
        currentCartList.relocate(310, 45);
        currentCartList.setPrefSize(280, 300);
        mostPopularItemsList = new ListView<>();
        mostPopularItemsList.relocate(600, 195);
        mostPopularItemsList.setPrefSize(180, 150);

        // Adding all components to pane
        getChildren().addAll(label1, label2, label3, label4, label5, label6, label7,
                salesTextField, revenueTextField, DollarPerSaleTextField,
                addToCartButton, removeItemButton, completeSaleButton, resetStockButton,
                parkInventoryList, currentCartList, mostPopularItemsList,dollarAmountInCart);
    }

    public Button getAddToCartButton() {
        return addToCartButton;
    }

    public Button getRemoveItemButton() {
        return removeItemButton;
    }

    public Button getCompleteSaleButton() {
        return completeSaleButton;
    }

    public Button getResetStockButton() {
        return resetStockButton;
    }

    public TextField getSalesTextField() {
        return salesTextField;
    }

    public TextField getRevenueTextField() {
        return revenueTextField;
    }

    public TextField getDollarPerSaleTextField() {
        return DollarPerSaleTextField;
    }

    public TextField getDollarAmountInCartTextField(){
        return dollarAmountInCart;
    }

    public ListView<String> getParkInventoryList() {
        return parkInventoryList;
    }

    public ListView<String> getMostPopularItemsList() {
        return mostPopularItemsList;
    }

    public ListView<String> getCurrentCartList() {
        return currentCartList;
    }
    public void updateSalesAndRevenue(double cartTotal){
        salesCount++;
        totalRevenue += cartTotal;
        avgRevenuePerSale += totalRevenue/salesCount;
        salesTextField.setText(String.valueOf(salesCount));
        revenueTextField.setText(String.format("%.2f", totalRevenue));
        DollarPerSaleTextField.setText(String.format("%.2f", avgRevenuePerSale));



    }
}
