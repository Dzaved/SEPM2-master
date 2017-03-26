package sample.entities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Date;

/**
 * Created by David on 15-Mar-17.
 */
public class Reservierung {

    private static final Logger LOGGER = LoggerFactory.getLogger(Reservierung.class);

    private int id;
    private String nameClient;
    private Date beginDate;
    private Date endDate;
    private boolean deleted;
    private BoxHorseList boxHorseList;

    public Reservierung(){}

    public Reservierung(String nameClient, BoxHorseList boxHorseList, Date beginDate, Date endDate, boolean deleted) {
        this.nameClient = nameClient;
        this.boxHorseList = boxHorseList;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.deleted = deleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public BoxHorseList getBoxHorseList() {
        return boxHorseList;
    }

    public void setBoxHorseList(BoxHorseList boxHorseList) {
        this.boxHorseList = boxHorseList;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
