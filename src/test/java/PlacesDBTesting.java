import com.example.project1.mainAPI;
import com.example.project1.mysqlconnect;
import org.junit.Before;
import org.junit.Test;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class PlacesDBTesting {

    public String count;

    @Before
    public void setUp() throws SQLException, ClassNotFoundException {
        count = mysqlconnect.placescount();
    }

    @Test
    public void CountTest(){
        String expected = count;
        String actual = "178";
        assertEquals(expected,actual);
    }
}
