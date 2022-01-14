package views.screen.rentbike;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import controller.PaymentController;
import entity.Bike;
import entity.BikeRental;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.Utils;
import views.screen.viewbikeinfo.RentingBikeInfoScreenHandler;

public class PaymentScreenHandler {

    @FXML
    private Button payBtn;

    @FXML
    private TextField cardNumber;

    @FXML
    private TextField expirationDate;

    @FXML
    private TextField holderName;

    @FXML
    private TextField securityCode;


    private BikeRental bikeRental = new BikeRental();

    private Bike bike = new Bike();

    public void initializeBike(Bike bike) {
        this.bike = bike;
    }

    public void initializeBikeRental() {
        bikeRental.setUserID(1);
        bikeRental.setBikeID(bike.getBikeID());
        bikeRental.setBikeIsReturned(false);
        bikeRental.setBatteryStatus(bike.getBattery());
        bikeRental.setStartRental(new Date(System.currentTimeMillis()));
        bikeRental.setTimeRented(0);
    }

    @FXML
    void payBtn(ActionEvent event) throws IOException {

        String contents = "pay order";
        PaymentController ctrl = new PaymentController();
        Map<String, String> response = ctrl.payOrder(Utils.calDeposit(bike), contents, cardNumber.getText(), holderName.getText(),
                expirationDate.getText(), securityCode.getText());
//        System.out.println(response.get("RESULT") +" "+response.get("MESSAGE"));

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("PAYMENT SUCCESSFUL!");
        alert.setContentText("Tiền đặt cọc: "+Utils.calDeposit(bike)+" VND");
        alert.showAndWait();

        Stage stage = (Stage) payBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/fxml/renting_bike_info.fxml"));
        Parent parent = loader.load();

        //khoi tao thong tin thue xe
        initializeBikeRental();

        RentingBikeInfoScreenHandler rentingBikeInfoScreenHandler = (RentingBikeInfoScreenHandler) loader.getController();
        rentingBikeInfoScreenHandler.initializeBike(bikeRental, bike);
        rentingBikeInfoScreenHandler.initializeText();

        stage.setScene(new Scene(parent));
        stage.show();
    }

}
