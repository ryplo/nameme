package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Album {

	private String albumName;
	private List<Song> albumSongs;
	private String albumType;
	private String albumYear;
	
	public String getAlbumType() {
		return albumType;
	}
	public void setAlbumType(String albumType) {
		this.albumType = albumType;
	}
	public String getAlbumYear() {
		return albumYear;
	}
	public void setAlbumYear(String albumYear) {
		this.albumYear = albumYear;
	}
	public String getAlbumName() {
		return albumName;
	}
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	public List<Song> getAlbumSongs() {
		return albumSongs;
	}
	public void setAlbumSongs(List<Song> albumSongs) {
		this.albumSongs = albumSongs;
	} 
	
	public List<Song> getRandomSongList() {
		Random rand = new Random();
		int randNum;
		List<Song> randomSongs = new ArrayList<Song>();
		List<Integer> randomNumbers = new ArrayList<Integer>();
		int i = 0;
		do {
			randNum = rand.nextInt(albumSongs.size()) + 1;
			System.out.println(" randomNumber: " + randNum );
			if (!randomNumbers.contains(randNum)) {
				randomNumbers.add(randNum);
				randomSongs.add(albumSongs.get(randNum - 1));
				
				i++;
			}
		} while (i < 3 );

		return randomSongs;
	}
	
}
