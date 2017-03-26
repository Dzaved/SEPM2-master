package sample.service;

import sample.dao.impl.JDBCReservierungDAO;
import sample.entities.Reservierung;
import sample.service.impl.ReservierungException;

import java.util.List;


public interface ReservierungService {

    /**
     * Inserts a new reservation.
     *
     * @param reservierung the reservation to be inserted
     * @return the inserted reservation
     * @throws ReservierungException if the reservation is null or at least one of the attributes of the reservation are null
     */
    Reservierung insert(Reservierung reservierung) throws ReservierungException;

    /**
     * Replaces an old reservation with a new one.
     *
     * @param reservierung is the new reservation that will be added
     * @param id is the id where the reservation will be added and the old reservation from that id will be replaced
     * @throws ReservierungException if the reservation is null or at least one of the attributes of the reservation is null
     */
    void update(Reservierung reservierung, int id) throws ReservierungException;

    /**
     * Deletes a reservation
     *
     * @param id is the id where the reservation will be deleted
     * @throws ReservierungException is the reservation is null or at least one of its parameters is null
     */
    void delete(int id) throws  ReservierungException;

    /**
     * Gets all the reservations
     *
     * @return all the reservations in a List
     * @throws ReservierungException if the list contains elements that are not compatible
     */
    List<Reservierung> selectAll() throws ReservierungException;


}
