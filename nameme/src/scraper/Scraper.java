package scraper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import utils.Album;
import utils.Artist;
import utils.Song;

public class Scraper {
	
	private final String URL_HEAD = "http://www.azlyrics.com";
	private final int[] list = {0, 1, 2, 3, 4, 5, 6, 7, 8};
	
	public Document connectHtml(String link) throws IOException {
		Document doc = Jsoup.connect(link).get();
		return doc;
	}
	
	public List<Artist> findArtistResults(String url) throws IOException {
		List<Artist> artistList = new ArrayList<Artist>();
		Document doc = connectHtml(url);
		Element table = doc.select("table.table").first();
		if (null == table) {
			System.out.println("No results - try again");
		}
		else {
			Elements resRows = table.select("td");
			int i = 0;
			
			for(Element res : resRows) {
				Artist artist = new Artist();
				artist.setName(formatName(res.text()));
				artist.setUrl(res.select("a").attr("href"));
				artistList.add(artist);
			}
		}
		return artistList;
	}
	
	public List<Album> findAlbumResults(String url) throws IOException {
		Document doc = connectHtml(url);
		List<Album> results = new ArrayList<Album>();
		
		Elements list = doc.select("div#listAlbum > *");
		Album newAlbum = null;
		
		for (Element e : list) {
			if (e.className().equals("album")) {
				newAlbum = new Album();
				List<Song> newSongList = new ArrayList<Song>();
				newAlbum.setAlbumSongs(newSongList);
				if (e.childNodeSize() > 1) {
					String albumName = e.select("b").text();
					newAlbum.setAlbumName(albumName.substring(1, albumName.length()-1));
					String typeYear = e.text().replace(albumName, "");
					String[] typeYearArr = typeYear.split("[:]", 2);
					newAlbum.setAlbumType(typeYearArr[0]);
					newAlbum.setAlbumYear(typeYearArr[1].replaceAll("[()]", "").trim());
				}
				else {
					newAlbum.setAlbumName(e.text());
				}
				results.add(newAlbum);
			}
			else if (e.nodeName() == "a" && e.hasText()) {
				Song newSong = new Song();
				newSong.setSongName(e.text());
				newSong.setSongUrl(e.attr("href").replace("..", URL_HEAD));
				newAlbum.getAlbumSongs().add(newSong);
			}
		}
		
		return results;
	}
	
	public List<String> getRandomLyric(Song correctSong) throws IOException {
		List<String> randomLyric = new ArrayList<String>();
		Document doc = connectHtml(correctSong.getSongUrl());
		Elements thing = doc.select("div:not([class]):not([id])");
		doc.select("i").remove();
		String s = thing.html().replaceAll("<br> ", "").replace("\n\n", "");
	    String[] songLyrics = s.split("\n");

	    Random rand = new Random();
	    String lyric;
	    String nextLyric;
	    int randNum;
	    do {
//	    	System.out.println("Songlyrics length = " + songLyrics.length);
			randNum = rand.nextInt(songLyrics.length - 1);
			lyric = songLyrics[randNum];
			nextLyric = songLyrics[randNum +1];
//			if (lyric.contains(correctSong.getSongName().toLowerCase()) || nextLyric.contains(correctSong.getSongName().toLowerCase())) {
//				System.out.println("got name");
//			}
			
			// check if next lyric exists, if it's some html, if it contains the song name (in lower case)
	    } while (lyric == null || nextLyric == null 
	    		|| lyric.contains("<") || nextLyric.contains("<")
	    		|| lyric.toLowerCase().contains(correctSong.getSongName().toLowerCase()) || nextLyric.toLowerCase().contains(correctSong.getSongName().toLowerCase()));
	    randomLyric.add(lyric);
	    randomLyric.add(nextLyric);
		return randomLyric;
	}
	
	private String formatName(String word) {
		return word.replaceAll("[^a-zA-Z ]", "").trim();
	}	
	
}

