// Create a program that implements a playlist for songs
// Create a Song class having Title and Duration for a song.
// The program will have an Album class containing a list of songs.
// The albums will be stored in an ArrayList
// Songs from different albums can be added to the playlist and will appear in the list in the order
// they are added.
// Once the songs have been added to the playlist, create a menu of options to:-
// Quit,Skip forward to the next song, skip backwards to a previous song.  Replay the current song.
// List the songs in the playlist
// A song must exist in an album before it can be added to the playlist (so you can only play songs that
// you own).
// Hint:  To replay a song, consider what happened when we went back and forth from a city before we
// started tracking the direction we were going.
// As an optional extra, provide an option to remove the current song from the playlist
// (hint: listiterator.remove()

package com.kostyukov;

import java.util.*;

public class Player
{
	private static LinkedList<Song> playlist = new LinkedList<>();
	private static ArrayList<Album> albums = new ArrayList<>();
	private static Album currentAlbum;
	private static boolean goingForward = true;
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args)
	{
		FillAlbums();
		FillPlaylist();
		PrintMenuHelp();
		
		boolean exit = false;
		int menu;
		
		while (!exit)
		{
			System.out.println("\nChoose option:");
			menu = scanner.nextInt();
			scanner.nextLine();
			
			switch (menu){
				case 0 -> exit = true;
				case 1 -> PrintAlbums();
				case 2 -> SelectAlbum();
				case 3 -> PrintAlbumSongs();
				case 4 -> AddSongToPlaylist();
				case 5 -> Play();
				case 9 -> PrintMenuHelp();
			}
		}
	}
	
	private static void Play()
	{
		ListIterator<Song> listIterator = playlist.listIterator();
		boolean exit = false;
		int menu;
		
		PrintPlayHelp();
		
		while (!exit)
		{
			System.out.println("\nChoose option:");
			menu = scanner.nextInt();
			scanner.nextLine();
			
			switch (menu){
				case 0 -> exit = true;
				case 1 -> SkipBackwards(listIterator);
				case 2 -> PlaySong(listIterator);
				case 3 -> SkipForwards(listIterator);
				case 4 -> PrintPlaylist();
				case 5 -> RemoveSong(listIterator);
				case 9 -> PrintPlayHelp();
			}
		}
	}
	
	private static void SkipBackwards(ListIterator<Song> listIterator)
	{
		if (goingForward)
		{
			if (listIterator.hasPrevious())
				listIterator.previous();
			goingForward = false;
		}
		
		if (listIterator.hasPrevious())
		{
			System.out.println("Previous song: " + listIterator.previous());
		}
		else
		{
			System.out.println("Start of the Playlist");
			goingForward = true;
		}
	}
	
	private static void PlaySong(ListIterator<Song> listIterator)
	{
		if (listIterator.hasPrevious() && goingForward)
		{
			System.out.println("Playing " + listIterator.previous());
			goingForward = false;
		}
		else {
			
			System.out.println("Playing " + listIterator.next());
			goingForward = true;
		}
	}
	
	private static void SkipForwards(ListIterator<Song> listIterator)
	{
		if (!goingForward){
			if (listIterator.hasNext())
				listIterator.next();
			goingForward = true;
		}
		
		if (listIterator.hasNext())
		{
			System.out.println("Next song: " + listIterator.next());
		}
		else
		{
			System.out.println("End of the Playlist");
			goingForward = false;
		}
	}
	
	public static void RemoveSong(ListIterator<Song> listIterator)
	{
		listIterator.remove();
		if (listIterator.hasNext())
			System.out.println("Next song: " + listIterator.next());
		else if (listIterator.hasPrevious())
			System.out.println("Previous song: " + listIterator.previous());
	}
	
	private static void PrintPlayHelp()
	{
		System.out.println("Press:\n" +
				"0. - To return to main menu\n" +
				"1. - To Skip backward to previous song\n" +
				"2. - To Play the current song\n" +
				"3. - To Skip forward to next song\n" +
				"4. - To list the songs in the Playlist\n" +
				"5. - To remove song from a playlist\n" +
				"9. - To print help menu");
	}
	
	private static void PrintMenuHelp()
	{
		System.out.println("Press:\n" +
				"0. - To quit\n" +
				"1. - To list the Albums\n" +
				"2. - To Select Album\n" +
				"3. - To list the song in the Album\n" +
				"4. - To Add song to the Playlist\n" +
				"5. - To play songs in the Playlist\n" +
				"9. - To print help menu");
	}
	
	private static void PrintAlbums()
	{
		for (Album album : albums)
		{
			System.out.println(album.getAlbumName());
		}
	}
	
	private static void SelectAlbum()
	{
		System.out.println("Enter Album name\n");
		
		String input = scanner.nextLine();
		for (Album album : albums)
		{
			if (album.getAlbumName().equals(input))
			{
				currentAlbum = album;
				System.out.println("Album " + album.getAlbumName() + " selected");
				return;
			}
		}
		System.out.println("Incorrect Album name");
	}
	
	private static void PrintAlbumSongs()
	{
		System.out.println("Songs in the album " + currentAlbum.getAlbumName());
		for (Song nextSong : currentAlbum.getSongs())
		{
			System.out.println(nextSong);
		}
	}
	
	private static void AddSongToPlaylist()
	{
		System.out.println("Enter a song name: ");
		String songName = scanner.nextLine();
		
		for (Song nextSong : currentAlbum.getSongs())
		{
			if (nextSong.getTitle().equals(songName))
			{
				playlist.add(nextSong);
				return;
			}
		}
	}
	
	private static void PrintPlaylist()
	{
		if (playlist.isEmpty())
		{
			System.out.println("Playlist is empty");
			return;
		}
		
		System.out.println("Songs in the Playlist");
		for (Song nextSong : playlist)
		{
			System.out.println(nextSong);
		}
	}
	
	private static void FillAlbums()
	{
		currentAlbum = new Album("Classic");
		albums.add(currentAlbum);
		currentAlbum.addSong("Silent night", 6.28d);
		currentAlbum.addSong("Cosmos stacker", 3.49d);
		currentAlbum.addSong("Holly trinity", 3.32d);
		
		currentAlbum = new Album("Rock");
		albums.add(currentAlbum);
		currentAlbum.addSong("Midnight club", 3.48d);
		currentAlbum.addSong("Paper cut", 4.02d);
		currentAlbum.addSong("Here I am", 3.30d);
	}
	
	private static void FillPlaylist()
	{
		playlist.add(albums.get(0).getSong(0));
		playlist.add(albums.get(1).getSong(1));
		playlist.add(albums.get(0).getSong(1));
		playlist.add(albums.get(1).getSong(2));
	}
}