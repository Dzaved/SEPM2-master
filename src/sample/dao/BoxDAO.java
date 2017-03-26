package sample.dao;

import sample.dao.impl.DAOException;
import sample.entities.Box;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by David on 15-Mar-17.
 */
public interface BoxDAO {

    void createBoxTable();

    /**
     * Inserts a new box.
     * @param box the box to be inserted
     * @return the created box if no errors occurred.
     * @throws DAOException if the box couldn't be inserted.
     */
    Box insert(Box box);


    /**
     * Gets a box
     *
     * @param id the id based on which the box is identified
     * @return the box with the specific id
     * @throws DAOException if the box couldn't be gotten
     */
    Box getBox(int id) ;

    /**
     * Gets all the boxes
     *
     * @return all the boxes in a List
     * @throws DAOException if the list contains elements that are not compatible
     */
    List<Box> selectAll();

    /**
     *Gets a list of boxes with elements that fulfill the given filter conditions
     *
     * @param fromPrice  fromPrice value specified and chosen by the user
     * @param toPrice toPrice value specified and chosen by the user
     * @param fromBoxSize fromBoxize value specified and chosen by the user
     * @param toBoxSize toBoxSize value specified and chosen by the user
     * @param fromFoodQuantity fromFoodQuantity value specified and chosen by the user
     * @param toFoodQuantity toFoodQuantity value specified and chosen by the user
     * @param priceCheckboxState priceCheckboxState value specified and chosen by the user
     * @param boxSizeCheckboxState boxSizeCheckboxState value specified and chosen by the user
     * @param foodQuantityCheckboxState foodQuantityCheckboxState value specified and chosen by the user
     * @return a list of boxes that fulfill the given conditions
     * @throws DAOException if there is any problem with the retrieval of any of the sought values
     */
    ArrayList<Box> filter(long fromPrice, long toPrice, long fromBoxSize, long toBoxSize, long fromFoodQuantity, long toFoodQuantity, boolean priceCheckboxState, boolean boxSizeCheckboxState, boolean foodQuantityCheckboxState);

    /**
     *
     * @param box1 the search is made from box1's parameters
     * @param box2 the search is made until box2's parameters
     * @return a list of boxes with the specified parameters
     * @throws DAOException if there is any problem with the retrieval of any of the sought values
     */
    List<Box> search(Box box1, Box box2) throws DAOException;

    /**
     * Replaces an old box with a new one.
     *
     * @param box1 is the new Box that will be added
     * @param box2 is the old Box that will be replaced
     * @throws DAOException if there is any problem with the retrieval of any of the sought values
     */
    void update(Box box1, Box box2);

    /**
     * Deletes a box.
     *
     * @param box is the box that will be deleted
     */
    void delete(Box box);


}
