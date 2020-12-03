package software.bigbade.fractioncalculator;

public class FracCalcTestHelper {
    public FracCalcTestHelper() {
    }

    public static boolean areFracsEqual(String answer, String candidate) {
        int answerNumerator = parseValue(answer, 1);
        int answerDenominator = parseValue(answer, 2);
        int answerWhole = parseValue(answer, 3);
        boolean isNegative = answerWhole < 0;
        answerNumerator += Math.abs(answerWhole) * answerDenominator;
        if (isNegative) {
            answerNumerator *= -1;
        }

        int candidateNumerator = parseValue(candidate, 1);
        int candidateDenominator = parseValue(candidate, 2);
        int candidateWhole = parseValue(candidate, 3);
        if (candidateDenominator == 0) {
            return false;
        } else {
            isNegative = candidateWhole < 0;
            candidateNumerator += Math.abs(candidateWhole) * candidateDenominator;
            if (isNegative) {
                candidateNumerator *= -1;
            }

            return answerNumerator * candidateDenominator == candidateNumerator * answerDenominator;
        }
    }

    private static int parseValue(String value, int what) {
        int whole = 0;
        int iUnderscore = value.indexOf("_");
        int iNum = iUnderscore + 1;
        int iSlash = value.indexOf("/");
        int iWholeEnd;
        if (iUnderscore == -1) {
            if (iSlash == -1) {
                iWholeEnd = value.length();
            } else {
                iWholeEnd = 0;
            }
        } else {
            if (iSlash == -1) {
                return 0;
            }

            iWholeEnd = iUnderscore;
        }

        String numString;
        if (iWholeEnd != 0) {
            numString = value.substring(0, iWholeEnd);
            if (notInteger(numString)) {
                return 0;
            }

            whole = Integer.parseInt(numString);
        }

        if (iSlash == -1) {
            if (what == 0) {
                return 1;
            } else if (what == 1) {
                return 0;
            } else {
                return what == 2 ? 1 : whole;
            }
        } else {
            numString = value.substring(iNum, iSlash);
            if (notInteger(numString)) {
                return 0;
            } else {
                int num = Integer.parseInt(numString);
                String denString = value.substring(iSlash + 1);
                if (notInteger(denString)) {
                    return 0;
                } else {
                    int den = Integer.parseInt(denString);
                    if (what == 0) {
                        return 1;
                    } else {
                        if (den < 0) {
                            den *= -1;
                            num *= -1;
                        }

                        if (what == 1) {
                            return num;
                        } else {
                            return what == 2 ? den : whole;
                        }
                    }
                }
            }
        }
    }

    private static boolean notInteger(String s) {
        int i = 0;
        if (s.charAt(0) == '-') {
            i = 1;
        }

        while(i < s.length()) {
            char c = s.charAt(i);
            if (c < '0' || c > '9') {
                return true;
            }

            ++i;
        }

        return false;
    }
}
