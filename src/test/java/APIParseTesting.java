import com.example.project1.mainAPI;
import org.junit.Before;
import org.junit.Test;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class APIParseTesting {

    @Before
    public void setUp() throws SQLException {
        String[] arguments = new String[] {"123"};
        mainAPI.main(arguments);
    }

    @Test
    public void ParseTest(){
        int expected = mainAPI.getShopparser();
        int actual = 179;
        assertEquals(expected,actual);
    }
}
