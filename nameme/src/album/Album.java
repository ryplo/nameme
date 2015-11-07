package album;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;

import scraper.Scraper;

public class Album {
	
	private static final String URL = "http://www.azlyrics.com/";
	
	public List<String> getAlbum(String artist) {
		List<String> albums = findAlbums(artist);
				return albums;
	//	String albumNumChoice = userAlbumChoice(albums);
		
	}
	
	public List<String> findAlbums(String artist) {
		Scraper scraper = new Scraper();
		List<String> results = new ArrayList<String>();
		try {
			Document doc = scraper.connectHtml((URL + artist.charAt(0) + "/" + artist +".html").toLowerCase());
			results = scraper.findAlbumResults(doc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return results;
	}
	
}
