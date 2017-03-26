package sample.test;

import org.junit.Test;
import sample.dao.BoxDAO;
import sample.dao.impl.DAOException;
import sample.entities.Box;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;



public abstract class AbstractBoxDAOTest {

    private BoxDAO boxDAO;
    private Box validBox;
    private Box boxWithNegativePrice;
    private Box boxWithNegativeSize;


    protected void setBoxDAO(BoxDAO boxDAO) {
        this.boxDAO = boxDAO;
    }

    protected void setBoxes(Box validBox, Box boxWithNegativePrice, Box boxWithNegativeSize) {
        this.validBox = validBox;
        this.boxWithNegativePrice = boxWithNegativePrice;
        this.boxWithNegativeSize = boxWithNegativeSize;
    }

    @Test(expected = DAOException.class)
    public void createBoxWithNullShouldThrowDAOException() throws DAOException {
        boxDAO.insert(null);
    }

    @Test(expected = DAOException.class)
    public void createBoxWithNegativePriceShouldThrowDAOException() throws DAOException {
        boxDAO.insert(boxWithNegativePrice);
    }

    @Test
    public void createBoxWithValidParametersShouldPersist() throws DAOException {
        Box box = new Box( 0L, 0L, 157L, true, true, "string", false);

        List<Box> boxList = boxDAO.selectAll();
        assertFalse(boxList.contains(box));

        boxDAO.insert(box);
        boxList = boxDAO.selectAll();
        assertTrue(boxList.contains(box));

        boxDAO.delete(box);
    }

    @Test(expected = DAOException.class)
    public void searchWithOneBoxNullShouldThrowDAOException() throws DAOException {
        boxDAO.search(validBox, null);
    }

    @Test
    public void searchWithValidParametersShouldReturnAllHorses() throws DAOException {
        List<Box> boxList = boxDAO.search(new Box( 1000L, 1000L, 1000L, true, true, null, false),
                new Box(2000L, 2000L, 2000L, true, true, null, false));
        assertEquals(boxList.size(), 2);
    }

    @Test(expected = DAOException.class)
    public void updateWithNonExistingIdShouldThrowDAOException() throws DAOException {
        boxDAO.update(boxWithNegativePrice,validBox);
    }

    @Test
    public void updateWithValidIdShouldUpdateHorse() throws DAOException {
        Box box = boxDAO.insert(validBox);
        List<Box> boxList = boxDAO.search(new Box( 1000L, 1000L, 1000L, true, true, null, false),
                new Box( 2000L, 2000L, 2000L, true, true, null, false));
        assertTrue(boxList.contains(box));
        box.setPrice(box.getPrice()+10);
        boxDAO.update(box,validBox);
    }


    @Test(expected = DAOException.class)
    public void deleteWithNonExistingSizeShouldThrowDAOException() throws DAOException {
        boxDAO.delete(boxWithNegativeSize);
    }

    @Test
    public void deleteWithValidSizeShouldDeleteHorse() throws DAOException {
        Box box = boxDAO.insert(validBox);
        List<Box> boxList = boxDAO.search(new Box( 1000L, 1000L, 1000L, true, true, null, false),
                new Box( 2000L, 2000L, 2000L, true, true, null, false));
        assertTrue(boxList.contains(box));
        boxDAO.delete(box);
        assertFalse(boxList.contains(box));
    }


}