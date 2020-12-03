package software.bigbade.fractioncalculator;

import org.junit.jupiter.api.Test;

// Checkpoint 2-only tests

class FracCalcTestCheckpoint2 {
    @Test
    void testCheckpoint2_1() {
        FracCalcTestALL.assertForEarlyCheckpoints(null, "whole:6 numerator:5 denominator:8",
                "12_3/8", FracCalc.produceAnswer("5_3/4 + 6_5/8"));
    }

    @Test
    void testCheckpoint2_2() {
        FracCalcTestALL.assertForEarlyCheckpoints(null, "whole:20 numerator:0 denominator:1",
                "-20_3/7", FracCalc.produceAnswer("-3/7 - 20"));
    }

    @Test
    void testCheckpoint2_3() {
        FracCalcTestALL.assertForEarlyCheckpoints(null, "whole:0 numerator:27 denominator:21",
                "-33_2/7", FracCalc.produceAnswer("-32 - 27/21"));
    }
}
