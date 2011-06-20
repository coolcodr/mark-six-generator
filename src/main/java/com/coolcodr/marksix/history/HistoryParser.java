package com.coolcodr.marksix.history;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HistoryParser
{
	private static final Pattern pattern = Pattern
			.compile("(\\d*/\\d*)&nbsp;.*?>(\\d*/\\d*/\\d*)</td>.*?no_(\\d*?)_s.gif.*?no_(\\d*?)_s.gif.*?no_(\\d*?)_s.gif.*?no_(\\d*?)_s.gif.*?no_(\\d*?)_s.gif.*?no_(\\d*?)_s.gif.*?no_(\\d*?)_s.gif",
					Pattern.MULTILINE | Pattern.DOTALL | Pattern.CASE_INSENSITIVE);

	public void parse(Hashtable<String, HistoryEntry> result, File file) throws IOException
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
				
				System.out.print(history.getId() +", " + history.getDate());
				for (int i = 0; i < 7; i++)
				{
					System.out.print(", ");
					System.out.print(history.getNumbers()[i]);
				}
				System.out.println();			
			}
		}
	}
}
