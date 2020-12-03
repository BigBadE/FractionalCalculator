package software.bigbade.fractioncalculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FracCalcTestALL {
    public static void assertForEarlyCheckpoints(String answer1, String answer2, String answer3, String candidate) {
        assertNotNull(candidate, "See console window for failure info");

        assertEquals(candidate, answer1, () -> "Checkpoint 1: Expected: '" + answer1 + "'");
        assertEquals(candidate, answer2, () -> "Checkpoint 2: Expected: '" + answer2 + "'");
        assertTrue(FracCalcTestHelper.areFracsEqual(answer3, candidate), () -> "Checkpoint 3: Expected any answer that " +
                "is equivalent to: '" + answer3 + "'\n    For checkpoint 3, your answer need not be reduced, " +
                "but it must be equivalent to the expected answer.");
    }
}
