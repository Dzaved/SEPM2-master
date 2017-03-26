package sample.gui;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sample.Main;
import sample.dto.BoxSearch;
import sample.entities.Box;
import sample.entities.Reservierung;
import sample.service.BoxService;
import sample.service.ReservierungService;
import sample.service.impl.ReservierungException;
import sample.service.impl.ServiceException;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;


public class BoxViewController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BoxViewController.class);

    private BoxService boxService;
    private ReservierungService reservierungService;

    private ObservableList<Box> data;

    private ObservableList<Reservierung> dataRes;

    @FXML
    private TableView<Box> boxTable = new TableView<>();
    @FXML
    private TableView<Reservierung> resTable = new TableView<>();
    @FXML
    private TableColumn<Box, String> boxPriceColumn, boxSizeColumn, boxFoodQuantityColumn, nameClientColumn;
    @FXML
    private TableColumn<Box, Boolean> boxHasWindowsColumn, boxIsOutsideColumn;
    @FXML
    private TableColumn<Box, Date> beginDateColumn, endDateColumn;
    @FXML
    private CheckBox priceCheckBox, boxSizeCheckBox, foodQuantityCheckBox;
    @FXML
    private TextField fromPriceTextField;
    @FXML
    private TextField toPriceTextField;
    @FXML
    private TextField fromBoxSizeTextField;
    @FXML
    private TextField toBoxSizeTextField;
    @FXML
    private TextField fromFoodQuantityTextField;
    @FXML
    private TextField toFoodQuantityTextField;
    @FXML
    private ImageView boxImageView;

    @FXML
    private void addBox() throws IOException {
        Main.showAddBoxStage();
    }

    public void priceCheckBoxClicked() {
        if (priceCheckBox.isSelected()) {
            fromPriceTextField.setDisable(false);
            toPriceTextField.setDisable(false);
        } else {
            fromPriceTextField.setDisable(true);
            toPriceTextField.setDisable(true);
            fromPriceTextField.clear();
            toPriceTextField.clear();
        }
    }

    public void boxSizeCheckBoxClicked() {
        if (boxSizeCheckBox.isSelected()) {
            fromBoxSizeTextField.setDisable(false);
            toBoxSizeTextField.setDisable(false);
        } else {
            fromBoxSizeTextField.setDisable(true);
            toBoxSizeTextField.setDisable(true);
            fromBoxSizeTextField.clear();
            toBoxSizeTextField.clear();
        }
    }

    public void foodQuantityCheckBoxClicked() {
        if (foodQuantityCheckBox.isSelected()) {
            fromFoodQuantityTextField.setDisable(false);
            toFoodQuantityTextField.setDisable(false);
        } else {
            fromFoodQuantityTextField.setDisable(true);
            toFoodQuantityTextField.setDisable(true);
            fromFoodQuantityTextField.clear();
            toFoodQuantityTextField.clear();
        }
    }

    public void searchButtonClicked() {

        LOGGER.info("Logger clicked 'search'");
        BoxSearch boxSearch = new BoxSearch(fromPriceTextField, toPriceTextField, fromBoxSizeTextField, toBoxSizeTextField, fromFoodQuantityTextField, toFoodQuantityTextField, priceCheckBox, boxSizeCheckBox, foodQuantityCheckBox);

        if (boxService.filterValidation(boxSearch)) {
            ArrayList<Box> list = boxService.filter(boxSearch);
            System.out.println(list.size());
            boxTable.setItems(FXCollections.observableArrayList(list));
        }
    }

    public Optional showAlertDialog(String title, String headerText, String contentText, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        return alert.showAndWait();
    }

    public void initialize(BoxService boxService, ReservierungService reservierungService) throws ServiceException, ReservierungException {

        this.boxService = boxService;
        data = FXCollections.observableArrayList(boxService.selectAll());

        boxPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        boxSizeColumn.setCellValueFactory(new PropertyValueFactory<Box, String>("boxSize"));
        boxFoodQuantityColumn.setCellValueFactory(new PropertyValueFactory<Box, String>("food_quantity"));
        boxHasWindowsColumn.setCellValueFactory(new PropertyValueFactory<Box, Boolean>("has_windows"));
        boxIsOutsideColumn.setCellValueFactory(new PropertyValueFactory<Box, Boolean>("is_outside"));
        boxTable.setItems(data);
        boxTable.getSelectionModel().selectFirst();
        LOGGER.info("BoxTable initialized.");


        this.reservierungService = reservierungService;
        dataRes = FXCollections.observableArrayList(reservierungService.selectAll());

        nameClientColumn.setCellValueFactory(new PropertyValueFactory<Box, String>("nameClient"));
        beginDateColumn.setCellValueFactory(new PropertyValueFactory<Box, Date>("beginDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<Box, Date>("endDate"));
        resTable.setItems(dataRes);
        resTable.getSelectionModel().selectFirst();
        LOGGER.info("ResTable initialized.");

    }

}
