package com.kostyukov;

public class Song
{
	private String title;
	private double duration;
	
	public Song(String title, double duration)
	{
		this.title = title;
		this.duration = duration;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public void setDuration(float duration)
	{
		this.duration = duration;
	}
	
	@Override
	public String toString()
	{
		return title + ": " + duration;
	}
}