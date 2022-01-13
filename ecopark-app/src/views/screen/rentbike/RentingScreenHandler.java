package views.screen.rentbike;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

import controller.RentBikeController;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import entity.Bike;

public class RentingScreenHandler {
	@FXML
    public Pane mainPane;

	@FXML
	private ImageView imageBike;

    @FXML
    private TableView<Bike> tableBikeInfo;
    
    @FXML
    private TableColumn<Bike, Integer> col_bikeID;

    @FXML
    private TableColumn<Bike, String> col_bikeType;
    
    @FXML
    private TableColumn<Bike, String> col_battery;
    
    @FXML
    private TableColumn<Bike, String> col_lisencePlate;

    @FXML
    private TextField textBikeCode;
    
    @FXML
    private Text textDockName;

	private ObservableList<Bike> tableOblist;
	private RentBikeController rentBikeController = new RentBikeController();
    

    @FXML
    void onClickWatch(ActionEvent event) {
    	int searchID = Integer.valueOf(textBikeCode.getText());
    	tableBikeInfo.getItems().stream()
        .filter(item -> item.getBikeID() == searchID)
        .findAny()
        .ifPresent(item -> {
            tableBikeInfo.getSelectionModel().select(item);
            tableBikeInfo.scrollTo(item);
        });
    }
    
    @FXML
    void onClickRentBikeBtn(ActionEvent event) throws IOException {
    	
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setHeaderText("Bạn có muốn thuê xe không?");
    	Optional<ButtonType> result = alert.showAndWait();
    	if(result.get() == ButtonType.OK) {
    		Stage stage = (Stage) mainPane.getScene().getWindow();
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/fxml/payment_screen.fxml"));
    		Parent parent = loader.load();

    		Bike bike = tableBikeInfo.getSelectionModel().getSelectedItem();
			if(bike == null){
				Alert alertBike = new Alert(AlertType.WARNING);
				alertBike.setHeaderText("Chọn một xe để tiếp tục");
				alertBike.showAndWait();
			}else {
				PaymentScreenHandler paymentScreenHandler = (PaymentScreenHandler) loader.getController();
				paymentScreenHandler.initializeBike(bike);

				stage.setScene(new Scene(parent));
				stage.show();
			}
    	}
    	
    }
    
    @FXML
    void handleRow(MouseEvent event) throws IOException {
		if(event.getClickCount() == 1){
			Bike bike = tableBikeInfo.getSelectionModel().getSelectedItem();
			File file = new File("assets/images/");
			Image image = new Image("file://"+file.getAbsolutePath()+"/"+bike.getImage());
			imageBike.setImage(image);
		}

    	if(event.getClickCount() == 2 && tableBikeInfo.getSelectionModel().getSelectedItem() != null) {

			Bike bike = tableBikeInfo.getSelectionModel().getSelectedItem();

    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/fxml/bike_detail.fxml"));
    		Parent parent = loader.load();
    		
    		BikeInfoHandler bikeInfoHandler = (BikeInfoHandler) loader.getController();
    		bikeInfoHandler.initializeTextField(bike);
    		
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
    	}
    }

	@FXML
	void onClickBack(ActionEvent event) throws IOException {
		Stage stage = (Stage) mainPane.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/fxml/dock_info.fxml"));
		Parent parent = loader.load();

		stage.setScene(new Scene(parent));
		stage.show();
	}

	public void initializeDock(Dock dock){
		textDockName.setText(dock.getDockName());

		col_lisencePlate.setCellValueFactory(new PropertyValueFactory<>("licensePlate"));
		col_battery.setCellValueFactory(new PropertyValueFactory<>("battery"));
		col_bikeID.setCellValueFactory(new PropertyValueFactory<>("bikeID"));
		col_bikeType.setCellValueFactory(new PropertyValueFactory<>("bikeType"));

		try {
			List<Bike> list = rentBikeController.getBikeFromDock(textDockName.getText());
			tableOblist = FXCollections.observableArrayList(list);
			tableBikeInfo.setItems(tableOblist);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
