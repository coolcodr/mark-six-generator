package com.coolcodr.marksix.filter;

import com.coolcodr.marksix.StringHelper;

public class SumFilter implements IFilter {

    @Override
    public boolean isMatch(int[] numbers) {
        int lowerLimit = 100;// 140;
        int upperLimit = 210;// 185;
        int sum = 0;
        for (int i = 0; i < 6; i++) {
            int number = numbers[i];
            sum += number;
        }
        if ((sum < lowerLimit) || (sum > upperLimit)) {
            System.out.println("Reject(Sum): " + StringHelper.getNumberString(numbers));
            return false;
        }
        return true;
    }

}
