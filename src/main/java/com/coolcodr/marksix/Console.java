package com.coolcodr.marksix;

import java.io.File;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;

import com.coolcodr.marksix.filter.IFilter;
import com.coolcodr.marksix.history.HistoryEntry;
import com.coolcodr.marksix.history.HistoryLoader;
import com.coolcodr.marksix.history.HistoryParser;
import com.coolcodr.marksix.history.HistorySaver;

public class Console {

    private static final Logger logger = Logger.getLogger(Console.class);

    private List<IFilter> filters = new ArrayList<IFilter>();

    public static void main(String[] args) {
        try {
            Hashtable<String, HistoryEntry> result = new Hashtable<String, HistoryEntry>();
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

                Console c = new Console();
                System.out.println(StringHelper.getNumberString(c.generate()));
            }
        } catch (Exception ex) {
            logger.error(ex, ex);
            ex.printStackTrace();
        }
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
    }

    public int[] generate() {
        int[] results = generateNumbers();
        while (!isValid(results))
            results = generateNumbers();
        return results;
    }

    private boolean isValid(int[] numbers) {
        for (IFilter filter : filters) {
            boolean match = filter.isMatch(numbers);
            if (!match) {
                return false;
            }
        }
        return true;
    }

    public void addFilter(IFilter filter) {
        this.filters.add(filter);
    }

}
