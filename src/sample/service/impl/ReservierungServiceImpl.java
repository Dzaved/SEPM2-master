package sample.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sample.dao.ReservierungDAO;
import sample.dao.impl.JDBCReservierungDAO;
import sample.entities.Box;
import sample.entities.Reservierung;
import sample.gui.BoxViewController;
import sample.service.ReservierungService;

import java.util.List;


public class ReservierungServiceImpl implements ReservierungService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservierungServiceImpl.class);

    private ReservierungDAO reservierungDAO;
    private BoxViewController boxViewController;



    public ReservierungServiceImpl() throws ReservierungException {
        this.reservierungDAO = new JDBCReservierungDAO();
        this.boxViewController = new BoxViewController();

        LOGGER.info("Service started.");
    }


    @Override
    public Reservierung insert(Reservierung reservierung) throws ReservierungException {
        LOGGER.debug("Entering insertReservierung method.");
        validateReservierung(reservierung);
        return reservierung;
    }

    @Override
    public void update(Reservierung reservierung, int id) throws ReservierungException {

    }

    @Override
    public void delete(int id) throws ReservierungException {

    }

    @Override
    public List<Reservierung> selectAll() throws ReservierungException {
        LOGGER.debug("Entering selectAll method.");
        return reservierungDAO.selectAll();
    }



    public void validateReservierung(Reservierung reservierung) throws ReservierungException {
        LOGGER.debug("Validating reservation: ", reservierung);

        if (reservierung == null) {
            LOGGER.debug("Reservation is null.");
            throw new ReservierungException("Reservation cannot be empty.");
        }

        if(reservierung.getNameClient() == null){
            LOGGER.debug("NameClient is null.");
            throw new ReservierungException("NameClient cannot be empty.");
        }


        if(reservierung.getBeginDate() == null){
            LOGGER.debug("BeginDate is null.");
            throw new ReservierungException("BeginDate cannot be empty.");
        }


        if(reservierung.getEndDate() == null){
            LOGGER.debug("EndDate is null.");
            throw new ReservierungException("EndDate cannot be empty.");
        }

        LOGGER.debug("Reservierung validation successful.");

    }


}
