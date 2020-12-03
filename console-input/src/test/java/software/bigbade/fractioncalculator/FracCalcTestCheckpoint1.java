package software.bigbade.fractioncalculator;

import org.junit.jupiter.api.Test;

// Checkpoint 1-only tests
class FracCalcTestCheckpoint1 {
    @Test
    void testCheckpoint1_1() {
        FracCalcTestALL.assertForEarlyCheckpoints("6_5/8", "whole:6 numerator:5 denominator:8",
                "12_3/8", FracCalc.produceAnswer("5_3/4 + 6_5/8"));
    }

    @Test
    void testCheckpoint1_2() {
        FracCalcTestALL.assertForEarlyCheckpoints("20", "whole:20 numerator:0 denominator:1",
                "-20_3/7", FracCalc.produceAnswer("-3/7 - 20"));
    }

    @Test
    void testCheckpoint1_3() {
        FracCalcTestALL.assertForEarlyCheckpoints("27/21", "whole:0 numerator:27 denominator:21",
                "-33_2/7", FracCalc.produceAnswer("-32 - 27/21"));
    }
}
