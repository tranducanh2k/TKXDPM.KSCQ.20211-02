package views.screen.rentbike;

import java.io.IOException;

import entity.Bike;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class BikeInfoHandler {	
	
	@FXML
    private Button rentBikeBtn;

    @FXML
    private Label textColor;

    @FXML
    private Label textPrice30Min;

    @FXML
    private Label textPriceEach15Min;
    
    private RentingScreenHandler rentingScreenHandler = new RentingScreenHandler();
    

    public void initializeTextField(Bike bike){
        if(bike.getBikeType() == "xe dien" || bike.getBikeType() == "xe dap doi")	{
        	textPrice30Min.setText("15000d");
        	textPriceEach15Min.setText("22500d");
        }else {
        	textPrice30Min.setText("10000d");
        	textPriceEach15Min.setText("15000d");
        }
    }
    
//    @FXML
//    void onClickRentBikeBtn(ActionEvent event) throws IOException {
//    	Stage stageBikeInfo = (Stage) textColor.getScene().getWindow();
//    	stageBikeInfo.close();
//    	
//    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/fxml/confirm_rent_bike.fxml"));
//    	Parent parent = loader.load();
//    	
//    	Stage stage = new Stage();
//    	
//    	stage.setScene(new Scene(parent));
//    	stage.show();
//    }
//    
//    @FXML
//    private Button cancelBtn;
//
//    @FXML
//    private Button confirmBtn;
//
//    @FXML
//    void cancelBtn(ActionEvent event) throws IOException {
//    	Stage stage = (Stage) cancelBtn.getScene().getWindow();
//    	stage.close();
//    }
//
//    @FXML
//    void onClickConfirmBtn(ActionEvent event) throws IOException {
//    	Stage stageConfirm = (Stage) cancelBtn.getScene().getWindow();
//    	stageConfirm.close();
//    	
//    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/fxml/payment_screen.fxml"));
//    	Parent parent = loader.load();
//    	
//    	
//    }
    
}
