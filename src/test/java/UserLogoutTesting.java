import com.example.project1.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserLogoutTesting {
    User test = new User();

    @Test
    public void testGetUsername(){
        String expexted = null;
        String actual = test.getUsername();
        assertEquals(expexted,actual);
    }

    @Test
    public void testGetPassword(){
        String expected = null;
        String actual = test.getPassword();
        assertEquals(expected,actual);
    }

    @Test
    public void testGetEmail(){
        String expected = null;
        String actual = test.getEmail();
        assertEquals(expected,actual);
    }

    @Test
    public void testGetName(){
        String expected = null;
        String actual = test.getName();
        assertEquals(expected,actual);
    }

    @Test
    public void testGetUsername2(){
        String expexted = null;
        String actual = User.username;
        assertEquals(expexted,actual);
    }

    @Test
    public void testGetPassword2(){
        String expected = null;
        String actual = User.password;
        assertEquals(expected,actual);
    }

    @Test
    public void testGetEmail2(){
        String expected = null;
        String actual = User.email;
        assertEquals(expected,actual);
    }

    @Test
    public void testGetName2(){
        String expected = null;
        String actual = User.name;
        assertEquals(expected,actual);
    }


}
