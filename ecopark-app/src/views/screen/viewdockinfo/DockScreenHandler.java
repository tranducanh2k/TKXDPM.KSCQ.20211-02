package views.screen.viewdockinfo;

import controller.ViewDockController;
import entity.Bike;
import entity.Dock;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import views.screen.rentbike.RentingScreenHandler;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class DockScreenHandler  implements Initializable {

    @FXML
    private TableColumn<Dock, String> col_area;

    @FXML
    private TableColumn<Dock, String> col_dock;

    @FXML
    private TableColumn<Dock, String> col_location;

    @FXML
    private TableColumn<Dock, Integer> col_numberOfBike;

    @FXML
    private ImageView dockImage;

    @FXML
    private TableView<Dock> dockTable;

    private ViewDockController viewDockController = new ViewDockController();
    private ObservableList tableOblist;

    @FXML
    void handleRow(MouseEvent event) {
        if(event.getClickCount() == 1){
            Dock dock = dockTable.getSelectionModel().getSelectedItem();
            File file = new File("assets/images/");
            Image image = new Image("file://"+file.getAbsolutePath()+"/"+dock.getImage());
            dockImage.setImage(image);
        }
    }

    @FXML
    void onClickViewDock(ActionEvent event) throws IOException {
        Dock dock = dockTable.getSelectionModel().getSelectedItem();
        Stage stage = (Stage) dockTable.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/fxml/rent_bike.fxml"));
        Parent parent = loader.load();

        RentingScreenHandler rentingScreenHandler = (RentingScreenHandler) loader.getController();
        rentingScreenHandler.initializeDock(dock);

        stage.setScene(new Scene(parent));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        col_dock.setCellValueFactory(new PropertyValueFactory<>("dockName"));
        col_area.setCellValueFactory(new PropertyValueFactory<>("area"));
        col_location.setCellValueFactory(new PropertyValueFactory<>("location"));
        col_numberOfBike.setCellValueFactory(new PropertyValueFactory<>("numberOfBike"));

        try {
            List<Dock> list = viewDockController.getDocks();
            tableOblist = FXCollections.observableArrayList(list);
            dockTable.setItems(tableOblist);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}