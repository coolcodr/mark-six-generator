package com.coolcodr.marksix.filter;

import com.coolcodr.marksix.StringHelper;

public class ConsecutiveFilter implements IFilter {

    @Override
    public boolean isMatch(int[] numbers) {
        int lastNumber = -1;
        int consecutive = 0;
        for (int i = 0; i < 6; i++) {
            int number = numbers[i];
            if (lastNumber == -1) {
                lastNumber = number;
                continue;
            }
            if (lastNumber + 1 == number)
                consecutive++;
        }
        if (consecutive >= 3) {
            System.out.println("Reject(Consecutive): " + StringHelper.getNumberString(numbers));
            return false;
        }
        return true;
    }

}
