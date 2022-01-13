package views.screen.viewbikeinfo;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import controller.RentBikeController;
import entity.Bike;
import entity.BikeRental;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Utils;
import views.screen.returnbike.ReturnBikeScreenHandler;

public class RentingBikeInfoScreenHandler {

    @FXML
    private TextField batteryText;

    @FXML
    private ImageView image;

    @FXML
    private TextField licensePlateText;

    @FXML
    private TextField priceText;

    @FXML
    private TextField bikeTypeText;

    @FXML
    private Button returnBikeBtn;

    @FXML
    private TextField startRentalText;

    @FXML
    private TextField timeRentedText;

    @FXML
    private TextField userIDText;

    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");

    private BikeRental bikeRental = new BikeRental();
    private Bike bike = new Bike();
    private RentBikeController rentBikeController = new RentBikeController();

    public void initializeBike(BikeRental bikeRental, Bike bike) {
        this.bike = bike;
        this.bikeRental = bikeRental;
    }

    @FXML
    void onClickReturnBikeBtn(ActionEvent event) throws IOException, SQLException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setHeaderText("Bạn có muốn trả xe không?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
            timer.stop();

            //luu vao database
            rentBikeController.addRental(bikeRental);
//          bikeRental.setRentalID(rentBikeController.findRental(bikeRental));

            //chuyen sang man tra xe
            Stage stage = (Stage) batteryText.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/fxml/return_bike.fxml"));
            Parent parent = loader.load();

            ReturnBikeScreenHandler returnBikeScreenHandler = (ReturnBikeScreenHandler) loader.getController();
            returnBikeScreenHandler.initializeBike(this.bikeRental, this.bike);

            stage.setScene(new Scene(parent));
            stage.show();
        }
    }

    public void initializeText() {
        userIDText.setText(String.valueOf(bikeRental.getUserID()));
        licensePlateText.setText(bike.getLicensePlate());
        startRentalText.setText(formatter.format(bikeRental.getStartRental()));
        batteryText.setText(bikeRental.getBatteryStatus());
        bikeTypeText.setText(bike.getBikeType());

        timer.start();
    }

    AnimationTimer timer = new AnimationTimer() {
        private long timestamp;
        private long time = 0;
        private long fraction = 0;

        @Override
        public void start() {
            // current time adjusted by remaining time from last run
            timestamp = System.currentTimeMillis() - fraction;
            super.start();
        }

        @Override
        public void stop() {
            super.stop();
            // save leftover time not handled with the last update
            fraction = System.currentTimeMillis() - timestamp;
        }

        @Override
        public void handle(long now) {
            long newTime = System.currentTimeMillis();
            if (timestamp + 1000 <= newTime) {
                long deltaT = (newTime - timestamp) / 1000;
                time += deltaT;
                timestamp += 1000 * deltaT;
                timeRentedText.setText(Long.toString(time/3600) + " giờ " + Long.toString(time%3600/60)+" phút "+Long.toString(time%3600%60)+"s");
                bikeRental.setTimeRented((int) time);
                priceText.setText(String.valueOf(Utils.calculatePrice(bikeRental, bike)) + " vnd");
            }
        }
    };

}