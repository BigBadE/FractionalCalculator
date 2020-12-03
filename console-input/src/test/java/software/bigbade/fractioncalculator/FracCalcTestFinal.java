package software.bigbade.fractioncalculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Final fraccalc submission tests
class FracCalcTestFinal {
    @Test
    void testAdditionSimple1() {
        assertEquals("2/5", FracCalc.produceAnswer("1/5 + 1/5"));
    }

    @Test
    void testAdditionSimple2() {
        assertEquals("4/5", FracCalc.produceAnswer("3/5 + 1/5"));
    }

    @Test
    void testAdditionSimple3() {
        assertEquals("4_3/7", FracCalc.produceAnswer("1_1/7 + 3_2/7"));
    }

    @Test
    void testAdditionReduce1() {
        assertEquals("1_1/5", FracCalc.produceAnswer("3/5 + 3/5"));
    }

    @Test
    void testAdditionReduce2() {
        assertEquals("1_1/5", FracCalc.produceAnswer("4/5 + 2/5"));
    }

    @Test
    void testAdditionReduce3() {
        assertEquals("1/4", FracCalc.produceAnswer("1/8 + 1/8"));
    }

    @Test
    void testAdditionWholeNumbers1() {
        assertEquals("1", FracCalc.produceAnswer("2/5 + 3/5"));
    }

    @Test
    void testAdditionWholeNumbers2() {
        assertEquals("1", FracCalc.produceAnswer("2/3 + 1/3"));
    }

    @Test
    void testAdditionWholeNumbers3() {
        assertEquals("10", FracCalc.produceAnswer("3 + 7"));
    }

    @Test
    void testAdditionWholeNumbers8() {
        assertEquals("2", FracCalc.produceAnswer("1 + 1"));
    }

    @Test
    void testAdditionWholeNumbers4() {
        assertEquals("4", FracCalc.produceAnswer("1 + 3"));
    }

    @Test
    void testAdditionWholeNumbers5() {
        assertEquals("452", FracCalc.produceAnswer("452 + 0"));
    }

    @Test
    void testAdditionWholeNumbers6() {
        assertEquals("254", FracCalc.produceAnswer("0 + 254"));
    }

    @Test
    void testAdditionWholeNumbers7() {
        assertEquals("1021778", FracCalc.produceAnswer("124543 + 897235"));
    }

    @Test
    void testAdditionWithNegatives1() {
        assertEquals("2/5", FracCalc.produceAnswer("3/5 + -1/5"));
    }

    @Test
    void testAdditionWithNegatives2() {
        assertEquals("900", FracCalc.produceAnswer("978 + -78"));
    }

    @Test
    void testAdditionWithNegatives3() {
        assertEquals("900", FracCalc.produceAnswer("-78 + 978"));
    }

    @Test
    void testAdditionWithNegatives4() {
        assertEquals("-1_1/4", FracCalc.produceAnswer("-3_3/4 + 2_2/4"));
    }

    @Test
    void testAdditionWithNegatives5() {
        assertEquals("-1_1/4", FracCalc.produceAnswer("2_2/4 + -3_3/4"));
    }

    @Test
    void testAdditionImproperFractionsAndReductions1() {
        assertEquals("5_5/6", FracCalc.produceAnswer("20/8 + 3_1/3"));
    }

    @Test
    void testAdditionImproperFractionsAndReductions2() {
        assertEquals("1_1/20", FracCalc.produceAnswer("4/5 + 2/8"));
    }

    @Test
    void testAdditionCombined1() {
        assertEquals("-9035", FracCalc.produceAnswer("-9035 + 0"));
    }

    @Test
    void testAdditionCombined2() {
        assertEquals("-64", FracCalc.produceAnswer("64 + -128"));
    }

    @Test
    void testAdditionCombined3() {
        assertEquals("-133", FracCalc.produceAnswer("-98 + -35"));
    }

    @Test
    void testAdditionCombined4() {
        assertEquals("62_11/19", FracCalc.produceAnswer("0 + 34_543/19"));
    }

    @Test
    void testAdditionCombined5() {
        assertEquals("-44_229/888", FracCalc.produceAnswer("-38_3/72 + -4_82/37"));
    }

    @Test
    void testSubtractionSimple1() {
        assertEquals("1/5", FracCalc.produceAnswer("3/5 - 2/5"));
    }

    @Test
    void testSubtractionSimple2() {
        assertEquals("0", FracCalc.produceAnswer("1/5 - 1/5"));
    }

    @Test
    void testSubtractionSimple3() {
        assertEquals("0", FracCalc.produceAnswer("4_1/2 - 4_1/2"));
    }

    @Test
    void testSubtractionReduce1() {
        assertEquals("4/5", FracCalc.produceAnswer("9/10 - 1/10"));
    }

    @Test
    void testSubtractionReduce2() {
        assertEquals("1/5", FracCalc.produceAnswer("5/10 - 3/10"));
    }

    @Test
    void testSubtractionWholeNumbers1() {
        assertEquals("0", FracCalc.produceAnswer("68591 - 68591"));
    }

    @Test
    void testSubtractionWholeNumbers2() {
        assertEquals("7", FracCalc.produceAnswer("42 - 35"));
    }

    @Test
    void testSubtractionWithNegatives1() {
        assertEquals("-2/5", FracCalc.produceAnswer("2/5 - 4/5"));
    }

    @Test
    void testSubtractionWithNegatives2() {
        assertEquals("-7/8", FracCalc.produceAnswer("5_3/4 - 6_5/8"));
    }

    @Test
    void testSubtractionWithNegatives3() {
        assertEquals("-1_1/4", FracCalc.produceAnswer("-3_3/4 - -2_2/4"));
    }

    @Test
    void testSubtractionWithNegatives4() {
        assertEquals("-1_5/8", FracCalc.produceAnswer("4_1/2 - 5_9/8"));
    }

    @Test
    void testSubtractionWithNegatives5() {
        assertEquals("-1_1/8", FracCalc.produceAnswer("3_3/4 - 4_7/8"));
    }

    @Test
    void testSubtractionWithNegatives6() {
        assertEquals("-6_1/4", FracCalc.produceAnswer("-3_3/4 - 2_2/4"));
    }

    @Test
    void testSubtractionWithNegatives7() {
        assertEquals("-36891", FracCalc.produceAnswer("48623 - 85514"));
    }

    @Test
    void testSubtractionWithNegatives8() {
        assertEquals("-9284", FracCalc.produceAnswer("0 - 9284"));
    }

    @Test
    void testSubtractionImproperFractionsAndReductions1() {
        assertEquals("53/96", FracCalc.produceAnswer("75/32 - 43/24"));
    }

    @Test
    void testSubtractionImproperFractionsAndReductions2() {
        assertEquals("16_23/24", FracCalc.produceAnswer("75/4 - 43/24"));
    }

    @Test
    void testSubtractionCombined1() {
        assertEquals("12_3/8", FracCalc.produceAnswer("5_3/4 - -6_5/8"));
    }

    @Test
    void testSubtractionCombined2() {
        assertEquals("8_5/21", FracCalc.produceAnswer("-12_3/7 - -20_2/3"));
    }

    @Test
    void testSubtractionCombined3() {
        assertEquals("-65_247/336", FracCalc.produceAnswer("-32_75/16 - 27_43/21"));
    }

    @Test
    void testMultiplicationBasic1() {
        assertEquals("3", FracCalc.produceAnswer("1_1/2 * 2"));
    }

    @Test
    void testMultiplicationBasic2() {
        assertEquals("6/25", FracCalc.produceAnswer("3/5 * 2/5"));
    }

    @Test
    void testMultiplicationBasic3() {
        assertEquals("164268", FracCalc.produceAnswer("234 * 702"));
    }

    @Test
    void testMultiplicationBasic4() {
        assertEquals("216", FracCalc.produceAnswer("12 * 18"));
    }

    @Test
    void testMultiplicationBasic5() {
        assertEquals("8", FracCalc.produceAnswer("12/3 * 2/1"));
    }

    @Test
    void testMultiplicationBasic6() {
        assertEquals("2", FracCalc.produceAnswer("16 * 1/8"));
    }

    @Test
    void testMultiplicationBasic7() {
        assertEquals("2", FracCalc.produceAnswer("3 * 2/3"));
    }

    @Test
    void testMultiplicationBasic8() {
        assertEquals("1_1/2", FracCalc.produceAnswer("6 * 1/4"));
    }

    @Test
    void testMultiplicationBasic9() {
        assertEquals("8994872", FracCalc.produceAnswer("1 * 8994872"));
    }

    @Test
    void testMultiplicationBasic10() {
        assertEquals("378/943", FracCalc.produceAnswer("27/41 * 14/23"));
    }

    @Test
    void testMultiplicationBasic11() {
        assertEquals("5_929/943", FracCalc.produceAnswer("1_27/41 * 3_14/23"));
    }

    @Test
    void testMultiplicationWithNegatives1() {
        assertEquals("-8", FracCalc.produceAnswer("12/3 * -2/1"));
    }

    @Test
    void testMultiplicationWithNegatives2() {
        assertEquals("-8", FracCalc.produceAnswer("-12/3 * 2/1"));
    }

    @Test
    void testMultiplicationWithNegatives3() {
        assertEquals("8", FracCalc.produceAnswer("-12/3 * -2/1"));
    }

    @Test
    void testMultiplicationWithNegatives4() {
        assertEquals("-15_5/7", FracCalc.produceAnswer("-3_2/3 * 4_2/7"));
    }

    @Test
    void testMultiplicationWithNegatives5() {
        assertEquals("-15_5/7", FracCalc.produceAnswer("3_2/3 * -4_2/7"));
    }

    @Test
    void testMultiplicationWithNegatives6() {
        assertEquals("15_5/7", FracCalc.produceAnswer("-3_2/3 * -4_2/7"));
    }

    @Test
    void testMultiplicationWithNegatives7() {
        assertEquals("-842346", FracCalc.produceAnswer("1 * -842346"));
    }

    @Test
    void testMultiplicationWithNegatives8() {
        assertEquals("-75421", FracCalc.produceAnswer("-1 * 75421"));
    }

    @Test
    void testMultiplicationWithNegatives9() {
        assertEquals("37953", FracCalc.produceAnswer("-1 * -37953"));
    }

    @Test
    void testMultiplicationByZero1() {
        assertEquals("0", FracCalc.produceAnswer("0 * 4/5"));
    }

    @Test
    void testMultiplicationByZero2() {
        assertEquals("0", FracCalc.produceAnswer("0 * 0"));
    }

    @Test
    void testMultiplicationByZero3() {
        assertEquals("0", FracCalc.produceAnswer("0 * 9321"));
    }

    @Test
    void testMultiplicationByZero4() {
        assertEquals("0", FracCalc.produceAnswer("0 * -5902"));
    }

    @Test
    void testMultiplicationByZero5() {
        assertEquals("0", FracCalc.produceAnswer("146 * 0"));
    }

    @Test
    void testMultiplicationByZero6() {
        assertEquals("0", FracCalc.produceAnswer("3_25/26 * 0"));
    }

    @Test
    void testMultiplicationByZero7() {
        assertEquals("0", FracCalc.produceAnswer("-24_1/3 * 0"));
    }

    @Test
    void testMultiplicationCombined1() {
        assertEquals("1065_115/168", FracCalc.produceAnswer("-32_75/16 * -27_43/21"));
    }

    @Test
    void testMultiplicationCombined2() {
        assertEquals("-15_67/943", FracCalc.produceAnswer("1_27/41 * -3_140/23"));
    }

    @Test
    void testMultiplicationCombined3() {
        assertEquals("4_2/3", FracCalc.produceAnswer("3_2/4 * 4/3"));
    }

    @Test
    void testDivisionBasic1() {
        assertEquals("9/16", FracCalc.produceAnswer("3/4 / 4/3"));
    }

    @Test
    void testDivisionBasic2() {
        assertEquals("2_1/4", FracCalc.produceAnswer("3/2 / 2/3"));
    }

    @Test
    void testDivisionBasic3() {
        assertEquals("9457", FracCalc.produceAnswer("9457 / 1"));
    }

    @Test
    void testDivisionBasic4() {
        assertEquals("6/11", FracCalc.produceAnswer("6 / 11"));
    }

    @Test
    void testDivisionBasic5() {
        assertEquals("4/9", FracCalc.produceAnswer("4 / 9"));
    }

    @Test
    void testDivisionBasic6() {
        assertEquals("1", FracCalc.produceAnswer("23 / 23"));
    }

    @Test
    void testDivisionBasic7() {
        assertEquals("2_6/7", FracCalc.produceAnswer("20 / 7"));
    }

    @Test
    void testDivisionBasic8() {
        assertEquals("13/24", FracCalc.produceAnswer("1_1/12 / 2"));
    }

    @Test
    void testDivisionBasic9() {
        assertEquals("1", FracCalc.produceAnswer("3/4 / 3/4"));
    }

    @Test
    void testDivisionWithNegatives1() {
        assertEquals("1_5/8", FracCalc.produceAnswer("-13 / -8"));
    }

    @Test
    void testDivisionWithNegatives2() {
        assertEquals("-2803", FracCalc.produceAnswer("-2803 / 1"));
    }

    @Test
    void testDivisionWithNegatives3() {
        assertEquals("-12457", FracCalc.produceAnswer("12457 / -1"));
    }

    @Test
    void testDivisionWithNegatives4() {
        assertEquals("45236", FracCalc.produceAnswer("-45236 / -1"));
    }

    @Test
    void testDivisionWithNegatives5() {
        assertEquals("-2_6/7", FracCalc.produceAnswer("-20 / 7"));
    }

    @Test
    void testDivisionWithNegatives6() {
        assertEquals("1_13/32", FracCalc.produceAnswer("-3_3/4 / -2_2/3"));
    }

    @Test
    void testDivisionWithNegatives7() {
        assertEquals("-1_13/32", FracCalc.produceAnswer("-3_3/4 / 2_2/3"));
    }

    @Test
    void testDivisionWithNegatives8() {
        assertEquals("-1_13/32", FracCalc.produceAnswer("3_3/4 / -2_2/3"));
    }

    @Test
    void testDivisionWithZero1() {
        assertEquals("0", FracCalc.produceAnswer("-0 / 98701"));
    }

    @Test
    void testDivisionWithZero2() {
        assertEquals("0", FracCalc.produceAnswer("-0 / -98701"));
    }

    @Test
    void testDivisionWithZero3() {
        assertEquals("0", FracCalc.produceAnswer("0 / -98701"));
    }

    @Test
    void testDivisionWithZero4() {
        assertEquals("0", FracCalc.produceAnswer("0 / 37569"));
    }

    @Test
    void testDivisionWithZero5() {
        assertEquals("0", FracCalc.produceAnswer("0 / 46/27"));
    }

    @Test
    void testDivisionWithZero6() {
        assertEquals("0", FracCalc.produceAnswer("0/24 / 1/46"));
    }

    @Test
    void testDivisionWithZero7() {
        assertEquals("0", FracCalc.produceAnswer("0/11 / 6/7"));
    }

    @Test
    void testDivisionCombined1() {
        assertEquals("2_2/3", FracCalc.produceAnswer("16/4 / 3/2"));
    }

    @Test
    void testDivisionCombined2() {
        assertEquals("-2_2/3", FracCalc.produceAnswer("16/4 / -3/2"));
    }

    @Test
    void testDivisionCombined3() {
        assertEquals("6_661/5520", FracCalc.produceAnswer("-38_3/72 / -4_82/37"));
    }

    @Test
    void testDivisionCombined4() {
        assertEquals("-5/21", FracCalc.produceAnswer("1_2/3 / -5_6/3"));
    }

    @Test
    void multipleOps1() {
        assertEquals("-20/21", FracCalc.produceAnswer("1_2/3 + 5/4 + 5_5/4 - 2_2/4 / -5_6/3"));
    }

    @Test
    void multipleOps2() {
        assertEquals("4", FracCalc.produceAnswer("1 + 3 + -3 - -3"));
    }

    @Test
    void multipleOps3() {
        assertEquals("0", FracCalc.produceAnswer("12 * 18 * 18 * 0"));
    }

    @Test
    void multipleOps4() {
        assertEquals("3_47/60", FracCalc.produceAnswer("20/8 + 3_1/3 - 4/5 - 5/4"));
    }

    @Test
    void multipleOps5() {
        assertEquals("1", FracCalc.produceAnswer("12345 - 12345 + 12345 - 12345 + 1"));
    }

    @Test
    void multipleOps6() {
        assertEquals("0", FracCalc.produceAnswer("0 * 0 / 1 / 4/6 / 2_3/4"));
    }

    @Test
    void multipleOps7() {
        assertEquals("-1", FracCalc.produceAnswer("1/5 + 1/5 + 1/5 + 1/5 + 1/5 - 2"));
    }

    @Test
    void multipleOps8() {
        assertEquals("0", FracCalc.produceAnswer("-4 + 1 + 1 + 1 + 1"));
    }

    @Test
    void multipleOps9() {
        assertEquals("4_1/2", FracCalc.produceAnswer("16/4 / 3/2 * 3/2 + 1/2"));
    }

    @Test
    void multipleOps10() {
        assertEquals("0", FracCalc.produceAnswer("12457 / -1 + 12457"));
    }

    @Test
    void multipleOps11() {
        assertEquals("7", FracCalc.produceAnswer("5_3/4 - -6_8/8 - 5_3/4"));
    }

    @Test
    void multipleOps12() {
        assertEquals("4", FracCalc.produceAnswer("2 * 3 - 6 + 1_1/2 + 1/2 - 1/2 - 1/2 + 3"));
    }

    @Test
    void multipleOps13() {
        assertEquals("-4", FracCalc.produceAnswer("2 * 3 - 6 + -1_1/2 + -1/2 - -1/2 - -1/2 - 3"));
    }

    @Test
    void multipleOps14() {
        assertEquals("4", FracCalc.produceAnswer("20 / 5 * -1 + 8"));
    }
}
