import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

//@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HorseTest {
    @Order(1)
    public void testNameTypeExcep() {
        Assertions.assertThrows(IllegalArgumentException.class,
                    () -> {
                    new Horse(null, 0.0);
                }
        );
    }
    @Test
    @Order(2)
    public void testNameExcepText() {
        try {
            new Horse(null, 0.0);
        } catch (IllegalArgumentException e) {
        assertEquals("Name cannot be null.", e.getMessage());
        }
    }
    @ParameterizedTest
    @Order(3)
    @ValueSource(strings = {"", " ", "\t", "\n"})
    public void testNameBlankExcep(String name) {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    new Horse(name, 0.0);
                }
        );
    }
    @ParameterizedTest
    @Order(4)
    @ValueSource(strings = {"", " ", "\t", "\n"})
    public void testNameParamBlankText(String name) {
        try {
            new Horse(name, 0.0);
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be blank.", e.getMessage());
        }
    }

    @Test
    @Order(5)
    public void testSpeedParamTypeExcep() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    new Horse("Plotva", -1.0);
                }
        );
    }

    @Test
    @Order(6)
    public void testSpeedParamTextExcep() {
        try {
            new Horse("Plotva", -1.0);
        } catch (IllegalArgumentException e) {
            assertEquals("Speed cannot be negative.", e.getMessage());
        }
    }
    @Test
    @Order(7)
    public void testDistanceParamTypeExcep() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    new Horse("Plotva", 1.0, -10.0);
                }
        );
    }
    @Test
    @Order(8)
    public void testDistanceParamTextExcep() {
        try {
            new Horse("Plotva", 1.0, -10.0);
        } catch (IllegalArgumentException e) {
            assertEquals("Distance cannot be negative.", e.getMessage());
        }
    }
    @Test
    @Order(9)
    public void testGetName() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("name", 2.05, 10.7);
        Field field = Horse.class.getDeclaredField("name");
        field.setAccessible(true);
        assertEquals("name", (String) field.get(horse));
    }
    @Test
    @Order(10)
    public void testGetSpeed() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("name", 2.05, 10.7);
        Field field = Horse.class.getDeclaredField("speed");
        field.setAccessible(true);
        assertEquals(2.05, (double) field.get(horse));
    }
    @Test
    @Order(11)
    public void testGetDistance() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("name", 2.05, 10.7);
        assertEquals(10.7, horse.getDistance(), 0);

        Field field = Horse.class.getDeclaredField("distance");
        field.setAccessible(true);
        assertEquals(10.7, field.get(horse));
    }

    @Test
    @Order(12)
    public void testGetDistanceNull() throws NoSuchFieldException, IllegalAccessException {
        Horse horse2 = new Horse("name2", 2.05);
        Field field = Horse.class.getDeclaredField("distance");
        field.setAccessible(true);
        assertEquals(0.0, (double) field.get(horse2), 0);
    }

    @Test
    @Order(13)
    public void testMoveUseGetRandomDouble() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("name", 2.05, 10.7);
            horse.move();
            mockedStatic.verify( () -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @Order(14)
    @ValueSource(doubles = {0.1, 0.2, 0.3, 0.5, 2.1, 10.0, 15.0})
    public void move(double temp) {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("name", 2.05, 10.7);
            mockedStatic.when( () ->
                    Horse.getRandomDouble(0.2, 0.9)).thenReturn(temp);

        horse.move();

        assertEquals(10.7 + 2.05 * temp, horse.getDistance());
        }

    }

}
