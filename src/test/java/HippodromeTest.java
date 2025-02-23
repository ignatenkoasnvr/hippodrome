import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HippodromeTest {

    @ParameterizedTest
    @Order(1)
    @NullSource
    public void testNullExcep(List<Horse> horses) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    new Hippodrome(horses);
                }
        );
    }
    @ParameterizedTest
    @Order(2)
    @NullSource
    public void testNullTextExcep(List<Horse> horses) {
        try {
            new Hippodrome(horses);
        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be null.", e.getMessage());
        }
    }
    @Test
    @Order(3)
    public void testEmptyExcep() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(new ArrayList<>()));
    }
    @Test
    @Order(4)
    public void testEmptyTextExcep() {
       try {
           new Hippodrome(new ArrayList<>());
       } catch (IllegalArgumentException e) {
           assertEquals("Horses cannot be empty.", e.getMessage());
       }
    }
    @Test
    @Order(5)
    public void testGetHorse() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("" + i, 1, 10));
        }
        Hippodrome hipp = new Hippodrome(horses);
        Assertions.assertEquals(hipp.getHorses(), horses);
    }

    @Test
    @Order(6)
    public void testMove() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }
        Hippodrome hipp = new Hippodrome(horses);

        hipp.move();

        for (Horse horse : horses) {
            verify(horse).move();
        }
    }

    @Test
    @Order(7)
    public void testWinner() {
        Horse horse1 = new Horse("Horse1", 1, 10);
        Horse horse2 = new Horse("Horse2", 1, 10);
        Horse horse3 = new Horse("Horse3", 1, 10);
        Horse horse4 = new Horse("Horse4", 1, 10);
        Horse horse5 = new Horse("Horse5", 1, 15);
        Hippodrome hipp = new Hippodrome(List.of(horse1, horse2, horse3, horse4, horse5));

        Assertions.assertSame(horse5, hipp.getWinner());
    }
}
