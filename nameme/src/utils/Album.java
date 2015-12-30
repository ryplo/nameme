package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jsoup.nodes.Document;

import scraper.Scraper;

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
		for (int i = 0; i < 3; i++) {
			randNum = rand.nextInt(albumSongs.size()) + 1;
			System.out.println("random number: " + randNum);
			randomSongs.add(albumSongs.get(randNum));
		}
		return randomSongs;
	}

//	public List<String> formatAlbums(List<String> results) {
//		int i = 0; 
//		for (String result : results) {
//			i++;
//			result = result.replaceAll("[\"]", "");
//			System.out.println(i + ". " + result);
//		}
//		return results;
//	}
	
	
}
