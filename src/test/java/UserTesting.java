import com.example.project1.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class UserTesting {
    //Create User Instance
    User test = new User("pandoflas","123456789","pantazis","example@mail.com");

    @Test
    public void testGetUsername(){
        String expexted = "pandoflas";
        String actual = test.getUsername();
        assertEquals(expexted,actual);
    }

    @Test
    public void testGetPassword(){
        String expected = "123456789";
        String actual = test.getPassword();
        assertEquals(expected,actual);
    }

    @Test
    public void testGetEmail(){
        String expected = "example@mail.com";
        String actual = test.getEmail();
        assertEquals(expected,actual);
    }

    @Test
    public void testGetName(){
        String expected = "pantazis";
        String actual = test.getName();
        assertEquals(expected,actual);
    }

    @Test
    public void testGetUsername2(){
        String expexted = "pandoflas";
        String actual = User.username;
        assertEquals(expexted,actual);
    }

    @Test
    public void testGetPassword2(){
        String expected = "123456789";
        String actual = User.password;
        assertEquals(expected,actual);
    }

    @Test
    public void testGetEmail2(){
        String expected = "example@mail.com";
        String actual = User.email;
        assertEquals(expected,actual);
    }

    @Test
    public void testGetName2(){
        String expected = "pantazis";
        String actual = User.name;
        assertEquals(expected,actual);
    }



}
