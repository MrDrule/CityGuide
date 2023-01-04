import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.project1.RegisterController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.junit.Test;
import static org.junit.Assert.*;

public class RegisterUserTest {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/cityguide";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";

    @Test
    public void testCheckUsername() throws SQLException {
        assertTrue(RegisterController.checkusername("pantazis"));
    }
    @Test
    public void testCheckUsernameNot() throws SQLException {
        assertFalse(RegisterController.checkusername("aaaaaaaa"));
    }

    @Test
    public void testCheckEmail() throws SQLException {
        assertTrue(RegisterController.checkemail("pan@hotmail.com"));
    }
    @Test
    public void testCheckEmailNot() throws SQLException {
        assertFalse(RegisterController.checkemail("papatzis"));
    }

    @Test
    public void testCheckRegisterFunction() throws SQLException {

        Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        String sql = "INSERT INTO users (username,password,name,email) VALUES (?,?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, "testusername");
        preparedStatement.setString(2, "123");
        preparedStatement.setString(3, "testname");
        preparedStatement.setString(4, "testemail");
        preparedStatement.executeUpdate();

        sql = "SELECT * FROM users WHERE username='testusername'";
        preparedStatement = conn.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();

        assertTrue(rs.next());
        assertEquals("testusername", rs.getString("username"));
        assertEquals("123", rs.getString("password"));
        assertEquals("testname", rs.getString("name"));
        assertEquals("testemail", rs.getString("email"));

        sql="DELETE FROM users WHERE username='testusername'";
        preparedStatement = conn.prepareStatement(sql);
        preparedStatement.executeUpdate();

        // Close the connection to the database
        conn.close();
    }
}
