package views.screen.returnbike;

import controller.PaymentController;
import controller.ReturnBikeController;
import controller.ViewDockController;
import entity.Bike;
import entity.BikeRental;
import entity.Dock;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.Utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class ReturnBikeScreenHandler implements Initializable {

    @FXML
    private Text bikeTypeText;

    @FXML
    private TableColumn<Dock, String> col_dock;

    @FXML
    private ImageView dockImage;

    @FXML
    private TableView<Dock> dockTable;

    @FXML
    private Text lisencePlateText;

    @FXML
    private Button payButton;

    @FXML
    private Text priceText;

    @FXML
    private Text returnTimeText;

    @FXML
    private Text startRentalText;

    @FXML
    private Text timeRentedText;

    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
    private ViewDockController viewDockController = new ViewDockController();
    private ObservableList tableOblist;
    private Bike bike;
    private BikeRental bikeRental;
    private ReturnBikeController returnBikeController = new ReturnBikeController();

    public void initializeBike(BikeRental bikeRental, Bike bike){
        this.bike = bike;
        this.bikeRental = bikeRental;

        bikeTypeText.setText(bike.getBikeType());
        lisencePlateText.setText(bike.getLicensePlate());
        startRentalText.setText(formatter.format(bikeRental.getStartRental()));
        returnTimeText.setText(formatter.format(System.currentTimeMillis()));
        int time = bikeRental.getTimeRented();
        timeRentedText.setText(Long.toString(time/3600) + " giờ " + Long.toString(time%3600/60)+" phút "+Long.toString(time%3600%60)+"s");
        priceText.setText(String.valueOf(Utils.calculatePrice(bikeRental, bike)) + " VND");
    }

    @FXML
    void onClickPayBtn(ActionEvent event) throws IOException, SQLException {
        returnBikeController.updateDockBike(dockTable.getSelectionModel().getSelectedItem(), bike);

        String contents = "pay order";
        PaymentController ctrl = new PaymentController();
        Map<String, String> response = ctrl.payOrder((int) Utils.calculatePrice(bikeRental, bike), contents, "kscq1_group2_2021", "Group 2",
                "1125", "323");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("PAYMENT SUCCESSFUL!");
        alert.setContentText("Tiền cọc hoàn lại: "+Utils.calDeposit(bike)+" VND\n" +
                                "Tiền phí: "+priceText.getText());
        alert.showAndWait();

        Stage stage = (Stage) priceText.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/fxml/dock_info.fxml"));
        Parent parent = loader.load();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @FXML
    void handleRow(MouseEvent event) {
        if(event.getClickCount() == 1){
            Dock dock = dockTable.getSelectionModel().getSelectedItem();
            File file = new File("assets/images/");
            Image image = new Image("file://"+file.getAbsolutePath()+"/"+dock.getImage());
            dockImage.setImage(image);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        col_dock.setCellValueFactory(new PropertyValueFactory<>("dockName"));

        try {
            List<Dock> list = viewDockController.getDocks();
            tableOblist = FXCollections.observableArrayList(list);
            dockTable.setItems(tableOblist);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
