package com.kostyukov;

public class Song
{
	private String title;
	private float duration;
	
	public Song(String title, float duration)
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
	
	public float getDuration()
	{
		return duration;
	}
	
	public void setDuration(float duration)
	{
		this.duration = duration;
	}
}