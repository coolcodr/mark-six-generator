package com.coolcodr.marksix.filter;

import com.coolcodr.marksix.StringHelper;

public class ExcludeFilter implements IFilter {

    private int[] excludeNumbers;

    public ExcludeFilter(int[] excludeNumbers) {
        this.excludeNumbers = excludeNumbers;
    }

    @Override
    public boolean isMatch(int[] numbers) {
        for (int i = 0; i < excludeNumbers.length; i++) {
            for (int j = 0; j < numbers.length; j++) {
                if (excludeNumbers[i] == numbers[j]) {
                    System.out.println("Reject(Exclude): " + StringHelper.getNumberString(numbers));
                    return false;
                }
            }
        }
        return true;
    }

}
