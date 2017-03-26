package sample.service.impl;

import com.sun.deploy.security.ValidationState;
import javafx.scene.control.Alert;
import jdk.nashorn.internal.runtime.ECMAErrors;
import sample.dao.BoxDAO;
import sample.dao.impl.DAOException;
import sample.dao.impl.JDBCBoxDAO;
import sample.dto.BoxSearch;
import sample.entities.Box;
import sample.gui.BoxViewController;
import sample.service.BoxService;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BoxServiceImpl implements BoxService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BoxServiceImpl.class);
    private BoxDAO boxDAO;
    private BoxViewController boxViewController;


    public BoxServiceImpl() throws ServiceException {
        this.boxDAO = new JDBCBoxDAO();
        this.boxViewController = new BoxViewController();

        LOGGER.info("Service started.");
    }

    @Override
    public Box insertBox(Box box) throws ServiceException {
        LOGGER.debug("Entering insertBox method.");
        validateBox(box);
        return boxDAO.insert(box);
    }

    @Override
    public List<Box> search(Box from, Box to) throws ServiceException {
        LOGGER.debug("Entering search method.");
        validateBox(from);
        validateBox(to);
        try {
            return boxDAO.search(from, to);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Box> selectAll() throws ServiceException {
        LOGGER.debug("Entering selectAll method.");
        return boxDAO.selectAll();
    }

    @Override
    public void update(Box box1, Box box2) throws ServiceException {
        LOGGER.debug("Entering update method.");
        validateBox(box1);
        validateBox(box2);
        boxDAO.update(box1, box2);
    }

    @Override
    public void delete(Box box) throws ServiceException {
        LOGGER.debug("Entering delete method.");
        boxDAO.delete(box);
    }

    @Override
    public boolean filterValidation(BoxSearch boxSearch) {

        int globalEmptyFieldsCounter = 0;
        int globalForeignSymbolsCoutner = 0;

        boolean priceCheckboxSelected = boxSearch.getPriceCheckBox().isSelected();
        boolean boxSizeCheckboxSelected = boxSearch.getBoxSizeCheckBox().isSelected();
        boolean foodQuantityCheckboxSelected = boxSearch.getFoodQuantityCheckBox().isSelected();


        if (priceCheckboxSelected) {


            int counterValid = 0;
            int counterError = 0;

            boolean a = boxSearch.getFromPriceTextField().getText().isEmpty();
            boolean b = boxSearch.getToPriceTextfield().getText().isEmpty();

            if (a || b) {
                if (!a) {
                    try {
                        Long.parseLong(boxSearch.getFromPriceTextField().getText());
                        counterValid++;
                    } catch (NumberFormatException ex) {
                        boxViewController.showAlertDialog("Input error", "", "Wrong data type entered!", Alert.AlertType.ERROR);
                        boxSearch.getFromPriceTextField().clear();
                        globalForeignSymbolsCoutner++;
                    }
                }
                if (!b) {
                    try {
                        Long.parseLong(boxSearch.getToPriceTextfield().getText());
                        counterValid++;
                    } catch (NumberFormatException ex) {
                        boxViewController.showAlertDialog("Input error", "", "Wrong data type entered!", Alert.AlertType.ERROR);
                        boxSearch.getToPriceTextfield().clear();
                        globalForeignSymbolsCoutner++;
                    }
                }
                if (counterValid == 1 || a && b) {
                    boxViewController.showAlertDialog("Input error", "", "One or more fields empty", Alert.AlertType.ERROR);
                    globalEmptyFieldsCounter++;
                }
            } else {
                try {
                    Long.parseLong(boxSearch.getFromPriceTextField().getText());
                } catch (Exception e) {
                    boxViewController.showAlertDialog("Input error", "", "Wrong data type entered!", Alert.AlertType.ERROR);
                    boxSearch.getFromPriceTextField().clear();
                    counterError++;
                    globalForeignSymbolsCoutner++;
                }

                try {
                    Long.parseLong(boxSearch.getToPriceTextfield().getText());
                } catch (Exception e) {
                    if (counterError == 1) {
                        boxSearch.getToPriceTextfield().clear();
                    } else {
                        boxViewController.showAlertDialog("Input error", "", "Wrong data type entered!", Alert.AlertType.ERROR);
                        boxSearch.getToPriceTextfield().clear();
                        globalForeignSymbolsCoutner++;
                    }
                }
            }
        }


        globalForeignSymbolsCoutner = 0;

        if (boxSizeCheckboxSelected) {


            int counterValid = 0;
            int counterError = 0;

            boolean a = boxSearch.getFromBoxSizeTextfield().getText().isEmpty();
            boolean b = boxSearch.getToBoxSizeTextfield().getText().isEmpty();

            if (a || b) {
                if (!a) {
                    try {
                        Long.parseLong(boxSearch.getFromBoxSizeTextfield().getText());
                        counterValid++;
                    } catch (NumberFormatException ex) {
                        if (globalForeignSymbolsCoutner != 1) {
                            boxViewController.showAlertDialog("Input error", "", "Wrong data type entered!", Alert.AlertType.ERROR);
                            boxSearch.getFromBoxSizeTextfield().clear();
                            globalForeignSymbolsCoutner++;
                        } else {
                            boxSearch.getFromBoxSizeTextfield().clear();
                        }
                    }
                }
                if (!b) {
                    try {
                        Long.parseLong(boxSearch.getToBoxSizeTextfield().getText());
                        counterValid++;
                    } catch (NumberFormatException ex) {
                        if (globalForeignSymbolsCoutner != 1) {
                            boxViewController.showAlertDialog("Input error", "", "Wrong data type entered!", Alert.AlertType.ERROR);
                            boxSearch.getToBoxSizeTextfield().clear();
                            globalForeignSymbolsCoutner++;
                        } else {
                            boxSearch.getToBoxSizeTextfield().clear();
                        }
                    }
                }
                if (counterValid == 1 || a && b) {
                    if (globalEmptyFieldsCounter != 1) {
                        boxViewController.showAlertDialog("Input error", "", "One or more fields empty", Alert.AlertType.ERROR);
                        globalEmptyFieldsCounter++;
                    }
                }
            } else {
                try {
                    Long.parseLong(boxSearch.getFromBoxSizeTextfield().getText());
                } catch (Exception e) {
                    if (globalForeignSymbolsCoutner != 1) {
                        boxViewController.showAlertDialog("Input error", "", "Wrong data type entered!", Alert.AlertType.ERROR);
                        boxSearch.getFromBoxSizeTextfield().clear();
                        globalForeignSymbolsCoutner++;
                        counterError++;
                    } else {
                        boxViewController.showAlertDialog("Input error", "", "Wrong data type entered!", Alert.AlertType.ERROR);
                        counterError++;
                    }
                }

                try {
                    Long.parseLong(boxSearch.getToBoxSizeTextfield().getText());
                } catch (Exception e) {
                    if (counterError == 1) {
                        if (globalForeignSymbolsCoutner == 1) {
                            boxSearch.getToBoxSizeTextfield().clear();
                        } else {
                            boxViewController.showAlertDialog("Input error", "", "Wrong data type entered!", Alert.AlertType.ERROR);
                            boxSearch.getToBoxSizeTextfield().clear();
                            globalForeignSymbolsCoutner++;
                        }
                    }
                }
            }
        }

        globalForeignSymbolsCoutner = 0;

        if (foodQuantityCheckboxSelected) {


            int counterValid = 0;
            int counterError = 0;

            boolean a = boxSearch.getFromFoodQuantityTextfield().getText().isEmpty();
            boolean b = boxSearch.getToFoodQuantityTextfield().getText().isEmpty();

            if (a || b) {
                if (!a) {
                    try {
                        Long.parseLong(boxSearch.getFromFoodQuantityTextfield().getText());
                        counterValid++;
                    } catch (NumberFormatException ex) {
                        if (globalForeignSymbolsCoutner != 1) {
                            boxViewController.showAlertDialog("Input error", "", "Wrong data type entered!", Alert.AlertType.ERROR);
                            boxSearch.getFromFoodQuantityTextfield().clear();
                            globalForeignSymbolsCoutner++;
                        }
                    }
                }
                if (!b) {
                    try {
                        Long.parseLong(boxSearch.getToFoodQuantityTextfield().getText());
                        counterValid++;
                    } catch (NumberFormatException ex) {
                        if (globalForeignSymbolsCoutner != 1) {
                            boxViewController.showAlertDialog("Input error", "", "Wrong data type entered!", Alert.AlertType.ERROR);
                            boxSearch.getToFoodQuantityTextfield().clear();
                            globalForeignSymbolsCoutner++;
                        }
                    }
                }
                if (counterValid == 1 || a && b) {
                    if (globalEmptyFieldsCounter != 1) {
                        boxViewController.showAlertDialog("Input error", "", "One or more fields empty", Alert.AlertType.ERROR);
                        globalEmptyFieldsCounter++;
                    }
                }
            } else {
                try {
                    Long.parseLong(boxSearch.getFromFoodQuantityTextfield().getText());
                } catch (Exception e) {
                    if (globalForeignSymbolsCoutner != 1) {
                        boxViewController.showAlertDialog("Input error", "", "Wrong data type entered!", Alert.AlertType.ERROR);
                        boxSearch.getFromFoodQuantityTextfield().clear();
                        counterError++;
                        globalForeignSymbolsCoutner++;
                    }
                }

                try {
                    Long.parseLong(boxSearch.getToFoodQuantityTextfield().getText());
                } catch (Exception e) {
                    if (counterError == 1) {
                        if (globalForeignSymbolsCoutner == 1) {
                            boxSearch.getToFoodQuantityTextfield().clear();
                        } else {
                            boxViewController.showAlertDialog("Input error", "", "Wrong data type entered!", Alert.AlertType.ERROR);
                            boxSearch.getToFoodQuantityTextfield().clear();
                            globalForeignSymbolsCoutner++;
                        }
                    }
                }
            }
        }

        if (!priceCheckboxSelected && !boxSizeCheckboxSelected && !foodQuantityCheckboxSelected) {
            boxViewController.showAlertDialog("Selection Error", "", "None of the selection options are chosen!", Alert.AlertType.ERROR);
        }
        return true;
    }

    @Override
    public ArrayList<Box> filter(BoxSearch boxSearch) {

        Long priceFrom = 0L;
        try {
            priceFrom = Long.parseLong(boxSearch.getFromPriceTextField().getText());
        } catch (NumberFormatException ex) {
            LOGGER.info("PriceFrom set to 0");
        }
        Long priceTo = 1000L;
        try {
            priceTo = Long.parseLong(boxSearch.getToPriceTextfield().getText());
        } catch (NumberFormatException ex) {
            LOGGER.info("PriceTo set to 1000");
        }
        Long sizeFrom = 0L;
        try {
            sizeFrom = Long.parseLong(boxSearch.getFromBoxSizeTextfield().getText());
        } catch (NumberFormatException ex) {
            LOGGER.info("BoxSizeFrom set to 0");
        }
        Long sizeTo = 1000L;
        try {
            sizeTo = Long.parseLong(boxSearch.getToBoxSizeTextfield().getText());
        } catch (NumberFormatException ex) {
            LOGGER.info("BoxSizeTo set to 1000.");
        }
        Long foodQuantityFrom = 0L;
        try {
            foodQuantityFrom = Long.parseLong(boxSearch.getFromFoodQuantityTextfield().getText());
        } catch (NumberFormatException ex) {
            LOGGER.info("FoodQuantityFrom set to 0.");
        }
        Long foodQuantityTo = 1000L;
        try {
            foodQuantityTo = Long.parseLong(boxSearch.getToFoodQuantityTextfield().getText());
        } catch (NumberFormatException ex) {
            LOGGER.info("FoodQuantityTo set to 1000.");
        }
        Boolean priceCheckbox = boxSearch.getPriceCheckBox().isSelected();
        Boolean boxSizeCheckbox = boxSearch.getBoxSizeCheckBox().isSelected();
        Boolean foodQuantityCheckbox = boxSearch.getFoodQuantityCheckBox().isSelected();


        ArrayList<Box> arrayList = boxDAO.filter(priceFrom, priceTo, sizeFrom, sizeTo, foodQuantityFrom, foodQuantityTo, priceCheckbox, boxSizeCheckbox, foodQuantityCheckbox);

        return arrayList;
    }

    public void validateBox(Box box) throws ServiceException {
        LOGGER.debug("Validating box: ", box);

        if (box == null) {
            LOGGER.debug("Box is null.");
            throw new ServiceException("Box cannot be empty.");
        }

        if (box.getPrice() == null) {
            LOGGER.debug("Price is null");
            throw new ServiceException("Price cannot be empty.");
        }

        if (box.getBoxSize() == null) {
            LOGGER.debug("BoxSize is null");
            throw new ServiceException("BoxSize cannot be empty.");
        }

        if (box.getFood_quantity() == null) {
            LOGGER.debug("Food_quantity is null");
            throw new ServiceException("Food_quantity cannot be empty.");
        }

        if (box.isHas_windows() == null) {
            LOGGER.debug("has_windows is null.");
            throw new ServiceException("has_windows canoot be empty");
        }

        if (box.isIs_outside() == null) {
            LOGGER.debug("is_outside is null");
            throw new ServiceException("is_outside cannot be empty");
        }

        if (box.getImage() == null || box.getImage().isEmpty()) {
            LOGGER.warn("Image path: '{}'.");
            throw new ServiceException("Image path cannot be empty");
        }

        LOGGER.debug("Box validation successful.");

    }

}
