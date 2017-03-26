package sample.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sample.Main;
import sample.entities.Box;
import sample.service.BoxService;
import sample.service.impl.BoxServiceImpl;
import sample.service.impl.ServiceException;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;



public class CreateBoxViewController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateBoxViewController.class);
    private BoxViewController boxViewController;
    private BoxService boxService = new BoxServiceImpl();
    ;
    private TableView<Box> boxTable;
    private Box box = new Box();
    private Stage stage = new Stage();


    @FXML
    private TextField priceTextField, boxSIzeTextField, foodQuantityTextField, pictureTextField;
    @FXML
    private CheckBox withWindows, isOutside;
    @FXML
    private Label priceLabel, boxSizeLabel, foodQuantityLabel;
    @FXML
    private Button okButton;

    public CreateBoxViewController() throws ServiceException {
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setBox(Box box) {
        this.box = box;
    }
    @FXML
    public void onBrowseButtonClicked() {
        LOGGER.info("User clicked 'Browse...'");
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choose picture");
        FileChooser.ExtensionFilter allowedFileTypes = new FileChooser.ExtensionFilter("Pictures (*.jpg;*.jpeg;*.png)", "*.jpg", "*.jpeg", "*.png");
        chooser.getExtensionFilters().add(allowedFileTypes);
        File file = chooser.showOpenDialog(stage);
        if (file != null) {
            LOGGER.info("User chose file {}", file.getAbsolutePath());
            pictureTextField.setText(file.getAbsolutePath());
        }
    }
    @FXML
    public void okButtonClicked() {
        LOGGER.info("User clicked 'Ok'.");


        box.setPrice(Long.valueOf(priceTextField.getText()));
        box.setBoxSize(Long.valueOf(boxSIzeTextField.getText()));
        box.setFood_quantity(Long.valueOf(foodQuantityTextField.getText()));
        box.setHas_windows(withWindows.isSelected());
        box.setIs_outside(isOutside.isSelected());
        box.setImage(pictureTextField.getText());
        box.setDeleted(false);

        try {

            Box b = boxService.insertBox(box);
            showAsddBoxStage();
            boxTable.getItems().add(b);
            boxTable.getSelectionModel().selectLast();

            stage.close();
             boxViewController.showAlertDialog("Create box", "", "Box created successfully.", Alert.AlertType.INFORMATION);
        } catch (ServiceException e) {
              boxViewController.showAlertDialog("Create box", "Couldn't create a new box.", e.getMessage(), Alert.AlertType.WARNING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void onCancelButtonClicked() {
        LOGGER.info("User clicked 'Cancel'.");
        stage.close();
    }

    public static void showAsddBoxStage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("BoxView.fxml"));
        AnchorPane addNewBox = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Add new Box");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(stage);

        Scene scene = new Scene(addNewBox);
        stage.setScene(scene);
        stage.showAndWait();
    }


}
