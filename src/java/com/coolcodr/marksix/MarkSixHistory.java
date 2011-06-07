package com.coolcodr.marksix;

public class MarkSixHistory
{
	private String id;
	private String date;
	private int[] numbers;

	public MarkSixHistory(String id, String date, int[] numbers)
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
