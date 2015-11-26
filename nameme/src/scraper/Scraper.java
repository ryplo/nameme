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

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import album.Album;
import artist.Artist;
import song.Song;

public class Scraper {
	
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
		
		Elements list = doc.select("div#listAlbum");
		Elements stuffs = list.select("b, a");
		Iterator<Element> stuffIt = stuffs.iterator();

		while(stuffIt.hasNext()) {
			Element thing = stuffIt.next();
			if (thing.hasText()) {
				Album album = new Album();
				album.setAlbumName(thing.text());
				System.out.println(album.getAlbumName());
				List<Song> songList = new ArrayList<Song>();
				boolean hasSong = false;
				Element otherThing = stuffIt.next();
				while(otherThing.tagName() == "a" && otherThing.hasText()) {
					Song song = new Song();
					song.setSongName(otherThing.text());
					System.out.println(song.getSongName());
					songList.add(song);
					hasSong = true;
					otherThing = stuffIt.next();
				}
				if(hasSong) {
					album.setAlbumSongs(songList);
				}
			}
		}
		return results;
	}
	
	private String formatName(String word) {
		return word.replaceAll("[^a-zA-Z ]", "").trim();
	}
	
}

