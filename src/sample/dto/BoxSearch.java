package sample.dto;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

/**
 * Created by David on 25-Mar-17.
 */

/**
 * This class represents a data transfer object that holds the textfields and the checkboxes from the Advanced Search section.
 */
public class BoxSearch {

    TextField fromPriceTextField;
    TextField toPriceTextfield;
    TextField fromBoxSizeTextfield;
    TextField toBoxSizeTextfield;
    TextField fromFoodQuantityTextfield;
    TextField toFoodQuantityTextfield;

    CheckBox priceCheckBox;
    CheckBox boxSizeCheckBox;
    CheckBox foodQuantityCheckBox;

    public BoxSearch(TextField fromPriceTextField, TextField toPriceTextfield, TextField fromBoxSizeTextfield, TextField toBoxSizeTextfield, TextField fromFoodQuantityTextfield, TextField toFoodQuantityTextfield, CheckBox priceCheckBox, CheckBox boxSizeCheckBox, CheckBox foodQuantityCheckBox) {
        this.fromPriceTextField = fromPriceTextField;
        this.toPriceTextfield = toPriceTextfield;
        this.fromBoxSizeTextfield = fromBoxSizeTextfield;
        this.toBoxSizeTextfield = toBoxSizeTextfield;
        this.fromFoodQuantityTextfield = fromFoodQuantityTextfield;
        this.toFoodQuantityTextfield = toFoodQuantityTextfield;
        this.priceCheckBox = priceCheckBox;
        this.boxSizeCheckBox = boxSizeCheckBox;
        this.foodQuantityCheckBox = foodQuantityCheckBox;
    }

    public TextField getFromPriceTextField() {
        return fromPriceTextField;
    }

    public void setFromPriceTextField(TextField fromPriceTextField) {
        this.fromPriceTextField = fromPriceTextField;
    }

    public TextField getToPriceTextfield() {
        return toPriceTextfield;
    }

    public void setToPriceTextfield(TextField toPriceTextfield) {
        this.toPriceTextfield = toPriceTextfield;
    }

    public TextField getFromBoxSizeTextfield() {
        return fromBoxSizeTextfield;
    }

    public void setFromBoxSizeTextfield(TextField fromBoxSizeTextfield) {
        this.fromBoxSizeTextfield = fromBoxSizeTextfield;
    }

    public TextField getToBoxSizeTextfield() {
        return toBoxSizeTextfield;
    }

    public void setToBoxSizeTextfield(TextField toBoxSizeTextfield) {
        this.toBoxSizeTextfield = toBoxSizeTextfield;
    }

    public TextField getFromFoodQuantityTextfield() {
        return fromFoodQuantityTextfield;
    }

    public void setFromFoodQuantityTextfield(TextField fromFoodQuantityTextfield) {
        this.fromFoodQuantityTextfield = fromFoodQuantityTextfield;
    }

    public TextField getToFoodQuantityTextfield() {
        return toFoodQuantityTextfield;
    }

    public void setToFoodQuantityTextfield(TextField toFoodQuantityTextfield) {
        this.toFoodQuantityTextfield = toFoodQuantityTextfield;
    }

    public CheckBox getPriceCheckBox() {
        return priceCheckBox;
    }

    public void setPriceCheckBox(CheckBox priceCheckBox) {
        this.priceCheckBox = priceCheckBox;
    }

    public CheckBox getBoxSizeCheckBox() {
        return boxSizeCheckBox;
    }

    public void setBoxSizeCheckBox(CheckBox boxSizeCheckBox) {
        this.boxSizeCheckBox = boxSizeCheckBox;
    }

    public CheckBox getFoodQuantityCheckBox() {
        return foodQuantityCheckBox;
    }

    public void setFoodQuantityCheckBox(CheckBox foodQuantityCheckBox) {
        this.foodQuantityCheckBox = foodQuantityCheckBox;
    }
}
