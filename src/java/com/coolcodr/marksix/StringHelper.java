package com.coolcodr.marksix;

public class StringHelper {
    public static String getNumberString(int[] results) {
        StringBuilder numbersStr = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            if (numbersStr.length() > 0)
                numbersStr.append(", ");
            numbersStr.append(results[i]);
        }
        return numbersStr.toString();
    }
}
