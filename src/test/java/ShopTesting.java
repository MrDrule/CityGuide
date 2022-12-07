import com.example.project1.Shop;
import static org.junit.Assert.*;
import org.junit.Test;

public class ShopTesting
{
    Shop test= new Shop(1, "cafe" ,"abc123", "O paliatsos" ,"open", 4.5,"Merarxias 14", 2.0);

    @Test
    public void testTownId(){
        Integer expected=1;
        Integer actual=test.town_id;
        assertEquals(expected,actual);
    }

    @Test
    public void testType(){
        String expected = "cafe";
        String actual = test.type;
        assertEquals(expected,actual);
    }

    @Test
    public void testPlaceId(){
        String expected = "abc123";
        String actual = test.placeID;
        assertEquals(expected,actual);
    }

    @Test
    public void testName(){
        String expected = "O paliatsos";
        String actual = test.name;
        assertEquals(expected,actual);
    }

    @Test
    public void testOpenNow(){
        String expected = "open";
        String actual = test.open_now;
        assertEquals(expected,actual);
    }

    @Test
    public void testRating(){
        Double expected = 4.5;
        Double actual = test.rating;
        assertEquals(expected,actual);
    }

    @Test
    public void testVicinity(){
        String expected = "Merarxias 14";
        String actual = test.vicinity;
        assertEquals(expected,actual);
    }

    @Test
    public void testPriceLevel(){
        Double expected = 2.0;
        Double actual = test.price_level;
        assertEquals(expected,actual);
    }
}
