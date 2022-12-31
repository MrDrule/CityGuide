import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.sql.*;
import org.junit.jupiter.api.Test;

public class VerifyDataTest {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/cityguide";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    @Test
    public void testUsersFromMysql() throws SQLException {
        try {
            // Create a new connection to the database
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE username='pantazis'");

            // Verify the data
            assertTrue(rs.next());
            assertEquals(1,rs.getInt("ID"));
            assertEquals("pantazis", rs.getString("username"));
            assertEquals("pan2@hotmail.com", rs.getString("email"));
            assertEquals("Pantazis", rs.getString("name"));
            assertEquals("a1782395", rs.getString("password"));
            assertFalse(rs.next());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPlacesFromMysql() throws SQLException {
        try {
            // Create a new connection to the database
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM places WHERE name='BROWN CAFE'");

            // Verify the data
            assertTrue(rs.next());
            assertEquals(4, rs.getInt("town_id"));
            assertEquals("ChIJ_84NJQPPqRQRVoGQH3JGE0E", rs.getString("place_id"));
            assertEquals("BROWN CAFE", rs.getString("name"));
            assertEquals("{\"open_now\":true}", rs.getString("open_now"));
            assertEquals(4.8, rs.getDouble("rating"),0);
            assertEquals("21is Iouniou 145, Kilkis", rs.getString("vicinity"));
            assertEquals("[\"cafe\",\"food\",\"point_of_interest\",\"establishment\"]", rs.getString("type"));
            assertEquals(0, rs.getDouble("price_level"),0);
            assertFalse(rs.next());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTownFromMysql() throws SQLException {
        try {
            // Create a new connection to the database
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM town WHERE town_id='1'");

            // Verify the data
            assertTrue(rs.next());
            assertEquals(1,rs.getInt("town_id"));
            assertEquals("Σέρρες", rs.getString("town_name"));
            assertFalse(rs.next());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}