package sample.dao;

import sample.dao.impl.DAOException;
import sample.entities.Reservierung;

import java.util.List;

/**
 * Created by David on 21-Mar-17.
 */
public interface ReservierungDAO {


    void createReservierungTable();

    /**
     * Inserts a new reservation.
     *
     * @param reservierung the reservation to be inserted
     * @return the created reservation if no errors occurred.
     * @throws DAOException if the reservation couldn't be inserted.
     */
    void insert(Reservierung reservierung);


    /**
     * Gets a reservation.
     *
     * @param id the id based on which the reservation is identified
     * @return the reservation with the specific id
     * @throws DAOException if the reservation couldn't be gotten
     */
    Reservierung getReservierung(int id);

    /**
     * Gets all reservations.
     *
     * @return gets all the reservations in a List
     * @throws DAOException if the list contains elements that are not compatible
     */
    List<Reservierung> selectAll();

    /**
     * Updates a reservation with a specific id.
     *
     * @param reservierung is the new reservation that will be added
     * @param id           is the id where the new reservation will replace the old one
     * @throws DAOException if there is any problem with the retrieval of any of the sought values
     */
    void update(Reservierung reservierung, int id);


    /**
     * Deletes a reservation
     *
     * @param id is the id where the reservation will be deleted
     */
    void delete(int id);

}
