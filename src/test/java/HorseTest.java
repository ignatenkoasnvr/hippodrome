import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
public class HorseTest {
    @Test
    public void testFirstParam() {
    Throwable e = Assertions.assertThrows(IllegalArgumentException.class,
            () -> {new Horse(null, 0.0);}
    );
    assertEquals("Name cannot be null.", e.getMessage());
}
    @Test
    public void testSecondParam() {
        Throwable e = Assertions.assertThrows(IllegalArgumentException.class,
                () -> {new Horse("Plotva", -1.0);}
        );
        assertEquals("Speed cannot be negative.", e.getMessage());
    }
    @Test
    public void testThirdParam() {
        Throwable e = Assertions.assertThrows(IllegalArgumentException.class,
                () -> {new Horse("Plotva", 1.0, -10.0);}
        );
        assertEquals("Distance cannot be negative.", e.getMessage());
    }
    @Test
    public void testGetName() {
        Horse horse = new Horse("name", 2.05, 10.7);
        assertEquals("name", horse.getName());
    }
    @Test
    public void testGetSpeed() {
        Horse horse = new Horse("name", 2.05, 10.7);
        assertEquals(2.05, horse.getSpeed(), 0);
    }
    @Test
    public void testGetDistance() {
        Horse horse = new Horse("name", 2.05, 10.7);
        Horse horse2 = new Horse("name2", 2.05);
        assertEquals(10.7, horse.getDistance(), 0);
        assertEquals(0.0, horse2.getDistance(), 0);
    }
    @Test
    public void testMove(){
    }
}
