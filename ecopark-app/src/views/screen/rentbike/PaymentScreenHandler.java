package views.screen.rentbike;

import java.io.IOException;
import java.util.Date;

import entity.Bike;
import entity.BikeRental;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import views.screen.viewbikeinfo.RentingBikeInfoScreenHandler;

public class PaymentScreenHandler {

    @FXML
    private Button payBtn;

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
