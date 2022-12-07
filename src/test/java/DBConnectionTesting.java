import com.example.project1.mysqlconnect;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.Connection;

public class DBConnectionTesting {
    Connection conn;

    @Test
    public void testDbConnection() {
        conn = mysqlconnect.ConnectDb();
        assertNotEquals(conn, null);
    }

}

