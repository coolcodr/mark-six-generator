package com.coolcodr.marksix.history;

public class HistoryEntry
{
	private String id;
	private String date;
	private int[] numbers;

	public HistoryEntry(String id, String date, int[] numbers)
	{
		super();
		this.id = id;
		this.date = date;
		this.numbers = numbers;
	}

	public String getId()
	{
		return id;
	}

	public String getDate()
	{
		return date;
	}

	public int[] getNumbers()
	{
		return numbers;
	}
}
