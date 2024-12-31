import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AutoParkApp extends Application {
    private AutoPark model;
    private AutoParkView view;

    @Override
    public void start(Stage primaryStage) {
        model = AutoPark.createPark();
        view = new AutoParkView();

        // Populate the park inventory list
        for (Item item : model.getItems()) {
            if (item != null) {
                view.getParkInventoryList().getItems().add(item.toString());
            }
        }

        // Populate Most Popular Items with the first 3 items (popularity is equal)
        for (int i = 0; i < 3 && i < model.getTotalItem(); i++) {
            view.getMostPopularItemsList().getItems().add(model.getItems()[i].toString());
        }

        // Initialize the controller
        AutoParkController controller = new AutoParkController(
                view.getParkInventoryList(),
                view.getCurrentCartList(),
                view.getMostPopularItemsList(),
                view.getSalesTextField(),
                view.getRevenueTextField(),
                view.getDollarPerSaleTextField(),
                view.getDollarAmountInCartTextField(),
                model,
                view.getAddToCartButton(),
                view.getRemoveItemButton(),
                view.getCompleteSaleButton(),
                view.getResetStockButton(),
                view // Pass the view reference
        );

        primaryStage.setTitle("VroomVille Vehicle Haven - Sales and Inventory");
        primaryStage.setResizable(true);
        primaryStage.setScene(new Scene(view, 800, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
