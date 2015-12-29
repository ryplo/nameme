package album;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;

import scraper.Scraper;
import song.Song;

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
