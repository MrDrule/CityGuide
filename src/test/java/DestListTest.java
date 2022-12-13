
import com.example.project1.DestList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DestListTest {
    DestList test = new DestList("Cafe","Vironos 12","4.5","8","abc");

    @Test
    void getNameTest(){
        String name= test.getName();
        String ExpRes="Cafe";
        assertEquals(ExpRes,name);
    }
    @Test
    void getAddressTest(){
        String address= test.getAddress();
        String ExpRes="Vironos 12";
        assertEquals(ExpRes,address);
    }
    @Test
    void getRatingTest(){
        String rating= test.getRating();
        String ExpRes= "4.5";
        assertEquals(ExpRes,rating);
    }
    @Test
    void getPriceTest(){
        String price= test.getPrice();
        String ExpRes="8";
        assertEquals(ExpRes,price);
    }
    @Test
    void setNameTest() {
        test.setName("cafe");
        String name = test.getName();
        assertEquals("cafe",name);
    }
    @Test
    void setAddressTest() {
        test.setAddress("Merarchias 76");
        String address = test.getAddress();
        assertEquals("Merarchias 76",address);
    }
    @Test
    void setRatingTest() {
        test.setRating("4.6");
        String rating = test.getRating();
        String ExpRes="4.6";
        assertEquals(ExpRes,rating);

    }
    @Test
    void setPriceTest() {
        test.setPrice("9.7");
        String price = test.getPrice();
        String ExpRes="9.7";
        assertEquals(ExpRes,price);
    }
}