package scraper;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import album.Album;
import artist.Artist;
import song.Song;

public class Scraper {
	
	private final String SKETCHY_URL = "http://www.azlyrics.com/";
	private final String HTML = ".html";
	private final int[] list = {0, 1, 2, 3, 4, 5, 6, 7, 8};
	
	public Document connectHtml(String link) throws IOException, URISyntaxException {
		Document doc = Jsoup.connect(link).get();
		String title = doc.title();
		System.out.println(title);
		return doc;
	}
	
	public List<Artist> findArtistResults(String url) throws IOException, URISyntaxException {
		List<Artist> artistList = new ArrayList<Artist>();
		Document doc = connectHtml(url);
		Element table = doc.select("table.table").first();
		Elements resRows = table.select("td");
		int i = 0;
		
		for(Element res : resRows) {
			Artist artist = new Artist();
			artist.setName(formatName(res.text()));
			artist.setUrl(res.select("a").attr("href"));
			artistList.add(artist);
		}
		return artistList;
	}
	
	public List<Album> findAlbumResults(String url) throws IOException, URISyntaxException {
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
				newAlbum.getAlbumSongs().add(new Song(e.text()));
			}
		}
		
		for (Album album : results) {
			System.out.println("ALBUM:" + album.getAlbumName());
			System.out.println("TYPE:" + album.getAlbumType());
			System.out.println("YEAR:" + album.getAlbumYear());
			for (Song song : album.getAlbumSongs()) {
				System.out.println("SONG:" + song.getSongName());
			}
		}
		
		return results;
	}
	
	private String formatName(String word) {
		return word.replaceAll("[^a-zA-Z ]", "").trim();
	}	
	
}

