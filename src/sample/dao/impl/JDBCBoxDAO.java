package sample.dao.impl;

import sample.dao.BoxDAO;
import sample.entities.Box;
import sample.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by David on 15-Mar-17.
 */
public class JDBCBoxDAO implements BoxDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(JDBCBoxDAO.class);
    Connection connection = null;

    @Override
    public void createBoxTable(){
        try {
            Statement statement = DBUtil.getConnection().createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS Box(id INTEGER auto_increment PRIMARY KEY,price BIGINT,boxSize BIGINT,food_quantity INTEGER," +
                    "has_windows BOOLEAN,is_outside BOOLEAN, image VARCHAR(255), deleted BOOLEAN)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String boxInsertString = "INSERT INTO Box (price, boxSize, food_quantity, has_windows, is_outside, image,deleted) VALUES (?,?,?,?,?,?,?)";

    @Override
    public Box insert(Box box){
        try {
            PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement(boxInsertString, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, box.getPrice());
            preparedStatement.setLong(2, box.getBoxSize());
            preparedStatement.setLong(3, box.getFood_quantity());
            preparedStatement.setBoolean(4, box.isHas_windows());
            preparedStatement.setBoolean(5, box.isIs_outside());
            preparedStatement.setString(6, box.getImage());
            preparedStatement.setBoolean(7,box.isDeleted());
            preparedStatement.executeUpdate();
            LOGGER.info("Box inserted.");

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                box.setId(generatedKeys.getInt(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return box;
    }

    @Override
    public Box getBox(int id) {
        Box box = new Box();

        try {
            PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement("SELECT * FROM Box WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                box.setId(resultSet.getInt("id"));
                box.setPrice(resultSet.getLong("price"));
                box.setBoxSize(resultSet.getLong("boxSize"));
                box.setFood_quantity(resultSet.getLong("food_quantity"));
                box.setHas_windows(resultSet.getBoolean("has_windows"));
                box.setIs_outside(resultSet.getBoolean("is_outside"));
                box.setImage(resultSet.getString("image"));
                box.setDeleted(resultSet.getBoolean("deleted"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return box;
    }




    @Override
    public ArrayList<Box> filter(long fromPrice, long toPrice, long fromBoxSize, long toBoxSize, long fromFoodQuantity, long toFoodQuantity, boolean priceCheckboxState, boolean boxSizeCheckboxState, boolean foodQuantityCheckboxState){

        ArrayList<Box> arr = new ArrayList<>();

        String query = "SELECT * FROM BOX WHERE";

        if(priceCheckboxState){
            query += " PRICE >= " + fromPrice;
            query += " AND PRICE <= " + toPrice;
        }

        if(boxSizeCheckboxState){
            query +=  priceCheckboxState ? " AND BOXSIZE >= " + fromBoxSize : " BOXSIZE >= " + fromBoxSize;
            query += " AND BOXSIZE <= " + toBoxSize;

        }

        if(foodQuantityCheckboxState) {
            query += priceCheckboxState || boxSizeCheckboxState ? " AND FOOD_QUANTITY >= " + fromFoodQuantity : " FOOD_QUANTITY >= " + fromFoodQuantity;
            query += " AND FOOD_QUANTITY <= " + toFoodQuantity;
        }


        try {
            PreparedStatement ps = DBUtil.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int boxId = rs.getInt(1);
                long boxPrice = rs.getLong(2);
                long boxSize = rs.getLong(3);
                long boxFoodQuantity = rs.getLong(4);
                boolean boxHasWindows = rs.getBoolean(4);
                boolean boxIsOutside = rs.getBoolean(5);
                String boxPhoto = rs.getString(7);
                boolean boxIsDeleted = rs.getBoolean(8);

                Box box = new Box(boxPrice, boxSize, boxFoodQuantity, boxHasWindows, boxIsOutside, boxPhoto, boxIsDeleted);
                box.setId(boxId);

                arr.add(box);
            }

            LOGGER.info("'Filter' method completed successfully.");
            return arr;


        } catch (SQLException e) {
            LOGGER.error("'Filter' method failed.");
            e.printStackTrace();
        }

        return arr;
    }



    @Override
    public List<Box> search(Box from, Box to) throws DAOException {
        checkIfBoxIsNull(from);
        checkIfBoxIsNull(to);

        connection = DBUtil.reconnectIfConnectionToDatabaseLost();
        ArrayList<Box> list = new ArrayList<Box>();
        try {
            ResultSet rs = connection.createStatement().executeQuery("SELECT* FROM Box WHERE deleted = false AND price BETWEEN " + from.getPrice() + " AND " + to.getPrice() + " AND boxSize BETWEEN " + from.getBoxSize() + " AND " + to.getBoxSize() + " AND food_quantity BETWEEN " + from.getFood_quantity() + " AND " + to.getFood_quantity() + ";");
            while(rs.next()){
                list.add(new Box(rs.getLong(1),
                        rs.getLong(2),rs.getLong(3),rs.getBoolean(4),
                        rs.getBoolean(5),
                        rs.getString(6),
                        rs.getBoolean(7)));
            }
        } catch (SQLException e) {
            LOGGER.debug(e.getMessage());
            throw new DAOException(e.getMessage());
        }
        LOGGER.debug("Successfully returned filtered list of horses:\n{}",list);
        return list;
    }


    @Override
    public  List<Box> selectAll(){
        List<Box> boxList = new ArrayList<>();

        try {
            Statement statement = DBUtil.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Box");

            while (resultSet.next()) {
                Box box = new Box();
                box.setId(resultSet.getInt("id"));
                box.setPrice(resultSet.getLong("price"));
                box.setBoxSize(resultSet.getLong("boxSize"));
                box.setFood_quantity(resultSet.getLong("food_quantity"));
                box.setHas_windows(resultSet.getBoolean("has_windows"));
                box.setIs_outside(resultSet.getBoolean("is_outside"));
                box.setImage(resultSet.getString("image"));
                box.setDeleted(resultSet.getBoolean("deleted"));


                boxList.add(box);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return boxList;
    }


    @Override
    public void update(Box box, Box box2){

        try {
            PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement("UPDATE Box SET " +
                    "price = ?, boxSize = ? , food_quantity = ? , has_windows = ? , is_outside = ? , image = ? , deleted = ? WHERE id = ?");
            preparedStatement.setLong(1, box.getPrice());
            preparedStatement.setLong(2, box.getBoxSize());
            preparedStatement.setLong(3, box.getFood_quantity());
            preparedStatement.setBoolean(4, box.isHas_windows());
            preparedStatement.setBoolean(5 ,box.isIs_outside());
            preparedStatement.setString(6, box.getImage());
            preparedStatement.setBoolean(7,box.isDeleted());
            preparedStatement.setInt(8,box2.getId());

            preparedStatement.executeUpdate();

            System.out.println("UPDATE Box SET id = ?, price = ?, boxSize = ? , food_quantity = ? , has_windows = ? , is_outside = ? ," +
                    " image = ? , deleted = ? WHERE boxName = ?");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Box box){
        try {
           PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement("DELETE FROM Box WHERE id = ?");
            preparedStatement.setInt(1, box.getId());
            preparedStatement.executeUpdate();
            System.out.println("DELETE FROM Box WHERE id = ?");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkIfBoxIsNull(Box box) throws DAOException{
        if(box == null){
            LOGGER.debug("Bos is null");
            throw new DAOException("Box cannot be null.");
        }
    }


}
