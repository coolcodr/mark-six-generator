package com.coolcodr.marksix.filter;

import com.coolcodr.marksix.StringHelper;

public class NumberTypeFilter implements IFilter {

    @Override
    public boolean isMatch(int[] numbers) {
        int odd = 0;
        int even = 0;
        for (int i = 0; i < 6; i++) {
            int number = numbers[i];
            if (number % 2 == 0)
                even++;
            else
                odd++;
        }
        if ((odd == 0) || (even == 0)) {
            System.out.println("Reject(Odd/Even): " + StringHelper.getNumberString(numbers));
            return false;
        }
        return true;
    }

}
