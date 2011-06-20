package com.coolcodr.marksix.filter;

import com.coolcodr.marksix.StringHelper;

public class DoorFilter implements IFilter {

    @Override
    public boolean isMatch(int[] numbers) {
        int[] door1 = new int[] { 1, 10 };
        int[] door2 = new int[] { 11, 20 };
        int[] door3 = new int[] { 21, 30 };
        int[] door4 = new int[] { 31, 40 };
        int[] door5 = new int[] { 41, 49 };

        int[] matchDoors = new int[] { 0, 0, 0, 0, 0 };
        for (int i = 0; i < 6; i++) {
            int number = numbers[i];
            if ((number >= door1[0]) && (number <= door1[1]))
                matchDoors[0] = 1;
            if ((number >= door2[0]) && (number <= door2[1]))
                matchDoors[1] = 1;
            if ((number >= door3[0]) && (number <= door3[1]))
                matchDoors[2] = 1;
            if ((number >= door4[0]) && (number <= door4[1]))
                matchDoors[3] = 1;
            if ((number >= door5[0]) && (number <= door5[1]))
                matchDoors[4] = 1;
        }

        int sum = 0;
        for (int i = 0; i < 5; i++)
            sum += matchDoors[i];

        if ((sum >= 5) || (sum <= 1)) {
            System.out.println("Reject(Door): " + StringHelper.getNumberString(numbers));
            return false;
        }

        return true;
    }

}
