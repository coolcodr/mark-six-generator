package com.coolcodr.marksix.filter;

import com.coolcodr.marksix.StringHelper;

public class DuplicateFilter implements IFilter {

    @Override
    public boolean isMatch(int[] numbers) {
        int lastNumber = -1;
        boolean duplicate = false;
        for (int i = 0; i < 6; i++) {
            int number = numbers[i];
            if (lastNumber == -1) {
                lastNumber = number;
                continue;
            }
            if (lastNumber == number)
                duplicate = true;
            lastNumber = number;
        }
        if (duplicate) {
            System.out.println("Reject(Duplicate): " + StringHelper.getNumberString(numbers));
            return false;
        }
        return true;
    }

}
