import com.example.project1.UsersDetails;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDetailsTest {
    UsersDetails test = new UsersDetails("Nats", "12345", "Nats@gmail.com", Integer.parseInt("1"), "Giorgos");


    @Test
    public void getUsernameTest() {
        String username = test.getUsername();
        String ExpResult="Nats";
        assertEquals(ExpResult,username);
    }
    @Test
    public void getPasswordTest() {
        String password = test.getPassword();
        String ExpResult="12345";
        assertEquals(ExpResult,password);
    }
    @Test
    public void getEmailTest() {
        String email = test.getEmail();
        String ExpResult="Nats@gmail.com";
        assertEquals(ExpResult,email);
    }
    @Test
    public void getIdTest() {
        int id  = test.getId();
        int ExpResult=1;
        assertEquals(ExpResult,id);
    }
    @Test
    public void getNameTest() {
        String name = test.getName();
        String ExpResult="Giorgos";
        assertEquals(ExpResult,name);
    }



}
