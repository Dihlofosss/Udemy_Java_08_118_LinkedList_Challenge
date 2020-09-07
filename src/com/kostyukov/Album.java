package com.kostyukov;

import java.util.ArrayList;

public class Album
{
	private ArrayList<Song> songs = new ArrayList<>();
	private String albumName;
	
	public String getAlbumName()
	{
		return albumName;
	}
	
	public ArrayList<Song> getSongs()
	{
		return songs;
	}
	
	public Album(String albumName)
	{
		this.albumName = albumName;
	}
	
	public void addSong(String songName, double songDuration)
	{
		if (getSong(songName) != null){
			System.out.println("Song " + songName + " is already in the Album");
			return;
		}
		addSong(new Song(songName, songDuration));
	}
	
	public void addSong(Song newSong)
	{
		songs.add(newSong);
	}
	
	public Song getSong(int songID)
	{
		return songs.get(songID);
	}
	
	public Song getSong(String songName)
	{
		for (int i = 0; i < songs.size(); i++)
		{
			if (songs.get(i).getTitle().equals(songName))
				return songs.get(i);
		}
		System.out.println("Song " + songName + " is not existing");
		return null;
	}
}
