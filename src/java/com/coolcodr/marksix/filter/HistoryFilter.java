package com.coolcodr.marksix.filter;

import java.util.Collections;
import java.util.Hashtable;
import java.util.Vector;

import com.coolcodr.marksix.StringHelper;
import com.coolcodr.marksix.history.HistoryEntry;

public class HistoryFilter implements IFilter {

    private Hashtable<String, HistoryEntry> history;

    public HistoryFilter(Hashtable<String, HistoryEntry> history) {
        this.history = history;
    }

    @Override
    public boolean isMatch(int[] numbers) {
        Vector<String> v = new Vector<String>(history.keySet());
        Collections.sort(v);
        boolean fullMatch = false;
        for (String key : v) {
            HistoryEntry historyNumbers = history.get(key);
            int matchCount = 0;
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {
                    if (numbers[i] == historyNumbers.getNumbers()[j]) {
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
            System.out.println("Reject(history): " + StringHelper.getNumberString(numbers));
            return false;
        }
        return true;
    }

}
