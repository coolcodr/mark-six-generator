package com.coolcodr.marksix.history;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HistoryLoader
{
	private static final Pattern pattern = Pattern
	.compile("(.*?),\\s(.*?),\\s(\\d*),\\s(\\d*),\\s(\\d*),\\s(\\d*),\\s(\\d*),\\s(\\d*),\\s(\\d*)",
			Pattern.MULTILINE | Pattern.DOTALL | Pattern.CASE_INSENSITIVE);

	public void load(Hashtable<String, HistoryEntry> result, File file) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		while ((line = reader.readLine()) != null)
		{
			Matcher m = pattern.matcher(line);
			if (m.find())
			{
				String id = m.group(1);
				String date = m.group(2);
				int[] numbers = new int[7];
				numbers[0] = Integer.parseInt(m.group(3));
				numbers[1] = Integer.parseInt(m.group(4));
				numbers[2] = Integer.parseInt(m.group(5));
				numbers[3] = Integer.parseInt(m.group(6));
				numbers[4] = Integer.parseInt(m.group(7));
				numbers[5] = Integer.parseInt(m.group(8));
				numbers[6] = Integer.parseInt(m.group(9));
				HistoryEntry history = new HistoryEntry(id, date, numbers);
				result.put(history.getId(), history);
			}
		}
	}
}
