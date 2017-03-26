package sample.test;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import sample.dao.impl.DAOException;
import sample.dao.impl.JDBCBoxDAO;
import sample.entities.Box;
import sample.util.DBUtil;

import java.sql.SQLException;


public class JDBCBoxDAOTests extends AbstractBoxDAOTest {


    @BeforeClass
    public static void resetBefore() throws DAOException {
        DBUtil.getConnection();
        DBUtil.reset(JDBCBoxDAOTests.class.getClassLoader().getResource("res/testdata.sql").getPath());
    }

    @Before
    public void setUp() throws DAOException, SQLException {
        setBoxDAO(new JDBCBoxDAO());
        Box validBox = new Box(30L,70L,4L,true,true,"",false);
        Box boxWithNegativePrice = new Box (-30L,70L,4L,true,true,"",false);
        Box boxWithNegativeSize = new Box (30L,-70L,4L,true,true,"",false);

        setBoxes(validBox,boxWithNegativePrice,boxWithNegativeSize);
    }

    @AfterClass
    public static void resetAfter() throws DAOException {
        DBUtil.reset(JDBCBoxDAOTests.class.getClassLoader().getResource("res/testdata.sql").getPath());
    }

}
