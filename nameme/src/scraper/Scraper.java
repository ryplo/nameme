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
//			System.out.println(res.text() + "\t" + res.select("a").attr("href"));
			artistList.add(artist);
		}
		return artistList;
	}
	
	public List<Album> findAlbumResults(String url) throws IOException, URISyntaxException {
		Document doc = connectHtml(url);
		List<Album> results = new ArrayList<Album>();
		Element list = doc.select("div#listAlbum").first();
		Elements elems = list.select("div, a");
		Iterator<Element> elemsIt = elems.iterator();
		
		while(elemsIt.hasNext()) {
			Element elem = elemsIt.next();
			if(elem.tagName() == "div") {
				Album album = new Album();
				album.setAlbumName(elem.select("b").text());
				System.out.println(album.getAlbumName());
			}
			
		}
			
			
//			
//			
//		Elements divs = doc.getElementsByClass("album");
//		Iterator<Element> divsIt = divs.iterator();
//		int i = 0;
//		
//		while(divsIt.hasNext()) {
//			Element div = divsIt.next();
//			Album album = new Album();
//			album.setAlbumName(formatName(div.select("b").text()));
//			Element divAlbum = doc.select("div.listAlbum").first();
//			Elements songs = divAlbum.select("div.album + a");
//			Iterator<Element> songsIt = songs.iterator();
//			
//				while(songsIt.hasNext()) {
//					System.out.println(songsIt.next().text());
//				}
//			results.add(album);
//			i++;
//		}
		
		return results;
	}
	
	private String formatName(String word) {
		return word.replaceAll("[^a-zA-Z ]", "").trim();
	}
	
}

