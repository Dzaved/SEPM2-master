package sample.dao.impl;

import sample.dao.ReservierungDAO;
import sample.entities.Reservierung;
import sample.util.DBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JDBCReservierungDAO implements ReservierungDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(JDBCReservierungDAO.class);


    private String reservierungInsertString = "INSERT INTO Reservierung (nameClient, beginDate, endDate, deleted) VALUES (?,?,?,?)";
    private String boxHorsesInsertString = "INSERT INTO BoxHorse (boxID, resID, horseName, price) VALUES (?,?,?,?)";
    private String boxPricebyID = "SELECT PRICE FROM BOX WHERE id = ?";
    private String getAllReservationsString = "SELECT * FROM RESERVATION WHERE DELETED = FALSE";
    private String getBoxResByIDString = "SELECT * FROM BOXRES WHERE RES = ?";


    @Override
    public void createReservierungTable() {
        try {
            Statement statement = DBUtil.getConnection().createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS Reservierung(id INTEGER auto_increment PRIMARY KEY,nameClient VARCHAR(255), " +
                    "beginDate DATE, endDate DATE, deleted BOOLEAN)");

            statement.execute("CREATE TABLE IF NOT EXISTS BoxHorse(boxID INTEGER, resID INTEGER, horseName VARCHAR(255), price BIGINT, FOREIGN KEY(boxID) REFERENCES Box(id), FOREIGN KEY(resID) REFERENCES Reservierung (id))");



        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void insert(Reservierung reservierung) {
        try {
            PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement(reservierungInsertString, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, reservierung.getNameClient());
            preparedStatement.setDate(2, reservierung.getBeginDate());
            preparedStatement.setDate(3, reservierung.getEndDate());
            preparedStatement.setBoolean(4, reservierung.isDeleted());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (!generatedKeys.next())  return;

            reservierung.setId(generatedKeys.getInt(1));


                for (int i = 0; i < reservierung.getBoxHorseList().getBoxList().length; ++i) {
                    PreparedStatement preparedStatement1 = DBUtil.getConnection().prepareStatement(this.boxPricebyID);
                    preparedStatement1.setInt(1, reservierung.getBoxHorseList().getBoxList()[i]);
                    ResultSet resultSet = preparedStatement1.executeQuery();

                    long price = 0;
                    while (resultSet.next()) {
                        price = resultSet.getLong(1);
                    }


                    PreparedStatement ps3 = DBUtil.getConnection().prepareStatement(boxHorsesInsertString);
                    ps3.setInt(1, reservierung.getBoxHorseList().getBoxList()[i]);
                    ps3.setInt(2, reservierung.getId());
                    ps3.setString(3, reservierung.getBoxHorseList().getHorseList()[i]);
                    ps3.setLong(4, price);
                    ps3.executeUpdate();

                }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Reservierung getReservierung(int id) {
        Reservierung reservierung = new Reservierung();

        try {
            PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement("SELECT * FROM Reservierung WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                reservierung.setId(resultSet.getInt("id"));
                reservierung.setNameClient(resultSet.getString("nameClient"));
                reservierung.setBeginDate(resultSet.getDate("beginDate"));
                reservierung.setEndDate(resultSet.getDate("endDate"));
                reservierung.setDeleted(resultSet.getBoolean("deleted"));
            }

            System.out.println("SELECT * FROM Reservierung WHERE id = ?");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return reservierung;
    }

    @Override
    public List<Reservierung> selectAll() {
        List<Reservierung> reservierungList = new ArrayList<>();
        try {
            Statement statement = DBUtil.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Reservierung");

            while (resultSet.next()) {
                Reservierung reservierung = new Reservierung();
                reservierung.setId(resultSet.getInt("id"));
                reservierung.setNameClient(resultSet.getString("nameClient"));
                reservierung.setBeginDate(resultSet.getDate("beginDate"));
                reservierung.setEndDate(resultSet.getDate("endDate"));
                reservierung.setDeleted(resultSet.getBoolean("deleted"));

                reservierungList.add(reservierung);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return reservierungList;
    }

    @Override
    public void update(Reservierung reservierung, int id) {
        try {
            PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement("UPDATE Reservierung SET " +
                    "id = ?, nameClient = ?, nameHorse = ? , beginDate = ? , endDate = ? , deleted = ? WHERE id = ?");

            preparedStatement.setInt(1, reservierung.getId());
            preparedStatement.setString(2, reservierung.getNameClient());
            preparedStatement.setDate(4, reservierung.getBeginDate());
            preparedStatement.setDate(5, reservierung.getEndDate());
            preparedStatement.setBoolean(6, reservierung.isDeleted());

            preparedStatement.executeUpdate();

            System.out.println("UPDATE Reservation SET id = ?, nameClient = ? , nameHorse = ?, beginDate = ?, endDate = ?, deleted = ? WHERE id = ?");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {

    }


}