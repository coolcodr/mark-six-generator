package com.coolcodr.marksix;

import java.io.File;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Vector;

import org.apache.log4j.Logger;

public class Console {

    private static final Logger logger = Logger.getLogger(Console.class);

    private int[] excludeNumbers;

    public static void main(String[] args) {
        try {
            Hashtable<String, MarkSixHistory> result = new Hashtable<String, MarkSixHistory>();
            if (args.length > 0 && args[0].equals("parse")) {
                System.out.println("Parse History");
                HistoryParser parser = new HistoryParser();
                for (int i = 1; i <= 3; i++) {
                    System.out.println(i + ".html");
                    parser.parse(result, new File(i + ".html"));
                }
                HistorySaver saver = new HistorySaver();
                saver.save(result, new File("parse.csv"));
            } else {
                int[] excludeInt = new int[0];
                if (args.length > 0) {
                    String exclude = args[0];
                    String[] excludeNumberArray = exclude.split(",");
                    excludeInt = new int[excludeNumberArray.length];
                    for (int i = 0; i < excludeNumberArray.length; i++) {
                        excludeInt[i] = Integer.parseInt(excludeNumberArray[i]);
                    }
                }
                HistoryLoader loader = new HistoryLoader();
                loader.load(result, new File("history.csv"));

                Console c = new Console(result, excludeInt);
                System.out.println(c.getFinalResult());
            }
        } catch (Exception ex) {
            logger.error(ex, ex);
            ex.printStackTrace();
        }
    }

    private Hashtable<String, MarkSixHistory> history;

    public Console(Hashtable<String, MarkSixHistory> history, int[] excludeNumbers) {
        this.history = history;
        this.excludeNumbers = excludeNumbers;
    }

    private int[] generateNumbers() {
        SecureRandom generator = new SecureRandom();
        int[] results = new int[6];
        for (int i = 0; i < 6; i++) {
            int randomIndex = generator.nextInt(48);
            results[i] = randomIndex + 1;
        }
        Arrays.sort(results);
        return results;
        // return new int[] { 19, 22, 23, 32, 44, 45};
    }

    public String getFinalResult() {
        int[] results = generateNumbers();
        while (!isValid(results))
            results = generateNumbers();
        return getNumberString(results);
    }

    private String getNumberString(int[] results) {
        StringBuilder numbersStr = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            if (numbersStr.length() > 0)
                numbersStr.append(", ");
            numbersStr.append(results[i]);
        }
        return numbersStr.toString();
    }

    private boolean isValid(int[] results) {
        if (!matchDuplicate(results))
            return false;
        if (!matchHistory(results))
            return false;
        if (!matchSum(results))
            return false;
        if (!matchDoor(results))
            return false;
        if (!matchNumbertype(results))
            return false;
        if (!matchConsecutive(results))
            return false;
        if (!matchExcludeNumber(results))
            return false;
        return true;
    }

    private boolean matchDoor(int[] results) {
        int[] door1 = new int[] { 1, 10 };
        int[] door2 = new int[] { 11, 20 };
        int[] door3 = new int[] { 21, 30 };
        int[] door4 = new int[] { 31, 40 };
        int[] door5 = new int[] { 41, 49 };

        int[] matchDoors = new int[] { 0, 0, 0, 0, 0 };
        for (int i = 0; i < 6; i++) {
            int number = results[i];
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
            System.out.println("Reject(Door): " + getNumberString(results));
            return false;
        }

        return true;
    }

    private boolean matchNumbertype(int[] results) {
        int odd = 0;
        int even = 0;
        for (int i = 0; i < 6; i++) {
            int number = results[i];
            if (number % 2 == 0)
                even++;
            else
                odd++;
        }
        if ((odd == 0) || (even == 0)) {
            System.out.println("Reject(Odd/Even): " + getNumberString(results));
            return false;
        }
        return true;
    }

    private boolean matchConsecutive(int[] results) {
        int lastNumber = -1;
        int consecutive = 0;
        for (int i = 0; i < 6; i++) {
            int number = results[i];
            if (lastNumber == -1) {
                lastNumber = number;
                continue;
            }
            if (lastNumber + 1 == number)
                consecutive++;
        }
        if (consecutive >= 3) {
            System.out.println("Reject(Consecutive): " + getNumberString(results));
            return false;
        }
        return true;
    }

    private boolean matchDuplicate(int[] results) {
        int lastNumber = -1;
        boolean duplicate = false;
        for (int i = 0; i < 6; i++) {
            int number = results[i];
            if (lastNumber == -1) {
                lastNumber = number;
                continue;
            }
            if (lastNumber == number)
                duplicate = true;
            lastNumber = number;
        }
        if (duplicate) {
            System.out.println("Reject(Duplicate): " + getNumberString(results));
            return false;
        }
        return true;
    }

    private boolean matchSum(int[] results) {
        int lowerLimit = 100;// 140;
        int upperLimit = 210;// 185;
        int sum = 0;
        for (int i = 0; i < 6; i++) {
            int number = results[i];
            sum += number;
        }
        if ((sum < lowerLimit) || (sum > upperLimit)) {
            System.out.println("Reject(Sum): " + getNumberString(results));
            return false;
        }
        return true;
    }

    private boolean matchHistory(int[] results) {
        Vector<String> v = new Vector<String>(history.keySet());
        Collections.sort(v);
        boolean fullMatch = false;
        for (String key : v) {
            MarkSixHistory historyNumbers = history.get(key);
            int matchCount = 0;
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {
                    if (results[i] == historyNumbers.getNumbers()[j]) {
                        matchCount++;
                    }
                }
            }
            if (matchCount >= 5) {
                fullMatch = true;
                break;
            }
        }

        if (fullMatch) {
            System.out.println("Reject(history): " + getNumberString(results));
            return false;
        }
        return true;
    }

    private boolean matchExcludeNumber(int[] results) {

        for (int i = 0; i < excludeNumbers.length; i++) {
            for (int j = 0; j < results.length; j++) {
                if (excludeNumbers[i] == results[j]) {
                    System.out.println("Reject(Exclude): " + getNumberString(results));
                    return false;
                }
            }
        }
        return true;
    }
}
