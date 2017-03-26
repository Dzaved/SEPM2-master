package sample.service;

import sample.dao.impl.JDBCBoxDAO;
import sample.dto.BoxSearch;
import sample.entities.Box;
import sample.service.impl.ServiceException;

import java.util.ArrayList;
import java.util.List;


public interface BoxService {



    /**
     * Inserts a new Box.
     *
     * @param box the box to be inserted
     * @return the inserted box
     * @throws ServiceException if the box is null or at least one of the attributes of the box are null
     */
    Box insertBox(Box box) throws ServiceException;

    /**
     * Searches boxes that have parameters between the two boxes
     *
     * @param from the first box that is selected
     * @param to the second box that is selected
     * @return a list with the boxes that fulfill the given conditions
     * @throws ServiceException if any of the boxes are null or at least one of the attributes of the boxes are null
     */
    List<Box> search(Box from, Box to) throws ServiceException;

    /**
     * Replaces an old box with a new one.
     *
     * @param box1 is the new Box that will be added
     * @param box2 is the old Box that will be replaced
     * @throws ServiceException if any of the boxes are null or at least one of the attributes of the boxes are null
     */
    void update(Box box1, Box box2) throws ServiceException;

    /**
     * Gets all the boxes
     *
     * @return all the boxes in a List
     * @throws ServiceException if the list contains elements that are not compatible
     */
    List<Box> selectAll() throws ServiceException;

    /**
     * Deletes a box
     *
     * @param box the box that will be deleted
     * @throws ServiceException is the box is null or at least one of its parameters is null
     */
    void delete(Box box) throws ServiceException;

    /**
     * Checks if the given boxsearch is correct, verifies and validates the inputs
     * and shows through alert dialogs the type of the input mistake
     *
     * @param boxSearch the boxsearch whose parameters will be checked
     * @return a value that tells us if the search was valid (had valid parameters) or not
     */
    boolean filterValidation(BoxSearch boxSearch);

    /**
     * Takes every string of the given boxSearch and replaces them with the minimum and maximum set values (0L, 1000L)
     * if the string is empty. Then passes the parameters to the boxDAO where the actual filtering is being executed
     *
     * @param boxSearch the boxSearch whose parameters will be set and passed forward to the dao layer
     * @return an array of boxes that fulfill the given conditions
     */
    ArrayList<Box> filter(BoxSearch boxSearch);

}
