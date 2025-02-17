import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

public class MainTest {

    @Disabled
    @Test @Timeout(value = 22000, unit = TimeUnit.MILLISECONDS)
    void failsIfExecutionTimeExceeds_22_000_Milliseconds() {
        // тест упадет, если займет более 22 000 миллисекунд
    }


}
