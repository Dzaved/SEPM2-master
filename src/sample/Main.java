package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.dao.impl.JDBCBoxDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sample.dao.impl.JDBCReservierungDAO;
import sample.entities.BoxHorseList;
import sample.entities.Reservierung;
import sample.gui.BoxViewController;
import sample.service.BoxService;
import sample.service.ReservierungService;
import sample.service.impl.BoxServiceImpl;
import sample.service.impl.ReservierungServiceImpl;
import sample.service.impl.ServiceException;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

public class Main extends Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {

        try {

            BoxService service = new BoxServiceImpl();
            ReservierungService serviceRes = new ReservierungServiceImpl();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BoxView.fxml"));
            Parent root = loader.load();
            BoxViewController controller = loader.getController();
            primaryStage.setTitle("David's Ranch");
            primaryStage.setScene(new Scene(root));
            JDBCBoxDAO boxDAO = new JDBCBoxDAO();
            JDBCReservierungDAO reservierungDAO = new JDBCReservierungDAO();
            controller.initialize(service,serviceRes);
            primaryStage.show();
            LOGGER.info("Application started.");
        } catch (ServiceException e) {
            LOGGER.info(e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

        public static void showAddBoxStage() throws IOException {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("CreateBoxView.fxml"));
            AnchorPane addNewBox = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Add new Box");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(primaryStage);

            Scene scene = new Scene(addNewBox);
            stage.setScene(scene);
            stage.showAndWait();
        }



    public static void main(String[] args) {
         launch(args);

         //DBUtil.getConnection();
        JDBCBoxDAO JDBCBoxDAO = new JDBCBoxDAO();
        //JDBCBoxDAO.createBoxTable();
    /*    Box box1 = new Box(100, 50,40,true,false,"bla",false);
        Box box2 = new Box(150, 75,50,true,true,"bla",false);
        Box box3 = new Box(85, 35,38,false,false,"bla",false);
        Box box4 = new Box(100, 50,40,false,true,"bla",false);
        Box box5 = new Box(200, 120,70,true,true,"bla",false);

        JDBCBoxDAO.insert(box1);
        JDBCBoxDAO.insert(box2);
        JDBCBoxDAO.insert(box3);
        JDBCBoxDAO.insert(box4);
        JDBCBoxDAO.insert(box5);
        //Box box6 = new Box(300L,120L,75L,false,true,"", false);
       Box referenceBox1 = new Box(30L, 0L, 0L, null, null, null, null);
        Box referenceBox2 = new Box(100L, 50L, 50L, null, null, null, null);
        try {
            List<Box> boxList1 = JDBCBoxDAO.search(referenceBox1, referenceBox2);
            for (Box boxes : boxList1) {
                System.out.println(boxes.getId() + ", " + boxes.getPrice() + ", " + boxes.getBoxSize() + ", " + boxes.getFood_quantity());
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }
*/
        //Box box = JDBCBoxDAO.getBox("Carribean Dream");
        //System.out.println(box.getBoxName() + ", " + box.getPrice() + ", " + box.getBoxSize() + ", " + box.getFood_quantity());
        //JDBCBoxDAO.delete("ola");

        // Box box = new Box(150,1000,70,false,true,"bla",false);
        //JDBCBoxDAO.update(box, 2);


        //JDBCBoxDAO.delete(2);
        JDBCReservierungDAO jdbcReservierungDAO = new JDBCReservierungDAO();
        jdbcReservierungDAO.createReservierungTable();

     /*   List<Box> boxList = JDBCBoxDAO.selectAll();
        for (Box boxes : boxList) {
            System.out.println(boxes.getId() + ", " + boxes.getPrice() + ", " + boxes.getBoxSize() + ", " + boxes.getFood_quantity());
        }
*/
        int[] boxList1 = {2, 3};
        String[] horseList = {"Rufus", "Cesar"};
        BoxHorseList boxHorseList1 = new BoxHorseList(boxList1, horseList);

        Calendar cal = Calendar.getInstance();
        cal.set(1, 2017);
        cal.set(2, 4);
        cal.set(5, 5);
        Date start = new Date(cal.getTimeInMillis());
        cal.set(1, 2017);
        cal.set(2, 1);
        cal.set(5, 8);
        Date end = new Date(cal.getTimeInMillis());

/*
      int[] boxList1 = {1, 4, 5};
        String[] horseList = {"Min", "Yang", "Xin"};
        BoxHorseList boxHorseList1 = new BoxHorseList(boxList1, horseList);

        Calendar cal = Calendar.getInstance();
        cal.set(1, 2016);
        cal.set(2, 4);
        cal.set(5, 5);
        Date start = new Date(cal.getTimeInMillis());
        cal.set(1, 2016);
        cal.set(2, 1);
        cal.set(5, 8);
        Date end = new Date(cal.getTimeInMillis());  */

        JDBCReservierungDAO reservierungDAO = new JDBCReservierungDAO();
        //Reservierung reservierung = new Reservierung("David", boxHorseList1, start, end, false);
        Reservierung reservierung1 = new Reservierung("Stefan", boxHorseList1, start, end, false);


        // reservierungDAO.insert(reservierung);
        // reservierungDAO.insert(reservierung1);

        //reservierungDAO.getReservierung(1);

    /*    List<Reservierung> reservierungList = reservierungDAO.selectAll();
        for (Reservierung reservierung : reservierungList) {
            System.out.println(reservierung.getId() + ", " + reservierung.getNameClient() + ", " + reservierung.getBeginDate() + ", "
            + reservierung.getEndDate() + ", " + reservierung.isDeleted());
        }
*/
    }
}
