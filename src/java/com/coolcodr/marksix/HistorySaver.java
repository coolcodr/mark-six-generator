package com.coolcodr.marksix;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Vector;

public class HistorySaver
{
	public void save(Hashtable<String, MarkSixHistory> result, File file) throws IOException
	{
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		Vector<String> v = new Vector<String>(result.keySet());
		Collections.sort(v);
		for(String key : v)
		{
			MarkSixHistory history = result.get(key);
			writer.write(history.getId() +", " + history.getDate());
			for(int i=0;i<7;i++)
			{
				writer.write(", ");
				writer.write(String.valueOf(history.getNumbers()[i]));
			}
			writer.write("\n");
		}
		writer.close();
	}
}
