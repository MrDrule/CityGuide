import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.Test;
import static org.junit.Assert.*;

public class InsertDbTest {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/cityguide";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";

    @Test
    public void testMysqlInsert() throws SQLException {
        // Create a new connection to the database
        Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

        // Insert a new row into the table
        String sql = "INSERT INTO places (town_id,place_id,name, open_now,rating, vicinity, type,price_level) VALUES (?,?,?, ?, ?, ?, ?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, 4);
        preparedStatement.setString(2, "abc");
        preparedStatement.setString(3, "Hello");
        preparedStatement.setString(4, "Yes");
        preparedStatement.setDouble(5, 4.5);
        preparedStatement.setString(6, "Address");
        preparedStatement.setString(7, "Something");
        preparedStatement.setDouble(8,2.0);
        preparedStatement.executeUpdate();

        // Query the database to verify that the data was inserted correctly
        sql = "SELECT * FROM places WHERE place_id='abc'";
        preparedStatement = conn.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();

        // Check that the result set contains only one row and that the values in
        // the row match the values we inserted
        assertTrue(rs.next());
        assertEquals(4, rs.getInt("town_id"));
        assertEquals("abc", rs.getString("place_id"));
        assertEquals("Hello", rs.getString("name"));
        assertEquals("Yes", rs.getString("open_now"));
        assertEquals(4.5, rs.getDouble("rating"),0);
        assertEquals("Address", rs.getString("vicinity"));
        assertEquals("Something", rs.getString("type"));
        assertEquals(2.0, rs.getDouble("price_level"),0);
        assertFalse(rs.next());

        sql="DELETE FROM places WHERE place_id='abc'";
        preparedStatement = conn.prepareStatement(sql);
        preparedStatement.executeUpdate();

        // Close the connection to the database
        conn.close();
    }
}
